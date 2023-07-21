package com.kitoko.forum.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.kitoko.forum.R;
import com.kitoko.forum.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding vBinder;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vBinder = FragmentProfileBinding.inflate(inflater);

        return vBinder.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String phone = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        String username = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        vBinder.usrnmLbl.setText(username);
        vBinder.phoneNumberLbl.setText(phone);

        vBinder.usrnmLbl.setOnClickListener(v -> Navigation.findNavController(v)
                .navigate(ProfileFragmentDirections.profile2update(UpdateBtmSheet.USERNAME_TEXT)));
        vBinder.phoneNumberLbl.setOnClickListener(v -> Navigation.findNavController(v)
                .navigate(ProfileFragmentDirections.profile2update(UpdateBtmSheet.PHONE_TEXT)));

        vBinder.signoutBtn.setOnClickListener(v ->
            FirebaseAuth.getInstance().signOut()
        );

        vBinder.delAccBtn.setOnClickListener(v -> FirebaseAuth.getInstance().getCurrentUser().delete());

    }
}