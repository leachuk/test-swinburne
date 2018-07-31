/*module: navtoggler */

export default () => {
  $(document).ready( () => {
      let $navToggler = $('[data-modules="navtoggler"]');
      let $navBar = $('div[component].navbar-nav ul');

      $navToggler.click(function(){
        $(this).toggleClass('collapsed');
        $navBar.slideToggle();
      });
  });
}
