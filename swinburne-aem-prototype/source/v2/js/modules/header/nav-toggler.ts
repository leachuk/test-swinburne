import _throttle from 'lodash/throttle'

let $dropdown: JQuery
let $collapsible : JQuery
let $window: JQuery<Window>

function closeNavigation(element) {
  console.log(element.target);
  $(element.target).parent().removeClass('show');
}

function attachDropdownEvents() {
  let isClosed;

  // Prevent navigation from closing is click outside the drop downs
  $dropdown.on({
    'shown.bs.dropdown': () => { isClosed = false; },
    'click':             () => { isClosed = true; },
    'hide.bs.dropdown':  () => { return isClosed; },
  })

  $dropdown.on('click', (e) => {
    const JQuery: any = $;

    let events = JQuery._data(document, 'events') || {}

    events = events.click || [];

    events.map( (event) => {
      if(event.selector) {
        if($(e.target).is(event.selector)) {
          event.handler.call(e.target, e);
        }

        $(e.target).parents(event.selector).each(function(){
          event.handler.call(this, e);
        });
      }
    });

    e.stopPropagation();
  });
}

function detechDropdownEvents() {
  $dropdown.off('shown.bs.dropdown click hide.bs.dropdown')
}

function getButton(title, level) {
  let srText = ` to level ${level} ${title}`;

  const button = document.createElement('button');
  button.classList.add('link');
  button.innerText = 'Back';
  button.setAttribute('data-nav', `${title}-${level}`);
  button.addEventListener('click', closeNavigation);

  const text = document.createElement('span');
  text.classList.add('sr-only');
  text.innerText = srText;

  const icon = document.createElement('i');
  icon.classList.add('icon');
  icon.classList.add('fal');
  icon.classList.add('fa-chevron-left');

  button.appendChild(icon);
  button.appendChild(text);

  return button;
}

function setNavToggler() {
  const $button = $('.brand-header button[data-toggle="collapse"]');
  let openContent = $button.html();
  openContent = `<div>${openContent}</div>`;
  $button.html(openContent);

  let closeContent = '<div><i class="icon fal fa-times"></i>';
  closeContent += '<span class="link-text">Close</span></div>';

  $button.prepend(closeContent);
}

function setBackButtons() {
  $('.brand-header__nav .dropdown-menu').each( (index, element) => {
    let $menu = $(element);
    let classes : any | undefined = $menu.prev().attr('class');
    let level = classes.split(' ').pop().split('-').pop();
    let title : string | undefined = $menu.attr('aria-labelledby');

    const backButton = getButton(title, level);
    $menu.prepend(backButton);
  });
}

function cloneActions() {
  const $actions = $('.brand-header__actions');
  const $nav_container = $('.brand-header__container');
  const clone = $actions.clone();
  $nav_container.append(clone);
}

export default () => {
  $dropdown = $('.dropdown')
  $window = $(window)
  $collapsible = $('#header-nav-container')

  cloneActions();
  setBackButtons();
  setNavToggler();

  $window.on('resize', _throttle(() => {
    const windowWidth = $window.outerWidth() || window.innerWidth

    if (windowWidth >= 1024) {
      detechDropdownEvents()
    } else {
      attachDropdownEvents()
    }
  }, 200)).trigger('resize')
}
