import _get from 'lodash/get'
import _isNil from 'lodash/isNil'
import _omitBy from 'lodash/omitBy'
import _throttle from 'lodash/throttle'

import sassVars from '../../scss/_common.scss'

import { isAuthorEditMode } from '@global/utilities/aem'
import { getWindowWidth } from '@global/utilities/dom'

declare interface CarouselOptions {
  [breakpoint: string]: {
    [key: number]: OwlCarousel.Options,
  },
}

// Internal
const breakpoints = {
  desktop      : parseInt(sassVars['breakpoint-lg'], 10),
  desktopLarge : parseInt(sassVars['breakpoint-xl'], 10),
  extraSmall   : parseInt(sassVars['breakpoint-xs'], 10),
  tablet       : parseInt(sassVars['breakpoint-md'], 10),
}

let carousels: NodeListOf<Element>
let lastWindowWidth: number = 0

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
      [breakpoints.tablet]: {
        items   : isEqualWidths ? 2 : (isThirdWidths ? 3 : 2),
        slideBy : isEqualWidths ? 1 : (isThirdWidths ? 3 : 2),
      },

      [breakpoints.desktop]: {
        items   : isEqualWidths ? 2 : (isThirdWidths ? 3 : 4),
        slideBy : isEqualWidths ? 1 : (isThirdWidths ? 3 : 2),
      },
    },
  }
}

function getNavTextElements() {
  return [
    '<i class="fal fa-long-arrow-left"></i>',
    '<i class="fal fa-long-arrow-right"></i>',
  ]
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

  const totalItems      = $list.find('li').length
  const itemsForTablet  = _get(options, `breakpoint.${breakpoints.tablet}.items`, 3)
  const itemsForDesktop = _get(options, `breakpoint.${breakpoints.desktop}.items`, 3)
  const splitEnabled    = parent.dataset.listSplitEnabled === 'true'
  const windowWidth     = getWindowWidth()

  // We only need the carousel in certain situations
  if (
    windowWidth >= breakpoints.tablet &&
    totalItems > 0 &&
    (
      (totalItems <= 2 && itemsForTablet === 2 && itemsForDesktop === 2) ||
      (totalItems <= 3 && itemsForTablet === 3 && itemsForDesktop === 3) ||
      (totalItems <= 4 && itemsForTablet === 4 && itemsForDesktop === 4)
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
      center       : _get(options, 'center', false),
      dots         : _get(options, 'dots', false),
      itemElement  : _get(options, 'itemElement', null),
      items        : _get(options, 'items', 1),
      loop         : _get(options, 'loop', false),
      margin       : _get(options, 'margin', 0),
      mouseDrag    : _get(options, 'mouseDrag', false),
      nav          : _get(options, 'nav', true),
      navText      : _get(options, 'navText', getNavTextElements()),
      slideBy      : _get(options, 'slideBy', 1),
      stageElement : _get(options, 'stageElement', null),
      stagePadding : _get(options, 'stagePadding', 0),

      responsive: {
        [breakpoints.extraSmall]: _omitBy({}, _isNil),

        [breakpoints.tablet]: _omitBy({
          items : itemsForTablet,
        }, _isNil),

        [breakpoints.desktop]: _omitBy({
          items: itemsForDesktop,
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
  carousels       = document.querySelectorAll('[data-modules*="carousel"]')
  lastWindowWidth = getWindowWidth()

  if (carousels.length && !isAuthorEditMode()) {
    loopAndGenerateCarousels()

    $(window).off('resize.carouselRefresh').on('resize.carouselRefresh', _throttle(() => {
      // If the width of the window matches the last known width, do nothing!
      if (getWindowWidth() === lastWindowWidth) {
        return
      }

      loopAndGenerateCarousels(true)

      lastWindowWidth = getWindowWidth()
    }, 200))
  }
}
