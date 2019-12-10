import 'package:flutter/material.dart';
import 'package:my_todo_list/services/firebase_services.dart';
import 'package:my_todo_list/utils/decoration.dart';
import 'package:my_todo_list/utils/loading.dart';

class Register extends StatefulWidget {
  final Function toggleView;

  Register({this.toggleView});

  @override
  _RegisterState createState() => _RegisterState();
}

class _RegisterState extends State<Register> {
  final _formkey = GlobalKey<FormState>();
  final FirebaseServices services = FirebaseServices();

  String email;
  String password;
  String name;
  bool loading = false;
  String error = '';

  @override
  Widget build(BuildContext context) {
    return loading
        ? Loading()
        : Scaffold(
            backgroundColor: Colors.grey[400],
            appBar: AppBar(
              backgroundColor: Colors.green,
              title: Text(
                'Register',
                style: TextStyle(
                  fontSize: 24.0,
                  fontFamily: 'Crete',
                ),
              ),
              actions: <Widget>[
                FlatButton.icon(
                  icon: Icon(
                    Icons.person_pin,
                    color: Colors.white,
                  ),
                  label: Text(
                    'Sign In',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 20.0,
                      fontFamily: 'Crete',
                    ),
                  ),
                  onPressed: () {
                    widget.toggleView();
                  },
                )
              ],
            ),
            body: GestureDetector(
              onTap: () => FocusScope.of(context).unfocus(),
              child: SingleChildScrollView(
                child: Container(
                  height: MediaQuery.of(context).size.height,
                  child: Padding(
                    padding: const EdgeInsets.symmetric(
                        vertical: 30.0, horizontal: 50.0),
                    child: Form(
                      key: _formkey,
                      child: Column(
                        children: <Widget>[
                          SizedBox(
                            height: 20.0,
                          ),
                          TextFormField(
                            validator: (val) {
                              bool regExp = RegExp(
                                      r"^[a-zA-Z0-9.a-zA-Z0-9.!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9]+\.[a-zA-Z]+")
                                  .hasMatch(val);
                              if (regExp) {
                                return null;
                              } else {
                                return 'Enter a valid email address';
                              }
                            },
                            onChanged: (val) {
                              setState(() => email = val);
                            },
                            decoration: textInputDecoration.copyWith(
                                hintText: 'Email', suffixIcon: Icon(Icons.email)),
                          ),
                          SizedBox(
                            height: 20.0,
                          ),
                          TextFormField(
                            onChanged: (val) {
                              setState(() => name = val);
                            },
                            decoration: textInputDecoration.copyWith(
                                hintText: 'Name',
                                suffixIcon: Icon(Icons.person_pin_circle)),
                          ),
                          SizedBox(
                            height: 20.0,
                          ),
                          TextFormField(
                            onChanged: (val) {
                              setState(() {
                                password = val;
                              });
                            },
                            validator: (val) {
                              if (val.length < 6) {
                                return 'Password must be greater than 6 characters';
                              } else {
                                return null;
                              }
                            },
                            decoration: textInputDecoration.copyWith(
                                hintText: 'Password',
                                suffixIcon: Icon(Icons.lock)),
                            obscureText: true,
                          ),
                          SizedBox(
                            height: 20.0,
                          ),
                          RaisedButton(
                            color: Colors.green,
                            onPressed: () async {
                              if (_formkey.currentState.validate()) {
                                setState(() => loading = true);
                                dynamic result =
                                    await services.registerWithEmailAndPassword(
                                        email, password, name);
                                if (result == null) {
                                  setState(() {
                                    loading = false;
                                    error = 'Error occured. Please try again';
                                  });
                                }
                                print(result);
                              }
                            },
                            child: (Text(
                              'Register',
                              style: TextStyle(
                                fontFamily: 'Crete',
                                fontSize: 18.0,
                                color: Colors.white,
                              ),
                            )),
                          ),
                          SizedBox(
                            height: 20.0,
                          ),
                          Center(
                            child: Text(
                              error,
                              style: TextStyle(
                                fontSize: 18.0,
                                color: Colors.red,
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                ),
              ),
            ),
          );
  }
}
