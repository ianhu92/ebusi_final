window.onload = function () {
    getProduct();
    getCartNum();
}
function getProduct() {
    var xmlhttp = getXMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var jo = JSON.parse(xmlhttp.responseText);
            var productid;
            var productname;
            var img;
            var price;
            var ul = document.getElementById("productList");
            for (var i = 0; i < jo.product.length; i++) {
                productid = jo.product[i].productid;
                productname = jo.product[i].productname;
                img = jo.product[i].img;
                price = jo.product[i].price;

                var li = document.createElement("li");
                ul.appendChild(li);
                li.className = "product underlineProduct";

                var div = document.createElement("div");
                li.appendChild(div);
                div.className = "productContainer";

                var imgE = document.createElement("img");
                div.appendChild(imgE);
                imgE.alt = productname;
                imgE.className = "thumbnail";
                imgE.src = img;

                var p = document.createElement("p");
                div.appendChild(p);
                p.innerHTML = productname;

                p = document.createElement("p");
                div.appendChild(p);
                p.innerHTML = "$" + price;

                var a = document.createElement("a");
                div.appendChild(a);
                a.id = "addToCart" + productid;
                a.className = "addToCart";
                a.innerHTML = "Add to Cart";
                a.onclick = function () {
                    addToCart(this);
                };
            }
        }
    };
    xmlhttp.open("Get", "GetProduct", true);
    xmlhttp.send();
}
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