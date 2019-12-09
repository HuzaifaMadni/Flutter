import 'package:flutter/material.dart';

class PostScreen extends StatefulWidget {
  @override
  _PostScreenState createState() => _PostScreenState();
}

class _PostScreenState extends State<PostScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        centerTitle: true,
        title: Text(
          'Instagram',
          style: TextStyle(
            fontFamily: 'Instagram',
            color: Colors.black,
            fontSize: 35.0,
          ),
        ),
      ),
      body: Center(
        child: Text('Camera'),
      ),
    );
  }
}
