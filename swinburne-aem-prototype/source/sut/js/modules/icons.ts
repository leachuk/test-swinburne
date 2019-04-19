import { isAuthorEditMode } from '@utility/aem'
import { hasParent, matches } from '@utility/dom'

declare interface ComponentConfig {
  [key: string]: {
    icon: Element,
    selectors: string[],
  }
}

// Internal
const bannedNodes = ['#comment', 'script', 'style']

const longArrowRight = document.createElement('i')
longArrowRight.setAttribute('class', 'icon fal fa-long-arrow-right')

const components: ComponentConfig = {
  link: {
    icon      : longArrowRight,
    selectors : ['.link.btn', '.btn.btn-link', 'header .nav-link'],
  },
}

export default () => {

  console.info('[Icons] Booting up...')

  // Apply icons to all components on the page by default
  for (const component of Object.keys(components)) {
    const config   = components[component]
    const elements = document.querySelectorAll(config.selectors.join(','))

    if (elements.length) {
      console.info('[Icons] Found %d elements for:', elements.length, config.selectors)

      for (const element of elements) {
        element.appendChild(config.icon.cloneNode())
      }
    }
  }

  // When in author mode, we need to watch for DOM changes so we can correctly inject the
  // icons needed dynamically.
  if (isAuthorEditMode()) {
    console.info('[Icons] Spinning up mutation observer for AEM author mode!')

    const mutationObserver = new MutationObserver((mutations: MutationRecord[]) => {
      console.info('\n[Icons] Change detected! %d mutations in play', mutations.length)

      mutations.forEach((mutation: MutationRecord) => {
        const addedNodes = mutation.addedNodes

        if (addedNodes.length) {
          for (const node of addedNodes) {
            if (
              bannedNodes.indexOf(node.nodeName.toLowerCase()) !== -1 ||
              (node as Element).querySelector('.icon')
            ) {
              continue
            }

            // Now, check through the pre-defined components
            for (const component of Object.keys(components)) {
              console.info('\n[Icons] Running through mutation %O against component %O', node, component)

              const config = components[component]

              for (const selector of config.selectors) {
                const selectorLevels = selector.split(' ')
                const selectorMatch  = selectorLevels.pop()

                console.info('[Icons] Checking selector %s with levels:', selectorMatch, selectorLevels)

                // If there are additional levels, use them as a way of detecting if the node exists in
                // the correct parent tree.
                if (selectorLevels.length) {
                  let count = 0

                  // Check the levels in reverse, this is because we want to go from the node up
                  for (const level of selectorLevels.reverse()) {
                    count = hasParent(node.parentNode, level) ? count + 1 : count
                  }

                  // If one or more of the parents didn't match, continue onto the next selector
                  if (count !== selectorLevels.length - 1) {
                    continue
                  }
                }

                // Finally, test to ensure the node matches
                if (selectorMatch && matches(node as Element, selectorMatch)) {
                  console.info('[Icons] Selector %s matched, appending icon:', selectorMatch, config.icon)
                  node.appendChild(config.icon.cloneNode())
                } else {
                  console.info('[Icons] Selector %s invalid, continuing on...', selectorMatch)
                }
              }
            }
          }
        }
      })
    })

    mutationObserver.observe(document.documentElement, {
      childList : true,
      subtree   : true,
    })
  }

}
