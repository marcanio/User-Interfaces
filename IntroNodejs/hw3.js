var user = require('readline-sync');

var fNum1 = user.question('1st Number: ');
var fNum2 = user.question('2nd Number: ');
var fNum3 = user.question('3rd Number: ');
var fNum4 = user.question('4th Number: ');

var  factorial = function(number){
    var total = 1;
    for(var i= 1; i <= number; i++){
        total = total * i;          //5 = 1 
    }
    return total;
}
var  sumOFdigits =function(number){
    var total = 0;
    while(number >= 1){
        total += number % 10;
        number = Math.floor(number / 10);
    }
    return total;
}

var reversed = function (num){
    var output ="" ;
    for(var i =num.length-1; i>=0 ; i--){
        
        output += num.charAt(i);
    }
    return output;
}

var isPal = function (number){
    var back = (number.length) -1;
    var front = 0;
    while(front < back){

        if(number.charAt(front) == number.charAt(back)){
            back--;
            front++;
        }else{
            return false;
        }
    }

    return true;
}




console.log("Factorial of the 1st Number is = " + factorial(fNum1));
console.log("The sum of all the digits of the 2nd number is = " + sumOFdigits(fNum2));
console.log("The reverse of the 3rd number is = "  + reversed(fNum3));
console.log("Is the 4th number a palindrome (True/ False)? = " + isPal(fNum4));


