var roomArray = [];

//GET request to obtain all rooms
//TODO: Get available rooms instead of all rooms
$.ajax({
    type: "GET",
    url: "api/rooms/overview",
    contentType: 'application/json',
    success: function(data) {
        roomArray = data.slice();
        var select = document.getElementById("roomSelection");
        //data.forEach(display);
        $.each(data, function(index, room) {
            var option = document.createElement("option");
            option.text = "Room " + room.id;
            option.value = room.id;
            select.add(option);
        });
    },
    dataType: 'json'
});

$(function(){
    $("#countryselect").load("countryselect.html"); 
  });

//Creates a POST request to send to the backend
function createBooking() {
    event.preventDefault();

    var pNumber = "+" + iti.selectedCountryData.dialCode + " " + iti.telInput.value;
    
    var guestAddress = {
        streetName : $("[name='streetName']").val(),
        houseNumber : $("[name='houseNumber']").val(),
        houseNumberAddition : $("[name='addition']").val(),
        postalCode : $("[name='zipcode']").val(),
        city : $("[name='city']").val(),
        country : $("[name='country']").val()
    }

    var guest = {
        firstName : $("[name='firstName']").val(),
        lastName : $("[name='lastName']").val(),
        phoneNumber : pNumber,
        birthDate : $("[name='birthday']").val(),
        emailAddress : $("[name='email']").val(),
        address : guestAddress
    }

    $.ajax({
        type: "POST",
        url: "api/bookings",
        contentType: 'application/json',
        data: JSON.stringify(new Booking(guest)),
        success: function() {
        },
        dataType: 'json'
    });

    return false;
}

//Show current time
$.fn.setNow = function (onlyBlank) {
    var now = new Date($.now()),
        year,
        month,
        date,
        hours,
        minutes,
        formattedDateTime;

    year = now.getFullYear();
    month = now.getMonth().toString().length === 1 ? '0' + (now.getMonth() + 1).toString() : now.getMonth() + 1;
    date = now.getDate().toString().length === 1 ? '0' + (now.getDate()).toString() : now.getDate();
    hours = now.getHours().toString().length === 1 ? '0' + now.getHours().toString() : now.getHours();
    minutes = now.getMinutes().toString().length === 1 ? '0' + now.getMinutes().toString() : now.getMinutes();

    formattedDateTime = year + '-' + month + '-' + date + 'T' + hours + ':' + minutes;

    if ( onlyBlank === true && $(this).val() ) {
        return this;
    }

    $(this).val(formattedDateTime);

    return this;
}
  
$(function () {
    // Handler for .ready() called.
    $('input[type="datetime-local"]').setNow();
});

//Models to send to backend
class Booking { 

    constructor(guest) {
        this.rooms = [roomArray[$("#roomSelection").val()-1]];
        this.extraItems = $("[name='extra']").val();
        this.startBooking = $("[name='start']").val();
        this.endBooking = $("[name='end']").val();
        this.numberOfAdults = $("[name='adults']").val();
        this.numberOfMinors = $("[name='minors']").val();
        this.guest = guest;
    }
}

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