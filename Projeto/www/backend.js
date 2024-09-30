// IMPORTANT: notice the next request is scheduled only after the
//            previous request is fully processed either successfully
//	      or not.

function refreshSharedBoard() {
    var request = new XMLHttpRequest();
    var vBoard = document.getElementById("shared-board");

    request.onload = function () {
        vBoard.innerHTML = this.responseText;
        vBoard.style.color = "black";
        setTimeout(refreshSharedBoard, 2000);
    };

    request.ontimeout = function () {
        vBoard.innerHTML = "<div class=\"loading-message\"><p>Server timeout, still trying... </p></div>";
        vBoard.style.color = "red";
        setTimeout(refreshSharedBoard, 100);
    };

    request.onerror = function () {
        vBoard.innerHTML = "<div class=\"loading-message\"><p>No server reply, still trying... </p></div>";
        vBoard.style.color = "red";
        setTimeout(refreshSharedBoard, 5000);
    };

    request.open("GET", "/sharedBoard", true);
    request.timeout = 5000;
    request.send();
}

