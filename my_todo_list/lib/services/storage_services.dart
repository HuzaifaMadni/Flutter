import 'package:cloud_firestore/cloud_firestore.dart';

class StorageService {

  final String uid;

  StorageService({this.uid});

  final CollectionReference _reference = Firestore.instance.collection('/users');

  Future addUserData(String name) async {
    return await _reference.document(uid).setData({
      'name' : name,
    });
  }

  Future updateUserData (String title, String desc, String date, String time) async {
    return await _reference.document(uid).setData({
      'title' : title,
      'description' : desc,
      'date' : date,
      'time' : time,
    });
  }
}