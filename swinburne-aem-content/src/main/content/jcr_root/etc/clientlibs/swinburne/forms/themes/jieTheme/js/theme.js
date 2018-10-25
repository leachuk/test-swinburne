$(document).ready(function() {
    var $select = $(".describe-you select");
    $select.selectmenu();

    $('input[type=radio][name=guideContainer-rootPanel-guideradiobutton___jqName]').change(function() {
        if($select.selectmenu( "instance" )) {
            $select.selectmenu( "destroy" );
            $select.selectmenu();
        };
    });

});

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