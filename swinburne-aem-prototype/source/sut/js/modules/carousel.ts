import _get from 'lodash/get'
import _isNil from 'lodash/isNil'
import _omitBy from 'lodash/omitBy'
import _throttle from 'lodash/throttle'

declare interface CarouselOptions {
  [breakpoint: string]: {
    [key: number]: OwlCarousel.Options,
  },
}

// Internal
let carousels: NodeListOf<Element>

/**
 * Page list configuration.
 *
 * @param {HTMLElement} pagelist The page list element
 */
function pageListConfiguration(pagelist: HTMLElement): CarouselOptions {
  const isEqualWidths = pagelist.classList.contains('theme--lists-equal')
  const isThirdWidths = pagelist.classList.contains('theme--lists-large')

  return {
    breakpoint: {
      768: {
        items   : isEqualWidths ? 2 : (isThirdWidths ? 3 : 2),
        slideBy : isEqualWidths ? 1 : (isThirdWidths ? 3 : 2),
      },

      1024: {
        items   : isEqualWidths ? 2 : (isThirdWidths ? 3 : 4),
        slideBy : isEqualWidths ? 1 : (isThirdWidths ? 3 : 2),
      },
    },
  }
}

/**
 * Binds `OwlCarousel` to the given target element.
 *
 * @param {HTMLElement} element An element to bind OwlCarousel to
 * @param {HTMLElement} parent The parent element of the carousel
 * @param {CarouselOptions} options Custom options for this carousel
 * @param {boolean} needsRefresh Should the carousel be completely refreshed?
 */
function bindCarouselToElement(
  element: HTMLElement,
  parent: HTMLElement,
  options: CarouselOptions = {},
  needsRefresh: boolean = false,
) {
  let $list = $(element)

  const totalItems   = $list.find('li').length
  const itemsFor768  = _get(options, 'breakpoint.768.items', 2)
  const itemsFor1024 = _get(options, 'breakpoint.1024.items', 4)
  const splitEnabled = parent.dataset.listSplitEnabled === 'true'
  const windowWidth  = $(window).width() || 0

  // We only need the carousel in certain situations
  if (
    windowWidth >= 768 &&
    totalItems > 0 &&
    (
      (totalItems <= 2 && itemsFor768 === 2 && itemsFor1024 === 2) ||
      (totalItems <= 3 && itemsFor768 === 3 && itemsFor1024 === 3) ||
      (totalItems <= 4 && itemsFor768 === 4 && itemsFor1024 === 4)
    ) &&
    $list.hasClass('owl-loaded') &&
    !splitEnabled
  ) {
    $list.owlCarousel('destroy').removeClass('owl-carousel')

    // Re-wrap the children so the markup is valid once again
    $list.children().wrap('<li />')

    return
  }

  // Do we need to remove OwlCarousel from the current instance
  if ((module.hot || needsRefresh) && $list.hasClass('owl-loaded')) {
    $list.owlCarousel('destroy')
  }

  let needsUnwrap = true

  // When a split is active we need to clone the list items into a mobile only version to ensure
  // the visual aspects of the list remain intact.
  if (splitEnabled) {
    const listParent = element.parentNode as Element

    if (listParent) {
      let mobileList = listParent.querySelector('ul.mobile-carousel')

      if (!mobileList) {
        mobileList = document.createElement('ul')
        mobileList.classList.add('mobile-carousel')

        // Migrate the existing lists items into the mobile clone
        const existingLists = [...listParent.querySelectorAll('ul')]

        if (existingLists.length) {
          existingLists.forEach((list) => {
            const children = [...list.children]

            children.forEach((item) => mobileList && mobileList.appendChild(item.cloneNode(true)))
          })
        }

        listParent.insertAdjacentElement('afterbegin', mobileList)
      } else {
        needsUnwrap = false;
      }

      $list = $(mobileList as HTMLElement)
    }
  }

  // Restore the OwlCarousel classes that are needed to style it
  $list.addClass('owl-carousel owl-theme')

  if (needsUnwrap && totalItems > 0) {
    $list.children().each((_, item) => {
      $(item).children().unwrap('li')
    })
  }

  // Janky fix to control pre-rendering issues with OwlCarousel
  setTimeout(() => {
    $list.owlCarousel(_omitBy({
      center       : _get(options, 'center', true),
      itemElement  : _get(options, 'itemElement', null),
      items        : _get(options, 'items', 1),
      loop         : _get(options, 'loop', false),
      margin       : _get(options, 'margin', 30),
      mouseDrag    : _get(options, 'mouseDrag', false),
      nav          : _get(options, 'nav', true),
      slideBy      : _get(options, 'slideBy', 1),
      stageElement : _get(options, 'stageElement', null),
      stagePadding : _get(options, 'stagePadding', 25),

      responsive: {
        0: _omitBy({
          stagePadding: _get(options, 'breakpoint.0.stagePadding', 0),
        }, _isNil),

        576: _omitBy({
          items        : _get(options, 'breakpoint.576.items', 2),
          stagePadding : _get(options, 'breakpoint.576.stagePadding', 0),
        }, _isNil),

        768: _omitBy({
          center       : _get(options, 'breakpoint.768.center', false),
          items        : itemsFor768,
          slideBy      : _get(options, 'breakpoint.768.slideBy', 2),
          stagePadding : _get(options, 'breakpoint.768.stagePadding', 25),
        }, _isNil),

        1024: _omitBy({
          center       : _get(options, 'breakpoint.1024.center', false),
          items        : itemsFor1024,
          slideBy      : _get(options, 'breakpoint.1024.slideBy', 2),
          stagePadding : _get(options, 'breakpoint.1024.stagePadding', 25),
        }, _isNil),
      },
    }, _isNil))
  }, 200)
}

const loopAndGenerateCarousels = (needsRefresh = false) => {
  carousels.forEach((carousel) => {
    const list: HTMLElement = carousel as HTMLElement

    let config: CarouselOptions = {}
    let target: HTMLElement | null = null

    switch (true) {
      case list.classList.contains('pagelist'):
        config = pageListConfiguration(list)
        target = list.querySelector('ul')
        break

      default:
        console.warn('Carousel definition not defined for:', carousel)
    }

    if (target) {
      bindCarouselToElement(target, list, config, needsRefresh)
    }
  })
}

export default () => {
  carousels = document.querySelectorAll('[data-modules*="carousel"]')

  if (carousels.length) {
    loopAndGenerateCarousels()

    $(window).off('resize.carouselRefresh').on('resize.carouselRefresh', _throttle(() => {
      loopAndGenerateCarousels(true)
    }, 200))
  }
}
