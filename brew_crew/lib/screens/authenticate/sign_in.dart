import 'package:brew_crew/services/auth.dart';
import 'package:brew_crew/shared/constants.dart';
import 'package:brew_crew/shared/loading.dart';
import 'package:flutter/material.dart';

class SignIn extends StatefulWidget {

  final Function toggleView;
  SignIn({this.toggleView});

  @override
  _SignInState createState() => _SignInState();
}

class _SignInState extends State<SignIn> {

  final AuthService _authService = AuthService();

  final _formKey = GlobalKey<FormState>();
  
  String email = '';
  String password = '';
  String error = '';
  bool loading = false;

  @override
  Widget build(BuildContext context) {
    return loading ? Loading() : Scaffold(
      backgroundColor: Colors.brown[100],
      appBar: AppBar(
        backgroundColor: Colors.brown[400],
        title: Text('Sign In'),
        actions: <Widget>[
          FlatButton.icon(
            icon: Icon(Icons.person_pin),
            label: Text('Register'),
            onPressed: (){
              widget.toggleView();
            },
          )
        ],
      ),
      body: Container(
        child: Padding(
          padding: const EdgeInsets.symmetric(vertical: 20.0, horizontal: 50.0),
          child: Form(
            key: _formKey,
            child: Column(children: <Widget>[
              SizedBox(height: 20.0,),
              TextFormField(
                validator: (val) {
                  bool regExp = RegExp(r"^[a-zA-Z0-9.a-zA-Z0-9.!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9]+\.[a-zA-Z]+").hasMatch(val);
                  if (regExp){
                    return null;
                  } else {
                    return 'Enter a valid email address';
                  }
                },
                keyboardType: TextInputType.emailAddress,
                onChanged: (val) {
                  setState(() => email = val );
                },
                decoration: textInputDecoration.copyWith(hintText: 'Enter Email', suffixIcon: Icon(Icons.person)),
                ),
                SizedBox(height: 20.0,),
                TextFormField(
                  validator: (val) => val.length < 6 ? 'Enter a password of atleast 6 characters' : null,
                  onChanged: (val) {
                    setState(() => password = val );
                },
                obscureText: true,
                decoration: textInputDecoration.copyWith(hintText: 'Enter Password', suffixIcon: Icon(Icons.lock)),
                ),
                SizedBox(height: 20.0,),
                RaisedButton(
                  color: Colors.pink[400],
                  child: Text(
                    'Sign In',
                    style: TextStyle(color: Colors.white),
                  ),
                  onPressed: () async {
                    if (_formKey.currentState.validate()){
                      setState(() => loading = true);
                      dynamic result = await _authService.signInWithEmailandPassword(email, password);
                      if (result == null){
                        setState(() {
                          error = 'Email or Password is incorrect. Try Again';
                          loading = false;
                        });
                      }
                    }
                  }
                  ),
                  SizedBox(height: 12.0,),
                  Text(
                    error,
                    style: TextStyle(color: Colors.red, fontSize: 16.0),
                    )
            ],
            ),
            )
        ),
      ),
    );
  }
}
