import 'package:flutter/material.dart';
import 'package:flutter_rounded_date_picker/flutter_rounded_date_picker_widget.dart';
import 'package:intl/intl.dart';
import 'package:my_todo_list/screens/home/home.dart';
import 'package:my_todo_list/utils/decoration.dart';

class Add_ToDo extends StatefulWidget {
  @override
  _Add_ToDoState createState() => _Add_ToDoState();
}

class _Add_ToDoState extends State<Add_ToDo> {

  final _formkey = GlobalKey<FormState>();
  DateTime date;
  String currentDate;
  String currentTime;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[400],
      appBar: AppBar(
        backgroundColor: Colors.green,
        title: Text(
          'Add ToDo',
          style: TextStyle(
            fontFamily: 'Crete',
            fontSize: 20.0,
            color: Colors.white
          ),
        ),
        automaticallyImplyLeading: true,
        actions: <Widget>[
          FlatButton.icon(
            icon: Icon(Icons.done_all, color: Colors.white,size: 32.0,),
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
              context,
              MaterialPageRoute(builder: (context) => Home())
              );
            },
          )
        ],
      ),
      body: Padding(
        padding: EdgeInsets.all(20.0),
        child: Form(
          key: _formkey,
          child: Column(
            children: <Widget>[
              TextFormField(
                decoration: textInputDecoration.copyWith(hintText: 'Enter title', suffixIcon: Icon(Icons.title)),
                maxLines: 1,
                keyboardType: TextInputType.text,
              ),
              SizedBox(height: 20.0,),
              TextFormField(
                decoration: textInputDecoration.copyWith(hintText: 'Enter description'),
                maxLines: 4,
                keyboardType: TextInputType.multiline,
              ),
              SizedBox(height: 20.0,),
              GestureDetector(
                onTap: () {
                  FocusScope.of(context).requestFocus(new FocusNode());
                },
                child: TextFormField(
                readOnly: true,
                autofocus: false,
                showCursor: false,
                decoration: textInputDecoration.copyWith(hintText: 'Choose Date', labelText: (currentDate ?? 'Choose Date')),
                onTap: () async {
                  DateTime dateTime = await showRoundedDatePicker(
                    context: context,
                    initialDate: DateTime.now(),
                    firstDate: DateTime(DateTime.now().year - 1),
                    lastDate: DateTime(DateTime.now().year + 1),
                    borderRadius: 2, 
                    );
                    if (dateTime != null){
                      setState(() {
                        date = dateTime;
                        currentDate = DateFormat.yMd().format(date);
                      });
                    }
                  },
                ),
              ),
              SizedBox(height: 20.0,),
              
            ],
          )
        ),
      ),
    );
  }
}