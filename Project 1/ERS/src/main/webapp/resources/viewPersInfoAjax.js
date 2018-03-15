function getPersInfo() {
    var xhr = new XMLHttpRequest();
    
    xhr.open("GET", "http://localhost:8080/ERS/UserInfoServlet", true);
     
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4) {
            var resp = xhr.responseText;

            document.getElementById("viewPersInfo").innerHTML = resp;
        }
    };
    
    xhr.send();
};


window.onload = getPersInfo();