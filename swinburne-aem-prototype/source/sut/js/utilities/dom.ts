/**
 * Retrieves the current width of the window.
 *
 * @return {Number}
 */
export function getWindowWidth() {
  return $(window).width() || window.innerWidth || 0
}
