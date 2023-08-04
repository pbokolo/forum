package com.kitoko.forum.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthInstance {
    private static  FirebaseUser fbUser = null;

    public static  FirebaseUser getInstance(){
        if(fbUser == null){
            fbUser = FirebaseAuth.getInstance().getCurrentUser();
        }

        return  fbUser;
    }

}
