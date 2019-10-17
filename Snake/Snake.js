//Global varibles used to make everything easier
var row= 0;
var col= 300;
var direction= "Right";
var path= 0;
var canvas= 0;
var onORoff= false;
var interval =0;
var locations =[];


function start(){
    initialize();
    document.getElementById("start").onclick = StartStop;
    document.getElementById("Turn Left").onclick = turnLeft;
    document.getElementById("Turn Right").onclick = turnRight;
    interval = setInterval(drawSnake , 10);
    
}

function drawSnake(){
    console.log(onORoff)
    if(onORoff){
        controller();
        update();
    }
    
}

function update(){

    for( var i = 0; i < locations.length-1; i++){
        if(locations[i].row == row && locations[i].col == col){
            alert("You lose!");
            clearInterval(interval);
        }
    }
}

function initialize(){
    canvas = document.getElementById("Canvas");
    path = canvas.getContext("2d");         //Used to draw on the canvas
}

function StartStop(){
    onORoff = !onORoff;
    drawSnake();
}


function controller(){
    if(direction == "Up"){
        col -= 1;             //Move to the right
        path.fillStyle = "#FF0000";
        path.fillRect(row, col, 10,10);
        locations.push({row, col});
    }
    if(direction == "Down"){
        col += 1;             //Move to the right
        path.fillStyle = "#FF0000";
        path.fillRect(row, col, 10,10);
        locations.push({row, col});
    }
    if(direction == "Right"){
        row += 1;             //Move to the right
        path.fillStyle = "#FF0000";
        path.fillRect(row, col, 10,10);
        locations.push({row, col});
    }
    if(direction == "Left"){
        row -= 1;             //Move to the right
        path.fillStyle = "#FF0000";
        path.fillRect(row, col, 10,10);
        locations.push({row, col});
    }
    if(!(row>0 && col>0 && row<canvas.width-1 && col < canvas.height-1 )){ //Boundarys
        clearInterval(interval);
        alert("out of bounds");
    }
   

}



function turnLeft(){
    if(direction == "Up"){
      direction = "Left";
    }
    else if(direction == "Down"){
        direction = "Right";
    }
    else if(direction == "Right"){
        direction = "Up";
    }
    else if(direction == "Left"){
        direction = "Down";
    }

}

function turnRight(){
    if(direction == "Up"){
        direction = "Right";
      }
     else if(direction == "Down"){
          direction = "Left";
      }
     else if(direction == "Right"){
          direction = "Down";
      }
     else if(direction == "Left"){
          direction = "Up";
      }

}