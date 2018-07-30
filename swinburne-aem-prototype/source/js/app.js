// Inject the Sass into the page
import '../scss/styleguide.scss'
import '../scss/app.scss'

// Core modules...
import Carousels from './modules/carousel'
import HashLinks from './modules/hash-links'
import ParallaxImage from './modules/parallaximage'
import LoadMore from './modules/load-more'
import Renderers from './modules/renderers'
import Subscribers from './modules/subscribers'

import {
  TOPIC_HIDE_SUGGESTIONS,
} from './utilities/constants'

import { isAuthorEditMode } from './utilities/aem'

// Begin the app...
$(() => {

  // Remove the 300ms delay using FastClick
  /* global FastClick */
  FastClick.attach(document.body)

  // Listen for clicks on the body
  $(document.body).on('click', e => {
    const $suggestions = $('.suggestions')
    const $target      = $(e.target)

    if ($suggestions.length && !$target.is('.suggestions') && !$target.parents('.suggestions').length) {
      PubSub.publish(TOPIC_HIDE_SUGGESTIONS)
    }
  })

  // Load more functionality for page lists
  LoadMore()

  // Carousel functionality for anything, this dynamically loads slick carousel
  // to reduce the weight of the page.
  Carousels()

  // Renders any React components
  Renderers()

  // Bind the pub/sub event subscribers
  Subscribers()

  // Brand header
  ParallaxImage()

  // Take over any hash links on the page so they correctly jump to the content
  HashLinks()

  // 'object-fit' polyfill for unsupported browsers
  /* global objectFitImages */
  objectFitImages()

  // Open all the 'collapse' elements on the page when in author
  if (isAuthorEditMode()) {
    $('.collapse[data-parent]').collapse('dispose')
  }

})

// HMR (Hot Module Replacement)
if (module.hot) {
  module.hot.accept()
}
