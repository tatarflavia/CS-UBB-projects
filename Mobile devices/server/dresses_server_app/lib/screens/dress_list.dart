import 'package:dresses_server_app/model/dress.dart';
import 'package:dresses_server_app/notifier/dress_notifier.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'add_dress.dart';
import 'dress_details.dart';
import 'package:provider/provider.dart';

class DressList extends StatefulWidget{
  DressList({Key key, this.title}) : super(key: key);

  final String title;

  @override
  State<StatefulWidget> createState() {
    return DressListState();
  }
}

class DressListState extends State<DressList> {
  @override
  Widget build(BuildContext context) {
    //define root element(scaffold) that contains the bar and the list view
    DressNotifier dressNotifier=Provider.of<DressNotifier>(context);
    return Scaffold(
      appBar: AppBar(
        title: Text('Mistique dresses'),
      ),
      body: Container(
        child: GridView.builder(
          itemCount: dressNotifier.dressList.length,
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

                              this.getURLForDress(dressNotifier.dressList[index]),
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
                        child: Text("#" + dressNotifier.dressList[index].dressCode,
                            style: TextStyle(
                                fontWeight: FontWeight.bold, fontSize: 20)),
                      ),
                    ),
                    SizedBox(
                      height: 20,
                      child: FittedBox(
                        fit: BoxFit.fitWidth,
                        child: Text(dressNotifier.dressList[index].dressName,
                            style: TextStyle(
                                fontStyle: FontStyle.italic, fontSize: 15)),
                      ),
                    ),
                  ],
                ),
                onTap: () {
                  debugPrint("Dress item clicked.");
                  navigateToDetails(dressNotifier.dressList[index]);
                },
              ),
            );
          }),),
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

  String getURLForDress(Dress dress){

    if(dress.dressPhoto==null || dress.dressPhoto==""){
      return 'https://d30celkwnl03x3.cloudfront.net/blog/wp-content/uploads/2019/06/ezgif.com-gif-maker.gif?raw=true';
    }
    else return dress.dressPhoto;
  }

  // GridView getDressListView() {
  //
  //   //here we define a text style then return the list view by using the builder fct
  //   return GridView.builder(
  //       itemCount: dressNotifier.dressList.length,
  //       gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
  //         crossAxisCount: 2,
  //         childAspectRatio: MediaQuery.of(context).size.width /
  //             (MediaQuery.of(context).size.height),
  //       ),
  //       itemBuilder: (BuildContext context, int index) {
  //         debugPrint("index."+index.toString());
  //         //we have a list item that we return here as a card
  //         return Card(
  //           elevation: 3.0,
  //           child: GestureDetector(
  //             child: Column(
  //               children: [
  //                 SizedBox(
  //                   height: 5,
  //                   child: Text(""),
  //                 ),
  //                 Expanded(
  //                     child: Container(
  //                       child: Material(
  //                         shape: Border(),
  //                         elevation: 3.0,
  //                         child: Image.network(
  //
  //                           this.getURLForDress(index),
  //                           fit: BoxFit.fitHeight,
  //                           //height: BoxFit.fitHeight,
  //                           //width: BoxFit.fitWidth,
  //                         ),
  //                       ),
  //                       padding:
  //                       EdgeInsets.only(bottom: 10.0, left: 10.0, right: 10.0),
  //                     )),
  //                 SizedBox(
  //                   height: 20,
  //                   child: FittedBox(
  //                     fit: BoxFit.fitWidth,
  //                     child: Text("#" + dressNotifier.dressList[index].dressCode,
  //                         style: TextStyle(
  //                             fontWeight: FontWeight.bold, fontSize: 20)),
  //                   ),
  //                 ),
  //                 SizedBox(
  //                   height: 20,
  //                   child: FittedBox(
  //                     fit: BoxFit.fitWidth,
  //                     child: Text(dressNotifier.dressList[index].dressName,
  //                         style: TextStyle(
  //                             fontStyle: FontStyle.italic, fontSize: 15)),
  //                   ),
  //                 ),
  //               ],
  //             ),
  //             onTap: () {
  //               debugPrint("Dress item clicked.");
  //               navigateToDetails(dressNotifier.dressList[index]);
  //             },
  //           ),
  //         );
  //       });
  // }

  // void updateDressList(){
  //   //update dress list and count
  //   //get the db singleton
  //   final Future<Database> dbFuture=databaseHelper.initialiseDatabase();
  //   dbFuture.then((database) {
  //     Future<List<Dress>> dressListFuture=databaseHelper.getDressList();
  //     dressListFuture.then((dressList) {
  //       setState(() {
  //         this.dressList=dressList;
  //         this.count=dressList.length;
  //       });
  //     } );
  //   });
  // }

  void navigateToDetails(Dress dress) async {
    await Navigator.push(context, MaterialPageRoute(builder: (context) {
      return DressDetails(dress);
    }));

  }

  void navigateToAdd() async {
    await Navigator.push(context, MaterialPageRoute(builder: (context) {
      return AddDress();
    }));
  }
}