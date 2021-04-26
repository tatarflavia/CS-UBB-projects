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

  Dress(this._dressID,this._dressCode,this._dressName,this._dressSize,
      this._dressPrice,this._dressColour,this._dressQuantity,this._tailoringTime,[this._dressDescription,this._dressPhoto]);

  String get dressDescription => _dressDescription;

  String get tailoringTime => _tailoringTime;

  int get dressQuantity => _dressQuantity;

  String get dressColour => _dressColour;

  int get dressPrice => _dressPrice;

  int get dressSize => _dressSize;

  String get dressName => _dressName;

  String get dressCode => _dressCode;

  int get dressID => _dressID;

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

  @override
  String toString() {
    return 'Dress{_dressID: $_dressID, _dressCode: $_dressCode, _dressName: $_dressName, _dressSize: $_dressSize, _dressPrice: $_dressPrice, _dressColour: $_dressColour, _dressQuantity: $_dressQuantity, _tailoringTime: $_tailoringTime, _dressDescription: $_dressDescription, _dressPhoto: $_dressPhoto}';
  }
}