package com.kitoko.forum.model;

import com.google.firebase.firestore.FirebaseFirestore;

public class DBInstance {

    private static FirebaseFirestore db;

    public static FirebaseFirestore getInstance() {
        if (db == null) {
            db = FirebaseFirestore.getInstance();
        }
        return db;
    }
}
