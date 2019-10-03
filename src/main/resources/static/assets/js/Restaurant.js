function postData() {
    console.log("posting data...");

    // Get values from html.
    var input_dateandtime = $("#datetime").val();
    var input_lastname = $("#lastName").val(); 
    var input_adults = parseInt($("#amountAd").val(), 0);

    console.log("input adult" + input_adults);

    var input_children =  $("#amountChild").val();
    
    
    var input_extraItems;

    if ($("#disabled").prop('checked')) {
      input_extraItems = $("#disabled").val();
    } if ($("#booster").prop('checked')) {
      input_extraItems = $("#booster").val(); 
    } if ($("#child").prop('checked')) {
      input_extraItems = $("#child").val(); 
    }if ($("#terrace").prop('checked')) {
        input_extraItems = $("#terrace").val(); 
    }
    var input_reservationDate = new Date();

    // Create JS object with data.
    var newBooking = {
        lastName: input_lastname,
        bookingTime: input_dateandtime,
        numberOfGuests: input_adults,
        numberOfMinors: input_children,
        roomId: 7,
        extraItems: [
            {
                id: null,
                disabledFriendly: input_extraItems,
                numberOfChildChairs: input_extraItems,
                numberOfBoosterSeats: input_extraItems,
                terrace:  input_extraItems
            }
        ],

    };
    console.log(newBooking);

    // Convert JS object to JSON.
    var validJsonBooking = JSON.stringify(newBooking);
    console.log(validJsonBooking);

    // Post JSON to endpoint.
    $.ajax({
        url:"http://localhost:1010/api/v1/restaurantbookings/create",
        type:"post",
        data: validJsonBooking,
        contentType: "application/json",
        success: function(result) {
            // On successful post, reload data to get the added one as well.
            console.log("API Success function");
            console.log(result);
            //getData();
        }
    });
}