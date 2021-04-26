import 'dart:collection';

import 'package:dresses_server_app/database/database_helper.dart';
import 'package:dresses_server_app/model/dress.dart';
import 'package:dresses_server_app/server/server_helper.dart';
import 'package:flutter/cupertino.dart';
import 'package:connectivity/connectivity.dart';
import 'dart:developer';


class DressNotifier with ChangeNotifier{
  //class that makes the conn between the database helper and the server helper classes
  DatabaseHelper _databaseHelper;
  ServerHelper _serverHelper;
  List<Dress> _dressList;

  DressNotifier(){
    _databaseHelper=DatabaseHelper();
    _serverHelper=ServerHelper();
    _dressList=List<Dress>();
    _syncData();
  }

  UnmodifiableListView<Dress> get dressList => UnmodifiableListView(_dressList);

  void _syncData() async{
    // await _loadDataFromLocalDB();
    // log('a pus din db');
    try{
      var connected = await (Connectivity().checkConnectivity());
      log('conn: '+connected.toString());
      if (connected == ConnectivityResult.mobile ||
          connected == ConnectivityResult.wifi) {
        _dressList=await _serverHelper.getAll();
      }

    }
    catch (e) {
      log('Data sync failed');
      log(e.toString());
    }
  }

  void _loadDataFromLocalDB() async{
    _dressList=await _databaseHelper.getDressList();
    notifyListeners();
  }

  Dress dress(int id) {
    return _dressList.firstWhere((element) => element.id == id,
        orElse: () => Dress.withID(
            -1,'Not found', 'Not found', 0,0,'Not found', 0,'Not found','Not found','Not found',
        ));
  }


  void add(String dressCode, String dressName, int dressSize, int dressPrice,String dressColour,int dressQuantity,
  String tailoringTime, String dressDescription,String dressPhoto) {
    final dress = Dress.withID(null, dressCode, dressName, dressSize, dressPrice, dressColour,
        dressQuantity, tailoringTime, dressDescription, dressPhoto);

    log("in notif inainte de post in serv");
    _postDress(dress);
    log("acuma bagam pe db");
    _databaseHelper.addDress(dress);
    _loadDataFromLocalDB();
  }

  void _postDress(dress) async {
    var connected = await (Connectivity().checkConnectivity());
    if (connected == ConnectivityResult.mobile ||
        connected == ConnectivityResult.wifi) {
      log("a mers conn si bagam pe seerv");
      _serverHelper.postDress(dress);
    }
  }

  void update(int id, String dressCode, String dressName, int dressSize, int dressPrice,String dressColour,int dressQuantity,
      String tailoringTime, String dressDescription,String dressPhoto) {
    final dress = Dress.withID(id, dressCode, dressName, dressSize, dressPrice, dressColour,
        dressQuantity, tailoringTime, dressDescription, dressPhoto);

    _putDress(dress);
    _databaseHelper.updateDress(dress);
    _loadDataFromLocalDB();
  }

  void _putDress(dress) async {
    var connected = await (Connectivity().checkConnectivity());
    if (connected == ConnectivityResult.mobile ||
        connected == ConnectivityResult.wifi) {
      _serverHelper.putDress(dress);
    }
  }

  void delete(int id) {
    _deleteDress(id);
    _databaseHelper.deleteDress(id);
    _loadDataFromLocalDB();
  }

  void _deleteDress(id) async {
    var connected = await (Connectivity().checkConnectivity());
    if (connected == ConnectivityResult.mobile ||
        connected == ConnectivityResult.wifi) {
      _serverHelper.deleteDress(id);
    }
  }

}