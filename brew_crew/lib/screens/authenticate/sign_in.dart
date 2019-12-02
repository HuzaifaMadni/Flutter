import 'package:brew_crew/services/auth.dart';
import 'package:flutter/material.dart';

class SignIn extends StatefulWidget {
  @override
  _SignInState createState() => _SignInState();
}

class _SignInState extends State<SignIn> {

  final AuthService _authService = AuthService();
  @override
  Widget build(BuildContext context) {
    return Scaffold(

      backgroundColor: Colors.brown[100],
      appBar: AppBar(
        backgroundColor: Colors.brown[400],
        title: Text('Sign In'),
        elevation: 0.0,
      ),
      body: Container(
        child: Padding(
          padding: const EdgeInsets.symmetric(vertical: 20.0, horizontal: 50.0),
          child: RaisedButton(
            onPressed: () async {
              dynamic result = await _authService.signInAnon();
              if (result == null){
                print('Error');
              } else {
                print('Success');
              }
            },
            child: Text('Sign In Anonymously'),
          ),
        ),
      ),
    );
  }
}
