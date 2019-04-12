import _throttle from 'lodash/throttle'

import { breakpoints } from '@global/utilities/config'
import { getWindowWidth } from '@global/utilities/dom'

// Internal
let $backButtons: JQuery
let $body: JQuery
let $collapsible: JQuery
let $dropdown: JQuery
let $navLink: JQuery
let $toggleButton: JQuery
let $window: JQuery<Window>
let $activeElements: JQuery

let scrollOffset

function closeSubNavigation(element) {
  if (element.target) {
    $(element.target)
      .parent()
      .removeClass('show')
      .prev()
      .attr('aria-expanded', 'false')
  }
}

function openNavAtLink() {
    let $level1Link : JQuery

    $level1Link = $(`a.active.l-1`)
    if($level1Link.length > 0) {
      $level1Link.trigger('click').trigger('blur');
    }
}

function toggleNavigation() {
  $navLink.on('click', () => {
    if (getWindowWidth() < breakpoints.desktop) {
      $toggleButton.trigger('click')
    }
  })
}

// Close Sub navigation when navigation is closing.
function resetNavigation() {
  $backButtons = $('button[data-nav]')

  $collapsible.on('hide.bs.collapse', () => {
    if (getWindowWidth() < breakpoints.desktop) {
      $backButtons.trigger('click')
    }
  })
}

function attachDropdownEvents() {
  let isClosed: boolean

  // Prevent navigation from closing is click outside the drop downs
  $dropdown.on({
    click() {
      isClosed = true
    },

    ['hide.bs.dropdown']() {
      return getWindowWidth() >= breakpoints.desktop ? true : isClosed
    },

    ['shown.bs.dropdown']() {
      isClosed = false
      $activeElements.removeClass('active')
    },
  })

  $dropdown.on('click', (e: JQuery.TriggeredEvent) => {
    const JQuery: any = $

    let events = JQuery._data(document, 'events') || {}
    events = events.click || []

    events.map((event) => {
      if (event.selector) {
        if ($(e.target).is(event.selector)) {
          event.handler.call(e.target, e)
        }

        $(e.target).parents(event.selector).each(function() {
          event.handler.call(this, e)
        })
      }
    })

    e.stopPropagation()
  })
}

function getButton(title, level) {
  const srText = ` to level ${level} ${title}`

  const button = document.createElement('button')
  button.classList.add('link')
  button.innerText = 'Back'
  button.setAttribute('data-nav', `${title}-${level}`)
  button.addEventListener('click', closeSubNavigation)

  const text = document.createElement('span')
  text.classList.add('sr-only')
  text.innerText = srText

  const icon = document.createElement('i')
  icon.classList.add('icon')
  icon.classList.add('fal')
  icon.classList.add('fa-chevron-left')

  button.appendChild(icon)
  button.appendChild(text)

  return button
}

function setNavToggler() {
  const $button = $('.brand-header button[data-toggle="collapse"]')

  let openContent = $button.html()
  openContent = `<div>${openContent}</div>`

  $button.html(openContent)

  let closeContent = '<div><i class="icon fal fa-times"></i>'
  closeContent += '<span class="link-text">Close</span></div>'

  $button.prepend(closeContent)
}

function setBackButtons() {
  $('.dropdown-menu, .dropdown-submenu', $('.brand-header__nav')).each((_, element) => {
    const $menu = $(element)
    const classes: any | undefined = $menu.prev().attr('class')
    const level = classes.split(' ').pop().split('-').pop()
    const title: string | undefined = $menu.attr('aria-labelledby')

    const backButton = getButton(title, level)
    $menu.prepend(backButton)
  })
}

function cloneActions() {
  const $actions      = $('.brand-header__actions')
  const $navContainer = $('.brand-header__container')
  const clone         = $actions.clone()

  $navContainer.append(clone)
}

function resetScrollOffset() {
  scrollOffset = parseInt($body.css('top'), 10)

  // Restore the page scrollbar
  $body.removeClass('no-scroll').css('top', 'auto')
  $window.scrollTop(-scrollOffset)
}

function dropdownSubMenuFix() {
  $('.dropdown-menu a.dropdown-toggle').on('click.navbar.dropdown.toggle', function(event: JQuery.Event) {
    event.stopImmediatePropagation()

    const $this = $(this)

    // If the submenu doesn't have a class of 'show', remove the 'show' class from the parent first
    if (!$this.next().hasClass('show')) {
      $this
        .parents('.dropdown-menu')
        .first()
        .find('.show')
        .removeClass('show')
        .prev()
        .attr('aria-expanded', 'false')
    }

    // Toggle the submenu on/off
    $this
      .attr('aria-expanded', $this.attr('aria-expanded') === 'false' ? 'true' : 'false')
      .next(".dropdown-menu")
      .toggleClass('show')

    // Required to show the focus styles on submenu items
    if (getWindowWidth() < breakpoints.desktop) {
      this.focus()
    }

    // Listen for the 'hidden' event on the parent dropdown and hide the submenu
    $(this).parents('li.nav-item.dropdown.show').on('hidden.bs.dropdown', () => {
      $('.dropdown-submenu .show')
        .removeClass('show')
        .prev()
        .attr('aria-expanded', 'false')
    })
  })
}

export default () => {
  $body           = $(document.body)
  $collapsible    = $('#header-nav-container')
  $dropdown       = $('.dropdown')
  $navLink        = $('a.nav-link').not('[href="#"]')
  $toggleButton   = $('.navbar-toggler')
  $window         = $(window)
  $activeElements = $('a.dropdown-toggle.active, .dropdown-menu.active')

  cloneActions()
  setBackButtons()
  setNavToggler()
  toggleNavigation()
  resetNavigation()
  openNavAtLink()


  $window.on('resize', _throttle(() => {
    attachDropdownEvents()
  }, 200)).trigger('resize')

  // Submenu fix for nested drop downs
  // @see https://stackoverflow.com/a/45755948
  dropdownSubMenuFix()

  // Listen for when the toggle button is clicked
  $toggleButton.on('click.navbar.toggler', () => {
    if ($body.hasClass('no-scroll')) {
      resetScrollOffset()
    } else {
      scrollOffset = $window.scrollTop()

      // Prevent the scroll on the body of the page and set the top offset to the current scroll
      // position so the user remains at the same offset.
      $body.addClass('no-scroll').css('top', -scrollOffset)
    }
  })
}
