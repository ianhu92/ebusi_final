/**
 * Created by Ian on 4/15/2015.
 */
window.onload = function () {
    getSession();
    getCartNum();
    getCustomerInfo();
}
function validatePassword() {
    var old = document.getElementById("oldpassword").value;
    var new1 = document.getElementById("password").value;
    var new2 = document.getElementById("password2").value;
    if (old == "" || new1 == "" || new2 == "") {
        alert("Please fill in all the blanks in Change Password section.");
        return false;
    }

    if (new1 == new2 && old != new1) {
        return true;
    } else if (old == new1) {
        alert("Your old password is equal to new password.");
        return false;
    } else {
        alert("Two new passwords are not the same.");
        return false;
    }
}
function validateSetting() {
    var inputs = document.forms[0].elements;
    var selects = document.getElementsByTagName("select");
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].type === "text") {
            if (inputs[i].value == "") {
                alert("Please fill in all the blanks.");
                return false;
            } else if (inputs[i].name == "zip") {
                var pattern = /\b\d{5}\b/g;
                if (!pattern.test(inputs[i].value)) {
                    alert("Invalid zip code.");
                    return false;
                }
            }
            else if (inputs[i].name == "state") {
                var pattern = /\b[A-Z]{2}\b/g;
                if (!pattern.test(inputs[i].value)) {
                    alert("Invalid state.(Use abbreviate)");
                    return false;
                }
            }
        }
    }
    return true;
}
function getCustomerInfo() {
    var xmlhttp = getXMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            if (xmlhttp.responseText != "Invalid Session.") {
                var jo = JSON.parse(xmlhttp.responseText);
                var email = jo.product.email;
                var firstname = jo.product.firstname;
                var lastname = jo.product.lastname;
                var address = jo.product.address;
                var phone = jo.product.phone;

                document.getElementById("email").value = email;
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