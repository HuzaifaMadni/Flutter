import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:my_todo_list/models/user.dart';
import 'package:my_todo_list/services/storage_services.dart';

class FirebaseServices {

  final FirebaseAuth _auth = FirebaseAuth.instance;
  final CollectionReference reference = Firestore.instance.collection('users');

  User _userFromFirebaseUser(FirebaseUser user){
    return user != null ? User(uid : user.uid) : null;
  }

  Stream<User> get user {
    return _auth.onAuthStateChanged
        .map((FirebaseUser user) => _userFromFirebaseUser(user));
  }  
  
  Future registerWithEmailAndPassword(String email, String password, String name) async {

    try {
      AuthResult result = await _auth.createUserWithEmailAndPassword(email: email, password: password);
      FirebaseUser user = result.user;
      await StorageService(uid: user.uid).addUserData(name);
      return _userFromFirebaseUser(user);
    } catch(e) {

      print('Error is $e');
      return null;

    }
  }

  Future signInWithEmailAndPassword(String email, String password) async{
    
    try {

      AuthResult result = await _auth.signInWithEmailAndPassword(email: email, password: password);
      FirebaseUser user = result.user;
      print(user);
      return _userFromFirebaseUser(user);

    } catch(e){
      
      print('Error is $e');
      return null;
    }
  }

  Future signOut() async {
    try {
      return await _auth.signOut();
    } catch(e) {
      print(e.toString());
      return null;
    }
  }
}