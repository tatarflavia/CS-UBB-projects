
import 'dart:convert';
import 'package:dresses_server_app/model/dress.dart';
import 'package:flutter/cupertino.dart';
import 'package:http/http.dart' as http;
import 'dart:async';
import 'dart:developer';



class ServerHelper{
  //this class manages all the server related business, direct requests and such
  static const HOME_URL= 'http://10.0.2.2:8000/dresses/';

  List<Dress> _parseDressListResponse(String body) {
    final parsed = jsonDecode(body).cast<Map<String, dynamic>>();

    return parsed.map<Dress>((json) => Dress.fromJson(json)).toList();
  }

  Future<List<Dress>> getAll() async {
    //makes a get all request and sends the result to other fct to be converted from json format
    final response = await http.post(HOME_URL);

    if (response.statusCode == 200) {
      return _parseDressListResponse(response.body);
    } else {
      throw Exception('Failed to get list of dresses from the server');
    }
  }

  Future<Dress> getDress(Dress dress) async {
    // makes a request to get this dress from the server
    final url = '$HOME_URL${dress.id}/';
    final response = await http.post(url);

    if (response.statusCode == 200) {
      return Dress.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to get dress from the server');
    }
  }

  Future<void> postDress(Dress dress) async {
    //puts this dress with a post req to the server to be added there
    final headers = <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    };
    debugPrint("acuma facem request post");
    http.post(HOME_URL, headers: headers, body: jsonEncode(dress.toJson()));
  }

  Future<void> putDress(Dress dress) async {
    final url = '$HOME_URL${dress.id}/';
    final headers = <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    };

    http.put(url, headers: headers, body: jsonEncode(dress.toJson()));
  }

  Future<void> deleteDress(int id) async {
    final url = '$HOME_URL$id/';
    http.delete(url);
  }

  // Future<void> syncData(List<Dress> dresses) async {
  //   //delete all req +  filling the list once again with the given one
  //   final url = '${HOME_URL}delete_all/';
  //   final headers = <String, String>{
  //     'Content-Type': 'application/json; charset=UTF-8',
  //   };
  //   await http.get(url);
  //   log(jsonEncode(dresses));
  //   await http.post(HOME_URL, headers: headers, body: jsonEncode(dresses));
  // }






}

