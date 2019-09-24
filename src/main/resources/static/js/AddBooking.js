var roomArray = [];

$.getJSON("api/rooms/overview", function(rooms) {
    roomArray = rooms.slice();
    var select = document.getElementById("roomSelection");
    rooms.forEach(display);

    function display(item) {
        var option = document.createElement("option");
        option.text = "Room " + item.id;
        option.value = item.id;
        select.add(option);
    }
})

$(function(){
    $("#countryselect").load("countryselect.html"); 
  });

function createBooking() {
    event.preventDefault();

    var booking = {
        rooms : roomArray[$("#roomSelection").val()-1],
        extraItems : $("[name='extra']").val(),
        startBooking : $("[name='start']").val(),
        endBooking :$("[name='end']").val()
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
        firstName : $("[name='firstName']").val(),
        lastName : $("[name='lastName']").val(),
        phoneNumber : $("[name='phone']").val(),
        birthDate : $("[name='birthday']").val(),
        emailAddress : $("[name='email']").val(),
        address : guestAddress
    }

    var guestJson = JSON.stringify(guest);
    var bookingJson = JSON.stringify(booking);

    console.log(bookingJson);

    $.ajax({
        type: "POST",
        url: "api/bookings",
        contentType: 'application/json',
        data: JSON.stringify(new Booking(booking, guest)),
        success: function() {
            console.log("Success");
        },
        dataType: 'json'
      });

    console.log("Booked.");

    //test
    console.log(JSON.stringify(new GuestAddress(guestAddress)));
    console.log(JSON.stringify(new Guest(guest)));
    console.log(JSON.stringify(new Booking(booking, guest)));

    return false;
}

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

  class Booking { 

    constructor(booking, guest) {
        this.booking = booking;
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