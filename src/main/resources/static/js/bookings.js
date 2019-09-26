function getBookingsOverview() {
    $.get("/api/bookings/overview", function(bookingData) {

            $('#table').DataTable().clear();
            $('#table').DataTable().rows.add(bookingData);
            $('#table').DataTable().columns.adjust().draw();
    });
}