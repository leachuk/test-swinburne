function closeNavigation(element){
  $(element.target).parent().removeClass('show');
}

function preventNavClosing(){
  let isClosed;
  const $dropdown = $('.dropdown');

  //Prevent navigation from closing is click outside the dropdowns
  $dropdown.on({
    "shown.bs.dropdown": () => { isClosed = false; },
    "click":             () => { isClosed = true; },
    "hide.bs.dropdown":  () => { return isClosed; }
  });

  setCloseInnerNav($dropdown);
}


function setCloseInnerNav($dropdown){
  // Refactored from => https://github.com/TheCoder4eu/BootsFaces-OSP/issues/849
  $dropdown.on('click', function(e){
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

export default () => {
  let $actions = $('.brand-header__actions');
  let $nav_container = $('.brand-header__container');


  if($actions.length && $nav_container.length && window.innerWidth < 1024) {
    const clone = $actions.clone();
    $actions.remove();
    $nav_container.append(clone);

    $('.brand-header__nav .dropdown-menu').each( (index, element) => {
      let $menu = $(element);
      let classes : any | undefined = $menu.prev().attr('class');
      let level = classes.split(' ').pop().split('-').pop();
      let title : string | undefined = $menu.attr('aria-labelledby');

      $menu.prepend(getButton(title, level));

      preventNavClosing();
    });
  }

}
