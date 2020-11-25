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



    

    // --------- End Business Logic HTTP Requests -----------
    // ------------------------------------------------------

    // ----------- Callback Handler Functions ---------------
    // ------------------------------------------------------








    // --------- End Callback Handler Functions -------------
    // ------------------------------------------------------

    // ---------------------- Main --------------------------
    // ------------------------------------------------------

});





// GetFilteredResults = function() {
//     var xhttp = new XMLHttpRequest();
//     xhttp.onreadystatechange = function() {
//         if (this.readyState == 4 && this.status == 200) {
//             document.getElementById("demo").innerHTML = this.responseText;
//         }
//     };
//     xhttp.open("GET", "url", true);
//     xhttp.send();
// }

// PostFilters = function() {
//     var xhttp = new XMLHttpRequest();
//     xhttp.onreadystatechange = function() {
//         if (this.readyState == 4 && this.status == 200) {
//             document.getElementById("Listing_Summaries_List").innerHTML = this.responseText;
//         }
//     };

//     var xmlHttpBody = {

//     };


//     xhttp.open("POST", "/filteredResults", true);
//     xhttp.send(JSON.stringify(xmlHttpBody));
// }
