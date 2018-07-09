import throttle from 'lodash/throttle'

// Internal use only.
let hasScrolled
let header
let headerDummy
let height
let previousOffset

/**
 * Determines if the header needs to become sticky or not.
 */
function scrollCallback() {
  const offset = window.scrollY || document.documentElement.scrollTop

  // Sticky mode
  if (
    (
      (!hasScrolled && offset >= previousOffset) ||
      (hasScrolled && offset > previousOffset)
    ) &&
    offset > height &&
    !header.classList.contains('brand-header--stuck')
  ) {
    header.classList.add('brand-header--stuck')
  }

  // Non-sticky mode
  if (
    offset < previousOffset && offset < height * 2 &&
    header.classList.contains('brand-header--stuck') &&
    !document.body.classList.contains('no-scroll')
  ) {
    header.classList.remove('brand-header--stuck')
  }

  hasScrolled = true
  previousOffset = offset
}

/**
 * Sets the height of the dummy header element so that the page doesn't jump when the
 * header becomes fixed.
 */
function setDummyElementHeight() {
  headerDummy.style.height = `${height}px`
}

/**
 * Gets the currently computed height for the header.
 *
 * @return {Number}
 */
function navigationHeight() {
  return parseFloat(window.getComputedStyle(header).height, 10)
}

export default () => {
  header = document.querySelector('.brand-header')

  // Do nothing if the header wasn't found
  if (!header) {
    return
  }

  height         = navigationHeight()
  previousOffset = window.screenY || document.documentElement.scrollTop || 0

  // Create a dummy element that will fill in the gap that the header leaves when it
  // becomes fixed to the top of the page.
  headerDummy = document.querySelector('.brand-header__dummy')

  if (!headerDummy) {
    headerDummy = document.createElement('div')
    headerDummy.setAttribute('class', 'brand-header__dummy')

    header.parentNode.insertBefore(headerDummy, header)
  }

  // Adjust the height of the dummy element so it matches the header
  setDummyElementHeight()

  // Listen for when the user scrolls the page
  $(window)
    .off('scroll.brand.header')
    .on('scroll.brand.header', throttle(scrollCallback, 100))

  // Listen for when the page is resized
  $(window).off('resize.brand.header').on('resize.brand.header', throttle(() => {
    height = navigationHeight()

    // Adjust the height of the dummy element so it matches the header
    setDummyElementHeight()
  }, 100))

  // Check if the header should be stuck straight away
  scrollCallback()
}
