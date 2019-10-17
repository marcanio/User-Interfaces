

function valid2(){

    if(valid()){
        setTimeout(alert("Your information has been submitted!"), 10000);
    }
}

function valid(){
    
    var Email = document.getElementById("Email").value;
    var Phone = document.getElementById("Phone").value;
    var Address= document.getElementById("Address").value;
    var errorAlert ="";
    var checker = true;
    if( checkEmail(Email) ){
        correct("emailimg");
        checker = checker && true;
    }else{
        incorrect("emailimg")
        checker = checker && false;
        errorAlert += "Email should be format: xxx@xxx.xxx \n";
    }

    if(checkPhone(Phone)){
        correct("phoneimg");
        checker = checker && true;
    }else{
        incorrect("phoneimg");
        checker = checker && false;
        errorAlert += "Phone number format: xxx-xxx-xxxx or 10 digits \n";
    }

    if( checkAddress(Address) ){
        correct("Addressimg");
        checker = checker && true;
    }else{
        incorrect("Addressimg");
        checker = checker && false;
        errorAlert += "Address should be format: City,State";
    }
    if(!checker){
        alert(errorAlert);
    }
    

    return checker;
}

function checkEmail(str){
    //      First part  @     .     Last part is com it can only be 2-3 letters
    return /^[a-z0-9]+@[a-z0-9]+\.[a-z]{2,3}$/.test(str);
}

function checkPhone(str){
    //  Either check for 10 nums OR   3 nums  -  3 nums-  4 nums
    return  /^\d{10}$/.test(str) || /^[0-9]{3}-[0-9]{3}-[0-9]{4}$/.test(str);
}

function checkAddress(str){
    str = str.split(' ').join('');
    return /^[a-zA-Z]+\,[a-zA-Z]{0,8}$/.test(str);
}

function correct(id){
    document.getElementById(id).hidden = false; 
    document.getElementById(id).src = "correct.png";
}

function incorrect(id){
    document.getElementById(id).hidden = false; 
    document.getElementById(id).src = "wrong.png";
}