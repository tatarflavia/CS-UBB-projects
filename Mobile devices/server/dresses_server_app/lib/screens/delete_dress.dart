import 'package:dresses_server_app/model/dress.dart';
import 'package:dresses_server_app/database/database_helper.dart';
import 'package:dresses_server_app/notifier/dress_notifier.dart';
import 'package:flutter/material.dart';
import 'dart:async';

import 'package:provider/provider.dart';

class DeleteDress extends StatefulWidget{
  final Dress selectedDress;


  DeleteDress(this.selectedDress);

  @override
  State<StatefulWidget> createState() {
    return DeleteDressState(selectedDress);
  }
}

class DeleteDressState extends State<DeleteDress>{
  Dress selectedDress;


  DeleteDressState(this.selectedDress);


  @override
  Widget build(BuildContext context) {
    DressNotifier dressNotifier=Provider.of<DressNotifier>(context);

    //how the screen looks
    return Scaffold(
      appBar: AppBar(
        title: Text('Delete a dress'),
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
                  child: Text('Are you sure you wish to delete this dress?',
                    style: TextStyle(fontSize: 30.0,fontWeight: FontWeight.bold),)
              ),

              //second elem:text field
              Padding(
                padding: EdgeInsets.only(top: 15.0),
                child: Text('Code: '+selectedDress.dressCode,style: TextStyle(fontSize: 20.0,fontWeight: FontWeight.w500)),),



              //third elem:text field
              Padding(
                  padding: EdgeInsets.only(top: 5.0, bottom: 5.0),
                  child: Text('Name: '+selectedDress.dressName,style: TextStyle(fontSize: 20.0))),


              Expanded(
                  child: Container(
                    child: Material(
                      shape: Border(),
                      elevation: 3.0,
                      child: Image.network(

                        getURLForDress(),
                        fit: BoxFit.fitHeight,
                        //height: BoxFit.fitHeight,
                        //width: BoxFit.fitWidth,
                      ),
                    ),
                    padding:
                    EdgeInsets.only(bottom: 10.0, left: 10.0, right: 10.0),
                  )),

              //forth elem:buttons for cancel and save
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
                          'No',
                          textScaleFactor: 1.5,
                        ),
                        onPressed: (){
                          setState(() {
                            debugPrint("No clicked");
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
                          'Yes',
                          textScaleFactor: 1.5,
                        ),
                        onPressed: (){
                          setState(() {
                            debugPrint("Yes clicked");
                            deleteButtonPressed(dressNotifier);
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

  void deleteButtonPressed(DressNotifier dressNotifier) async{
    dressNotifier.delete(selectedDress.id);
    Navigator.pop(context);
    Navigator.pop(context,true);
    // if(result!=0){
    //   //success updating in the repo
    //   _showAlertDialog('Status', 'Dress Deleted Successfully');
    // }
    // else{
    //   _showAlertDialog('Status', 'Problem Deleting Dress');
    // }

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

  String getURLForDress(){
    if(selectedDress.dressPhoto==null || selectedDress.dressPhoto==""){
      return 'https://d30celkwnl03x3.cloudfront.net/blog/wp-content/uploads/2019/06/ezgif.com-gif-maker.gif?raw=true';
    }
    else return selectedDress.dressPhoto;
  }
}