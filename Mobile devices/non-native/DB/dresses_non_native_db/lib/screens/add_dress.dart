import 'package:dresses_non_native_db/model/dress.dart';
import 'package:flutter/material.dart';
import 'dart:async';
import 'package:dresses_non_native_db/repository/database_helper.dart';
import 'package:flutter/services.dart';

class AddDress extends StatefulWidget{

  @override
  State<StatefulWidget> createState() {
    return AddDressState();
  }

}


class AddDressState extends State<AddDress>{
  DatabaseHelper databaseHelper = DatabaseHelper();

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
    //how the screen looks
    return Scaffold(
      appBar: AppBar(
        title: Text('Add a dress'),
      ),
      backgroundColor: Colors.red.shade50,
      //define body as a list view with text boxes within the padding wizzard
      body: Padding(
        padding: EdgeInsets.only(top: 15.0, left:10.0, right: 10.0),
        //define child as static listView that contains smaller amount of elems
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
                         'Save',
                         textScaleFactor: 1.5,
                       ),
                       onPressed: (){
                         setState(() {
                           debugPrint("Save clicked");
                           addButtonPressed();
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




  void addButtonPressed() async{
    String dressCode=codeController.text;
    String dressName=nameController.text;
    int dressSize=int.parse(sizeController.text);
    int dressPrice=int.parse(priceController.text);
    String dressColour=colourController.text;
    int dressQuantity=int.parse(quantityController.text);
    String tailoringTime=tailoringTimeController.text;
    String dressDescription=descriptionController.text;
    String dressPhoto=dressPhotoController.text;

    Navigator.pop(context,true);

    int result=await databaseHelper.addDress(new Dress(dressCode, dressName, dressSize, dressPrice, dressColour, dressQuantity, tailoringTime,dressDescription,dressPhoto));
    if(result!=0){
      //success adding to the repo
      _showAlertDialog('Status', 'Dress Added Successfully');
    }
    else{
      _showAlertDialog('Status', 'Problem Saving Dress');
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