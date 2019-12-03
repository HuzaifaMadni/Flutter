import 'package:brew_crew/models/user.dart';
import 'package:brew_crew/services/database.dart';
import 'package:brew_crew/shared/loading.dart';
import 'package:flutter/material.dart';
import 'package:brew_crew/shared/constants.dart';
import 'package:provider/provider.dart';

class Settings extends StatefulWidget {
  @override
  _SettingsState createState() => _SettingsState();
}

class _SettingsState extends State<Settings> {

  final _formkey = GlobalKey<FormState>();
  final List<String> sugars = ['0', '1', '2', '3', '4'];

  String _currentname;
  String _currentsugar;
  int _currentstrength;

  @override
  Widget build(BuildContext context) {

    final user = Provider.of<User>(context);

    return StreamBuilder<UserData>(
      stream: DatabaseService(uid: user.uid).userData,
      builder: (context, snapshot) {
        if (snapshot.hasData){
          UserData userData = snapshot.data;
          return Form(
            key: _formkey,
            child: Column(
              children: <Widget>[
                Text(
                  'Update your Brew Settings',
                  style: TextStyle(
                    fontSize: 18.0,
                  ),
                ),
                SizedBox(height: 20.0,),
                TextFormField(
                  initialValue: userData.name,
                  decoration: textInputDecoration.copyWith(hintText: 'Enter Name', suffixIcon: Icon(Icons.person)),
                  validator: (val) => val.isEmpty ? 'Please enter a name' : null,
                  onChanged: (val) => setState(() => _currentname = val),
                ),
                SizedBox(height: 20.0,),
                //dropdown
                DropdownButtonFormField(
                  decoration: textInputDecoration,
                  value: _currentsugar ?? userData.sugars,
                  items: sugars.map((sugar){
                    return DropdownMenuItem(
                      value: sugar,
                      child: Text('$sugar sugars'),
                    );
                  }).toList(),
                  onChanged: (val) => setState(() => _currentsugar = val),
                ),
                //slider
                Slider(
                  min: 100,
                  max: 900,
                  divisions: 8,
                  onChanged: (val) => setState(() => _currentstrength = val.round()),
                  value: (_currentstrength ?? userData.strength).toDouble(),
                  inactiveColor: Colors.brown[_currentstrength ?? userData.strength],
                  activeColor: Colors.brown[_currentstrength ?? userData.strength],
                ),
                RaisedButton(
                  color: Colors.pink[400],
                  child: Text(
                    'Update',
                    style: TextStyle(
                    color: Colors.white
                    ),
                  ),
                  onPressed: () async {
                    if (_formkey.currentState.validate()){
                      await DatabaseService(uid: user.uid).updateUserData(
                        _currentsugar ?? userData.sugars, 
                        _currentname ?? userData.name, 
                        _currentstrength ?? userData.strength
                      );
                      Navigator.pop(context);
                    }
                  },
                )
              ],
            ),
          );
        } else {
          return Loading();
        }
        
      }
    );
  }
}