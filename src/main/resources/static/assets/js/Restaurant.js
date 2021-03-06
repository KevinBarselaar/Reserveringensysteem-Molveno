var host = "http://localhost:8080";

function postRestaurantData() {
    console.log("posting data...");

    // Get values from html.

    var input_isGuest = $("#isGuest").prop('checked');
    var input_date = $("#date").val();
    var input_time = $("#time").val();
    var input_firstname = $("#firstName").val();
    var input_lastname = $("#lastName").val(); 
    var input_adults = parseInt($("#amountAd").val(), 0);

    console.log("input datetime: "+ input_date + input_time);
    console.log("input adult" + input_adults);

    var input_children = parseInt($("#amountChild").val(), 0);
    
    var input_booster = parseInt($("#booster").val(), 0);
    var input_childChair = parseInt($("#childChair").val(), 0);

    var isDisabled = $("#disabled").prop('checked');
    var isTerrace = $("#terrace").prop('checked');

    var input_reservationDate = new Date();

    // Create JS object with data.
    var newBooking = {
        isGuest: input_isGuest,
        bookingDate: input_date,
        bookingTime: input_time, 
        firstName:input_firstname,
        lastName: input_lastname,
        numberOfGuests: input_adults,
        numberOfMinors: input_children,
        roomId: 7,
        extraItems: [
            {
                id: null,
                disabledFriendly: isDisabled,
                numberOfChildChairs: input_childChair,
                numberOfBoosterSeats: input_booster,
                terrace:  isTerrace
            }
        ],

    };
    console.log(newBooking);

    // Convert JS object to JSON.
    var validJsonBooking = JSON.stringify(newBooking);
    console.log(validJsonBooking);

    // Post JSON to endpoint.
    $.ajax({
        url: host + "/api/v1/restaurantbookings/create",
        type:"post",
        data: validJsonBooking,
        contentType: "application/json",
        success: function(result) {
            // On successful post, reload data to get the added one as well.
            console.log("API Success function");
            console.log(result);
            getRestaurantData();
        }
    });
}

function getRestaurantData() {
    console.log("getting data...");

    // Get the data from endpoint.
    $.ajax({
        url: host + "/api/v1/restaurantbookings/overview",
        type:"get",
        success: function(restaurantbookings) {
            // On successful get, reload the datatable with new data.
            console.log("This is the data: ");
            restaurantbookings.forEach(element => {
                console.log(element);
            });
            $('#restaurantDatatables').DataTable().clear();
            $('#restaurantDatatables').DataTable().rows.add(restaurantbookings);
            $('#restaurantDatatables').DataTable().columns.adjust().draw();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Booking was unsuccessful.");
        }
    });
}

function deleteBooking(id) {
    console.log("Deleting Booking with ID: " + id + "...");
    $.ajax({
        url: host + "/api/v1/restaurantbookings/delete/" + id,
        type:"delete",
        success: function() {
            console.log("Succesfully deleted");
        },
        error: function () {
            console.log ("Invalid Id?");
        }
    });
}

function getRestaurantDetails(id) {
    
    $.ajax({
        url: host + "/api/v1/restaurantbookings/" + id,
        type:"get",
        success: function(data, status) {
            console.log('Get booking ' + data.bookingId + ' success');
            var restaurant = data;

            //retrieve personal data from back-end
            $('#tableBookingDetailsTitle').html('Table booking #' + restaurant.bookingId);
            if (restaurant.isGuest) {
                $("#tableBookingDetailsGuest").html(restaurant.firstName + ' is a guest at the hotel.');
            } else {
                $("#tableBookingDetailsGuest").html(restaurant.firstName + ' isn\'t currently a guest.');
            }
            $("#tableBookingDetailsFirstName").html(restaurant.firstName);
            $("#tableBookingDetailsLastName").html(restaurant.lastName);

            $("#tableBookingDetailsChildChair").html(restaurant.extraItems[0].numberOfChildChairs);
            $("#tableBookingDetailsBoosterSeat").html(restaurant.extraItems[0].numberOfBoosterSeats);

            $("#tableBookingDetailsDate").html(restaurant.bookingDate);
            $("#tableBookingDetailsTime").html(restaurant.bookingTime);

            $("#tableBookingDetailsAdults").html(restaurant.numberOfGuests);
            $("#tableBookingDetailsMinors").html(restaurant.numberOfMinors);

            $('.extra-item-title').remove();
            // Show extra items if present
            if (restaurant.extraItems[0].terrace) {
                $("#tableBookingDetailsExtraItems").after("<h4 class='extra-item-title'>Terrace table preferred.</h4>");
            }

            if (restaurant.extraItems[0].disabledFriendly) {
                $("#tableBookingDetailsExtraItems").after("<h4 class='extra-item-title'>Accommodation needed for physically disadvantaged.</h4>");
            }

            $('#tableBookingDetails').modal();
        }
    });
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