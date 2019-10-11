var host = "http://localhost:8080";
var currentBooking;

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

var availableRoomsList = [];
var selectedRooms = [];

function addFields(){
    var container = document.getElementById("guest-container");
    console.log(container);
    var template = document.querySelector('#guest-template');

    var clone = document.importNode(template.content, true);
    container.appendChild(clone);

    // Edit names & ID's
    var guestCount = $('#guest-container > div.guest-input').length;
    console.log(guestCount);
    // Get latest MainGuest
    var lastGuest = $('#guest-container > div.guest-input:last-child');
    console.log(lastGuest);
    var lastGuestInputs = lastGuest.find('input');
    console.log(lastGuestInputs);
    //Set title radio buttons name attribute to title-radio-mainGuest{count}
    lastGuestTitleRadios = $.grep(lastGuestInputs, function( element, index ) {
        return $(element).attr('name') == 'title-radio-guest';
    });

    lastGuestTitleRadios.forEach(function (radio) {
        $(radio).attr('name', 'title-radio-guest' + guestCount);
        $(radio).attr('id', $(radio).attr('id') + guestCount);
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

    md.initFormExtendedDatetimepickers();
}

function removeTemplate() {
    $(".guest-input").remove();
}

function removeSingleGuestInput() {
    $(".guest-input").last().remove();
}

function postData() {
    console.log("posting data...");

    var input_firstname = $("#firstName").val(); 
    var input_lastname = $("#lastName").val(); 
    var input_phonenumber = $("#telNo").val();

    var input_birthday =  moment($("#birthday").val());
    
    var input_title;
    if ($("#mr").prop('checked')) {
      input_title = $("#mr").val();
    } else if ($("#ms").prop('checked')) {
      input_title = $("#ms").val();
    } else if ($("#mrs").prop('checked')) {
      input_title = $("#mrs").val();
    }
    var input_email =  $("#customerEmail").val();

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
        address : mainGuestAddress
    }
    
    var guestList = [];

    for (let index = 1; index <= $('#guest-container > div.guest-input').length; index++) {
        var bdayYear = $("#guest_birthday" + index).val().substring(6,10);
        var bdayMonth = $("#guest_birthday" + index).val().substring(3,5);
        var bdayDay = $("#guest_birthday" + index).val().substring(0,2);
    
        guestBirthday = new Date(bdayYear, bdayMonth, bdayDay);
        
        var guestTitle;

        if ($("#guest_mr" + index).prop('checked')) {
            guestTitle = $("#guest_mr" + index).val();
        } else if ($("#guest_ms" + index).prop('checked')) {
            guestTitle = $("#guest_ms" + index).val();
        } else if ($("#guest_mrs" + index).prop('checked')) {
            guestTitle = "MRS";
        }

        var guest = {
            "title" : guestTitle,
            "firstName" : $("#guest_firstName" + index).val(),
            "lastName" : $("#guest_lastName" + index).val(),
            "birthDate" : guestBirthday
        }

        console.log("This is the guest" + guest);

        guestList.push(guest);     
    }

    console.log("This is the guest array" + guestList);

    // Create JS object with data.
    var newBooking = new Booking(mainGuest, guestList);
    newBooking.rooms = window.selectedRooms;
    console.log(newBooking);

    // Convert JS object to JSON.
    var validJsonBooking = JSON.stringify(newBooking);
    console.log(validJsonBooking);

    // Post JSON to endpoint.
    $.ajax({
        url: host + "/api/bookings/create",
        type:"post",
        data: validJsonBooking,
        contentType: "application/json",
        success: function(result) {
            // On successful post, reload data to get the added one as well.
            console.log("API Success function");
            console.log(result);
            // Reset room dropdown
            clearRoomSelect();
            removeTemplate();
            getData();
        }
    });
}

function clearRoomSelect() {
    window.selectedRooms = [];
    $("#availableRooms").find('option').remove();
    $("#availableRooms").selectpicker("refresh");
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
        success: function(bookings) {
            // On successful get, reload the datatable with new data.
            console.log("GET success");
            $('#datatables').DataTable().clear();
            $('#datatables').DataTable().rows.add(bookings);
            $('#datatables').DataTable().columns.adjust().draw();
        }
    });
}

function getAvailableRoomsBetweenDates(startDate, endDate) {
    console.log("getting all available rooms for ..." + startDate + " till " + endDate);
    clearRoomSelect();

    // Get the data from endpoint.
    $.ajax({
        url: host + "/api/rooms/overview/available/between?startDate=" + startDate + "&endDate=" + endDate,
        type: "get",
        success: function(rooms) {
            // On successful get, reload the datatable with new data.
            console.log("This is the data: " + rooms);            
            window.availableRoomsList = rooms;
        
            for(var index = 0; index < rooms.length; index++) {
                var element = rooms[index];
                $('#availableRooms').append(new Option("Room " + element.id + " (" + element.type + ")", index));
            }
            
            $("#availableRooms").selectpicker("refresh");
        }
    });
}


function checkInOut(id, in_out, icon) {
    var message = $('#bookingDetailMainGuestName').html() + " has checked " + in_out + ".";
    $.ajax({
        url: host + "/api/bookings/check-in-out/" + id,
        type:"put",
        success: function(booking) {
            // On successful PUT, reload the datatable with new data.
            console.log("PUT success");
            console.log(booking);
            showNotification('top','right', icon, 'info',  message);
            getData();
        }
    });
}

