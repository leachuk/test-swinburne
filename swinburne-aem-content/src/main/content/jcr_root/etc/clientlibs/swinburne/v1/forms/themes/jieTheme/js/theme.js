$(document).ready(function() {
    var $select = $(".describe-you");
    var $selects = $(".select-menu");



    //Best describe you logics.
    initDropDowns($select);
    var $selectDescribe = $select.find("select");
    $('input[type=radio][name=guideContainer-rootPanel-guideradiobutton___jqName]').change(function() {
        if($selectDescribe.selectmenu( "instance" )) {
            guideBridge.setProperty(["describesYou"],"value",[""]); // Reset value
            $selectDescribe.selectmenu( "destroy" );
            $selectDescribe.selectmenu();

            //Rebind blur event on selectdrop downs.
            bindBlurEvent($select.find('.ui-selectmenu-button'));
        };
    });

    //Init custom drop downs
    initDropDowns($selects);

    // Close dropdown on blur
    bindBlurEvent($('.ui-selectmenu-button'));

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

    $(document).keypress(function(e) {
        if(e.which === 13) {
            submitForm();
        }
    });


    guideBridge.on('submitStart', function (event, payload) {
        setNewValue();
    });

});

// CONSTANT
var newValues = "div[class*='to-value']";

function bindBlurEvent($selector){
    $selector.blur( function(){
        $(this).prev().selectmenu( "close" );
    });
};
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
function submitForm() {
    guideBridge.submit({
        error : function (guideResultObject) {
            alert(setErrorsMessage(guideResultObject));
            console.error(setErrorsMessage(guideResultObject));
            unsetNewValue();
        },
        success : function (guideResultObject) {
            unsetNewValue();
        }
    });
}
function setErrorsMessage(response) {
    var message = response.data.statusText + ";";

    response.data.responseJSON.errors.map( function(error) {
        message += " " + error.errorMessage + "; ";
    });

    return message;
}
function unConcatLabelToValue() {
    guideBridge.visit(function(item) {
        if(item.cssClassName === "label-to-value") {
            var value = item.value;
            var newValue = value.split(":").pop();
            guideBridge.setProperty([item.name],"value",[newValue]);
        }
    });
}
function concatLabelToValue() {
    guideBridge.visit(function(item) {
        if(item.cssClassName === "label-to-value") {
            var name = item.name;
            var title = item.title;
            var value = item.value;
            var newValue = title + ":" + value;
            guideBridge.setProperty([name],"value",[newValue]);
        }
    });
}
function proceedNewValue(datatype) {
    $elements = $(newValues);
    $elements.each(function( index ) {
        var $this = $(this);
        var newValue = $this.find("input[type='text']").attr(datatype);
        var name = $this.get(0).classList.item(2); // Get property name
        guideBridge.setProperty([name],"value",[newValue]);
    });
}
function setNewValue() {
    proceedNewValue('data-new-value');
}
function unsetNewValue() {
    proceedNewValue('data-value');
}
function showList(input) {
    var isOpen = $(input).autocomplete( "widget" ).is( ":visible" );
    if (!isOpen){
        $(input).autocomplete("search", "");

        // clear binding to improve performance.
        // see https://stackoverflow.com/a/43393889/1909499
        $(input).data("ui-autocomplete").menu.bindings = $();
    }
}