import { isAuthorEditMode } from '@utility/aem'

// Internal
const icons: { [key: string]: string } = {
  chevronRight   : 'chevron-right',
  longArrowRight : 'long-arrow-right',
}

const components: ComponentConfig = {
  breadcrumb: {
    icon      : buildIcon(icons.chevronRight),
    selectors : ['.breadcrumb-item:not(:last-child)'],
  },

  link: {
    icon      : buildIcon(icons.longArrowRight),
    selectors : ['.link.btn', '.btn.btn-link', 'header .nav-link'],
  },
}

/**
 * Generates an HTML element for the given Font Awesome `iconClass`.
 *
 * @param {string} iconClass CSS class needed for Font Awesome
 * @return {Element}
 */
function buildIcon(iconClass: string): Element {
  const icon = document.createElement('i')
  icon.setAttribute('class', `icon fal fa-${iconClass}`)

  return icon
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

  console.info('[Icons] All done!')

  // When in author mode, we need to watch for DOM changes so we can correctly inject the
  // icons needed dynamically.
  if (isAuthorEditMode()) {
    const {
      default: authorIcons,
    } = await import(/* webpackChunkName: "icons-author" */ '@module/icons-author')

    authorIcons(components)
  }
}
