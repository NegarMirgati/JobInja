var xMLHttpRequest = new XMLHttpRequest();
var url='http://localhost:8080/projects';
xMLHttpRequest.open("GET", url);
xMLHttpRequest.send();
xMLHttpRequest.onreadystatechange = function () {
    if(xMLHttpRequest.readyState === 4 && xMLHttpRequest.status === 200) {
        console.log(xMLHttpRequest.responseText);
    }
}
