$(document).ready(function() {
    $(".describe-you select").selectmenu();
    var $formContainer = $("body");

	//Catch Ajax requests for hiding and and showing overlay
    $(this).ajaxStart(function (ev) {
        console.log(ev);
        $formContainer.addClass('loading');
    });
    
    $(this).ajaxComplete(function () {
        $formContainer.removeClass('loading');
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