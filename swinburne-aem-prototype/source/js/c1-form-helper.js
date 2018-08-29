$(document).ready(function(e) {

  // This all currently lives here because it requires data prefilled from PHP. When I make it available via AJAX or other means some day, it can be it's own file

  // START LOAD AJAX DATA
  function createSelectFromTags(tags) {
    var select_string = '<option value="">Select an option</option>';
    for (idx in tags) {
      select_string += '<option value="'+tags[idx].tag+'">'+tags[idx].label+'</option>';
    }
    return select_string;
  }

  function setBdTags() {
    $.getJSON("//www.swinburne.edu.au/cwis/php_pages/webapps/marketing/forms/public/tags/?type="+($('#mkt_residency_international').is(':checked') ? 'int' : '')+"bd",function(data) {
      $('#mkt_bestDescribes').html(createSelectFromTags(data));
      $('input[name="mkt_bestDescribes"]').val('');
    });
  }

  function setHqTags() {
    $.getJSON("//www.swinburne.edu.au/cwis/php_pages/webapps/marketing/forms/public/tags/?type="+($('#mkt_residency_international').is(':checked') ? 'int' : '')+"hq",function(data) {
      $('#mkt_highestQualification').html(createSelectFromTags(data));
      $('input[name="mkt_highestQualification"]').val('');
    });
  }

  $.getJSON("//www.swinburne.edu.au/cwis/php_pages/webapps/marketing/lists/api/?list=countries_c1&callback=?",function(data) {
    if(data.success) {
      var selectHtml = '<option value="">Select your country of residence</option>';
      for (option in data.response) {
        selectHtml += '<option value="'+data.response[option].val+'" data-deflect="'+(data.response[option].deflect || false)+'" data-malaysia-promo="'+(data.response[option].malaysiaPromo || false)+'">'+data.response[option].val+'</option>';
      }
      $('#mkt_countryOfResidence').html(selectHtml);
      $.getJSON("//www.swinburne.edu.au/cwis/php_pages/webapps/marketing/lists/json-files/countrycodes.json",function(data) {
        var countries_list = $.map(data, function(value, index) {return [value];});
        var countryList = '';

        var options = $('#mkt_countryOfResidence option');
        var countries = $.map(options ,function(option) {
          if (option.value != "" && option.value != "61")
            return option.value;
        });
        for (c in countries_list) {
          if (countries.indexOf(countries_list[c].name) > -1)
            countryList += '<option value="'+countries_list[c].code+'"'+(countries_list[c].code == 61 ? ' selected' : '')+'>+'+countries_list[c].code+' ('+countries_list[c].name+')</option>';
        }
        $('#mkt_countryCode').html(countryList);
        //  $('#mkt_countryCode + span.custom-combobox').css({ 'width' : '35%', 'min-width' : '50px', 'display' : 'inline-block'});

      });
    }
  });

  $.getJSON("//www.swinburne.edu.au/cwis/php_pages/webapps/marketing/coursesearch-v2/system/api/json/?q=study_areas",function(data) {
    if(data.success) {
      var selectHtml = '<option value="">Select a study area</option><option value="all">All areas</option>';
      for (option in data.response) {
        selectHtml += '<option data-id="'+data.response[option].Id+'" value="'+data.response[option].Name+'">'+data.response[option].Name+'</option>';
      }
      $('#mkt_areaOfInterest').html(selectHtml);
    } else {
      console.log('Something went wrong');
    }
  });

  var queryString = "?q=all_courses&ResidencyId=1&AccreditationStatusId=1&StudyAreaId=&c=read,write&callback=?";
  $.getJSON("//www.swinburne.edu.au/cwis/php_pages/webapps/marketing/coursesearch-v2/system/api/json/"+queryString,function(data) {
    if(data.success) {
      var selectHtml = '<option value="">Select a course</option>';
      for (option in data.response.SearchResultsList) {
        var onlineCourse = data.response.SearchResultsList[option].Campuses.length == 1 && data.response.SearchResultsList[option].Campuses[0] == "Online";
        selectHtml += '<option value="'+data.response.SearchResultsList[option].CourseCode+(onlineCourse ? "_online" : "")+'">'+data.response.SearchResultsList[option].CourseTitle+' ('+data.response.SearchResultsList[option].CourseCode+')'+'</option>';
      }
      $('#mkt_courseOfInterest').html(selectHtml);
    } else {
      console.log('Something went wrong');
    }
  });

  $.getJSON("//www.swinburne.edu.au/cwis/php_pages/webapps/marketing/lists/api/?list=schools&onlyvalues=true&callback=?",function(data) {
    if(data.success) {
      var schoolList = [];
      for (item in data.response) {
        schoolList.push(data.response[item]);
      }

      var options = {
        data: schoolList,
        list: {
          match: {
            enabled: true
          }
        }
      };
      $('#mkt_secondarySchool').easyAutocomplete(options);

    }
  });

  /** END LOAD AJAX DATA **/

  /** START FIELD UPDATING FUNCTIONS **/

  deflect = function() {
    if ($('#mkt_countryOfResidence option:selected').attr('data-deflect') == "true") {
      $('.deflection_fields').hide();

      if ($('#mkt_countryOfResidence option:selected').attr('data-malaysia-promo') == "true") {
        $('#malaysia_promo_note').show();
        $('#deflection_note').hide();
      }else{
        $('#deflection_note').show();
        $('#malaysia_promo_note').hide();
      }
    } else {
      $('#deflection_note').hide();

      if ($('#mkt_countryOfResidence option:selected').attr('data-malaysia-promo') == "true") {
        $('#malaysia_promo_note').show();
        $('.deflection_fields').hide();
      }else{
        $('#malaysia_promo_note').hide();
        $('.deflection_fields').show();
      }
    }
  }

  updateCourseList = function(areaOfInterest) {
    var residency = $('#mkt_residency_international').is(':checked') ? '2' : '1';

    var currentVal = $('#mkt_areaOfInterest option[value="'+(typeof areaOfInterest !== "undefined" ? areaOfInterest : $('#mkt_areaOfInterest + span.custom-combobox input').val())+'"]').data('id');
    $('#mkt_courseOfInterest + span.custom-combobox input').val('Loading filtered courses...');
    $('#mkt_courseOfInterest + span.custom-combobox input').attr('disabled', 'disabled');
    var classSave = $('#mkt_courseOfInterest + span.custom-combobox a span:first').attr('class');
    $('#mkt_courseOfInterest + span.custom-combobox a span:first').attr('class', 'ui-button-icon ui-icon fa fa-refresh fa-spin fa-fw');
    var queryString = "?q=all_courses&ResidencyId="+residency+"&AccreditationStatusId=1"+(typeof currentVal === "undefined" ? '' : '&StudyAreaId='+currentVal)+"&c=read,write&callback=?";
    $.getJSON("//www.swinburne.edu.au/cwis/php_pages/webapps/marketing/coursesearch-v2/system/api/json/"+queryString,function(data) {
      if(data.success) {
        var selectHtml = '<option value="">Select course</option>';
        for (option in data.response.SearchResultsList) {
          selectHtml += '<option value="'+data.response.SearchResultsList[option].CourseCode+'">'+data.response.SearchResultsList[option].CourseTitle+' ('+data.response.SearchResultsList[option].CourseCode+')'+'</option>';
        }
        $('#mkt_courseOfInterest').html(selectHtml);

      } else {
        console.log('Something went wrong');
      }
      $('#mkt_courseOfInterest + span.custom-combobox input').val('');
      $('#mkt_courseOfInterest + span.custom-combobox input').removeAttr('disabled');
      $('#mkt_courseOfInterest + span.custom-combobox a span:first').attr('class', classSave);
    });
  }

  changeResidency = function($residency) {
    setBdTags();
    setHqTags();

    updateCourseList();
  }

  // END FIELD UPDATING FUNCTIONS

  // START EVENT LISTENERS

  $('#mkt_countryOfResidence').change(function() {
    deflect();
  });

  $('#mkt_areaOfInterest').change(function(event, ui) {
    updateCourseList(event.currentTarget.options[event.currentTarget.options.selectedIndex].value);
  });


  $('#mkt_residency_international, #mkt_residency_local').change(function() {
    changeResidency($('#mkt_residency_international').is(':checked') ? 'international' : 'local');
  });


  // END EVENT LISTENERS

  // Page has loaded all the other stuff, time to finalise data for entry

  deflect();
  changeResidency($('#mkt_residency_international').is(':checked') ? 'international' : 'local');

  var comboboxFields = ['#mkt_areaOfInterest', '#mkt_courseOfInterest', '#mkt_bestDescribes', '#mkt_highestQualification', '#mkt_countryCode', '#mkt_countryOfResidence', '#mkt_dayChoice']
  $(comboboxFields.join(',')).combobox();

  var attrs_to_match = ['placeholder', 'required', 'title', 'aria-label'];

  for (attr in attrs_to_match) {
    $('span.custom-combobox input').attr(attrs_to_match[attr],
      function(i, val) {
        return $($('span.custom-combobox').get(i)).prev('select').attr(attrs_to_match[attr]);
      }
    );
  }

  $('span.custom-combobox input').attr('name',
    function(i, val) {
      return $($('span.custom-combobox').get(i)).prev('select').attr('id');
    }
  );

// Javascript is the worst and doesn't like to obey order of code, so for some
// reason the values aren't set yet 'cause it's slow and f* you, so this is
// making us wait 1 second so it can pull itself together and give us what we want.
  setTimeout(function() {
    comboboxFields.forEach(function(field) {
      if ($(field).val()) {
        var textToShow = $(field).find(":selected").text();
        $(field).parent().find("span").find("input").val(textToShow);
      }
    });
  }, 1000);

  // AND WE'RE DONE, TIME TO FILL SOME FORMS!

});

//
// prepare form
//

FormEnhancer.prepare("mkt-form",{
  validate: true,
  spamFilterTypes: ['invisibleReCaptcha']
});
