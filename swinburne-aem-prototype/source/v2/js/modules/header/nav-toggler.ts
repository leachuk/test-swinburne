function closeNavigation(element){
  $(element.target).parent().removeClass('show');
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

      $menu.prepend(button);
    });
  }

}
