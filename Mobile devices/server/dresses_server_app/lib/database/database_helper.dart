import 'package:dresses_server_app/model/dress.dart';
import 'package:flutter/cupertino.dart';
import 'package:sqflite/sqflite.dart';
import 'dart:async';
import 'dart:io';
import 'package:path_provider/path_provider.dart';

class DatabaseHelper{
  static DatabaseHelper _databaseHelper; //singleton databaseHelper, only one instance in all classes
  static Database _database; //singleton db obj
  String dressTable='DressTable'; //table name
  //column names
  String dressIDCol='id';
  String dressCodeCol='dressCode';
  String dressNameCol='dressName';
  String dressSizeCol='dressSize';
  String dressPriceCol='dressPrice';
  String dressColourCol='dressColour';
  String dressQuantityCol='dressQuantity';
  String tailoringTimeCol='tailoringTime';
  String dressDescriptionCol='dressDescription';
  String dressPhotoCol='dressPhoto';

  DatabaseHelper._createInstance(); //named constructor to create instance of databaseHelper

  factory DatabaseHelper(){
    //factory constructor=> constructor allows to return some value

    //check as to create this class only once in app
    if(_databaseHelper==null){
      _databaseHelper=DatabaseHelper._createInstance(); //statement exec only in app
    }
    return _databaseHelper;
  }

  //create getter for the database singleton from the params
  Future<Database> get database async{
    //if null initialise, else return older instance
    if(_database==null){
      _database=await initialiseDatabase();
    }
    return _database;
  }

  //function to initialise our db with path and all
  Future<Database> initialiseDatabase() async{
    //get the directory path for Android to store the db
    Directory directory=await getApplicationDocumentsDirectory();
    String path=directory.path+'DressStore.db';

    //create the db at the path made before and return it
    var dressStoreDb=openDatabase(path,version: 1,onCreate: _createDb);
    return dressStoreDb;
  }

  void _createDb(Database db, int newVersion) async{
    //executes a statement to create our db
    await db.execute('CREATE TABLE $dressTable('
        '$dressIDCol INTEGER PRIMARY KEY AUTOINCREMENT, '
        '$dressCodeCol TEXT,$dressNameCol TEXT, $dressSizeCol INTEGER,'
        '$dressPriceCol INTEGER, $dressColourCol TEXT,'
        '$dressQuantityCol INTEGER, $tailoringTimeCol TEXT,'
        '$dressDescriptionCol TEXT, $dressPhotoCol TEXT)');
  }

  //CRUD OPERATIONS:

  //get all operation : list of map to be converted to dresses later
  Future<List<Dress>> getDressList() async{
    Database db=await this.database;
    //write in query an sql statement
    var mapsOfDresses=await db.rawQuery('SELECT * FROM $dressTable');
    var count=mapsOfDresses.length;
    List<Dress> dressList=List<Dress>();
    //convert all maps to dresses
    for(int i=0;i<count;i++){
      dressList.add(Dress.fromMap(mapsOfDresses[i]));
    }
    return dressList;
  }

  //insert operation: gets a dress and adds the mapped dress to the db
  Future<int> addDress(Dress dress) async {
    Database db=await this.database;
    //returns the id where the new dress is put
    var result=await db.insert(dressTable,dress.toMap());
    debugPrint("result de la add in db "+result.toString());
    return result;
  }

  //update operation
  Future<int> updateDress(Dress dress) async{
    Database db=await this.database;
    //returns the id from where the updated dress is
    var result=await db.update(dressTable, dress.toMap(),where: '$dressIDCol=?',whereArgs: [dress.id]);
    return result;
  }

  //delete operation
  Future<int> deleteDress(int dressID) async {
    Database db=await this.database;
    //returns the id from where the dress was deleted
    var result=await db.delete(dressTable, where: '$dressIDCol=?',whereArgs: [dressID]);
    return result;
  }

  //get count of dresses
  Future<int> getSize() async{
    Database db=await this.database;
    //execute statement which gives the count
    List<Map<String, dynamic>> resultFromStmt=await db.rawQuery('SELECT COUNT(*) FROM $dressTable');
    int count=Sqflite.firstIntValue(resultFromStmt);
    return count;
  }

}

