import Carousels from '@global/modules/carousel'
import Header from '@global/modules/header'
import Subscribers from '@global/modules/subscribers'

import { isAuthorEditMode } from '@global/utilities/aem'

// Begin the app...
$(async () => {

  // Remove the 300ms delay using FastClick
  FastClick.attach(document.body)

  // Carousel functionality for anything!
  Carousels()

  // Bind the pub/sub event subscribers
  Subscribers()

  // 'object-fit' polyfill for unsupported browsers
  if ('objectFit' in document.documentElement.style === false) {
    const {
      default: objectFitImages,
    } = await import(/* webpackChunkName: "object-fit-images" */ 'object-fit-images')

    objectFitImages()
  }

  // Header controls
  Header()

  // Apply some fixes when we aren't in the AEM author 'edit' mode
  if (isAuthorEditMode()) {
    // Open all the 'collapse' elements on the page when in author
    $('.collapse[data-parent]').collapse('dispose')
  } else {
    // Append Font Awesome icons to any/all elements that need them
    const { default: Icons } = await import(/* webpackChunkName: "icons" */ '@global/modules/icons')
    Icons()
  }

  // Load the Font Awesome icons last as they are the heaviest payload
  await import(/* webpackChunkName: "fontawesome-pro-brands" */ '@fortawesome/fontawesome-pro/js/brands')
  await import(/* webpackChunkName: "fontawesome-pro-light" */ '@fortawesome/fontawesome-pro/js/light')
  await import(/* webpackChunkName: "fontawesome-pro" */ '@fortawesome/fontawesome-pro/js/fontawesome')

})

// HMR (Hot Module Replacement)
if (module.hot) {
  module.hot.accept()
}
