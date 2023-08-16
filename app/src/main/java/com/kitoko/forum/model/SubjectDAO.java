package com.kitoko.forum.model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.kitoko.forum.business.Subject;

public class SubjectDAO {

    private static final String SUBJECTS_COLLECTION_NAME = "subjects";
    private static final String FORUM_COLLECTION_NAME = "forum";

    public static Task<Void> create(Subject subject){
        return DBInstance.getInstance().collection(SUBJECTS_COLLECTION_NAME)
                .document(subject.getUid()).set(subject);
    }

    public static CollectionReference getAll(){
        return DBInstance.getInstance().collection(SUBJECTS_COLLECTION_NAME);
    }

}
