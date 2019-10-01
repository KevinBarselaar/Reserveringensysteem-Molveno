var host = "http://localhost:8080";

function postData() {
    console.log("posting data...");

    // Get values from html.
    var input_startDate = $("#checkInDate").val();
    var input_endDate = $("#checkOutDate").val();

    var startYear = input_startDate.substring(6,10);
    var startMonth = input_startDate.substring(3,5);
    var startDay = input_startDate.substring(0,2);
    
    input_startDate = new Date(startYear, startMonth, startDay);
    console.log(input_startDate);

    var endYear = input_endDate.substring(6,10);
    var endMonth = input_endDate.substring(3,5);
    var endDay = input_endDate.substring(0,2);
    
    input_endDate = new Date(endYear, endMonth, endDay);

    console.log(input_endDate);

    var input_firstname = $("#firstName").val(); 
    var input_lastname = $("#lastName").val(); 
    var input_phonenumber = $("#telNo").val();
    var input_birthday =  $("#birthday").val();

    var bdayYear = input_birthday.substring(6,10);
    var bdayMonth = input_birthday.substring(3,5);
    var bdayDay = input_birthday.substring(0,2);
    
    input_birthday = new Date(bdayYear, bdayMonth, bdayDay);

    console.log(input_birthday);

    var input_email =  $("#customerEmail").val();
    var input_boardType;


    if ($("#acc").prop('checked')) {
      input_boardType = $("#acc").val();
    } else if ($("#bnb").prop('checked')) {
      input_boardType = $("#bnb").val(); 
    } else if ($("#half").prop('checked')) {
      input_boardType = $("#half").val(); 
    }
    
    var input_reservationDate = new Date();

    // Create JS object with data.
    var newReservation = {
      reservationNumber: null,
      reservationDate: input_reservationDate,
      startDate: input_startDate,
      endDate: input_endDate,
      totalPrice: 0.0,
      customer: {
          customerId: null,
          title: "MS",
          firstName: input_firstname,
          lastName: input_lastname,
          address: {},
          phoneNumber: input_phonenumber,
          email: input_email,
          birthday: input_birthday
      },
      boardType: input_boardType,
      rooms: [],
      checkedIn: false
    };
    console.log(newReservation);

    // Convert JS object to JSON.
    var validJsonReservation = JSON.stringify(newReservation);
    console.log(validJsonReservation);

    // Post JSON to endpoint.
    $.ajax({
        url: host + "/api/bookings/create",
        type:"post",
        data: validJsonReservation,
        contentType: "application/json",
        success: function(result) {
            // On successful post, reload data to get the added one as well.
            console.log("API Success function");
            console.log(result);
            getData();
        }
    });
}

function setFormValidation(id) {
    $(id).validate({
        highlight: function(element) {
        $(element).closest('.form-group').removeClass('has-success').addClass('has-danger');
        $(element).closest('.form-check').removeClass('has-success').addClass('has-danger');
        },
        success: function(element) {
        $(element).closest('.form-group').removeClass('has-danger').addClass('has-success');
        $(element).closest('.form-check').removeClass('has-danger').addClass('has-success');
        },
        errorPlacement: function(error, element) {
        $(element).closest('.form-group').append(error);
        },
    });
}

function getData() {
    console.log("getting data...");

    // Get the data from endpoint.
    $.ajax({
        url: host + "/api/bookings/overview",
        type:"get",
        success: function(reservations) {
            // On successful get, reload the datatable with new data.
            console.log("This is the data: ");
            reservations.forEach(element => {
                console.log(element);
            });
            $('#datatables').DataTable().clear();
            $('#datatables').DataTable().rows.add(reservations);
            $('#datatables').DataTable().columns.adjust().draw();
        }
    });
}

function getReservation(id) {
    console.log("getting reservation...");

    // Get the data from endpoint.
    $.ajax({
        url: host + "/api/bookings/" + id,
        type:"get",
        success: function(reservation) {
            // On successful get, reload the datatable with new data.
            console.log("This is the reservation data (" + id + "): ");
            console.log(reservation);
        }
    });
}



$(document).ready(function() {
    /* Form validation init */
    setFormValidation('#RegisterValidation');
    setFormValidation('#TypeValidation');
    setFormValidation('#LoginValidation');
    setFormValidation('#RangeValidation');
    // initialise Datetimepicker and Sliders
    md.initFormExtendedDatetimepickers();
        if ($('.slider').length != 0) {
        md.initSliders();
    }
});