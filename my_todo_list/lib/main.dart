import 'package:flutter/material.dart';
import 'package:my_todo_list/Wrapper.dart';
import 'package:my_todo_list/models/user.dart';
import 'package:my_todo_list/services/firebase_services.dart';
import 'package:provider/provider.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return StreamProvider<User>.value(
      value: FirebaseServices().user,
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        home: Wrapper(),
      ),
    );
  }
}