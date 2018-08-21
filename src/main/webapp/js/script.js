var currentDate = new Date();

var pickupDateDefault = new Date();
pickupDateDefault.setHours(currentDate.getHours() + 3);
pickupDateDefault.setMinutes(0);

var dropoffDateDefault = new Date();
dropoffDateDefault.setDate(currentDate.getDate() + 1);
dropoffDateDefault.setHours(currentDate.getHours() + 3);
dropoffDateDefault.setMinutes(0);

var pickupDateMax = new Date();
pickupDateMax.setMonth(currentDate.getMonth() + 3);

var dropoffDateMax = new Date();
dropoffDateMax.setMonth(currentDate.getMonth() + 6);

$(function () {
  $('#button_upload').hide();
  $('#file_picker').on('change', function() {
    if($('#file_picker').val()) {
      $('#button_upload').show();
    }
  });

  $('#pickup').datetimepicker({
    format: 'DD.MM.YYYY HH:mm',
    defaultDate: pickupDateDefault,
    minDate: pickupDateDefault,
    maxDate: pickupDateMax,
    stepping: 60
  });

  $('#dropoff').datetimepicker({
    format: 'DD.MM.YYYY HH:mm',
    defaultDate: dropoffDateDefault,
    minDate: dropoffDateDefault,
    maxDate: dropoffDateMax,
    stepping: 60
  });

  $('#pickup').on('dp.change', function(e){
    $('#dropoff').data("DateTimePicker").minDate(e.date);
  });

});
