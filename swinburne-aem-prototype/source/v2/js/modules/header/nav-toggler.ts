export default () => {
  let $actions = $('.brand-header__actions');
  let $nav_container = $('.brand-header__container');
  const window_w : number | 0 = window.innerWidth;

  if($actions.length && $nav_container.length && window_w < 1024) {
    let clone = $actions.clone();
    $actions.remove();
    $nav_container.append(clone);
  }

}
