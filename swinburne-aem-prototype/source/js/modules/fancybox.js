export default () => {
  let $fancyboxes = $('[class*="fancybox-"]');
  if($fancyboxes.length > 0) {
    import(/* webpackChunkName: "js/vendorlib/fancybox" */ '@fancyapps/fancybox')
      .then(() => {
        console.info('Fancybox has loaded.');
        $fancyboxes.attr('data-fancybox', '');

        $('[data-fancybox]').fancybox({
          afterShow: function( instance, current ) {
            let $element = $(instance.$lastFocus);
            let size_class = $element[0].className.split(' ').filter( item => item.split('fancybox-').length === 2 ).pop();
            $('.fancybox-container .fancybox-content').addClass(size_class);
          }
        });
      })
      .catch(console.error)
  }
}
