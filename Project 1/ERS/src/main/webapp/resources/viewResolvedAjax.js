function getResolvedRequests() {
    var xhr = new XMLHttpRequest();
    
    xhr.open("GET", "http://localhost:8080/ERS/ViewResolvedServlet", true);
     
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4) {
            var resp = xhr.responseText;

            document.getElementById("viewResolved").innerHTML = resp;
        }
    };

    xhr.send();
};


window.onload = getResolvedRequests();