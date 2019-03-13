function closeNavigation(element) {
  $(element.target).parent().removeClass('show');
}

function preventNavClosing() {
  const $dropdown = $('.dropdown');
  setCloseOuterNav($dropdown);
  setCloseInnerNav($dropdown);
}

function setCloseOuterNav($dropdown) {
  let isClosed;
  //Prevent navigation from closing is click outside the drop downs
  $dropdown.on({
    "shown.bs.dropdown": () => { isClosed = false; },
    "click":             () => { isClosed = true; },
    "hide.bs.dropdown":  () => { return isClosed; }
  });
}

function setCloseInnerNav($dropdown) {
  $dropdown.on('click', (e) => {
    const JQuery : any = $;
    let events = JQuery._data(document, 'events') || {};
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

function setNavTogglerAA() {
    const $buttons = $('.brand-header button[data-toggle="collapse"]');
    const text = '<span class="sr-only"> navigation</span>';
    let $exploreBtn = $buttons.last();
    let $closeBtn = $buttons.first();

    $exploreBtn.append(text);
    $closeBtn.append(text);
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
  cloneActions();
  setBackButtons();
  setNavTogglerAA();
  if(window.innerWidth < 1024) {
    preventNavClosing();
  }
}
