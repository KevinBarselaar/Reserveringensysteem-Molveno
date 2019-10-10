function getRestaurantDetails(id) {
    $.ajax({
        url: host + "/api/v1/restaurantbookings/" + id,
        type:"get",
        success: function(data, status) {
        console.log('Get booking ' + data.id + ' success');
        var restaurant = data;

        //retrieve personal data from back-end
        $('#tableBookingDetailsTitle').html('Table booking #' + restaurant.restaurantbooking.bookingId);


        $('#tableBookingDetails').modal();
        }
    });
}

    function createRoomTableRow(rowName, rowValue) {
        return '<tr><th scope="row">' + rowName + '</th><td>' + rowValue + '</td></tr>';
    }
    
    function createRoomTableHeader(headerName, headerValue) {
        return '<tr><tr class="thead-dark"><th scope="col">' + headerName + ' ' + headerValue + '</th><th scope="col"></th></tr></tr>';
    }