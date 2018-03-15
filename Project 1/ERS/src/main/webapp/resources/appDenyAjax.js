function sendAjaxPost() {

	var xhr = new XMLHttpRequest();

	xhr.open("post", "http://localhost:8080/ERS/AppDenyServlet", true);
	
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