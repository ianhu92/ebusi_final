var username = null;
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
function getCartNum() {
    var xmlhttp = getXMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            alert(xmlhttp.responseText);
            document.getElementById("cartNum").innerHTML = xmlhttp.responseText;
        }
    };
    xmlhttp.open("Post", "GetCardNum", false);
    xmlhttp.send();
}
function getSession() {
    var xmlhttp = getXMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {

            username = xmlhttp.responseText;
            if (username != "Invalid Session") {
                document.getElementById("accountName").innerHTML = "Hello " + username;
                document.getElementById("accountName").setAttribute("href", "order.html");
                var account = document.getElementById("account");
                var li = document.createElement("li");
                li.className = "accountLi";
                account.appendChild(li);

                var a = document.createElement("a");
                a.className = "headerLink";
                a.onclick = function () {
                    Logout();
                };
                a.innerHTML = "Log Out";
                li.appendChild(a);
            }
        }
    };
    xmlhttp.open("Post", "GetSession", false);
    xmlhttp.send();
}

function Logout() {
    var xmlhttp = getXMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            alert(xmlhttp.responseText);
        }
    };
    xmlhttp.open("Post", "Logout", true);
    xmlhttp.send();
}