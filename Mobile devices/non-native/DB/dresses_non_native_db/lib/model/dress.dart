import 'dart:core';


class Dress{
  int _dressID;
  String _dressCode;
  String _dressName;
  int _dressSize;
  int _dressPrice;
  String _dressColour;
  int _dressQuantity;
  String _tailoringTime;
  String _dressDescription;
  String _dressPhoto;

  Dress.withID(this._dressID,this._dressCode,this._dressName,this._dressSize,
      this._dressPrice,this._dressColour,this._dressQuantity,
      this._tailoringTime,[this._dressDescription,this._dressPhoto]);

  Dress(this._dressCode,this._dressName,this._dressSize,
      this._dressPrice,this._dressColour,this._dressQuantity,
      this._tailoringTime,[this._dressDescription,this._dressPhoto]);

  String get dressDescription => _dressDescription;

  String get tailoringTime => _tailoringTime;

  int get dressQuantity => _dressQuantity;

  String get dressColour => _dressColour;

  int get dressPrice => _dressPrice;

  int get dressSize => _dressSize;

  String get dressName => _dressName;

  String get dressCode => _dressCode;

  int get dressID => _dressID;


  String get dressPhoto => _dressPhoto;

  set dressDescription(String value) {
    _dressDescription = value;
  }

  set tailoringTime(String value) {
    _tailoringTime = value;
  }

  set dressQuantity(int value) {
    _dressQuantity = value;
  }

  set dressColour(String value) {
    _dressColour = value;
  }

  set dressPrice(int value) {
    _dressPrice = value;
  }

  set dressSize(int value) {
    _dressSize = value;
  }

  set dressName(String value) {
    _dressName = value;
  }

  set dressCode(String value) {
    _dressCode = value;
  }


  set dressPhoto(String value) {
    _dressPhoto = value;
  }

  @override
  String toString() {
    return 'Dress{_dressID: $_dressID, _dressCode: $_dressCode, _dressName: $_dressName, _dressSize: $_dressSize, _dressPrice: $_dressPrice, _dressColour: $_dressColour, _dressQuantity: $_dressQuantity, _tailoringTime: $_tailoringTime, _dressDescription: $_dressDescription, _dressPhoto: $_dressPhoto}';
  }

  Map<String,dynamic> toMap(){
    var map=Map<String, dynamic>();
      if(_dressID!=null){
        map['dressID']=_dressID;
      }

    map['dressCode']=_dressCode;
    map['dressName']=_dressName;
    map['dressSize']=_dressSize;
    map['dressPrice']=_dressPrice;
    map['dressColour']=_dressColour;
    map['dressQuantity']=_dressQuantity;
    map['tailoringTime']=_tailoringTime;
    map['dressDescription']=_dressDescription;
    map['dressPhoto']=_dressPhoto;

    return map;
  }

  Dress.fromMap(Map<String,dynamic> map){
    _dressID=map['dressID'];
    _dressCode=map['dressCode'];
    _dressName=map['dressName'];
    _dressSize=map['dressSize'];
    _dressPrice=map['dressPrice'];
    _dressColour=map['dressColour'];
    _dressQuantity=map['dressQuantity'];
    _tailoringTime=map['tailoringTime'];
    _dressDescription=map['dressDescription'];
    _dressPhoto=map['dressPhoto'];
  }


}