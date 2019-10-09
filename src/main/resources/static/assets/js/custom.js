var host = "http://localhost:8080"; 

var titleString = {
    "MR": "Mr.",
    "MRS": "Mrs.",
    "MS": "Ms.",
}
var boardTypes = {
    "ACCOMMODATIONS": "Accommodations",
    "BED_AND_BREAKFAST": "Bed & Breakfast",
    "HALF_BOARD": "Half Board",
}
var roomTypes = {
    "SINGLE": "Single",
    "DOUBLE": "Double",
    "TWO_DOUBLE": "2x Double",
    "PENTHOUSE": "Penthouse"
}

function addFields(){
    var container = document.getElementById("guest-container");

    var template = document.querySelector('#guest-template');

    var clone = document.importNode(template.content, true);
    container.appendChild(clone);

    // Edit names & ID's
    var guestCount = $('#guest-container > div.form-row').length;
    
    // Get latest MainGuest
    var lastGuest = $('#guest-container > div.form-row:last-child');

    var lastGuestInputs = lastGuest.find('input');

    //Set title radio buttons name attribute to title-radio-mainGuest{count}
    lastGuestTitleRadios = $.grep(lastGuestInputs, function( element, index ) {
        return $(element).attr('name') == 'title-radio-guest';
    });

    lastGuestTitleRadios.forEach(function (radio) {
        $(radio).attr('name', 'title-radio-guest' + guestCount);
    });

    //Set firstName id attribute to guest_firstName{count}
    lastGuestFirstName = $.grep(lastGuestInputs, function( element, index ) {
        return $(element).attr('id') == "guest_firstName";
    });

    lastGuestFirstName.forEach(function (text) {
        $(text).attr('id', 'guest_firstName' + guestCount);
    });

    //Set lastName id attribute to guest_lastName{count}
    lastGuestLastName = $.grep(lastGuestInputs, function( element, index ) {
        return $(element).attr('id') == "guest_lastName";
    });

    lastGuestLastName.forEach(function (text) {
        $(text).attr('id', 'guest_lastName' + guestCount);
    });

    //Set birthday id attribute to guest_birthday{count}
    lastGuestBirthday = $.grep(lastGuestInputs, function( element, index ) {
        return $(element).attr('id') == "guest_birthday";
    });

    lastGuestBirthday.forEach(function (text) {
        $(text).attr('id', 'guest_birthday' + guestCount);
    });
}

