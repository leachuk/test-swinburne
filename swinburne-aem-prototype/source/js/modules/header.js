import Sticky from './header/sticky'

export default () => {
  // Bind a click handler to the navigation bar when viewing it on a mobile device, this
  // allows a user to hide it when they click on the blacked out area.
  $('.navbar-collapse').off('click.navbar.toggle').on('click.navbar.toggle', e => {
    const $target = $(e.target)

    // Did the user click on the empty space around the nav?
    if ($target.is('.navbar-collapse') && $(window).width() < 992) {
      $target.collapse('hide')

      // Restore the page scrollbar
      const scrollOffset = parseInt($('body').css('top'), 10)

      $('body').removeClass('no-scroll').css('top', 'auto')
      $(window).scrollTop(-scrollOffset)
    }
  })

  // Bind a click handler to the toggler
  $('.navbar-toggler').off('click.navbar.toggler').on('click.navbar.toggler', () => {
    const scrollOffset = $(window).scrollTop()

    // Prevent the scroll on the body of the page and set the top offset to the current scroll
    // position so the user remains at the same offset.
    $('body').addClass('no-scroll').css('top', -scrollOffset)
  })

  // Bind a click handler to the entire page that detects when the user clicks outside the search
  // box on mobile which closes it when open.
  $(document.body).off('click.header.globalSearch').on('click.header.globalSearch', e => {
    if (!$(e.target).parents('[data-modules*="global-search"]').length) {
      $(document.body).removeClass('global-search')
    }
  })

  // Sticky behaviour
  Sticky()
}
