import 'dart:io';

import 'package:firebase_storage/firebase_storage.dart';
import 'package:flutter_image_compress/flutter_image_compress.dart';
import 'package:instagram_clone/utils/constants.dart';
import 'package:path_provider/path_provider.dart';
import 'package:uuid/uuid.dart';

class StorageService {

  

  static Future<File> compressImage(String photoId, File imageFile) async {
    final tempDir = await getTemporaryDirectory();
    final path = tempDir.path;
    File compressedImage = await FlutterImageCompress.compressAndGetFile(
      imageFile.absolute.path, 
      '$path/img_$photoId.jpg',
      quality: 70,
      );
      return compressedImage;
  }

  static Future<String>uploadUserProfileImage(String url, File imageFile) async {
    String photoID = Uuid().v4();
    File image = await compressImage(photoID, imageFile);
    StorageUploadTask uploadTask = storageRef.child('images/users/userProfile_$photoID.jpg')
    .putFile(image);

    StorageTaskSnapshot storageTaskSnapshot = await uploadTask.onComplete;
    String downloadUrl = await storageTaskSnapshot.ref.getDownloadURL();
    return downloadUrl;
  }
}