function postData() {
    console.log("posting data...");

    var input_firstname = $("#firstName").val(); 
    var input_lastname = $("#lastName").val(); 
    var input_phonenumber = $("#telNo").val();
    var input_birthday =  $("#birthday").val();

    var input_title;

    if ($("#mr").prop('checked')) {
      input_title = $("#mr").val();
    } else if ($("#ms").prop('checked')) {
      input_title = $("#ms").val();
    } else if ($("#mrs").prop('checked')) {
      input_title = $("#mrs").val();
    }

    console.log(input_title);

    var bdayYear = input_birthday.substring(6,10);
    var bdayMonth = input_birthday.substring(3,5);
    var bdayDay = input_birthday.substring(0,2);
    
    input_birthday = new Date(bdayYear, bdayMonth, bdayDay);

    console.log(input_birthday);

    var input_email =  $("#customerEmail").val();

    var input_boardType;

    if ($("#accommodations").prop('checked')) {
      input_boardType = $("#accommodations").val();
    } else if ($("#bedandbreakfast").prop('checked')) {
      input_boardType = $("#bedandbreakfast").val();
    } else if ($("#halfboard").prop('checked')) {
      input_boardType = $("#halfboard").val();
    }
    
    var mainGuestAddress = {
        streetName : $("#streetName").val(),
        houseNumber : $("#houseNumber").val(),
        houseNumberAddition : $("#addition").val(),
        postalCode : $("#zipcode").val(),
        city : $("#city").val(),
        country : $("#country").val()
    }

    var mainGuest = {
        firstName : input_firstname,
        lastName : input_lastname,
        phoneNumber : input_phonenumber,
        birthDate : input_birthday,
        emailAddress : input_email,
        title : input_title,
        mainGuestAddress : mainGuestAddress
    }
    
    var guestList = [];

    for (let index = 1; index <= $('#guest-container > div.form-row').length; index++) {
        var guest = {
            "title" : "MR",
            "firstName" : $("#guest_firstName" + index).val(),
            "lastName" : $("#guest_lastName" + index).val(),
            "birthDate" : $("#guest_birthday" + index).val()
        }

        console.log("This is the guest" + guest);

        guestList.push(guest);
    }

    console.log("This is the guest array" + guestList);

    // Create JS object with data.
    var newReservation = new Booking(mainGuest, guestList);
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

// Callback function from AJAX request if the model requests information
function openBookingDetail(booking) {
    var rooms = "";
    for (var i = 0; i < booking.rooms.length; i++){
        rooms += booking.rooms[i].id + " (" + roomTypes[booking.rooms[i].type] + ")" + "<br/>";
    }
    
    // Set data
    $('#bookingDetailsTitle').html("Booking (#" + booking.id + ")");
    $('#bookingDetailsSubtitle').html("Booked on " + booking.creationDate.substr(0,10));
    $('#bookingDetailsCheckin').html(booking.startBooking);
    $('#bookingDetailsCheckout').html(booking.endBooking);
    $('#bookingDetailsRooms').html(rooms.substring(0, rooms.length - 5));
    $('#bookingDetailsBoardType').html(boardTypes[booking.boardType]);
    $('#bookingDetailsMainGuestName').html(titleString[booking.mainGuest.title] + " " + booking.mainGuest.firstName + " " + booking.mainGuest.lastName);
    $('#bookingDetailsMainGuestPhone').html(booking.mainGuest.phoneNumber);
    $('#bookingDetailsMainGuestEmail').html(booking.mainGuest.emailAddress);
    $('#bookingDetailsMainGuestAddress').html(booking.mainGuest.mainGuestAddress.streetName + " " + booking.mainGuest.mainGuestAddress.houseNumber + " " + booking.mainGuest.mainGuestAddress.houseNumberAddition + "<br/>" + booking.mainGuest.mainGuestAddress.postalCode + ", " + booking.mainGuest.mainGuestAddress.city + "<br/>" + booking.mainGuest.mainGuestAddress.country);
    $('#bookingDetailsExtras').html(booking.extraItems);

    // MainGuest calculation Adults & Children
    var children = 0;
    var currentYear = new Date().getFullYear();

    $('#bookingDetailsGuestsTable tbody').html('');
    // Parse year to int and check if there's an 18 year difference, this should be done with Date objects after we parse these correctly from the Back End
    if (booking.guests.length > 0) {
        for (var i = 0; i < booking.guests.length; i++) {
            /***** INFO: Currently the dummy data contains no adults because of date creation *******/
            if (currentYear - parseInt(booking.guests[i].birthDate.substring(0,4)) < 18) {
                children++;
            }
            $('#bookingDetailsGuestsTable tbody').append('<tr><td>' + titleString[booking.guests[i].title] + " " + booking.guests[i].firstName + " " + booking.guests[i].lastName + '</td><td>' + booking.guests[i].birthDate.substr(0,10) + '</td></tr>');
        }
        $('#bookingDetailsGuests').html(booking.guests.length - children + " adults, " + children + " children");
    } else {
        $('#bookingDetailsGuests').html("No other guests");
    }
    
    $('#bookingDetails').modal();
}

function getBooking(id, modalRequested) {
    console.log("getting booking " + id +"...");
    // Get the data from endpoint.
    $.ajax({
        url: host + "/api/bookings/" + id,
        type:"get",
        success: function(data) {
            // On successful get, reload the datatable with new data.
            console.log("This is the booking data from #" + data.id);
            console.log(data);
            if (modalRequested) { openBookingDetail(data); }
        },
        error: function () {
            console.log ("invalid Id?");
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
    constructor(mainGuest, guestList) {
        var input_start = $("#checkInDate").val();
        var input_end = $("#checkOutDate").val();

        this.rooms = [];
        this.extraItems = "Hey";
        this.startBooking = input_start;
        this.endBooking = input_end;
        this.numberOfAdults = $("[name='adults']").val();
        this.numberOfMinors = $("[name='minors']").val();
        this.mainGuest = mainGuest;
        this.guests = guestList;
        this.boardType = $("[name='exampleRadios2']:checked").val();
    }}

class MainGuest {
    constructor(mainGuest) {
        this.mainGuest = mainGuest;
    }
}

// class Guest {
//     constructor(guest) {
//         this.guest = guest;
//     }
// }

class mainGuestAddress {
    constructor(mainGuestAddress) {
        this.mainGuestAddress = mainGuestAddress;
    }
}