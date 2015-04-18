/**
 * Created by Ian on 4/17/2015.
 */
function addToCart(a) {
    var productid = a.id.substring(9);
    if (productid !== "") {
        var xmlhttp = getXMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                getCartNum();
                alert(xmlhttp.responseText);
            }
        };
        xmlhttp.open("Post", "AddToCart", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("productid=" + productid);
    }
    else {
        alert("Wrong invocation.");
    }
}