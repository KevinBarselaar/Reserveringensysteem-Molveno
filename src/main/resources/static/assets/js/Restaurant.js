var host = "http://localhost:1010";

function postRestaurantData() {
    console.log("posting data...");

    // Get values from html.

    var input_roomNo = parseInt($("#roomNo").val(), 0);
    var input_date = $("#date").val();
    var input_time = $("#time").val();
    var input_firstname = $("#firstName").val();
    var input_lastname = $("#lastName").val(); 
    var input_adults = parseInt($("#amountAd").val(), 0);

    console.log("input adult" + input_adults);

    var input_children = parseInt($("#amountChild").val(), 0);
    
    
    // var input_extraItems;
    
    var input_booster = parseInt($("#booster").val(), 0);
    var input_childChair = parseInt($("#childChair").val(), 0);


    var isDisabled = $("#disabled").prop('checked');

    var isTerrace = $("#terrace").prop('checked');

    // if ($("#disabled").prop('checked')) {
    //   input_extraItems = $("#disabled").val();
    // }
    // if ($("#terrace").prop('checked')) {
    //     input_extraItems = $("#terrace").val(); 
    // }



    var input_reservationDate = new Date();

    // Create JS object with data.
    var newBooking = {
        roomNo: input_roomNo,
        date:input_date,
        time: input_time, 
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
        }
    });
}
