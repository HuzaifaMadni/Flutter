import 'package:flutter/material.dart';

class Settings extends StatefulWidget {
  @override
  _SettingsState createState() => _SettingsState();
}

class _SettingsState extends State<Settings> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[400],
      appBar: AppBar(
        backgroundColor: Colors.green,
        title: Text(
          'Settings',
          style: TextStyle(
            fontFamily: 'Crete',
            fontSize: 20.0,
          ),
          ),
        automaticallyImplyLeading: true,
      ),
    );
  }
}