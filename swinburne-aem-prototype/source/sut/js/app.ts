import Carousels from '@module/carousel'
import Header from '@module/header'
import Icons from '@module/icons'
import Subscribers from '@module/subscribers'

import { isAuthorEditMode } from '@utility/aem'

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
  }

  // Append Font Awesome icons to any/all elements that need them
  Icons()

  // Load the Font Awesome icons last as they are the heaviest payload
  await import(/* webpackChunkName: "fontawesome-pro-brands" */ '@fortawesome/fontawesome-pro/js/brands')
  await import(/* webpackChunkName: "fontawesome-pro-light" */ '@fortawesome/fontawesome-pro/js/light')
  await import(/* webpackChunkName: "fontawesome-pro" */ '@fortawesome/fontawesome-pro/js/fontawesome')

})
