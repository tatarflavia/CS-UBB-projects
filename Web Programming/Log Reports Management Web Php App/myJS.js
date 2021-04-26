pageNumber=1;

function changePageNumber(number) {
    pageNumber+=number;
}

function submitName() {
    var str=document.getElementById("username").value;
    if (str === "") {
        alert("Please enter a name:");
    } else {
        xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("txtHint").innerHTML = this.responseText;
                setTimeout(function(){
                    window.open("clientViewAll.html","_self");
                }, 2000);

            }
        };
        xmlhttp.open("GET","server.php/start?q="+str,true);
        xmlhttp.send();}
}

function deleteLog() {
        var number=document.getElementById("logID").value;
        if(isNaN(number) || number<1){
            alert("ID not valid");
        }
        else{
            xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    document.getElementById("showYour").innerHTML = this.responseText;
                }
            };
            xmlhttp.open("GET","server.php/deleteYour?q="+number+"&pageno="+pageNumber,true);
            xmlhttp.send();
        }
}

function addLog() {
    var type=document.getElementById("typeAdd").value;
    var severity=document.getElementById("severityAdd").value;
    var date=document.getElementById("dateAdd").value;
    var message=document.getElementById("messageAdd").value;
    if(date==="" || severity===""){
        alert("Date or severity can't be left empty!");
    }
    else{
        xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("showYour").innerHTML = this.responseText;
            }
        };
        xmlhttp.open("GET","server.php/addYour?t="+type+"&s="+severity+"&d="+date+"&m="+message+"&pageno="+pageNumber,true);
        xmlhttp.send();
    }
}

function updateLog() {
    var id=document.getElementById("idUpdate").value;
    var type=document.getElementById("typeUpdate").value;
    var severity=document.getElementById("severityUpdate").value;
    var date=document.getElementById("dateUpdate").value;
    var message=document.getElementById("messageUpdate").value;
    if(isNaN(id) || id<1){
        alert("ID invalid!");
    }
    else{
        xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("showYour").innerHTML = this.responseText;
            }
        };
        xmlhttp.open("GET","server.php/updateYour?t="+type+"&s="+severity+"&d="+date+"&m="+message+"&i="+id+"&pageno="+pageNumber,true);
        xmlhttp.send();
    }
}


function showALl() {
        xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("showAll").innerHTML = this.responseText;
            }
        };
        xmlhttp.open("GET","server.php/showAll?pageno="+pageNumber,true);
        xmlhttp.send();
}

function showYour() {
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("showYour").innerHTML = this.responseText;
        }
    };
    xmlhttp.open("GET","server.php/showYour?pageno="+pageNumber,true);
    xmlhttp.send();

}


function showFilter() {
    var str=document.getElementById("filterInput").value;
    if (str == "") {
        document.getElementById("showFilter").innerHTML = "";
        return;
    }
    else{
        xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("showFilter").innerHTML = this.responseText;
            }
        };
        xmlhttp.open("GET","server.php/showFilter?q="+str+"&pageno="+pageNumber,true);
        xmlhttp.send();
    }

}