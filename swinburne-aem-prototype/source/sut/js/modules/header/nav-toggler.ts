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
    'click':             () => { isClosed = true },
    'hide.bs.dropdown':  () => isClosed,
    'shown.bs.dropdown': () => { isClosed = false },
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

function detachDropdownEvents() {
  $dropdown.off('shown.bs.dropdown click hide.bs.dropdown')
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
  $('.brand-header__nav .dropdown-menu').each( (_, element) => {
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

export default () => {
  $body         = $(document.body)
  $collapsible  = $('#header-nav-container')
  $dropdown     = $('.dropdown')
  $navLink      = $('a.parent')
  $toggleButton = $('.navbar-toggler')
  $window       = $(window)

  cloneActions()
  setBackButtons()
  setNavToggler()
  toggleNavigation()
  resetNavigation()

  $window.on('resize', _throttle(() => {
    if (getWindowWidth() >= breakpoints.desktop) {
      detachDropdownEvents()
    } else {
      attachDropdownEvents()
    }
  }, 200)).trigger('resize')

  // Listen for when the toggle button is clicked
  $toggleButton.off('click.navbar.toggler').on('click.navbar.toggler', () => {
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
