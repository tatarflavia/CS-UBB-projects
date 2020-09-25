/*
document.getElementById('1').onclick = ImageClicked;
document.getElementById('2').onclick = ImageClicked;
document.getElementById('3').onclick = ImageClicked;
document.getElementById('4').onclick = ImageClicked;
document.getElementById('5').onclick = ImageClicked;
document.getElementById('6').onclick = ImageClicked;
document.getElementById('7').onclick = ImageClicked;
document.getElementById('8').onclick = ImageClicked;
document.getElementById('9').onclick = ImageClicked;*/

id1ForFirstImg="";
count=0;
id2ForSecondImg="";
arrayOfImages=[0,1,2,3,4,5,6,7,8]; /*it tells us which index is over that position in the board*/


function swapImages(id1,id2) {
    /*swaps 2 images*/
    var first=document.getElementById(id1).src;
    var second=document.getElementById(id2).src;
    document.getElementById(id1).src=second;
    document.getElementById(id2).src=first;
    var index1=parseInt(id1);
    var index2=parseInt(id2);
    var ceEra1=arrayOfImages[index1];
    var ceEra2=arrayOfImages[index2];
    arrayOfImages[index1]=ceEra2;
    arrayOfImages[index2]=ceEra1;
}

function ImageClicked(clicked) {
    /*changes the indexes for the clicked images*/
    if(count%2===0){
        id1=clicked;
        count+=1;
    }
    else if(count%2===1)
    {
        id2=clicked;
        count+=1;
        swapButtonImages()
    }
}

function swapButtonImages() {
    /*when the button swap is clicked, the images are swaped*/
    if(checkCorrect()){
        document.getElementById("wellDoneMessage").style.visibility = "visible";
        alert("WELL DONE!!!");
    }
    swapImages(id1,id2);
    if(checkCorrect()){
        document.getElementById("wellDoneMessage").style.visibility = "visible";
        alert("WELL DONE!!!");
    }
}

function shuffle(array) {
    /*to suffle the array*/
    array.sort(() => Math.random() - 0.5);
    return array
}

function startFunction() {
    /*when start is pressed, array for indexes is prepared and the first swaps from the random are made to prepare the game*/
    let arrayOfImg = shuffle([0,1,2,3,4,5,6,7,8]);
    var i;
    for(i=0;i<Math.floor(arrayOfImg.length);i++)
    {

        var first=document.getElementById(arrayOfImg[i]).src;
        var second=document.getElementById(i).src;
        document.getElementById(i).src=first;
        document.getElementById(arrayOfImg[i]).src=second;
        pos1=arrayOfImages[arrayOfImg[i]];
        pos2=arrayOfImages[i];
        arrayOfImages[arrayOfImg[i]]=pos2;
        arrayOfImages[i]=pos1;
    }
}



function checkCorrect(){
    /*checks if the solution is reached based on the array of indexes*/
    return arrayOfImages[0]===0 &&
                arrayOfImages[1]===1 &&
                arrayOfImages[2]===2 &&
                arrayOfImages[3]===3 &&
                arrayOfImages[4]===4 &&
                arrayOfImages[5]===5 &&
                arrayOfImages[6]===6 &&
                arrayOfImages[7]===7 &&
                arrayOfImages[8]===8 ;
}

