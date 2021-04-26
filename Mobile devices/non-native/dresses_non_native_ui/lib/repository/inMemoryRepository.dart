
import 'dart:math';

import 'package:dresses_non_native_ui/model/dress.dart';

class InMemoryRepository{
  static final InMemoryRepository _repository=InMemoryRepository._internal(); //singleton repo
  static var listOfDresses=fillList(20);

  factory InMemoryRepository(){
    return _repository;
  }

  InMemoryRepository._internal();

  static String getRandomString(int length){
    String chars='AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890';
    return String.fromCharCodes(Iterable.generate(
      length, (_) => chars.codeUnitAt(Random().nextInt(chars.length))));}

  static int getRandomInt(int min, int max) => min + Random().nextInt(max - min);



  static List<Dress> fillList(int size){
    var list=new List<Dress>();
    for(int i=0;i<size;i++){
      list.add(new Dress(i+1,getRandomString(10),getRandomString(3),getRandomInt(36,46),getRandomInt(1000,10000),getRandomString(9),getRandomInt(20,50),getRandomString(8),getRandomString(100),""));
    }
    return list;
  }

  void addDress(Dress dress){
    listOfDresses.add(dress);
  }

  List<Dress> getDressesList(){
    return listOfDresses;
  }

  int getSize(){
    return listOfDresses.length;
  }

  Dress getDressByID(int dressID){
    for(var dress in listOfDresses){
      if(dress.dressID==dressID){
        return dress;
      }
    }
    return null;
  }

  void deleteDress(int dressID){
    bool found=false;
    int index=0;
    for(var dress in listOfDresses){
      if(dress.dressID==dressID){
        found=true;
        break;
      }
      index++;
    }
    if(found){
      listOfDresses.removeAt(index);
    }
  }

  void updateDress(Dress updatedDress){
    bool found=false;
    int index=0;
    for(var dress in listOfDresses){
      if(dress.dressID==updatedDress.dressID){
        found=true;
        break;
      }
      index++;
    }
    if(found){
      listOfDresses[index]=updatedDress;
    }
  }



}