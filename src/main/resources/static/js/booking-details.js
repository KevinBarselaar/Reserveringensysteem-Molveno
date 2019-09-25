
function getBookingDetails() {
//TODO get doen naar de backend voor specifieke booking (1)
    $.get("/api/bookings/1", function(data, status) {
        var booking = data;
        console.log(booking);

        //retrieve personal data from back-end
        $("#full-name").text(booking.guest.firstName + " " + booking.guest.lastName);
        $("#birth-date").text(booking.guest.birthDate);
        $("#phone-number").text(booking.guest.phoneNumber);
        $("#email-address").text(booking.guest.emailAddress);

        //retrieve specific address details from back-end
        $("#home-address").text(booking.guest.address.streetName + " "
        + booking.guest.address.houseNumber + ""
        + booking.guest.address.houseNumberAddition + ", "
        + booking.guest.address.postalCode + ", "
        + booking.guest.address.city + ", "
        + booking.guest.address.country);

        //retrieve booking information from back-end
        $("#booking-period").text(booking.startBooking + " - " + booking.endBooking);
        $("#extra-items").text(booking.extraAccommodation);

        //retrieve booked room information from back-end

        var rooms = booking.rooms;
        var roomsContainer = $("#room-rows");
        for(var index = 0; index < rooms.length; index++) {
            var room = rooms[index];
            $("#room-table").append(createRoomTableHeader("Room", room.id));
            roomsContainer.append(createRoomTableRow("Room type", room.type));
            roomsContainer.append(createRoomTableRow("Adult capacity", room.adultCapacity));
            roomsContainer.append(createRoomTableRow("Minor capacity", room.minorCapacity));
            roomsContainer.append(createRoomTableRow("Bed type", room.bedTypes));
            roomsContainer.append(createRoomTableRow("Floor", room.floor));
            roomsContainer.append(createRoomTableRow("Disabled friendly", room.disabledFriendly));
            roomsContainer.append(createRoomTableRow("Price", room.price));

        }
    });
}

function createRoomTableRow(rowName, rowValue) {
    return '<tr><th scope="row">' + rowName + '</th><td>' + rowValue + '</td></tr>';
}

function createRoomTableHeader(headerName, headerValue) {
    return '<tr><tr class="thead-dark"><th scope="col">' + headerName + ' ' + headerValue + '</th><th scope="col"></th></tr></tr>';
}