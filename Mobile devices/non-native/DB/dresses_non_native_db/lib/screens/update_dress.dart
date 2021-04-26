import 'package:dresses_non_native_db/model/dress.dart';
import 'package:flutter/material.dart';
import 'dart:async';
import 'package:dresses_non_native_db/repository/database_helper.dart';


class UpdateDress extends StatefulWidget{
  final Dress selectedDress;


  UpdateDress(this.selectedDress);

  @override
  State<StatefulWidget> createState() {
    return UpdateDressState(selectedDress);
  }
}

class UpdateDressState extends State<UpdateDress>{
  DatabaseHelper databaseHelper = DatabaseHelper();

  Dress selectedDress;


  UpdateDressState(this.selectedDress);


  //controllers for editing the values in the text boxes
  TextEditingController codeController=TextEditingController();
  TextEditingController nameController=TextEditingController();
  TextEditingController sizeController=TextEditingController();
  TextEditingController priceController=TextEditingController();
  TextEditingController colourController=TextEditingController();
  TextEditingController quantityController=TextEditingController();
  TextEditingController tailoringTimeController=TextEditingController();
  TextEditingController descriptionController=TextEditingController();
  TextEditingController dressPhotoController=TextEditingController();

  @override
  Widget build(BuildContext context) {
    codeController.text=selectedDress.dressCode;
    nameController.text=selectedDress.dressName;
    sizeController.text=selectedDress.dressSize.toString();
    priceController.text=selectedDress.dressPrice.toString();
    colourController.text=selectedDress.dressColour;
    quantityController.text=selectedDress.dressQuantity.toString();
    tailoringTimeController.text=selectedDress.tailoringTime.toString();
    descriptionController.text=selectedDress.dressDescription;
    dressPhotoController.text=selectedDress.dressPhoto;


    //how the screen looks
    return Scaffold(
      appBar: AppBar(
        title: Text('Update dress'),
      ),
      backgroundColor: Colors.red.shade50,
      //define body as a list view with text boxes within the padding wizzard
      body: Padding(
          padding: EdgeInsets.only(top: 15.0, left:10.0, right: 10.0),
          //define child as static listview that contains smaller amount of elems
          child:ListView(
            //define elements aka children as array of widgets
            children: <Widget>[

              //first elem:text field
              Padding(
                padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                child: TextField(
                  controller: codeController,
                  style: TextStyle(),
                  onChanged: (value){
                    debugPrint("Code changed");
                    updateCode();
                  },
                  decoration: InputDecoration(
                      labelText: 'Dress Code',
                      labelStyle: TextStyle(),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(5.0)
                      )
                  ),
                ),
              ),

              //second elem:text field
              Padding(
                padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                child: TextField(
                  controller: nameController,
                  style: TextStyle(),
                  onChanged: (value){
                    debugPrint("Name changed");
                    updateName();
                  },
                  decoration: InputDecoration(
                      labelText: 'Dress Name',
                      labelStyle: TextStyle(),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(5.0)
                      )
                  ),
                ),
              ),


              //third elem:text field
              Padding(
                padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                child: TextField(
                  keyboardType: TextInputType.number,
                  controller: sizeController,
                  style: TextStyle(),
                  onChanged: (value){
                    debugPrint("Size changed");
                    updateSize();
                  },
                  decoration: InputDecoration(
                      labelText: 'Dress Size',
                      labelStyle: TextStyle(),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(5.0)
                      )
                  ),
                ),
              ),


              //forth elem:text field
              Padding(
                padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                child: TextField(
                  keyboardType: TextInputType.number,
                  controller: priceController,
                  style: TextStyle(),
                  onChanged: (value){
                    debugPrint("Price changed");
                    updatePrice();
                  },
                  decoration: InputDecoration(
                      labelText: 'Dress Price',
                      labelStyle: TextStyle(),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(5.0)
                      )
                  ),
                ),
              ),


              //fifth elem:text field
              Padding(
                padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                child: TextField(
                  controller: colourController,
                  style: TextStyle(),
                  onChanged: (value){
                    debugPrint("Colour changed");
                    updateColour();
                  },
                  decoration: InputDecoration(
                      labelText: 'Dress Colour',
                      labelStyle: TextStyle(),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(5.0)
                      )
                  ),
                ),
              ),


