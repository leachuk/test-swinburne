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

  // Open all the 'collapse' elements on the page when in author
  if (isAuthorEditMode()) {
    $('.collapse[data-parent]').collapse('dispose')
  }

  // Load the Font Awesome icons last as they are the heaviest payload
  await import(/* webpackChunkName: "fa-pro" */ '@fortawesome/fontawesome-pro/js/all')

})

// HMR (Hot Module Replacement)
if (module.hot) {
  module.hot.accept()
}
