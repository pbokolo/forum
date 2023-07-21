package com.kitoko.forum.activities;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       vBinder = BottomSheetUpdateBinding.inflate(inflater);
       setTitle();
       vBinder.submitBtn.setOnClickListener(v -> update());



        return vBinder.getRoot();
    }

    private void setTitle(){
        String hint="Nouveau";
        String title = "";
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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest profileUpdates;

        switch (toDo){
            case USERNAME_TEXT:
                 profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(vBinder.newValueTxt.getText().toString())
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();
                                dismiss();
                            }else{
                                Toast.makeText(getContext(), "Not updated", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case PHONE_TEXT:

                break;
            case EMAIL_TEXT:
                profileUpdates = new UserProfileChangeRequest.Builder()
                        .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            default:
                vBinder.titleLbl.setText("Modifiez");
                break;
        }
    }
}
