package com.kitoko.forum.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.kitoko.forum.databinding.BottomSheetUpdateBinding;

public class UpdateBtmSheet extends BottomSheetDialogFragment {

    public static final String USERNAME_TEXT= "username";
    public static final String EMAIL_TEXT= "email";
    public static final String PHONE_TEXT= "PHONE";

   private BottomSheetUpdateBinding vBinder;
   private String toDo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toDo = UpdateBtmSheetArgs.fromBundle(getArguments()).getProperty();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       vBinder = BottomSheetUpdateBinding.inflate(inflater);
       setTitle();
       vBinder.submitBtn.setOnClickListener(v -> update());



        return vBinder.getRoot();
    }

    private void setTitle(){
        String hint="Nouveau";
        String title = "Modifiez";
        switch (toDo){
            case USERNAME_TEXT:
                title += "Nom d'utilisateur";
                hint += " "+"nom";
                break;
            case PHONE_TEXT:
                title += "Numéro de téléphone";
                hint += " "+ "numéro de téléphone";
                break;
            case EMAIL_TEXT:
                title += "Email";
                hint = "Nouvel email";
                break;
            default:
                vBinder.titleLbl.setText("Modifiez");
                break;
        }

        vBinder.titleLbl.setText(title);
        vBinder.newValueTxt.setHint(hint);
    }

    private void update(){

    }
}
