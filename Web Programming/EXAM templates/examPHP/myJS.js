
function logIn() {
    var str=document.getElementById("username").value;
   if (str === "" ) {
        alert("the field can't be empty!");
    } else {
        xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var text=this.responseText;
                console.log(text);
                document.getElementById("txtHint").innerHTML = text;
                setTimeout(function(){
                    if(text.includes("such user")){
                        window.open("logIn.html","_self");
                    }
                    else {
                        window.open("mainPage.html","_self");
                    }
                    }, 2000);
            }
        };
        xmlhttp.open("GET","server.php/start?q="+str,true);
        xmlhttp.send();}
}


function showAll() {
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var text=this.responseText;
            if(text.includes("back to"))
            {
                window.open("logIn.html","_self");
            }
            document.getElementById("showAll").innerHTML = this.responseText;
        }
    };
    xmlhttp.open("GET","server.php/showAll",true);
    xmlhttp.send();
}



function add() {
    var name=document.getElementById("addProduct-name").value;
    var description=document.getElementById("addProduct-description").value;

    if(name===""){
        alert("Name  can't be left empty!");
    }
    else{
        xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var text=this.responseText;
                if(text.includes("back to"))
                {
                    window.open("logIn.html","_self");
                }
                document.getElementById("add-result").innerHTML = this.responseText;
            }
        };
        xmlhttp.open("GET","server.php/addYour?n="+name+"&d="+description,true);
        xmlhttp.send();
    }
}


function buy() {
    var name=document.getElementById("buyProduct-name").value;
    var quantity=document.getElementById("buyProduct-quantity").value;

    if(name==="" || quantity===""){
        alert("Name or quantity can't be left empty!");
    }
    else{
        xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var text=this.responseText;
                if(text.includes("back to"))
                {
                    window.open("logIn.html","_self");
                }
                document.getElementById("buy-result").innerHTML = this.responseText;
            }
        };
        xmlhttp.open("GET","server.php/buyYour?n="+name+"&q="+quantity,true);
        xmlhttp.send();
    }
}




function update() {
    var id=document.getElementById("idUpdate").value;
    var name=document.getElementById("nameUpdate").value;
    var description=document.getElementById("descriptionUpdate").value;
    var value=document.getElementById("valueUpdate").value;
    if(isNaN(id) || id<1 || name==="" || value===""){
        alert("You need to fill out the id, name and value fields!");
    }
    else{
        xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var text=this.responseText;
                if(text.includes("back to"))
                {
                    window.open("logIn.html","_self");
                }
                document.getElementById("update-result").innerHTML = this.responseText;
            }
        };
        xmlhttp.open("GET","server.php/updateYour?i="+id+"&n="+name+"&d="+description+"&v="+value,true);
        xmlhttp.send();
    }
}


function showFilter() {
    var str=document.getElementById("filterInput").value;
    if (str === "") {
        document.getElementById("showFilter").innerHTML = "";
    }
    else{
        xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("showFilter").innerHTML = this.responseText;
            }
        };
        xmlhttp.open("GET","server.php/showFilter?q="+str,true);
        xmlhttp.send();
    }

}

function logOut() {
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            window.open("logIn.html","_self");

        }
    };
    xmlhttp.open("GET","server.php/logOut",true);
    xmlhttp.send();
}