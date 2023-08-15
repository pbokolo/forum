package com.kitoko.forum.model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;
import com.kitoko.forum.business.Subject;

public class SubjectDAO {

    private static final String SUBJECTS_COLLECTION_NAME = "subjects";

    public static Task<Void> create(Subject subject){
        return DBInstance.getInstance().collection(SUBJECTS_COLLECTION_NAME)
                .document(subject.getUid()).set(subject);
    }

    public static Task<QuerySnapshot> getSubjects(){
        return DBInstance.getInstance().collection(SUBJECTS_COLLECTION_NAME).get();
    }

}
