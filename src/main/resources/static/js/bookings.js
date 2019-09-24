getBookingsOverview = function() {
    $.get("/api/bookings/overview", function(bookingData) {

        console.log(bookingData);
        for(var index = 0; index < bookingData.length; index++) {
            var booking = bookingData[index];

            $("#bookings").append('<tr><th scope="row">' + booking.id + '</th><td>' + booking.guest.lastName + '</td><td>' + booking.startBooking +'</td><td>' + booking.endBooking + '</td></tr>');
        }
    });

}

class Booking {
    constructor(id, rooms, startBooking, endBooking, guest, numberOfMinors, numberOfAdults) {
        this.id = id;
        this.rooms = rooms;
        this.startBooking = startBooking;
        this.endBooking = endBooking;
        this.guest = guest;
        this.numberOfMinors = numberOfMinors;
        this.numberOfAdults = numberOfAdults;
    }
}