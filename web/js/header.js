function getXMLHttpRequest() {//from W3school, create XMLHttpRequest Object
    var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}
function getCartNum(){
    var xmlhttp = getXMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            document.getElementById("cartNum").innerHTML=xmlhttp.responseText;
        }
    };
    xmlhttp.open("Get", "GetCartNum", true);
    xmlhttp.send();
}