var host = "http://localhost:8080"; 

var titleString = {
    "MR": "Mr.",
    "MRS": "Mrs.",
    "MS": "Ms.",
}
var boardTypeString = {
    "ACCOMMODATIONS": "Accommodations",
    "BED_AND_BREAKFAST": "Bed & Breakfast",
    "HALF_BOARD": "Half Board",
}


function postData() {
<<<<<<< HEAD
    console.log("posting data...");

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
    
    var guestAddress = {
        streetName : $("[name='streetName']").val(),
        houseNumber : $("[name='houseNumber']").val(),
        houseNumberAddition : $("[name='addition']").val(),
        postalCode : $("[name='zipcode']").val(),
        city : $("[name='city']").val(),
        country : $("[name='country']").val()
    }

    var guest = {
        firstName : input_firstname,
        lastName : input_lastname,
        phoneNumber : input_phonenumber,
        birthDate : input_birthday,
        emailAddress : input_email,
        address : guestAddress
    }

    // Create JS object with data.
    var newReservation = new Booking(guest);
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
=======
    // Wordt in andere branch gefixt, werkt in deze geenszins door project merge
>>>>>>> front-end-integration
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

class Booking {  

    constructor(guest) {

        var input_start = $("#checkInDate").val();
        var input_end = $("#checkOutDate").val();

        this.rooms = [];
        this.extraItems = "Hey";
        this.startBooking = input_start;
        this.endBooking = input_end;
        this.numberOfAdults = $("[name='adults']").val();
        this.numberOfMinors = $("[name='minors']").val();
        this.mainGuest = guest;
        this.boardType = $("[name='exampleRadios2']:checked").val();
    }}

class Guest {
    constructor(guest) {
        this.guest = guest;
    }
}

class GuestAddress {
    constructor(address) {
        this.guestAddress = address;
    }
}