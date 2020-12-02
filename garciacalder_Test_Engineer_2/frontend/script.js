// --== CS400 File Header Information ==--
// Name: Jacob Lorenz
// Email: jlorenz2@wisc.edu
// Team: BC
// Role: Front End Developer 2
// TA: Bri Cochran
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

$(document).ready(function() {

    // ------------- General AJAX Handlers ------------------
    // ------------------------------------------------------

    /**
     * A generic AJAX handler for all outgoing POST requests
     * @param {String} pathUrl The url to send the request to
     * @param {String} bodyData The stringified JSON request body
     * @param {Function} successCallback The callback function to call on a successful response
     */
    function SendPostAJAXRequest(pathUrl, bodyData, successCallback) {
        
        $.ajax({
            type: 'post',
            url: pathUrl,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            data: bodyData,
            success: successCallback
        });

    }

    /**
     * A generic AJAX handler for all outgoing GET requests
     * @param {String} pathUrl The url to send the request to
     * @param {Function} successCallback The callback function to call on a successful response
     */
    function SendGetAJAXRequest(pathUrl, successCallback) {
        $.ajax({
            type: 'get',
            url: pathUrl,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: successCallback
        });
    }

    // ----------- End General AJAX Handlers ----------------
    // ------------------------------------------------------

    // --------- Business Logic HTTP Requests ---------------
    // ------------------------------------------------------

    /**
     * Retrieves the list of cities for which there exists at least one property
     */
    function LoadCityList() {
        var url = "/getcitylist";
        var successCallback = HandleLoadCityListResponse;

        // Generate a JS Object representing the JSON request
        var messageBodyJSObj = {
        };

        // Turn the generated JS Object into a JSON String to pass to server
        var messageBodyString = JSON.stringify(messageBodyJSObj);

        // Execute the HTTP Call
        SendPostAJAXRequest(url, messageBodyString, successCallback);
    }

    /**
     * Loads the listings to populate the table without filters
     */
    function LoadInitialListings() {
        var url = "/loadinitiallistings";
        var successCallback = HandleLoadInitialListingsResponse;

        // Generate a JS Object representing the JSON request
        var messageBodyJSObj = {
            quantity: 10
        };

        // Turn the generated JS Object into a JSON String to pass to server
        var messageBodyString = JSON.stringify(messageBodyJSObj);

        // Execute the HTTP Call
        SendPostAJAXRequest(url, messageBodyString, successCallback);
    }

    /**
     * Loads the listings that match the specified filters
     */
    function PostFilters() {
        var url = "/postfilters";
        var successCallback = HandlePostFiltersResponse;

        var maxInt = 2147483647;

        // Get the values for each of the filters
        var cityFilterFlag = true;
        var citySelection = document.getElementById("City_Options_Select").value;
        
        var priceFilterFlag = true;
        var priceRangeStart = -1;
        var priceRangeEnd = -1;
        var priceRangeSelection = document.getElementById("Price_Options_Select").value;
        
        var reviewsFilterFlag = true;
        var reviewsRangeStart = -1;
        var reviewsRangeEnd = -1;
        var numReviewsSelection = document.getElementById("Reviews_Options_Select").value;

        if (citySelection == "none_selected") {
            cityFilterFlag = false;
        }

        if (priceRangeSelection == "none_selected") {
            priceFilterFlag = false;
        } else {
            var priceRange = priceRangeSelection.split("-");
            priceRangeStart = priceRange[0];
            if (priceRange[1] == "+") {
                priceRangeEnd = maxInt
            } else {
                priceRangeEnd = priceRange[1];
            }
        }

        if (numReviewsSelection == "none_selected") {
            reviewsFilterFlag = false;
        } else {
            var reviewsRange = numReviewsSelection.split("-");
            reviewsRangeStart = reviewsRange[0];
            if (reviewsRange[1] == "+") {
                reviewsRangeEnd = maxInt
            } else {
                reviewsRangeEnd = reviewsRange[1];
            }
        }

        // Generate a JS Object representing the JSON request
        var messageBodyJSObj = {
            city: {
                filterFlag: cityFilterFlag,
                citySelection: citySelection
            },
            price: {
                filterFlag: priceFilterFlag,
                priceRangeStart: priceRangeStart,
                priceRangeEnd: priceRangeEnd
            },
            reviews: {
                filterFlag: reviewsFilterFlag,
                reviewsRangeStart: reviewsRangeStart,
                reviewsRangeEnd: reviewsRangeEnd
            }
        };

        // console.log("City Selected: " + cityFilterFlag);
        // console.log("City: " + citySelection);
        // console.log("Price Selected: " + priceFilterFlag);
        // console.log("Low Price: " + priceRangeStart);
        // console.log("High Price: " + priceRangeEnd);
        // console.log("Reviews Selected: " + reviewsFilterFlag);
        // console.log("Low Reviews: " + reviewsRangeStart);
        // console.log("High Reviews: " + reviewsRangeEnd);

        // Turn the generated JS Object into a JSON String to pass to server
        var messageBodyString = JSON.stringify(messageBodyJSObj);

        // Execute the HTTP Call
        SendPostAJAXRequest(url, messageBodyString, successCallback);
    }

    

    // --------- End Business Logic HTTP Requests -----------
    // ------------------------------------------------------

    // ----------- Callback Handler Functions ---------------
    // ------------------------------------------------------

    /**
     * Creates a select element with a list of all available cities
     * @param {JSONObject} responseText The response from the request
     */
    function HandleLoadCityListResponse(responseText) {
        var cityCount = responseText.count;

        var html = '';
        for (var i = 0; i < cityCount; i++) {
            html += '<option value="' + responseText.cityNames[i] + '">' + responseText.cityNames[i] + '</option>\r\n';
        }

        $('#City_Options_Select option:first').after(html);
    }

    /**
     * Parses the response containing city listings into the summary table
     * @param {JSONObject} responseText The response from the request
     */
    function HandleLoadInitialListingsResponse(responseText) {

        var numListings = responseText.count;
        var html = '';

        for (var i = 0; i < numListings; i++) {
            var curListing = responseText.listings[i];
            html += '<tr class="removeRow">\r\n';
            html += '<td>' + curListing.name + '</td>\r\n';
            html += '<td>' + curListing.type + '</td>\r\n';
            html += '<td>' + curListing.city + '</td>\r\n';
            html += '<td>' + curListing.state + '</td>\r\n';
            html += '<td>' + curListing.price + '</td>\r\n';
            html += '<td>' + curListing.minNights + '</td>\r\n';
            html += '<td>' + curListing.reviews + '</td>\r\n';
            html += '</tr>\r\n';
        }

        $('.removeRow').remove();
        $('#listings_table tr:last').before(html);
        $("#listings_table td").css('border-bottom', "1px solid white");
        $("#listings_table td").css('color', "white");
    }

    /**
     * Parses the response containing city listings into the summary table
     * @param {JSONObject} responseText The response from the request
     */
    function HandlePostFiltersResponse(responseText) {
        var numListings = responseText.count;
        var html = '';

        for (var i = 0; i < numListings; i++) {
            var curListing = responseText.listings[i];
            html += '<tr class="removeRow">\r\n';
            html += '<td>' + curListing.name + '</td>\r\n';
            html += '<td>' + curListing.type + '</td>\r\n';
            html += '<td>' + curListing.city + '</td>\r\n';
            html += '<td>' + curListing.state + '</td>\r\n';
            html += '<td>' + curListing.price + '</td>\r\n';
            html += '<td>' + curListing.minNights + '</td>\r\n';
            html += '<td>' + curListing.reviews + '</td>\r\n';
            html += '</tr>\r\n';
        }

        $('.removeRow').remove();
        $('#listings_table tr:last').before(html);
        $("#listings_table td").css('border-bottom', "1px solid white");
        $("#listings_table td").css('color', "white");
    }


    // --------- End Callback Handler Functions -------------
    // ------------------------------------------------------

    // ---------------- Element Handlers --------------------
    // ------------------------------------------------------

    $("#FilterSubmitButton").click(function() {
        PostFilters();
    });


    // -------------- End Element Handlers ------------------
    // ------------------------------------------------------

    // ----------------- Misc Functions ---------------------
    // ------------------------------------------------------

    /**
     * 
     */
    function LoadInitialContent() {
        // Uncomment these when fully implemented
        // LoadCityList();
        // LoadInitialListings();
    }

    // --------------- End Misc Functions -------------------
    // ------------------------------------------------------

    // ---------------------- Main --------------------------
    // ------------------------------------------------------

    LoadInitialContent();

    // -------------------- End Main ------------------------
    // ------------------------------------------------------
});

// RequestRemplate = function() {
//     var url = "/request_URL";
//     var successCallback = CallbackHandlerFunction;

//     // Generate a JS Object representing the JSON request
//     var messageBodyJSObj = {

//     };

//     // Turn the generated JS Object into a JSON String to pass to server
//     var messageBodyString = JSON.stringify(messageBodyJSObj);

//     // Execute the HTTP Call
//     SendPostAJAXRequest(url, messageBodyString, successCallback);
// }

// $().click(function() {

// });