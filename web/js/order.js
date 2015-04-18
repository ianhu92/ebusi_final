/**
 * Created by Ian on 4/15/2015.
 */
window.onload= function () {
    getSession();
    getOrder();
}

function getOrder() {
    var xmlhttp = getXMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            alert(xmlhttp.responseText);
        }
    };
    xmlhttp.open("Post", "GetOrder", true);
    xmlhttp.send();
}