// Callback function from AJAX request if the model requests information
function openBookingDetail(booking) {
    console.log('open booking'); 
    var rooms = "";
    for (var i = 0; i < booking.rooms.length; i++){
        rooms += booking.rooms[i].id + " (" + roomTypes[booking.rooms[i].type] + ")" + "<br/>";
    }
    
    // Set data
    $('#bookingDetailTitle').html("Booking #" + booking.id);
    $('#bookingDetailSubtitle').html("Booked on " + moment(booking.creationDate).format('DD-MM-YYYY'));
    $('#bookingDetailCheckin').html(moment(booking.startBooking).format('DD-MM-YYYY'));
    $('#bookingDetailCheckout').html(moment(booking.endBooking).format('DD-MM-YYYY'));
    $('#bookingDetailRooms').html(rooms.substring(0, rooms.length - 5));
    $('#bookingDetailBoardType').html(boardTypes[booking.boardType]);
    $('#bookingDetailMainGuestName').html(titleString[booking.mainGuest.title] + " " + booking.mainGuest.firstName + " " + booking.mainGuest.lastName);
    $('#bookingDetailMainGuestPhone').html(booking.mainGuest.phoneNumber);
    $('#bookingDetailMainGuestEmail').html(booking.mainGuest.emailAddress);
    $('#bookingDetailMainGuestAddress').html(booking.mainGuest.address.streetName + " " + booking.mainGuest.address.houseNumber + " " + booking.mainGuest.address.houseNumberAddition + "<br/>" + booking.mainGuest.address.postalCode + ", " + booking.mainGuest.address.city + "<br/>" + booking.mainGuest.address.country);
    $('#bookingDetailExtras').html(booking.extraItems);
    $('#bookingDetailPreference').html(booking.preference);

    // MainGuest calculation Adults & Children
    var children = 0;
    $('#bookingDetailGuestsTable tbody').html('');

    // Parse year to int and check if there's an 18 year difference, this should be done with Date objects after we parse these correctly from the Back End
    if (booking.guests.length > 0) {
        for (var i = 0; i < booking.guests.length; i++) {
            var birthdateMoment = moment(booking.guests[i].birthDate); // Parse birth date to Moment JS Date object
            var checkinMoment = moment(booking.startBooking); // Parse check in date to Moment JS Date object
            var difference = moment.duration(checkinMoment.diff(birthdateMoment, 'years', true)); // Check difference between birth date and check in date
            console.log(difference);
            if (difference < 10) {
                children++;
            }
            $('#bookingDetailGuestsTable tbody').append('<tr><td>' + titleString[booking.guests[i].title] + " " + booking.guests[i].firstName + " " + booking.guests[i].lastName + '</td><td>' + booking.guests[i].birthDate.substr(0,10) + '</td></tr>');
        }  
        $('#bookingDetailGuests').html(booking.guests.length - children + " adults, " + children + " children");
    } else {
        $('#bookingDetailGuests').html("No other guests");
    }
    
    $('#checkInButton').removeClass();
    $('#checkOutButton').removeClass();
    $('#checkInButton').show();
    $('#checkOutButton').show();

    // Disable check out button if guest hasn't checked in yet
    if (booking.checkedIn) {
        $('#checkInButton').addClass('btn btn-secondary disabled mr-3').html('<i class="material-icons">check_circle</i> Checked in');
        $('#checkOutButton').addClass('btn btn-primary');
    } else if (moment().isAfter(moment(booking.endDate))) { // Check if check out date is in the past, checking in after this should not be possible
        $('#checkInButton').hide();
        $('#checkOutButton').hide();
    } else {
        $('#checkInButton').addClass('btn btn-primary mr-3').html('<i class="material-icons">check_circle</i> Check in');
        $('#checkOutButton').addClass('btn btn-secondary disabled');
    }

    $('#bookingDetail').modal();
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
            currentBooking = data;
            switch (modalRequested) {
                case 'detail':
                    openBookingDetail(data);
                    break;
                default: return getData();
            }
        },
        error: function () {
            console.log ("invalid Id?");
        }
    });
}

function showNotification (from, align, icon, color, message) {
    type = ['', 'info', 'danger', 'success', 'warning', 'rose', 'primary'];

    $.notify({
        icon: icon,
        message: message

    }, {
        type: type[color],
        timer: 2000,
        placement: {
            from: from,
            align: align
        }
    });
}

$(document).ready(function() {
    // initialise Datetimepicker and Sliders
    md.initFormExtendedDatetimepickers();
    if ($('.slider').length != 0) {
        md.initSliders();
    }
});

function deleteBooking(id) {
    console.log("Deleting Booking with ID: " + id + "...");
    $.ajax({
        url: host + "/api/bookings/delete/" + id,
        type:"delete",
        success: function(data) {
            console.log("Succesfully deleted Booking with ID: " + data.id + ".");
            console.log(data);
        },
        error: function () {
            console.log ("Invalid Id?");
        }
    });
}

class Booking {  
    constructor(mainGuest, guestList) {
        var input_start = $("#checkInDate").val();
        var input_end = $("#checkOutDate").val();

        this.rooms = [];
        this.extraItems = "Hey";
        this.startBooking = input_start;
        this.endBooking = input_end;
        this.mainGuest = mainGuest;
        this.guests = guestList;
        this.boardType = $("[name='boardtype-radio']:checked").val();
        this.preference = $("[name='preference-radio']:checked").val();
    }
}

class MainGuest {
    constructor(mainGuest) {
        this.mainGuest = mainGuest;
    }
}

class MainGuestAddress {
    constructor(address) {
        this.mainGuestAddress = address;
    }
}