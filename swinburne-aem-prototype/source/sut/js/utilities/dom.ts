/**
 * Retrieves the current width of the window.
 *
 * @return {Number}
 */
export function getWindowWidth(): number {
  return $(window).width() || window.innerWidth || 0
}

/**
 * Sanitize and encode all HTML in a user-submitted string.
 *
 * @copyright Chris Ferdinandi
 * @license MIT
 * @external https://gomakethings.com
 * @param {string} input The user-submitted string
 * @return {string} The sanitized string
 */
export function sanitizeHTML(input: string) {
  const temp = document.createElement('div')
  temp.textContent = input

  return temp.innerHTML
}
