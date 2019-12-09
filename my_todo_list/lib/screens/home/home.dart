import 'package:flutter/material.dart';
import 'package:my_todo_list/screens/home/add_todo.dart';
import 'package:my_todo_list/services/firebase_services.dart';
import 'package:my_todo_list/utils/settings.dart';

class Home extends StatelessWidget {
  final FirebaseServices services = FirebaseServices();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[400],
      appBar: AppBar(
        backgroundColor: Colors.green,
        title: Text(
          'Home',
          style: TextStyle(
            fontSize: 20.0,
            fontFamily: 'Crete',
          ),
        ),
        actions: <Widget>[
          FlatButton.icon(
            icon: Icon(
              Icons.settings,
              color: Colors.white,
            ),
            label: Text(
              'Settings',
              style: TextStyle(
                color: Colors.white,
                fontFamily: 'Crete',
                fontSize: 18.0,
              ),
            ),
            onPressed: () {
              Navigator.push(
                  context, MaterialPageRoute(builder: (context) => Settings()));
            },
          ),
          FlatButton.icon(
            icon: Icon(
              Icons.exit_to_app,
              color: Colors.white,
            ),
            label: Text(
              'Sign Out',
              style: TextStyle(
                fontSize: 20.0,
                fontFamily: 'Crete',
                color: Colors.white,
              ),
            ),
            onPressed: () async {
              await services.signOut();
            },
          )
        ],
      ),
      floatingActionButton: FloatingActionButton(
        backgroundColor: Colors.green,
        onPressed: () {
          Navigator.push(
              context, MaterialPageRoute(builder: (context) => Add_ToDo()));
        },
        child: Icon(
          Icons.add,
          size: 40.0,
        ),
      ),
    );
  }
}
