/*module: navtoggler */

export default () => {
  $(document).ready( () => {
      let $navToggler = $('[data-modules="navtoggler"]');
      let $navBar = $('div[component].navbar-nav ul');
      let $body = $('body');

      $navToggler.click(function(){
        $(this).toggleClass('collapsed');
        $body.toggleClass('collapsed');
        $navBar.slideToggle();
      });
  });
}
