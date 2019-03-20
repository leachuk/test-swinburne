import _get from 'lodash/get'
import _isNil from 'lodash/isNil'
import _omitBy from 'lodash/omitBy'
import _throttle from 'lodash/throttle'

import sassVars from '../../scss/_common.scss'

import { isAuthorEditMode } from '@global/utilities/aem'
import { getWindowWidth } from '@global/utilities/dom'

declare interface BreakpointOptions {
  [key: number]: OwlCarousel.Options,
}

declare interface CarouselOptions {
  breakpoint?: BreakpointOptions,
  refresh?: boolean,
}

// Internal
const breakpoints = {
  desktop      : parseInt(sassVars['breakpoint-lg'], 10),
  desktopLarge : parseInt(sassVars['breakpoint-xl'], 10),
  extraSmall   : parseInt(sassVars['breakpoint-xs'], 10),
  tablet       : parseInt(sassVars['breakpoint-md'], 10),
}

const margins = {
  default : parseInt(sassVars['list-item-margin'], 10),
  desktop : parseInt(sassVars['list-item-lg-margin'], 10),
  tablet  : parseInt(sassVars['list-item-md-margin'], 10),
}

let carousels: NodeListOf<Element>
let lastWindowWidth: number = 0

/**
 * Retrieves the list configuration for the current `list`.
 *
 * @param {HTMLElement} list List element
 * @return {CarouselOptions}
 */
function getListConfiguration(list: HTMLElement): CarouselOptions {
  return {}
}

/**
 * Builds a basic list of elements for the previous/next nav buttons.
 *
 * @return {string[]}
 */
function getNavTextElements(): string[] {
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

  const totalItems   = $list.find('li').length
  const splitEnabled = parent.dataset.listSplitEnabled === 'true'

  // First check before destorying the carousel, do we actually need to remove the current instance?
  if (needsRefresh && options.refresh === true && $list.hasClass('owl-loaded')) {
    $list.owlCarousel('destroy')
  } else if (needsRefresh && options.refresh !== true) {
    return
  }

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
      }

      $list = $(mobileList as HTMLElement)
    }
  } else {
    // Create a new host element for the carousel
    const $carousel = $('<div />', { class: 'owl-carousel' }).insertBefore($list)

    if (totalItems > 0) {
      $list.children().each((_, item) => {
        $(item)
          .children()
          .clone(false)
          .appendTo($carousel)
          .wrap('<div class="item"></div>')
      })
    }

    $list = $carousel
  }

  // Janky fix to control pre-rendering issues with OwlCarousel
  setTimeout(() => {
    const carouselConfig = _omitBy({
      autoWidth    : _get(options, 'autoWidth', true),
      center       : _get(options, 'center', false),
      dots         : _get(options, 'dots', false),
      items        : _get(options, 'items', 1),
      loop         : _get(options, 'loop', false),
      margin       : _get(options, 'margin', margins.default),
      mouseDrag    : _get(options, 'mouseDrag', false),
      nav          : _get(options, 'nav', true),
      navText      : _get(options, 'navText', getNavTextElements()),
      slideBy      : _get(options, 'slideBy', 1),
      stageElement : _get(options, 'stageElement', null),
      stagePadding : _get(options, 'stagePadding', 0),

      responsive: {
        // Mobile
        [breakpoints.extraSmall]: _omitBy({}, _isNil),

        // Large mobile (landscape) and tablets
        [breakpoints.tablet]: _omitBy({
          center : _get(options, `breakpoint.${breakpoints.tablet}.center`, false),
          items  : _get(options, `breakpoint.${breakpoints.tablet}.items`, 3),
          margin : _get(options, 'breakpoint.${breakpoints.tablet}.margin', margins.tablet),
        }, _isNil),

        // Tablets (landscape) and small desktop browsers
        [breakpoints.desktop]: _omitBy({
          center : _get(options, `breakpoint.${breakpoints.desktop}.center`, false),
          items  : _get(options, `breakpoint.${breakpoints.desktop}.items`, 3),
          margin : _get(options, 'breakpoint.${breakpoints.tablet}.margin', margins.desktop),
        }, _isNil),
      },
    }, _isNil)

    // Set the parent list as 'ready'
    parent.classList.add('owl-ready')

    // Create the carousel instance
    $list.owlCarousel(carouselConfig)
  }, 200)
}

/**
 * Attempts to detect the give `type` using the `list` element given.
 *
 * @param {HTMLElement} list Parent element containing the list
 * @param {string} type Type of list to detect
 * @return {boolean}
 */
function detectListType(list: HTMLElement, type: string): boolean {
  return list.classList.contains(type)
}

/**
 * Detects and creates a carousel instance for the target elements.
 *
 * @param {boolean} [needsRefresh=false] Force refresh the carousel?
 */
function loopAndGenerateCarousels(needsRefresh: boolean = false) {
  carousels.forEach((carousel) => {
    const list: HTMLElement = carousel as HTMLElement

    let config: CarouselOptions = {}
    let target: HTMLElement | null = null

    switch (true) {
      case detectListType(list, 'newslist'):
      case detectListType(list, 'pagelist'):
        config = getListConfiguration(list)
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
