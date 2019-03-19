var xMLHttpRequest = new XMLHttpRequest();
var url='http://localhost:8080/user/addSkill';
var params='id=1&name=Photoshop';
xMLHttpRequest.open("PUT", url+"?"+params, true);
xMLHttpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
xMLHttpRequest.onreadystatechange = function () {
    if(xMLHttpRequest.readyState === 4 && xMLHttpRequest.status === 200) {
        console.log(xMLHttpRequest.responseText);
    }
}
xMLHttpRequest.send(null);
