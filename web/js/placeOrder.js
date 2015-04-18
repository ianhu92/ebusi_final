/**
 * Created by Ian on 4/15/2015.
 */
window.onload= function () {
    getSession();
    document.forms[0].reset();
    initialYearAndDay();
}

function placeOrder(){
    if(validate()) {
        var inputs = document.forms[0].elements;
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
        var jsonString = JSON.stringify(jsonObject);
        var xmlhttp = getXMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                alert(xmlhttp.responseText);//show the response from server: succeed, failed or session error.
                if (xmlhttp.responseText === "Session error.") {//back to home page if there is a session error.
                    window.location.replace("./index.html");
                    return;
                }
                window.location="order.html";
            }
        };
        xmlhttp.open("Post", "PlaceOrder", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("jsonString=" + jsonString);
    }
}
function validate(){
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


function changeDay(originalDay,correctDay){
    originalDay=parseInt(originalDay);
    if(originalDay<correctDay){
        for(var i=1;i<=(correctDay-originalDay);i++){
            var optionNode=document.createElement("option");
            optionNode.value=originalDay+i;
            optionNode.innerHTML=originalDay+i;
            document.getElementById("day").appendChild(optionNode);
        }
    }
    else if(originalDay>correctDay){
        for(var i=1;i<=(originalDay-correctDay);i++){
            document.getElementById("day").removeChild(document.getElementById("day").lastChild);
        }
    }
}
function calculateDay(year,month){
    var result=0;
    if(month=="January"||month=="March"||month=="May"||month=="July"||month=="August"||month=="October"||month=="December"){
        result=31;
    }
    else if(month=="April"||month=="June"||month=="September"||month=="November"){
        result=30;
    }
    else if(month=="February"){
        if(year%4==0&&year%100!=0||year%400==0){
            result=29;
        }
        else{
            result=28;
        }
    }
    else{
        alert("What month?");
        return -1;
    }
    return result;
}
function changeMonthOrYear(select){
    var year=document.getElementById("year").value;
    var month=document.getElementById("month").value;
    var lastDay=document.getElementById("day").value;
    var correctDay=calculateDay(year,month);
    changeDay(lastDay,correctDay);
}
function initialYearAndDay(){
    var yearSelect=document.getElementById("year");
    var daysSelect=document.getElementById("day");
    for(var i=2014;i>=1900;i--){
        var optionNode=document.createElement("option");
        optionNode.value=i;
        optionNode.innerHTML=i;
        yearSelect.appendChild(optionNode);
    }
    for(var i=1;i<=31;i++){
        var optionNode=document.createElement("option");
        optionNode.value=i;
        optionNode.innerHTML=i;
        daysSelect.appendChild(optionNode);
    }
}
