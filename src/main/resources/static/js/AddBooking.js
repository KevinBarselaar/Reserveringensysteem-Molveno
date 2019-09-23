$.getJSON("api/rooms/overview", function(rooms) {
    var select = document.getElementById("rooms");
    rooms.forEach(display);

    function display(item) {
        var option = document.createElement("option");
        option.text = "Room " + item.id;
        select.add(option);
    }
})
