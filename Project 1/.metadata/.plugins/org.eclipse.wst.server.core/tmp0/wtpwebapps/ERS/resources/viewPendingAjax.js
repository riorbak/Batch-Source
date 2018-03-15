function getPendingRequests() {
    var xhr = new XMLHttpRequest();
    
    xhr.open("GET", "http://localhost:8080/ERS/ViewPendServlet", true);
     
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4) {
            var resp = xhr.responseText;

            document.getElementById("viewPend").innerHTML = resp;
        }
    };

    
    xhr.send();
};


window.onload = getPendingRequests();