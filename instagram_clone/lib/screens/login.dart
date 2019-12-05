import 'package:flutter/material.dart';
import 'package:instagram_clone/screens/register.dart';
import 'package:instagram_clone/services/auth_services.dart';

class LoginScreen extends StatefulWidget {

  static final String id = 'login_screen';
  @override
  _LoginScreenState createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {

  final _formkey = GlobalKey<FormState>();
  String email, password;

  _submit(){
    if (_formkey.currentState.validate()){
      _formkey.currentState.save();
      AuthServices.login(email, password);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Text(
              'Instagram', 
              style: TextStyle(
                fontSize: 50.0,
                fontFamily: 'Instagram'
              ),
            ),
            Form(
              key: _formkey,
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: <Widget>[
                  Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 30.0, vertical: 10.0),
                    child: TextFormField(
                      decoration: InputDecoration(
                        labelText: 'Email',
                      ),
                      validator: (val) {
                        bool regExp = RegExp(r"^[a-zA-Z0-9.a-zA-Z0-9.!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9]+\.[a-zA-Z]+").hasMatch(val);
                        if (regExp){
                          return null;
                        } else {
                          return 'Enter a valid email address';
                        }
                      },
                      onSaved: (input) => email = input,
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 30.0, vertical: 10.0),
                    child: TextFormField(
                      decoration: InputDecoration(
                        labelText: 'Password',
                      ),
                      validator: (val) => val.length < 6 ? 'Password must be of more than 6 characters' : null,
                      onSaved: (input) => password = input,
                      obscureText: true,
                    ),
                  ),
                  SizedBox(height: 20.0,),
                  Container(
                    width: 250.0,
                    child: FlatButton(
                      onPressed: _submit,
                      color: Colors.blue,
                      child: Text(
                        'Login', 
                        style: TextStyle(
                          color: Colors.white, 
                          fontSize: 18.0
                        ),
                      ),
                    ),
                  ),
                  SizedBox(height: 10.0,),
                  Container(
                    width: 250.0,
                    child: FlatButton(
                      onPressed: () => Navigator.pushNamed(context, RegisterScreen.id) ,
                      color: Colors.blue,
                      child: Text(
                        'Go to Sign Up', 
                        style: TextStyle(
                          color: Colors.white, 
                          fontSize: 18.0
                        ),
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}