/**
 * Created by Ian on 4/15/2015.
 */
window.onload = function () {
    getSession();
    getCartNum();
    getOrder();
}

function getOrder() {
    var xmlhttp = getXMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            alert(xmlhttp.responseText);
            var jo=JSON.parse(xmlhttp.responseText);
            var ul = document.getElementById("orderUl");

            for (var i = 0; i < jo.length; i++) {
                alert("yeah");
            }
        }
    };
    xmlhttp.open("Post", "GetOrder", true);
    xmlhttp.send();
}