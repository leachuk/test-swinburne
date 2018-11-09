$(document).ready(function() {
    var $select = $(".describe-you");
    var $selects = $(".select-menu");



	//Best describe you logics.
    initDropDowns($select);
    var $selectDescribe = $select.find("select");
    $('input[type=radio][name=guideContainer-rootPanel-guideradiobutton___jqName]').change(function() {
        if($selectDescribe.selectmenu( "instance" )) {
           $selectDescribe.selectmenu( "destroy" );
           $selectDescribe.selectmenu();

            //Rebind blur event on selectdrop downs.
            bindBlurEvent($select.find('.ui-selectmenu-button'));
        };
    }); 

    //Init custom drop downs
    initDropDowns($selects);

    bindBlurEvent($('.ui-selectmenu-button'));

    // Close dropdown on blur
    $('.ui-selectmenu-button').blur( function(){
        $(this).prev().selectmenu( "close" );
    });

    //Tooltips hover handler when screen is not a touchscreen
    var $tooltips = $('.guideHelpQuestionMark');
    if (!("ontouchstart" in document.documentElement)) {
        $tooltips.mouseenter(function(){
            var $description = $(this).next();
            $description.show();
        });
        $tooltips.mouseleave(function(){
            var $description = $(this).next();
            $description.hide();
        });
    }

});
function bindBlurEvent($selector){
    $selector.blur( function(){
        $(this).prev().selectmenu( "close" );
    });
};
//function to validate auto complete fields.
function validateSearch($field, items) {
    var val = $field.val();
    var hasMatch = false;
    items.values.map(function (item) {
        if(item.value.toLowerCase().indexOf(val.toLowerCase()) !== -1) {
            hasMatch = true;
        }
    });
    val = !hasMatch ? "" : val;
    $field.val(val);
}
function isValuePresent(val, items) {
    var hasMatch = false;
    items.values.map(function (item) {
        if( item.value.toLowerCase() === val.toLowerCase() ) {
            hasMatch = true;
        }
    });
    return hasMatch;
}
function initDropDowns($elements) {
    $elements.each(function( index ) {
      	var $element = $(this).find('select');
        var name = $(this).get(0).classList.item(2); // Get property name
        $element.selectmenu();
        $element.on( "selectmenuchange", function( event, ui ) {
    		guideBridge.setProperty([name],"value",[ui.item.value]);
  		}); 
    });
}