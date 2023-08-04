package com.kitoko.forum.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.kitoko.forum.databinding.BottomSheetUpdateBinding;
import com.kitoko.forum.model.AuthInstance;

public class UpdateBtmSheet extends BottomSheetDialogFragment {

    public static final String USERNAME_TEXT = "username";
    public static final String PHONE_TEXT = "PHONE";

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
        return vBinder.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        vBinder.submitBtn.setOnClickListener(v -> update());
    }

    private void update() {
        FirebaseUser user = AuthInstance.getInstance();
        UserProfileChangeRequest profileUpdates;

        switch (toDo) {
            case USERNAME_TEXT:
                profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(vBinder.newValueTxt.getText().toString())
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();
                                dismiss();
                            } else {
                                Toast.makeText(getContext(), "Not updated", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case PHONE_TEXT:

                break;

        }
    }
}
