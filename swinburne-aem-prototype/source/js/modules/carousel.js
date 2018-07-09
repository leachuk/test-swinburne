import get from 'lodash/get'
import isNil from 'lodash/isNil'
import omitBy from 'lodash/omitBy'

/**
 * Page list configuration.
 * @type {Object}
 */
const pageListConfiguration = {}

/**
 * Binds OwlCarousel carousel to given element.
 *
 * @param {Element} element An element to bind OwlCarousel to
 * @param {Object}  options Custom options for this carousel
 */
const bindSlickToElement = (element, options = {}) => {
  const $parent = $(element)

  if (module.hot && $parent.hasClass('owl-loaded')) {
    // Destory the current instance
    $parent.owlCarousel('destroy')
  } else {
    // Add the OwlCarousel classes
    $parent.addClass('owl-carousel owl-theme')

    // Unwrap the LI elements from around the content within them.
    $parent.children().each((_, item) => {
      $(item).children().unwrap()
    })
  }

  $parent.owlCarousel(omitBy({
    center       : get(options, 'center', true),
    itemElement  : get(options, 'itemElement', null),
    items        : get(options, 'items', 1),
    loop         : get(options, 'loop', false),
    margin       : get(options, 'margin', 30),
    mouseDrag    : get(options, 'mouseDrag', false),
    nav          : get(options, 'nav', true),
    slideBy      : get(options, 'slideBy', 1),
    stageElement : get(options, 'stageElement', null),
    stagePadding : get(options, 'stagePadding', 15),

    responsive: {
      0: omitBy({
        stagePadding: get(options, 'breakpoint.0.stagePadding', 50),
      }, isNil),

      576: omitBy({
        items        : get(options, 'breakpoint.576.items', 2),
        stagePadding : get(options, 'breakpoint.576.stagePadding', 50),
      }, isNil),

      768: omitBy({
        center  : get(options, 'breakpoint.768.center', false),
        items   : get(options, 'breakpoint.768.items', 4),
        slideBy : get(options, 'breakpoint.768.slideBy', 2),
      }, isNil),

      1024: omitBy({
        center  : get(options, 'breakpoint.1024.center', false),
        items   : get(options, 'breakpoint.1024.items', 4),
        slideBy : get(options, 'breakpoint.1024.slideBy', 2),
      }, isNil),
    },
  }, isNil))
}

export default () => {
  const carousels = [ ...document.querySelectorAll('[data-modules*="carousel"]') ]

  if (carousels.length) {
    import(/* webpackChunkName: "js/vendorlib/owl.carousel" */ 'owl.carousel')
      .then(() => {
        console.info('OwlCarousel has been loaded and bound to `$.fn.owlCarousel()`')

        // Initalise the carousels
        carousels.forEach(carousel => {
          switch (true) {
            case carousel.classList.contains('pagelist'):
              bindSlickToElement(carousel.querySelector('ul'), pageListConfiguration)
              break

            default:
              console.warn('Carousel definition not defined for:', carousel)
          }
        })
      })
      .catch(console.error)
  }
}
