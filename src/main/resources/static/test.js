console.log("jej");

$(document).ready(function() {

        $('#table').DataTable({
            columns: [
                { "data": "id" },
                { "data": "guest.lastName" },
                { "data": "startBooking" },
                { "data": "endBooking" }
                    ]
        });
        //TODO use it from the external file!
        console.log("1");
        getBookingsOverview();
                console.log("2");
                });

function getBookingsOverview() {
       console.log("wut?");
    $.get("/api/bookings/overview", function(bookingData) {

            $('#table').DataTable().clear();
            $('#table').DataTable().rows.add(bookingData);
            $('#table').DataTable().columns.adjust().draw();
    });
}