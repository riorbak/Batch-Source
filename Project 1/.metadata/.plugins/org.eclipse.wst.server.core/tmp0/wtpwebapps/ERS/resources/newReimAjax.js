function sendAjaxPost() {

	var xhr = new XMLHttpRequest();

	xhr.open("post", "http://localhost:8080/ERS/ReimReqServlet", true);
	
	xhr.onreadystatechange = function() {
		// console.log(this.readyState);
		if (this.readyState == 4 && this.status == 200) {
			var res = formReqJSON(this);
		}
	};

	xhr.send(res);
};

function formReqJSON(xhr) {
	
	
};

document.getElementById("reimButton").addEventListener("click", sendAjaxPost);