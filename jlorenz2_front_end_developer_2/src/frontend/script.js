GetFilteredResults = function() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("demo").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "url", true);
    xhttp.send();
}

PostFilters = function() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("Listing_Summaries_List").innerHTML = this.responseText;
        }
    };

    var xmlHttpBody = {

    };


    xhttp.open("POST", "/filteredResults", true);
    xhttp.send(JSON.stringify(xmlHttpBody));
}