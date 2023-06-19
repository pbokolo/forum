package com.kitoko.forum.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kitoko.forum.R;
import com.kitoko.forum.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private FragmentMainBinding vBinder;

    public MainFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vBinder.nextBtn.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.main2profile));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vBinder = FragmentMainBinding.inflate(inflater);
        
        return vBinder.getRoot();
    }
}