

function valid1(){

    if(valid()){
        setTimeout(window.open("validation2.html","_self"),10000); //Wait to make sure info is correct
    }
    return false;
}

function valid(){
    
    var first = document.getElementById("FirstName").value;
    var last = document.getElementById("LastName").value;
    var gender= document.getElementById("Gender").value;
    var state = document.getElementById("State").value;
    var checker = true;
    var errorString= "";
    if(isLetter(first)){
        correct("firstNameimg");
        checker = checker && true;
        
    }else{
        incorrect("firstNameimg")
        checker = checker && false;
        errorString += "First name should be alphanumeric \n";
    }

    if(isLetter(last)){
        correct("lastNameimg");
        checker = checker && true;
    }else{
        incorrect("lastNameimg");
        checker = checker && false;
        errorString += "Last name should be alphanumeric\n";
        
    }

    if(empty(gender)){
        correct("genderimg");
        checker = checker && true;
    }else{
        incorrect("genderimg");
        checker = checker && false;
        errorString += "Gender is required \n";
    }

    if(empty(state)){
        correct("stateimg");
        checker = checker && true;
    }else{
        incorrect("stateimg");
        checker = checker && false;
        errorString += "State is required\n";
       
    }
    if(!checker){
        alert(errorString);
    }
    
    return checker;
}

function empty(str){
    return !str =="";
}

function isLetter(str){
    return /[A-Za-z]/.test(str);
}

function correct(id){
    document.getElementById(id).hidden = false; 
    document.getElementById(id).src = "correct.png";
}

function incorrect(id){
    document.getElementById(id).hidden = false; 
    document.getElementById(id).src = "wrong.png";
}