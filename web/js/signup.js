/**
 * Created by Ian on 4/14/2015.
 */
window.onload= function () {
    getSession();
}
function signup(){
    var pass1=document.getElementsByName("password");
    var pass2=document.getElementsByName("password2");
    if(pass1.value==pass2.value){
        return true;
    }else{
        alert("Two password are not the same.");
        return false;
    }
}