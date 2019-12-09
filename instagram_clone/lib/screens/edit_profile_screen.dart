import 'dart:io';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:instagram_clone/models/user_model.dart';
import 'package:instagram_clone/services/database_service.dart';
import 'package:instagram_clone/services/storage_service.dart';

class EditProfile extends StatefulWidget {

  final User user;

  EditProfile({this.user});
  @override
  _EditProfileState createState() => _EditProfileState();
}

class _EditProfileState extends State<EditProfile> {

  String name, bio = '';
  bool isLoading = false;
  File profileImage;
  final _formkey = GlobalKey<FormState>();

  handleImageFromGallery() async {
    File imageFile = await ImagePicker.pickImage(source: ImageSource.gallery);
    if (imageFile != null) {
      setState(() {
        profileImage = imageFile;
      });
    }
  }

  @override
  void initState() {
    super.initState();
    name = widget.user.name;
    bio = widget.user.bio;
  }

  _displayProfileImage(){

    if (profileImage == null){
      if (widget.user.profileImageUrl.isEmpty){
        return AssetImage('assets/images/user_placeholder.png');
      } else {
        return CachedNetworkImageProvider(widget.user.profileImageUrl);
      }
    } else {
      return FileImage(profileImage);
    }
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.white,
        title: Text(
          'Edit Profile',
          style: TextStyle(
            color: Colors.black,
          ),
        ),
      ),
      body: GestureDetector(
          onTap: () => FocusScope.of (context).unfocus(),
          child: ListView(
            children: <Widget>[
              isLoading ? LinearProgressIndicator(
                backgroundColor: Colors.blue[200],
                valueColor: AlwaysStoppedAnimation(Colors.blue),
              ) : SizedBox.shrink(),
              Padding(
                padding: const EdgeInsets.all(30.0),
                child: Form(
                  key: _formkey,
                  child: Column(
                    children: <Widget>[
                      CircleAvatar(
                        radius: 60.0,
                        backgroundImage: _displayProfileImage(),
                      ),
                      FlatButton(
                        onPressed: handleImageFromGallery,
                        child: Text(
                          'Change Profile Image',
                          style: TextStyle(
                            color: Theme.of(context).accentColor,
                            fontSize: 16.0,
                          ),
                        ),
                      ),
                      TextFormField(
                        initialValue: name,
                        style: TextStyle(
                          fontSize: 18.0,
                        ),
                        decoration: InputDecoration(
                          icon: Icon(
                            Icons.person,
                            size: 30.0,
                          ),
                          labelText: 'Name',
                        ),
                        validator: (val) => val.trim().length < 1 ? 'Please enter a valid input' : null,
                        onSaved: (val) => name = val,
                      ),
                      TextFormField(
                        initialValue: bio,
                        onSaved: (val) => bio = val,
                        style: TextStyle(
                          fontSize: 18.0,
                        ),
                        decoration: InputDecoration(
                          icon: Icon(
                            Icons.book,
                            size: 30.0,
                          ),
                          labelText: 'Bio',
                        ),
                        validator: (val) => val.trim().length > 150 ? 'Please enter a bio less than 150 characters' : null,
                      ),
                      Container(
                        margin: EdgeInsets.all(40.0),
                        height: 40.0,
                        width: 250.0,
                        child: FlatButton(
                          onPressed: _submit,
                          color: Colors.blue,
                          textColor: Colors.white,
                          child: Text(
                            'Update Profile',
                            style: TextStyle(
                              fontSize: 18.0,
                            ),
                          ),
                        ),
                      )
                    ],
                  ),
                ),
              ),
            ],
          )
      ),
    );
  }
  _submit() async {
    if (_formkey.currentState.validate()){
      _formkey.currentState.save();

      setState(() {
        isLoading = true;
      });

      //Update user in DB

      String profileImageUrl = '';

      if (profileImage == null){
        profileImageUrl = widget.user.profileImageUrl;
      } else {
        profileImageUrl = await StorageService.uploadUserProfileImage(widget.user.profileImageUrl, profileImage);
      }

      User user = User(
        id:widget.user.id,
        name: name, bio: bio,
        profileImageUrl: profileImageUrl,
      );

      //Database update function
      DatabaseService.updateUser(user);

      Navigator.pop(context);
    }
  }
}