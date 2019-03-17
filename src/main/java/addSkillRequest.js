var xMLHttpRequest = new XMLHttpRequest();
var url='http://localhost:8080/addSkill';
xMLHttpRequest.open("POST", url);
xMLHttpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
xMLHttpRequest.send("userID=1&selectedSkill=C");
xMLHttpRequest.onreadystatechange = function () {
    if(xMLHttpRequest.readyState === 4 && xMLHttpRequest.status === 200) {
        console.log(xMLHttpRequest.responseText);
    }
}

