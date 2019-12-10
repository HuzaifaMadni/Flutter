import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_rounded_date_picker/flutter_rounded_date_picker_widget.dart';
import 'package:intl/intl.dart';
import 'package:my_todo_list/screens/home/home.dart';
import 'package:my_todo_list/services/storage_services.dart';
import 'package:my_todo_list/utils/decoration.dart';
import 'package:my_todo_list/utils/loading.dart';

class Add_ToDo extends StatefulWidget {
  @override
  _Add_ToDoState createState() => _Add_ToDoState();
}

class _Add_ToDoState extends State<Add_ToDo> {
  final _formkey = GlobalKey<FormState>();
  DateTime date, time;
  String currentDate;
  String currentTime;
  String title, desc = '';
  bool isLoading = false;

  @override
  Widget build(BuildContext context) {
    MaterialLocalizations localizations = MaterialLocalizations.of(context);
    return Scaffold(
      backgroundColor: Colors.grey[400],
      appBar: AppBar(
        backgroundColor: Colors.green,
        title: Text(
          'Add ToDo',
          style: TextStyle(
              fontFamily: 'Crete', fontSize: 20.0, color: Colors.white),
        ),
        automaticallyImplyLeading: true,
        actions: <Widget>[
          FlatButton.icon(
            icon: Icon(
              Icons.done_all,
              color: Colors.white,
              size: 32.0,
            ),
            label: Text(
              '',
              style: TextStyle(
                fontSize: 20.0,
                fontFamily: 'Crete',
                color: Colors.white,
              ),
            ),
            onPressed: () {
              Navigator.push(
                  context, MaterialPageRoute(builder: (context) => Home()));
            },
          )
        ],
      ),
      body: isLoading
          ? Loading()
          : GestureDetector(
              onTap: () => FocusScope.of(context).unfocus(),
              child: SingleChildScrollView(
                child: Container(
                  height: MediaQuery.of(context).size.height,
                  child: Padding(
                    padding: EdgeInsets.all(20.0),
                    child: Form(
                      key: _formkey,
                      child: Column(
                        children: <Widget>[
                          TextFormField(
                            decoration: textInputDecoration.copyWith(
                              hintText: 'Enter title',
                              suffixIcon: Icon(Icons.title)),
                            maxLines: 1,
                            keyboardType: TextInputType.text,
                            validator: (val) {
                              if (val.isEmpty) {
                                return 'Please enter a valid title';
                              } else {
                                return null;
                              }
                            },
                            onChanged: (val) {
                              setState(() {
                                title = val;
                              });
                            },
                          ),
                          SizedBox(
                            height: 20.0,
                          ),
                          TextFormField(
                            decoration: textInputDecoration.copyWith(
                              hintText: 'Enter description'),
                            maxLines: 4,
                            keyboardType: TextInputType.multiline,
                            validator: (val) {
                              if (val.length < 10 || val.length > 150) {
                                return 'The title should be atleast 10 characters and not more than 150 characters';
                              } else {
                                return null;
                              }
                            },
                            onChanged: (val) {
                              setState(() {
                                desc = val;
                              });
                            },
                          ),
                          SizedBox(
                            height: 20.0,
                          ),
                          GestureDetector(
                            onTap: () {
                              FocusScope.of(context)
                                .requestFocus(new FocusNode());
                            },
                            child: TextFormField(
                              readOnly: true,
                              autofocus: false,
                              showCursor: false,
                              decoration: textInputDecoration.copyWith(
                                labelText: currentDate ?? 'Choose Date',
                                hintText: 'Choose Date',
                              ),
                              onTap: () async {
                                DateTime dateTime =
                                    await showRoundedDatePicker(
                                  context: context,
                                  initialDate: DateTime.now(),
                                  firstDate:
                                      DateTime(DateTime.now().year - 1),
                                  lastDate: DateTime(DateTime.now().year + 1),
                                  borderRadius: 2,
                                );
                                if (dateTime != null) {
                                  setState(() {
                                    date = dateTime;
                                    currentDate =
                                        DateFormat.yMd().format(date);
                                  });
                                }
                              },
                            ),
                          ),
                          SizedBox(
                            height: 20.0,
                          ),
                          GestureDetector(
                            onTap: () {
                              FocusScope.of(context)
                                  .requestFocus(new FocusNode());
                            },
                            child: TextFormField(
                              readOnly: true,
                              autofocus: false,
                              showCursor: false,
                              decoration: textInputDecoration.copyWith(
                                  hintText: 'Choose Time',
                                  labelText: currentTime ?? 'Choose Time'),
                              onTap: () async {
                                TimeOfDay _currentTime = new TimeOfDay.now();
                                TimeOfDay selectedTime = await showTimePicker(
                                    context: context,
                                    initialTime: _currentTime);
                                if (selectedTime != null) {
                                  setState(() {
                                    currentTime = localizations
                                        .formatTimeOfDay(selectedTime,
                                            alwaysUse24HourFormat: false);
                                  });
                                }
                              },
                            ),
                          ),
                          SizedBox(
                            height: 20.0,
                          ),
                          Container(
                            width: 250.0,
                            child: RaisedButton(
                              child: Text(
                                'Add',
                                style: TextStyle(
                                    color: Colors.white,
                                    fontFamily: 'Crete',
                                    fontSize: 18.0),
                              ),
                              color: Colors.green,
                              onPressed: () async {
                                isLoading = true;
                                FirebaseUser result1 = (await FirebaseAuth
                                    .instance
                                    .currentUser());
                                String uid = result1.uid;
                                await StorageService(uid: uid).updateUserData(
                                    title, desc, currentDate, currentTime);
                                Navigator.pop(context);
                              },
                            ),
                          ),
                        ],
                      )),
                ),
              ),
            ),
          ),
  );
}
}
