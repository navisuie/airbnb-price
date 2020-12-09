// --== CS400 File Header Information ==--
// Name: Alex Green
// Email: ajgreen4@wisc.edu
// Team: BC
// Role: Front End Developer 1
// TA: Bri Cochran
// Lecturer: Florian Heimerl
// Notes to TA:
// SendPostAJAXRequest, PostSelect, and HandlePostSelect written
// by Jake Lorenz and remastered to work with my front end.

$(document).ready(function () {

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
     * Loads the listings that match the specified filters
     */
    function PostSelect() {
        var url = "/AirBnB/filteredlistings";
        var successCallback = HandlePostSelectResponse;

        // Get the values for each of the filters
        var cityFilterFlag = true;
        var citySelection = document.getElementById("city").value;

        if (citySelection == "NULL") {
            cityFilterFlag = false;
        }

        // Generate a JS Object representing the JSON request
        // this front end only takes use of the citySelection field
        var JSObject = {
            quantity: 0,
            city: {
                filterFlag: cityFilterFlag,
                citySelection: citySelection
            },
            price: {
                filterFlag: false,
                priceRangeStart: -1,
                priceRangeEnd: -1
            },
            reviews: {
                filterFlag: false,
                reviewsRangeStart: -1,
                reviewsRangeEnd: -1
            }
        };

        // Turn the generated JS Object into a JSON String to pass to server
        var JSONString = JSON.stringify(JSObject);

        // Execute the HTTP Call
        SendPostAJAXRequest(url, JSONString, successCallback);
    }

    /**
     * Parses the response containing city listings into the summary table
     * @param {JSONObject} responseText The response from the request
     */
    function HandlePostSelectResponse(responseText) {
        var numListings = responseText.count;
        var html = '';
        html += '<table>\r\n';
        html += '<tr>\r\n';
        html += '<th>Name</th>\r\n';
        html += '<th>Type</th>\r\n';
        html += '<th>City</th>\r\n';
        html += '<th>Price</th>\r\n';
        html += '<th>Minimum Nights</th>\r\n';
        html += '<th>Number of Reviews</th>\r\n';
        html += '</tr>\r\n';

        for (var i = 0; i < numListings; i++) {
            var curListing = responseText.listings[i];
            html += '<tr>\r\n';
            html += '<td>' + curListing.name + '</td>\r\n';
            html += '<td>' + curListing.type + '</td>\r\n';
            html += '<td>' + curListing.city + '</td>\r\n';
            html += '<td>' + curListing.price + '</td>\r\n';
            html += '<td>' + curListing.minNights + '</td>\r\n';
            html += '<td>' + curListing.reviews + '</td>\r\n';
            html += '</tr>\r\n';
        }

        html += '</table>';

        document.getElementById("table").innerHTML = html;

    }

});

/**
 * Selects the city the user chose based on the state and confirms
 * with the user.
 */
function chooseCity() {
    document.getElementById("other").style.display = "block";
    let element = document.getElementById("city");
    var state = document.getElementById("state").value;
    if (state == "NULL"){ // if no state is chosen, hide all dropboxes to go back to default start of page
        document.getElementById("MA").style.display = "none";
        document.getElementById("CA").style.display = "none";
        document.getElementById("other").style.display = "none";
        return;
    }
    if (state == "CA") { 
        var city = document.getElementById("calCity").value;
        element.value = city;
        return;
    }
    if (state == "MA") {
        var city = document.getElementById("massCity").value;
        element.value = city;
        return;
    }
}

/**
 * Shows the user the available cities based on the state they chose,
 * or selects the city if there is only one available city for that state.
 */
function showCities() {
    let selection = document.getElementById("state");
    var state = selection.value;
    if (state == "NULL") { // if no state is selected, return
        document.getElementById("MA").style.display = "none";
        document.getElementById("CA").style.display = "none";
        document.getElementById("other").style.display = "none";
        return;
    }
    if (state == "CA") { // if Cal., display dropbox for Cal. cities
        document.getElementById(state).style.display = "block";
        document.getElementById("MA").style.display = "none";
        document.getElementById("other").style.display = "none";
        return;
    }
    else if (state == "MA") { // if Mass., display dropbox for Mass. cities
        document.getElementById(state).style.display = "block";
        document.getElementById("CA").style.display = "none";
        document.getElementById("other").style.display = "none";
        return;
    }
    else { // otherwise hide all other dropboxes and display form with correct city
        document.getElementById("MA").style.display = "none";
        document.getElementById("CA").style.display = "none";
        document.getElementById("other").style.display = "block";

        let element = document.getElementById("city");

        if (state == "CO") {
            element.value = "DV";
        }
        else if (state == "FL") {
            element.value = "BR";
        }
        else if (state == "HI") {
            element.value = "HW";
        }
        else if (state == "IL") {
            element.value = "CH";
        }
        else if (state == "LA") {
            element.value = "NO";
        }
        else if (state == "MI") {
            element.value = "TC";
        }
        else if (state == "NV") {
            element.value = "CC";
        }
        else if (state == "NJ") {
            element.value = "JC";
        }
        else if (state == "NY") {
            element.value = "NYC";
        }
        else if (state == "NC") {
            element.value = "AV";
        }
        else if (state == "OH") {
            element.value = "CO";
        }
        else if (state == "OR") {
            element.value = "NV";
        }
        else if (state == "RI") {
            element.value = "RI";
        }
        else if (state == "TN") {
            element.value = "NV";
        }
        else if (state == "TX") {
            element.value = "AU";
        }
        else if (state == "WA") {
            element.value = "SA";
        }
    }
}
