/**
 * Created by Ian on 4/14/2015.
 */
window.onload= function () {
    getSession();
    getCartNum();
}
function signup(){
    var pass1=document.getElementById("password").value;
    var pass2=document.getElementById("password2").value;
    var username=document.getElementById("username").value;
    if(pass1!=""&&pass1==pass2&&username!=""){
        return true;
    }else if(pass1==""||pass2==""||username=="") {
        alert("Please fill in all the blanks.");
        return false;
    }else if(pass1!=pass2){
        alert("Two password are not the same.");
        return false;
    }else{
        return false;
    }
}