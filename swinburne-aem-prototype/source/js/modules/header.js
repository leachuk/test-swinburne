

export default () => {
  $(document).ready( () => {
    let $img_paralax = $('.logo-wrapper > div[component]:last-of-type  img');
    let $window = $(window);
    let window_width = $window.width();

    $window.scroll( () => {
      if(window_width > 991) {
        let scrollTop = 0 > $window.scrollTop() ? 0 : -1 * $window.scrollTop();
        $img_paralax.css('margin-top', scrollTop);
      }
    });
  });
}
