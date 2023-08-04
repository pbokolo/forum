package com.kitoko.forum.model;

import com.google.firebase.storage.FirebaseStorage;

public class StorageInstance {

    private static FirebaseStorage storageInstance;

    public static FirebaseStorage getInstance(){
        if(storageInstance == null){
            storageInstance = FirebaseStorage.getInstance();
        }
        return storageInstance;
    }

}
