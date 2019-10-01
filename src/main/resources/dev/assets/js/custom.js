function postData() {
    console.log("posting data...");

    // Get values from html.
    var input_startDate = $("#checkInDate").val();
    var input_endDate = $("#checkOutDate").val();

    var startYear = input_startDate.substring(6,10);
    var startMonth = input_startDate.substring(3,5);
    var startDay = input_startDate.substring(0,2);
    
    input_startDate = new Date(startYear, startMonth, startDay);
    console.log(input_startDate);

    var endYear = input_endDate.substring(6,10);
    var endMonth = input_endDate.substring(3,5);
    var endDay = input_endDate.substring(0,2);
    
    input_endDate = new Date(endYear, endMonth, endDay);

    console.log(input_endDate);

    var input_firstname = $("#firstName").val(); 
    var input_lastname = $("#lastName").val(); 
    var input_phonenumber = $("#telNo").val();
    var input_birthday =  $("#birthday").val();

    var bdayYear = input_birthday.substring(6,10);
    var bdayMonth = input_birthday.substring(3,5);
    var bdayDay = input_birthday.substring(0,2);
    
    input_birthday = new Date(bdayYear, bdayMonth, bdayDay);

    console.log(input_birthday);

    var input_email =  $("#customerEmail").val();
    var input_boardType;


    if ($("#acc").prop('checked')) {
      input_boardType = $("#acc").val();
    } else if ($("#bnb").prop('checked')) {
      input_boardType = $("#bnb").val(); 
    } else if ($("#half").prop('checked')) {
      input_boardType = $("#half").val(); 
    }
    
    var input_reservationDate = new Date();

    // Create JS object with data.
    var newReservation = {
      reservationNumber: null,
      reservationDate: input_reservationDate,
      startDate: input_startDate,
      endDate: input_endDate,
      totalPrice: 0.0,
      customer: {
          customerId: null,
          title: "MS",
          firstName: input_firstname,
          lastName: input_lastname,
          address: {},
          phoneNumber: input_phonenumber,
          email: input_email,
          birthday: input_birthday
      },
      boardType: input_boardType,
      rooms: [],
      checkedIn: false
    };
    console.log(newReservation);

    // Convert JS object to JSON.
    var validJsonReservation = JSON.stringify(newReservation);
    console.log(validJsonReservation);

    // Post JSON to endpoint.
    $.ajax({
        url:"http://localhost:1337/api/v1/reservations/add",
        type:"post",
        data: validJsonReservation,
        contentType: "application/json",
        success: function(result) {
            // On successful post, reload data to get the added one as well.
            console.log("API Success function");
            console.log(result);
            //getData();
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

function getData() {
    console.log("getting data...");

    // Get the data from endpoint.
    $.ajax({
        url:"http://localhost:1337/api/v1/reservations/get",
        type:"get",
        success: function(reservations) {
            // On successful get, reload the datatable with new data.
            console.log("This is the data: ");
            reservations.forEach(element => {
                console.log(element);
            });
            $('#datatables').DataTable().clear();
            $('#datatables').DataTable().rows.add(reservations);
            $('#datatables').DataTable().columns.adjust().draw();
        }
    });
}

$(document).ready(function() {
    /* Form validation init */
    setFormValidation('#RegisterValidation');
    setFormValidation('#TypeValidation');
    setFormValidation('#LoginValidation');
    setFormValidation('#RangeValidation');
    // initialise Datetimepicker and Sliders
    md.initFormExtendedDatetimepickers();
        if ($('.slider').length != 0) {
        md.initSliders();
    }

    /* Style bar init */
    $().ready(function() {
    $sidebar = $('.sidebar');

    $sidebar_img_container = $sidebar.find('.sidebar-background');

    $full_page = $('.full-page');

    $sidebar_responsive = $('body > .navbar-collapse');

    window_width = $(window).width();

    fixed_plugin_open = $('.sidebar .sidebar-wrapper .nav li.active a p').html();

    if (window_width > 767 && fixed_plugin_open == 'Dashboard') {
        if ($('.fixed-plugin .dropdown').hasClass('show-dropdown')) {
        $('.fixed-plugin .dropdown').addClass('open');
        }

    }

    $('.fixed-plugin a').click(function(event) {
        // Alex if we click on switch, stop propagation of the event, so the dropdown will not be hide, otherwise we set the  section active
        if ($(this).hasClass('switch-trigger')) {
        if (event.stopPropagation) {
            event.stopPropagation();
        } else if (window.event) {
            window.event.cancelBubble = true;
        }
        }
    });

    $('.fixed-plugin .active-color span').click(function() {
        $full_page_background = $('.full-page-background');

        $(this).siblings().removeClass('active');
        $(this).addClass('active');

        var new_color = $(this).data('color');

        if ($sidebar.length != 0) {
        $sidebar.attr('data-color', new_color);
        }

        if ($full_page.length != 0) {
        $full_page.attr('filter-color', new_color);
        }

        if ($sidebar_responsive.length != 0) {
        $sidebar_responsive.attr('data-color', new_color);
        }
    });

    $('.fixed-plugin .background-color .badge').click(function() {
        $(this).siblings().removeClass('active');
        $(this).addClass('active');

        var new_color = $(this).data('background-color');

        if ($sidebar.length != 0) {
        $sidebar.attr('data-background-color', new_color);
        }
    });

    $('.fixed-plugin .img-holder').click(function() {
        $full_page_background = $('.full-page-background');

        $(this).parent('li').siblings().removeClass('active');
        $(this).parent('li').addClass('active');


        var new_image = $(this).find("img").attr('src');

        if ($sidebar_img_container.length != 0 && $('.switch-sidebar-image input:checked').length != 0) {
        $sidebar_img_container.fadeOut('fast', function() {
            $sidebar_img_container.css('background-image', 'url("' + new_image + '")');
            $sidebar_img_container.fadeIn('fast');
        });
        }

        if ($full_page_background.length != 0 && $('.switch-sidebar-image input:checked').length != 0) {
        var new_image_full_page = $('.fixed-plugin li.active .img-holder').find('img').data('src');

        $full_page_background.fadeOut('fast', function() {
            $full_page_background.css('background-image', 'url("' + new_image_full_page + '")');
            $full_page_background.fadeIn('fast');
        });
        }

        if ($('.switch-sidebar-image input:checked').length == 0) {
        var new_image = $('.fixed-plugin li.active .img-holder').find("img").attr('src');
        var new_image_full_page = $('.fixed-plugin li.active .img-holder').find('img').data('src');

        $sidebar_img_container.css('background-image', 'url("' + new_image + '")');
        $full_page_background.css('background-image', 'url("' + new_image_full_page + '")');
        }

        if ($sidebar_responsive.length != 0) {
        $sidebar_responsive.css('background-image', 'url("' + new_image + '")');
        }
    });

    $('.switch-sidebar-image input').change(function() {
        $full_page_background = $('.full-page-background');

        $input = $(this);

        if ($input.is(':checked')) {
        if ($sidebar_img_container.length != 0) {
            $sidebar_img_container.fadeIn('fast');
            $sidebar.attr('data-image', '#');
        }

        if ($full_page_background.length != 0) {
            $full_page_background.fadeIn('fast');
            $full_page.attr('data-image', '#');
        }

        background_image = true;
        } else {
        if ($sidebar_img_container.length != 0) {
            $sidebar.removeAttr('data-image');
            $sidebar_img_container.fadeOut('fast');
        }

        if ($full_page_background.length != 0) {
            $full_page.removeAttr('data-image', '#');
            $full_page_background.fadeOut('fast');
        }

        background_image = false;
        }
    });

    $('.switch-sidebar-mini input').change(function() {
        $body = $('body');

        $input = $(this);

        if (md.misc.sidebar_mini_active == true) {
        $('body').removeClass('sidebar-mini');
        md.misc.sidebar_mini_active = false;

        $('.sidebar .sidebar-wrapper, .main-panel').perfectScrollbar();

        } else {

        $('.sidebar .sidebar-wrapper, .main-panel').perfectScrollbar('destroy');

        setTimeout(function() {
            $('body').addClass('sidebar-mini');

            md.misc.sidebar_mini_active = true;
        }, 300);
        }

        // we simulate the window Resize so the charts will get updated in realtime.
        var simulateWindowResize = setInterval(function() {
        window.dispatchEvent(new Event('resize'));
        }, 180);

        // we stop the simulation of Window Resize after the animations are completed
        setTimeout(function() {
        clearInterval(simulateWindowResize);
        }, 1000);

    });
    });
});