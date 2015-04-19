/**
 * Created by Ian on 4/15/2015.
 */
window.onload=function(){
    getSession();
    getCartNum();
}
function validatePassword(){
    var old=document.getElementById("oldpassword").value;
    var new1=document.getElementById("password").value;
    var new2=document.getElementById("password2").value;
    if(old==""||new1==""||new2==""){
        alert("Please fill in all the blanks in Change Password section.");
        return false;
    }

    if(new1==new2&&old!=new1){
        return true;
    }else if(old==new1){
        alert("Your old password is equal to new password.");
        return false;
    }else{
        alert("Two new passwords are not the same.");
        return false;
    }
}
function validateSetting(){
    var inputs = document.forms[0].elements;
    var selects = document.getElementsByTagName("select");
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].type === "text") {
            if(inputs[i].value==""){
                alert("Please fill in all the blanks.");
                return false;
            }else if(inputs[i].name=="zip"){
                var pattern=/\b\d{5}\b/g;
                if(!pattern.test(inputs[i].value)){
                    alert("Invalid zip code.");
                    return false;
                }
            }
            else if(inputs[i].name=="state"){
                var pattern=/\b[A-Z]{2}\b/g;
                if(!pattern.test(inputs[i].value)){
                    alert("Invalid state.(Use abbreviate)");
                    return false;
                }
            }
        }
    }
    return true;
}