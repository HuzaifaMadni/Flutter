import 'package:flutter/material.dart';
import 'package:instagram_clone/services/auth_services.dart';

class Feed extends StatefulWidget {
  static final String id = "feed";
  @override
  _FeedState createState() => _FeedState();
}

class _FeedState extends State<Feed> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.blue,
      body: Center(
        child: FlatButton(
          onPressed: () => AuthServices.logOut(),
          child: Text('Log Out'),
          color: Colors.white,
        ),
      ),
    );
  }
}