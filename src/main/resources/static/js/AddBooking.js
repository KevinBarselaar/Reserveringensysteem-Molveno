$.getJSON("api/rooms/overview", function(rooms) {
    var select = document.getElementById("rooms");
    rooms.forEach(display);

    function display(item) {
        var option = document.createElement("option");
        option.text = "Room " + item.id;
        select.add(option);
    }
})

$(function(){
    $("#countryselect").load("countryselect.html"); 
  });


function createBooking() {
    event.preventDefault();

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

    $.ajax({
        type: "POST",
        url: "api/bookings",
        contentType: 'application/json',
        data: guestJson,
        success: function() {
            console.log("Success");
        },
        dataType: 'json'
      });

    console.log($(guest));
    console.log("Booked.");

    return false;
}