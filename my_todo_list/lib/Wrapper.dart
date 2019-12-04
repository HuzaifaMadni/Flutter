import 'package:flutter/material.dart';
import 'package:my_todo_list/models/user.dart';
import 'package:my_todo_list/screens/home/home.dart';
import 'package:my_todo_list/screens/onboarding/authenticate.dart';
import 'package:provider/provider.dart';

class Wrapper extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    
    final user = Provider.of<User>(context);
    print(user);

    if (user == null){
      return Authenticate();
    } else {
      return Home();
    }
  }
}