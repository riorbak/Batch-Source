function getAllEmp() {
    var xhr = new XMLHttpRequest();
    
    xhr.open("GET", "http://localhost:8080/ERS/ViewAllEmpServlet", true);
     
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4) {
            var resp = xhr.responseText;

            document.getElementById("viewAllEmp").innerHTML = resp;
        }
    };
    xhr.send();
};


window.onload = getAllEmp();