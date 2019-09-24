getBookingsOverview = function() {
    $.get("/api/bookings/overview", function(bookingData) {
        for(var index = 0; index < bookingData.length; index++) {
            var booking = bookingData[index];

            $("#bookings").append('<tr><th scope="row">' + booking.id + '</th><td>' + booking.guest.lastName + '</td><td>' + booking.startBooking +'</td><td>' + booking.endBooking + '</td></tr>');
        }
    });

}