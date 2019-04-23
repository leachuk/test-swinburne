import { hasParent, matches } from '@utility/dom'

export default (components: ComponentConfig) => {
  console.info('[Author Icons] Spinning up mutation observer for AEM author mode!')

  const mutationObserver = new MutationObserver((mutations: MutationRecord[]) => {
    console.info('\n[Author Icons] Change detected! %d mutations in play', mutations.length)

    mutations.forEach(({ target }: MutationRecord) => {
      if (!matches(target as Element, '.aem-AuthorLayer-Edit')) {
        return
      }

      for (const component of Object.keys(components)) {
        const config   = components[component]
        const elements = document.querySelectorAll(config.selectors.join(','))

        if (elements.length) {
          console.info('[Author Icons] Found %d elements for:', elements.length, config.selectors)

          for (const element of elements) {
            if (!element.querySelector('.icon')) {
              element.appendChild(config.icon.cloneNode())
            }
          }
        }
      }
    })

    console.info('\n[Author Icons] Finished mutation run!')
  })

  mutationObserver.observe(document.documentElement, {
    childList : true,
    subtree   : true,
  })
}
