var xMLHttpRequest = new XMLHttpRequest();
var url='http://localhost:8080/addSkill';
xMLHttpRequest.open("PUT", url);
xMLHttpRequest.send('userID=1&selectedSkill=C');
xMLHttpRequest.onreadystatechange = function () {
    if(xMLHttpRequest.readyState === 4 && xMLHttpRequest.status === 200) {
        console.log(xMLHttpRequest.responseText);
    }
}

