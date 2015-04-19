/**
 * Created by Ian on 4/15/2015.
 */
window.onload = function () {
    getSession();
    getCartNum();
    getCartProduct();
    document.forms[0].reset();
    initialYearAndDay();
    getCustomerInfo();
}
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
                alert("Your cart is empty now.");
                window.location = "index.html";
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
            inputText.value = "1";
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
function getCustomerInfo() {
    var xmlhttp = getXMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            if (xmlhttp.responseText != "Invalid Session.") {
                var jo = JSON.parse(xmlhttp.responseText);
                var firstname = jo.product.firstname;
                var lastname = jo.product.lastname;
                var address = jo.product.address;
                var phone = jo.product.phone;

                document.getElementById("firstname").value = firstname;
                document.getElementById("lastname").value = lastname;
                document.getElementById("address").value = address;
                document.getElementById("phone").value = phone;
            } else {
                alert("Invalid session.");
                window.location = "signin.html";
            }
        }
    };
    xmlhttp.open("Post", "CustomerServlet", false);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send();
}

function placeOrder() {
    if (validate()) {
        /*var inputs = document.forms[0].elements;
         var selects = document.getElementsByTagName("select");
         var jsonObject = {};
         for (var i = 0; i < inputs.length; i++) {
         if (inputs[i].type === "text") {
         jsonObject[inputs[i].name] = inputs[i].value;
         }
         }
         for (var i = 0; i < selects.length; i++) {
         jsonObject[selects[i].name] = selects[i].value;
         }
         jsonObject["address"]=document.getElementById("address").value;
         var jsonString = JSON.stringify(jsonObject);*/

        var firstname = document.getElementById("firstname").value;
        var lastname = document.getElementById("lastname").value;
        var address = document.getElementById("address").value;
        var phone = document.getElementById("phone").value;
        var xmlhttp = getXMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                alert(xmlhttp.responseText);
                if (xmlhttp.responseText === "Session error.") {
                    window.location.replace("./index.html");
                    return;
                }
                window.location = "order.html";
            }
        };
        xmlhttp.open("Post", "SubmitOrder", false);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("firstname=" + firstname + "&lastname=" + lastname + "&address=" + address + "&phone=" + phone);
    }
}
function validate() {
    var inputs = document.forms[0].elements;
    var selects = document.getElementsByTagName("select");
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].type === "text") {
            if (inputs[i].value == "") {
                alert("Please fill in all the blanks.");
                return false;
            }
        }
    }
    return true;
}


function changeDay(originalDay, correctDay) {
    originalDay = parseInt(originalDay);
    if (originalDay < correctDay) {
        for (var i = 1; i <= (correctDay - originalDay); i++) {
            var optionNode = document.createElement("option");
            optionNode.value = originalDay + i;
            optionNode.innerHTML = originalDay + i;
            document.getElementById("day").appendChild(optionNode);
        }
    }
    else if (originalDay > correctDay) {
        for (var i = 1; i <= (originalDay - correctDay); i++) {
            document.getElementById("day").removeChild(document.getElementById("day").lastChild);
        }
    }
}
function calculateDay(year, month) {
    var result = 0;
    if (month == "January" || month == "March" || month == "May" || month == "July" || month == "August" || month == "October" || month == "December") {
        result = 31;
    }
    else if (month == "April" || month == "June" || month == "September" || month == "November") {
        result = 30;
    }
    else if (month == "February") {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            result = 29;
        }
        else {
            result = 28;
        }
    }
    else {
        alert("What month?");
        return -1;
    }
    return result;
}
function changeMonthOrYear(select) {
    var year = document.getElementById("year").value;
    var month = document.getElementById("month").value;
    var lastDay = document.getElementById("day").value;
    var correctDay = calculateDay(year, month);
    changeDay(lastDay, correctDay);
}
function initialYearAndDay() {
    var yearSelect = document.getElementById("year");
    var daysSelect = document.getElementById("day");
    for (var i = 2014; i >= 1900; i--) {
        var optionNode = document.createElement("option");
        optionNode.value = i;
        optionNode.innerHTML = i;
        yearSelect.appendChild(optionNode);
    }
    for (var i = 1; i <= 31; i++) {
        var optionNode = document.createElement("option");
        optionNode.value = i;
        optionNode.innerHTML = i;
        daysSelect.appendChild(optionNode);
    }
}
