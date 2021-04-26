import 'package:dresses_server_app/model/dress.dart';
import 'package:dresses_server_app/screens/update_dress.dart';
import 'package:flutter/material.dart';

import 'delete_dress.dart';


class DressDetails extends StatefulWidget{
  final Dress selectedDress;



  DressDetails(this.selectedDress);

  @override
  State<StatefulWidget> createState() {
    return DressDetailsState(this.selectedDress);
  }
}

class DressDetailsState extends State<DressDetails>{
  Dress selectedDress;

  DressDetailsState(this.selectedDress);



  @override
  Widget build(BuildContext context) {

    final topContentText = Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        SizedBox(height: 10.0),
        Text(
          selectedDress.dressCode,
          style: TextStyle(color: Colors.white, fontSize: 45.0),
        ),
        SizedBox(height: 30.0),
        Padding(
            padding: EdgeInsets.only(left: 10.0),
            child: Text(
              "name: "+selectedDress.dressName,
              style: TextStyle(color: Colors.white,fontSize: 15.0),
            )),
        SizedBox(height: 10.0),
        Padding(
            padding: EdgeInsets.only(left: 10.0),
            child:
            Container(
              padding: const EdgeInsets.all(7.0),
              decoration: new BoxDecoration(
                  border: new Border.all(color: Colors.white),
                  borderRadius: BorderRadius.circular(5.0)),
              child: new Text(
                "\$" + this.selectedDress.dressPrice.toString(),
                style: TextStyle(color: Colors.white),
              ),
            )),
        SizedBox(height: 10.0),
        Padding(
            padding: EdgeInsets.only(left: 10.0),
            child: Text(
              "size: "+selectedDress.dressSize.toString(),
              style: TextStyle(color: Colors.white,fontSize: 15.0),
            )),
        SizedBox(height: 10.0),
        Padding(
            padding: EdgeInsets.only(left: 10.0),
            child: Text(
              "colour: "+selectedDress.dressColour.toString(),
              style: TextStyle(color: Colors.white,fontSize: 15.0),
            )),
        SizedBox(height: 10.0),
        Padding(
            padding: EdgeInsets.only(left: 10.0),
            child: Text(
              "quantity: "+selectedDress.dressQuantity.toString(),
              style: TextStyle(color: Colors.white,fontSize: 15.0),
            )),
        SizedBox(height: 10.0),
        Padding(
            padding: EdgeInsets.only(left: 10.0),
            child: Text(
              "done in: "+selectedDress.tailoringTime.toString(),
              style: TextStyle(color: Colors.white,fontSize: 15.0),
            )),


      ],
    );
    final topContent = Stack(
      children: <Widget>[
        Container(
          height: MediaQuery.of(context).size.height * 0.5,
          padding: EdgeInsets.all(40.0),
          width: MediaQuery.of(context).size.width,
          decoration: BoxDecoration(color: Color.fromRGBO(58, 66, 86, .9)),
          child: Center(
            child: topContentText,
          ),
        ),
      ],
    );

    final bottomContentText = Text(
      selectedDress.dressDescription,
      style: TextStyle(fontSize: 18.0),
    );
    final bottomContent =






    Container(
      //height: 1000,
        width: MediaQuery.of(context).size.width,
        // color: Theme.of(context).primaryColor,
        padding: EdgeInsets.all(30.0),
        child: Center(
            child: Column(
                children: <Widget>[bottomContentText,


                  Padding(
                    padding: EdgeInsets.only(top: 15.0, bottom: 15.0),
                    child: Row(
                      //define children as widgets: 3 buttons
                      children: <Widget>[
                        //wrap the buttons in expanded wizard
                        Expanded(
                          child: RaisedButton(
                            color: Colors.black,
                            textColor: Colors.white70,
                            child:Text(
                              'Go back',
                              textScaleFactor: 1.2,
                            ),
                            onPressed: (){
                              setState(() {
                                debugPrint("Go back clicked");
                                Navigator.pop(context);
                              });
                            },
                          ),
                        ),

//add space between buttons with container
                        Container(width: 5.0,),

                        Expanded(
                          child: RaisedButton(
                            color: Theme.of(context).primaryColorDark,
                            textColor: Theme.of(context).primaryColorLight,
                            child:Text(
                              'Update',
                              textScaleFactor: 1.2,
                            ),
                            onPressed: (){
                              setState(() {
                                debugPrint("Update clicked");
                                Navigator.push(context,MaterialPageRoute(builder: (context){
                                  return UpdateDress(selectedDress);
                                }));
                              });
                            },
                          ),
                        ),

//add space between buttons with container
                        Container(width: 5.0,),

                        Expanded(
                          child: RaisedButton(
                            color: Colors.red.shade800,
                            textColor: Colors.white,
                            child:Text(
                              'Delete',
                              textScaleFactor: 1.2,
                            ),
                            onPressed: (){
                              setState(() {
                                debugPrint("Delete clicked");
                                Navigator.push(context,MaterialPageRoute(builder: (context){
                                  return DeleteDress(selectedDress);
                                }));
                              });
                            },
                          ),
                        ),

                      ],
                    ),
                  )])));

    return Scaffold(
      appBar: AppBar(
        title: Text('Dress Details'),
      ),
      body:
      Column(
        children: <Widget>[topContent, bottomContent],
      ),
    );
  }
}
