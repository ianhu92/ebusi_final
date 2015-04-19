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

            var jo = JSON.parse(xmlhttp.responseText);
            var ul = document.getElementById("orderUl");

            for (var i = 0; i < jo.order.length; i++) {
                var order = jo.order[i];
                var datetime = order.datetime;
                var shippingaddr = order.shippingaddr;

                if(typeof datetime!="undefined"&&typeof shippingaddr!="undefined"){
                    var li = document.createElement("li");
                    ul.appendChild(li);
                    li.className = "orderLi";

                    var orderInfo = document.createElement("div");
                    li.appendChild(orderInfo);
                    orderInfo.className = "orderInfo";

                    var div = document.createElement("div");
                    orderInfo.appendChild(div);
                    var p = document.createElement("p");
                    div.appendChild(p);
                    p.innerHTML = "Order Placed";
                    p = document.createElement("p");
                    div.appendChild(p);
                    p.innerHTML = datetime;

                    div = document.createElement("div");
                    orderInfo.appendChild(div);
                    var p = document.createElement("p");
                    div.appendChild(p);
                    p.innerHTML = "Total";
                    p = document.createElement("p");
                    div.appendChild(p);
                    p.innerHTML = "total";
                    p.id = "total" + i;

                    var div = document.createElement("div");
                    orderInfo.appendChild(div);
                    var p = document.createElement("p");
                    div.appendChild(p);
                    p.innerHTML = "Ship to";
                    p = document.createElement("p");
                    div.appendChild(p);
                    p.innerHTML = shippingaddr;

                    var ul2 = document.createElement("ul");
                    li.appendChild(ul2);
                    ul2.className = "productList";

                    var total = 0;
                    for (var j = 0; j < order.product.length; j++) {
                        var img = order.product[j].img;
                        var quantity = order.product[j].quantity;
                        var pname = order.product[j].pname;
                        var price = order.product[j].price;
                        total += price * quantity;

                        var li2 = document.createElement("li");
                        ul2.appendChild(li2);
                        li2.className = "product";

                        div = document.createElement("div");
                        li2.appendChild(div);
                        div.className = "thumbnailDiv";

                        var imgE = document.createElement("img");
                        div.appendChild(imgE);
                        imgE.className = "thumbnail";
                        imgE.alt = pname;
                        imgE.src = img;

                        div = document.createElement("div");
                        li2.appendChild(div);
                        div.className = "productDetail";

                        p = document.createElement("p");
                        div.appendChild(p);
                        p.innerHTML = pname;

                        div = document.createElement("div");
                        li2.appendChild(div);
                        div.className = "price";

                        p = document.createElement("p");
                        div.appendChild(p);
                        p.innerHTML = "Price";

                        p = document.createElement("p");
                        div.appendChild(p);
                        p.className = "priceNum";
                        p.innerHTML = "$" + price;

                        div = document.createElement("div");
                        li2.appendChild(div);
                        div.className = "quantity";

                        p = document.createElement("p");
                        div.appendChild(p);
                        p.innerHTML = "Quantity";

                        p = document.createElement("p");
                        div.appendChild(p);
                        p.className = "quantityNum";
                        p.innerHTML = quantity;
                    }
                    document.getElementById("total" + i).innerHTML = "$" + total.toFixed(2);
                }
            }
        }
    };
    xmlhttp.open("Post", "GetOrder", true);
    xmlhttp.send();
}