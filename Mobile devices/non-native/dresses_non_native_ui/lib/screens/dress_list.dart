import 'package:dresses_non_native_ui/model/dress.dart';
import 'package:dresses_non_native_ui/repository/inMemoryRepository.dart';
import 'package:dresses_non_native_ui/screens/add_dress.dart';
import 'package:dresses_non_native_ui/screens/dress_details.dart';
import 'package:flutter/material.dart';

class DressList extends StatefulWidget{


  @override
  State<StatefulWidget> createState() {
    return DressListState();
  }
}

class DressListState extends State<DressList> {
  int count = 0;
  InMemoryRepository repository = InMemoryRepository();
  List<Dress> dressList;

  @override
  Widget build(BuildContext context) {
    if(dressList==null){
      dressList = repository.getDressesList();
      count = repository.getSize();
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
                  'ADD DRESS',
                  textScaleFactor: 1.5,
                  style: TextStyle(fontSize: 10)
              ),
              onPressed: (){
                debugPrint("Add button clicked.");
                navigateToAdd();
              },
            ),
          ),

      // floatingActionButton: FloatingActionButton(
      //   onPressed: () {
      //     //todo: change the type of button to look like native version
      //     debugPrint("Add button clicked.");
      //     navigateToAdd();
      //   },
      //   tooltip: 'Add dress',
      //   child: Icon(Icons.add),
      // ),


      color: Colors.red.shade50,
      ),);
  }

  ListView getDressListView() {
    //here we define a text style then return the list view by using the builder fct
    return ListView.builder(
        itemCount: count,
        itemBuilder: (BuildContext context, int position) {
          //we have a list item that we return here as a card
          return Card(
            //here we define its elements
            color: Colors.white,
            elevation: 2.0,
            child: ListTile( //how an item looks like, shape and what it has
              //todo: place img here
              title: Text(this.dressList[position].dressCode,
                style: TextStyle(fontWeight: FontWeight.bold),),
              subtitle: Text(this.dressList[position].dressName),
              //add on tap event handler for this card
              onTap: () {
                debugPrint("Dress item clicked.");
                navigateToDetails(this.dressList[position]);
              },

            ),
          );
        },
    );
  }

  void updateDressList(){
    setState(() {
      this.dressList=repository.getDressesList();
      this.count=repository.getSize();
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