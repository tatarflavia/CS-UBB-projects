import 'package:dresses_non_native_db/model/dress.dart';
import 'package:flutter/material.dart';
import 'dart:async';
import 'package:dresses_non_native_db/repository/database_helper.dart';
import 'package:dresses_non_native_db/screens/add_dress.dart';
import 'dress_details.dart';
import 'package:sqflite/sqflite.dart';

class DressList extends StatefulWidget{


  @override
  State<StatefulWidget> createState() {
    return DressListState();
  }
}

class DressListState extends State<DressList> {
  int count = 0;
  DatabaseHelper databaseHelper = DatabaseHelper();
  List<Dress> dressList;

  @override
  Widget build(BuildContext context) {
    if(dressList==null){
      updateDressList();
    }

    //define root element(scaffold) that co ntains the bar and the list view
    return Scaffold(
      appBar: AppBar(
        title: Text('Mistique dresses'),
      ),
      body: getDressListView(),
      backgroundColor: Colors.red.shade50,

      //call a func which returns a list view that is down below
      //define the add dress button with on pressed fct
      bottomNavigationBar:BottomAppBar(
        child: Padding(
          padding:EdgeInsets.only(top: 5.0, bottom: 5.0,right: 120.0,left: 120.0),
            child: RaisedButton(

              child:Text(
                  'ADD',
                  textScaleFactor: 1.5,
                  style: TextStyle(fontSize: 10)
              ),
              onPressed: (){
                debugPrint("Add button clicked.");
                navigateToAdd();
              },
            ),
          ),



      color: Colors.red.shade50,
      ),);
  }

  String getURLForDress(int index){
    if(dressList[index].dressPhoto==null || dressList[index].dressPhoto==""){
      return 'https://d30celkwnl03x3.cloudfront.net/blog/wp-content/uploads/2019/06/ezgif.com-gif-maker.gif?raw=true';
    }
    else return dressList[index].dressPhoto;
  }

  GridView getDressListView() {
    //here we define a text style then return the list view by using the builder fct
    return GridView.builder(
        itemCount: count,
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2,
          childAspectRatio: MediaQuery.of(context).size.width /
              (MediaQuery.of(context).size.height),
        ),
        itemBuilder: (BuildContext context, int index) {
          debugPrint("index."+index.toString());
          //we have a list item that we return here as a card
          return Card(
            elevation: 3.0,
            child: GestureDetector(
              child: Column(
                children: [
                  SizedBox(
                    height: 5,
                    child: Text(""),
                  ),
                  Expanded(
                      child: Container(
                    child: Material(
                      shape: Border(),
                      elevation: 3.0,
                      child: Image.network(

                          this.getURLForDress(index),
                          fit: BoxFit.fitHeight,
                          //height: BoxFit.fitHeight,
                          //width: BoxFit.fitWidth,
                          ),
                    ),
                    padding:
                        EdgeInsets.only(bottom: 10.0, left: 10.0, right: 10.0),
                  )),
                  SizedBox(
                    height: 20,
                    child: FittedBox(
                      fit: BoxFit.fitWidth,
                      child: Text("#" + this.dressList[index].dressCode,
                          style: TextStyle(
                              fontWeight: FontWeight.bold, fontSize: 20)),
                    ),
                  ),
                  SizedBox(
                    height: 20,
                    child: FittedBox(
                      fit: BoxFit.fitWidth,
                      child: Text(this.dressList[index].dressName,
                          style: TextStyle(
                              fontStyle: FontStyle.italic, fontSize: 15)),
                    ),
                  ),
                ],
              ),
              onTap: () {
                debugPrint("Dress item clicked.");
                navigateToDetails(this.dressList[index]);
              },
            ),
          );
        });
  }

  void updateDressList(){
    //update dress list and count
    //get the db singleton
    final Future<Database> dbFuture=databaseHelper.initialiseDatabase();
    dbFuture.then((database) {
      Future<List<Dress>> dressListFuture=databaseHelper.getDressList();
      dressListFuture.then((dressList) {
        setState(() {
          this.dressList=dressList;
          this.count=dressList.length;
        });
      } );
    });
  }

  void navigateToDetails(Dress dress) async {
    bool result= await Navigator.push(context, MaterialPageRoute(builder: (context) {
      return DressDetails(dress);
    }));
    if(result!=null && result){
      updateDressList();
    }
  }

  void navigateToAdd() async {
    bool result=await Navigator.push(context, MaterialPageRoute(builder: (context) {
      return AddDress();
    }));
    if(result!=null && result){
      updateDressList();
    }
  }
}