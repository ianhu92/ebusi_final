/**
 * Created by Ian on 4/15/2015.
 */
window.onload = function () {
    getSession();
    getCartNum();
    document.forms[0].reset();
    initialYearAndDay();
    getCustomerInfo();
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

        var firstname=document.getElementById("firstname").value;
        var lastname=document.getElementById("lastname").value;
        var address=document.getElementById("address").value;
        var phone=document.getElementById("phone").value;
        var xmlhttp = getXMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                alert(xmlhttp.responseText);//show the response from server: succeed, failed or session error.
                if (xmlhttp.responseText === "Session error.") {//back to home page if there is a session error.
                    window.location.replace("./index.html");
                    return;
                }
                window.location = "order.html";
            }
        };
        xmlhttp.open("Post", "PlaceOrder", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("firstname=" + firstname+"&lastname=" + lastname+"&address=" + address+"&phone=" + phone);
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
