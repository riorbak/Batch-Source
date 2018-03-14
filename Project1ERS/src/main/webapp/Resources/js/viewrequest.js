function loadReqs(array) {

    var existingrows = document.getElementsByName("deleteable");
    while (existingrows[0]) existingrows[0].parentNode.removeChild(existingrows[0])

    var listOfReqs = array;

    for (let i = 0; i < array.length; i++) {
        var newrow = document.createElement("tr");
        newrow.setAttribute("id", "request0" + i);
        newrow.setAttribute("name", "deleteable");
        newrow.setAttribute("class", "row100 body");
        //document.getElementById("headrow").appendSibling(newrow)
        document.getElementById("reqlist").appendChild(newrow);

        var col1 = document.createElement("td");
        var col2 = document.createElement("td");
        var col3 = document.createElement("td");
        var col4 = document.createElement("td");
        var col5 = document.createElement("td");
        var col6 = document.createElement("td");
        var col7 = document.createElement("td");
        var col8 = document.createElement("td");
        var col9 = document.createElement("td");
        var col10 = document.createElement("td");

        col1.textContent = array[i].r_id;
         var num = array[i].r_amount;
         col2.textContent = "$" + parseFloat(num).toFixed(2);
        col3.textContent = array[i].r_description;
        //col4.textContent = array[i].r_receipt;

        // image things
        col4.innerHTML = '<button type="button" class="btn btn-custom" data-toggle="modal" data-target="#imgmodal'+i+'">'+
                        'Click for Receipt' +
                        '</button>' +
                        '<div class="modal fade" id="imgmodal'+ i +'" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">' +
                        '<div class="modal-dialog" role="document">' +
                        '<div class="modal-content">' +
                        '<div class="modal-header orange-title-custom">' +
                        '<h5 class="modal-title" id="exampleModalLabel">Receipt Image</h5>' +
                        '<button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
                        '<span aria-hidden="true">&times;</span>' +
                        '</button>' +
                        '</div>' +
                        '<div class="modal-body">' +
                        '<img src="data:img/png;base64,' + array[i].r_receipt +'">' +
                        '</div>' +
                        '<div class="modal-footer">' +
                        '<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>' +
                        '</div></div></div></div>'

        col5.textContent = array[i].r_submitted;
        col6.textContent = array[i].u_author;
        col7.textContent = array[i].r_resolved;
        col8.textContent = array[i].u_resolver;
        col9.textContent = array[i].rt_type;
        col10.textContent = array[i].rt_status;
        document.getElementById("request0" + i).appendChild(col1);
        document.getElementById("request0" + i).appendChild(col2);
        document.getElementById("request0" + i).appendChild(col3);
        document.getElementById("request0" + i).appendChild(col4);
        document.getElementById("request0" + i).appendChild(col5);
        document.getElementById("request0" + i).appendChild(col6);
        document.getElementById("request0" + i).appendChild(col7);
        document.getElementById("request0" + i).appendChild(col8);
        document.getElementById("request0" + i).appendChild(col9);
        document.getElementById("request0" + i).appendChild(col10);
    }//end for

    $(document).ready( function () {
        $('#ajaxTable').DataTable();
    } );

}

function getAllRequestArray() {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var list = JSON.parse(xhr.responseText);
            loadReqs(list);
        }
    }

    xhr.open("GET", "http://localhost:8080/Project1ERS/viewreqservlet", true);
    xhr.send();
}

function getPendRequestArray() {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var list = JSON.parse(xhr.responseText);
            loadReqs(list);
        }
    }

    xhr.open("GET", "http://localhost:8080/Project1ERS/getpendingservlet", true);
    xhr.send();
}
// based on selected call different servlet, change getpending servlet to have the same json format as view all except with nulls in the null spaces

window.onload = function () {
    getAllRequestArray();
}


var radioButtons = document.getElementsByName("viewtype");
console.log(radioButtons);
var buttons = radioButtons;

for (var i = 0; i < buttons.length; i++) {
    buttons[i].onchange = function () {
        for (var i = 0; i < buttons.length; i++) {
            if (buttons[i].checked == true) {
                if (buttons[i].value == 0) {
                    getPendRequestArray();
                } else if (buttons[i].value == 3) {
                    getAllRequestArray();
                } 
            }
        }

    }
}