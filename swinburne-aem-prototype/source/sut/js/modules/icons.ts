import { isAuthorEditMode } from '@utility/aem'

// Internal
const longArrowRight = document.createElement('i')
longArrowRight.setAttribute('class', 'icon fal fa-long-arrow-right')

const components: ComponentConfig = {
  link: {
    icon      : longArrowRight,
    selectors : ['.link.btn', '.btn.btn-link', 'header .nav-link'],
  },
}

export default async () => {
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
    const {
      default: authorIcons,
    } = await import(/* webpackChunkName: "icons-author" */ '@module/icons-author')

    authorIcons(components)
  }
}
