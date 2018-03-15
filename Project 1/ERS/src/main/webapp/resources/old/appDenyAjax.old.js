function appOrDeny(obj) {
    var xhr = new XMLHttpRequest();
    
    xhr.open("POST", "http://localhost:8080/ERS/AppDenyServlet", true);
    
    xhr.setRequestHeader('Content-Type','application/json');

    xhr.send(obj);
};

function updLst() {
    
    var sel = document.getElementById("rID");

    if (sel.childElementCount == 0)
    {
    var idList = document.getElementsByClassName("reqID");

        for (i=0; i < idList.length; i++)
        {
            sel.add(opt);
        }
    }
};

 function subAppDeny() {

    console.log("SubAppDeny() called");
    
    var r = document.getElementById("rID");
    var rIDCh = r.value;
    
    var ad = document.getElementById("appDen");
    var adCh = ad.options[ad.selectedIndex].value;


    var reimbursement = {
        "author": null,
        "authID": null,
        "id": rIDCh,
        "amount": null,
        "description": null,
        "receipt": null,
        "statusID": adCh,
        "type": null,
        "typeID": null,
        "submitted": null,
        "resolved": null
    }

    var myJSON = JSON.stringify(reimbursement);

    appOrDeny(myJSON);

};

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


window.onload = function(){
    getPendingRequests();
    document.getElementById("updateList").addEventListener("onclick", updLst(), false);
    document.getElementById("subChg").addEventListener("onclick", subAppDeny(), false);
}