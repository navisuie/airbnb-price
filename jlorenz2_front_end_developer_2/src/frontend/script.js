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

    function LoadInitialData() {
        var url = "/loadInitialProperties";
        var successCallback = HandleInitialDataLoad;

        // Generate a JS Object representing the JSON request
        var messageBodyJSObj = {
            number: 10
        };

        // Turn the generated JS Object into a JSON String to pass to server
        var messageBodyString = JSON.stringify(messageBodyJSObj);

        // Execute the HTTP Call
        SendPostAJAXRequest(url, messageBodyString, successCallback);
    }

    function PostFilters() {
        var url = "/postfilters";
        var successCallback = HandlePostFiltersResponse;

        // Generate a JS Object representing the JSON request
        var messageBodyJSObj = {

        };

        // Turn the generated JS Object into a JSON String to pass to server
        var messageBodyString = JSON.stringify(messageBodyJSObj);

        // Execute the HTTP Call
        SendPostAJAXRequest(url, messageBodyString, successCallback);
    }

    

    // --------- End Business Logic HTTP Requests -----------
    // ------------------------------------------------------

    // ----------- Callback Handler Functions ---------------
    // ------------------------------------------------------

    function HandleInitialDataLoad(responseText) {

    }

    function HandlePostFiltersResponse(responseText) {
        
    }


    // --------- End Callback Handler Functions -------------
    // ------------------------------------------------------

    // -------------------- Handlers ------------------------
    // ------------------------------------------------------

    $("#FilterSubmitButton").click(function() {
        PostFilters();
    });


    // ------------------ End Handlers ----------------------
    // ------------------------------------------------------

    // ---------------------- Main --------------------------
    // ------------------------------------------------------

    LoadInitialData();



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