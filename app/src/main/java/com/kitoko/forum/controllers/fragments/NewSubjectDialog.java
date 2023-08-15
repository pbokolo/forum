package com.kitoko.forum.controllers.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.kitoko.forum.R;
import com.kitoko.forum.business.Subject;
import com.kitoko.forum.model.AuthInstance;
import com.kitoko.forum.model.SubjectDAO;

import java.util.Date;

public class NewSubjectDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        //Inflates the layout view
        View layoutView = getLayoutInflater().inflate(R.layout.dialog_new_subject, null);

        EditText titleTxt = layoutView.findViewById(R.id.titleTxt);

        Button cancelBtn = layoutView.findViewById(R.id.cancelButton),
                createBtn = layoutView.findViewById(R.id.createBtn);

        cancelBtn.setOnClickListener(v -> dismiss());
        createBtn.setOnClickListener(v -> {

            Subject subject = new Subject();
            subject.setTitle(titleTxt.getText().toString());
            subject.setCreator(AuthInstance.getInstance().getUid());
            subject.setDateCreated(new Date());

            SubjectDAO.create(subject);

            dismiss();
        });

        dialogBuilder.setView(layoutView);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.setTitle(getString(R.string.new_subject_text));

        return alertDialog;
    }
}
