import Carousels from '@global/modules/carousel'
import NavToggler from '@global/modules/header/nav-toggler'
import Subscribers from '@global/modules/subscribers'

import {
  TOPIC_HIDE_SUGGESTIONS,
} from '@global/utilities/constants'

import { isAuthorEditMode } from '@global/utilities/aem'
import ObjectFit from '@global/utilities/object-fit'

// Begin the app...
$(() => {

  // Remove the 300ms delay using FastClick
  FastClick.attach(document.body)

  // Listen for clicks on the body
  $(document.body).on('click', (e) => {
    const $suggestions = $('.suggestions')
    const $target      = $(e.target)

    if ($suggestions.length && !$target.is('.suggestions') && !$target.parents('.suggestions').length) {
      PubSub.publish(TOPIC_HIDE_SUGGESTIONS, null)
    }
  })

  // Carousel functionality for anything, this dynamically loads slick carousel to reduce
  // the weight of the page.
  Carousels()

  // Bind the pub/sub event subscribers
  Subscribers()

  // 'object-fit' polyfill for unsupported browsers
  ObjectFit()

  // Header navigation toggler
  NavToggler()

  // Open all the 'collapse' elements on the page when in author
  if (isAuthorEditMode()) {
    $('.collapse[data-parent]').collapse('dispose')
  }

})

// HMR (Hot Module Replacement)
if (module.hot) {
  module.hot.accept()
}