              //sixth elem:text field
              Padding(
                padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                child: TextField(
                  keyboardType: TextInputType.number,
                  controller: quantityController,
                  style: TextStyle(),
                  onChanged: (value){
                    debugPrint("Quantity changed");
                    updateQuantity();
                  },
                  decoration: InputDecoration(
                      labelText: 'Dress Quantity',
                      labelStyle: TextStyle(),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(5.0)
                      )
                  ),
                ),
              ),


              //seventh elem:text field
              Padding(
                padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                child: TextField(
                  controller: tailoringTimeController,
                  style: TextStyle(),
                  onChanged: (value){
                    debugPrint("Tailoring time changed");
                    updateTailorTime();
                  },
                  decoration: InputDecoration(
                      labelText: 'Tailoring time',
                      labelStyle: TextStyle(),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(5.0)
                      )
                  ),
                ),
              ),


              //eighth elem:text field
              Padding(
                padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                child: TextField(
                  controller: descriptionController,
                  style: TextStyle(),
                  onChanged: (value){
                    debugPrint("Description changed");
                    updateDescription();
                  },
                  decoration: InputDecoration(
                      labelText: 'Dress Description',
                      labelStyle: TextStyle(),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(5.0)
                      )
                  ),
                ),
              ),


              Padding(
                padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                child: TextField(
                  controller: dressPhotoController,
                  style: TextStyle(),
                  onChanged: (value) {
                    debugPrint("Photo text changed");
                    updatePhoto();
                  },
                  decoration: InputDecoration(
                      labelText: 'Dress URL',
                      labelStyle: TextStyle(),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(5.0))),
                ),
              ),

              //ninth elem:buttons for cancel and save
              Padding(
                padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                child: Row(
                  //define children as widgets: 2 buttons
                  children: <Widget>[
                    //wrap the buttons in expanded wizard
                    Expanded(
                      child: RaisedButton(
                        color: Colors.black,
                        textColor: Colors.white70,
                        child:Text(
                          'Cancel',
                          textScaleFactor: 1.5,
                        ),
                        onPressed: (){
                          setState(() {
                            debugPrint("Cancel clicked");
                            Navigator.pop(context);
                          });
                        },
                      ),
                    ),

                    //add space between buttons with container
                    Container(width: 20.0,),

                    Expanded(
                      child: RaisedButton(
                        color: Colors.red.shade800,
                        textColor: Colors.white,
                        child:Text(
                          'Update',
                          textScaleFactor: 1.5,
                        ),
                        onPressed: (){
                          setState(() {
                            debugPrint("Update clicked");
                            updateButtonPressed();
                          });
                        },
                      ),
                    ),
                  ],
                ),
              ),
            ],
          )
      ),
    );

  }

  void updateCode(){
    selectedDress.dressCode=codeController.text;
  }

  void updateName(){
    selectedDress.dressName=nameController.text;
  }
  void updateSize(){
    selectedDress.dressSize=int.parse(sizeController.text.toString());
  }
  void updatePrice(){
    selectedDress.dressPrice=int.parse(priceController.text.toString());
  }
  void updateQuantity(){
    selectedDress.dressQuantity=int.parse(quantityController.text.toString());
  }
  void updateColour(){
    selectedDress.dressColour=colourController.text;
  }
  void updateTailorTime(){
    selectedDress.tailoringTime=tailoringTimeController.text;
  }
  void updateDescription(){
    selectedDress.dressDescription=descriptionController.text;
  }
  void updatePhoto(){
    selectedDress.dressPhoto=dressPhotoController.text;
  }

  void updateButtonPressed() async {
    int result=await databaseHelper.updateDress(selectedDress);
    Navigator.pop(context);
    Navigator.pop(context,true);
    if(result!=0){
      //success updating in the repo
      _showAlertDialog('Status', 'Dress Updated Successfully');
    }
    else{
      _showAlertDialog('Status', 'Problem Updating Dress');
    }
  }

  void _showAlertDialog(String title, String message){
    AlertDialog alertDialog=AlertDialog(
      title: Text(title),
      content: Text(message),

    );
    showDialog(
        context: context,
        builder: (_) => alertDialog
    );
  }


}