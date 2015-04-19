window.onload = function () {
    getSession();
    getCartNum();
    getCartProduct();
};
function getCartProduct() {
    var xmlhttp = getXMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            //alert(xmlhttp.responseText);
            var jo = JSON.parse(xmlhttp.responseText);
            var productid;
            var productname;
            var img;
            var price;
            var inventory;
            var quantity;
            var ul = document.getElementById("productList");
            ul.innerHTML = "";
            var subtotal = 0;

            if (jo.product != undefined) {
                for (var i = 0; i < jo.product.length; i++) {
                    productid = jo.product[i].productid;
                    productname = jo.product[i].productname;
                    img = jo.product[i].img;
                    price = jo.product[i].price;
                    inventory = jo.product[i].inventory;
                    quantity = jo.product[i].quantity;
                    if (inventory > 0) {
                        subtotal += price * quantity;
                    }


                    var li = document.createElement("li");
                    ul.appendChild(li);
                    if (i === 0) {
                        li.className = "product";
                    } else {
                        li.className = "product secondProduct";
                    }


                    var div = document.createElement("div");
                    li.appendChild(div);
                    div.className = "thumbnailDiv";

                    var imgE = document.createElement("img");
                    div.appendChild(imgE);
                    imgE.alt = productname;
                    imgE.className = "thumbnail";
                    imgE.src = img;

                    div = document.createElement("div");
                    li.appendChild(div);
                    div.className = "productDetail";

                    var p = document.createElement("p");
                    div.appendChild(p);
                    p.innerHTML = productname;

                    p = document.createElement("p");
                    div.appendChild(p);
                    p.id = "inventory" + productid;
                    p.className = "inventory";
                    var span = document.createElement("span");
                    p.appendChild(span);
                    span.innerHTML = inventory;
                    if (parseInt(inventory) > 1) {
                        p.innerHTML += " items left.";
                    } else if (parseInt(inventory) === 1) {
                        p.innerHTML += " item left.";
                    } else {
                        p.innerHTML = "Out of Stock.";
                    }

                    var a = document.createElement("a");
                    div.appendChild(a);
                    a.id = "deleteProduct" + productid;
                    a.className = "deleteProduct";
                    a.innerHTML = "Delete";
                    a.onclick = function () {
                        deleteProduct(this);
                    };

                    div = document.createElement("div");
                    li.appendChild(div);
                    div.className = "price";

                    p = document.createElement("p");
                    div.appendChild(p);
                    p.innerHTML = "Price";

                    p = document.createElement("p");
                    div.appendChild(p);
                    p.className = "priceNum";
                    p.innerHTML = "$" + price;

                    div = document.createElement("div");
                    li.appendChild(div);
                    div.className = "quantity";

                    p = document.createElement("p");
                    div.appendChild(p);
                    p.innerHTML = "Quantity";

                    var input = document.createElement("input");
                    div.appendChild(input);
                    input.type = "button";
                    input.value = "-";
                    input.id = "m" + productid;
                    input.onclick = function () {
                        changeQuantity(this);
                    };
                    if (parseInt(inventory) === 0) {
                        input.disabled = "disabled";
                    }

                    input = document.createElement("input");
                    div.appendChild(input);
                    input.type = "text";
                    input.value = quantity;
                    input.id = "quantityText" + productid;
                    input.onchange = function () {
                        checkQuantity(this);
                    };
                    if (parseInt(inventory) === 0) {
                        input.disabled = "disabled";
                        input.value = "0";
                    }

                    input = document.createElement("input");
                    div.appendChild(input);
                    input.type = "button";
                    input.value = "+";
                    input.id = "p" + productid;
                    input.onclick = function () {
                        changeQuantity(this);
                    };
                    if (parseInt(inventory) === 0) {
                        input.disabled = "disabled";
                    }
                }
            } else {
                var h2 = document.createElement("h2");
                ul.appendChild(h2);
                h2.innerHTML = "Your cart is empty now.";
            }
            document.getElementById("subtotalNum").innerHTML = subtotal.toFixed(2);
        }
    };
    xmlhttp.open("Post", "GetCartProduct", true);
    xmlhttp.send();
}
function deleteProduct(a) {
    var productid = a.id.substring(13);
    if (productid !== "" && confirm("Are you sure to delete this product from shopping cart?")) {
        var xmlhttp = getXMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                getCartNum();
                getCartProduct();
                alert(xmlhttp.responseText);
            }
        };
        xmlhttp.open("Post", "DeleteProduct", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("productid=" + productid);
    }
    else {
        if (productid === "") {
            alert("Wrong invocation.");
        }
    }
}
function checkQuantity(input) {
    var quantity = parseInt(input.value);
    var productid = input.id.substring(12);
    if (isNaN(input.value)) {
        input.value = "1";
        doChangeQuantity(productid, 1);
        alert("Please type in only digits.");
    } else {
        var inventory = parseInt(document.getElementById("inventory" + productid).firstChild.innerHTML);
        if (quantity < 1 || quantity > inventory) {
            input.value = "1";
            doChangeQuantity(productid, 1);
            alert("Quantity cannot be greater than inventory or smaller than one.");
        } else {
            doChangeQuantity(productid, quantity);
        }
    }
}
function changeQuantity(btn) {
    var operation = btn.value;
    var productid = btn.id.substring(1);
    var inputText = document.getElementById("quantityText" + productid);
    var quantity = parseInt(inputText.value);
    if (operation === "-") {
        if (inputText.value === "1") {
            alert("Quantity must be greater than zero.");
        } else {
            doChangeQuantity(productid, (quantity - 1));
        }
    } else if (operation === "+") {
        var inventory = parseInt(document.getElementById("inventory" + productid).firstChild.innerHTML);
        if (inputText.value >= inventory) {
            alert("Quantity cannot be greater than inventory.");
            inputText.value="1";
            doChangeQuantity(productid, 1);
        } else {
            doChangeQuantity(productid, (quantity + 1));
        }
    }
}
function doChangeQuantity(productid, quantity) {
    var xmlhttp = getXMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            if (xmlhttp.responseText === "Successfully changed quantity.") {
                getCartProduct();
            } else {
                alert(xmlhttp.responseText);
            }
        }
    };
    xmlhttp.open("Post", "ChangeQuantity", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("productid=" + productid + "&quantity=" + quantity);
}