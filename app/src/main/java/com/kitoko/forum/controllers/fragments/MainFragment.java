package com.kitoko.forum.controllers.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;
import com.kitoko.forum.R;
import com.kitoko.forum.business.Subject;
import com.kitoko.forum.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private FragmentMainBinding vBinder;

    public MainFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mni_profile){
            Navigation.findNavController(vBinder.newSubjectBtn).navigate(MainFragmentDirections.main2profile());
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vBinder.newSubjectBtn.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.main2newSubject));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vBinder = FragmentMainBinding.inflate(inflater);
        
        return vBinder.getRoot();
    }

    private FirestoreRecyclerOptions<Subject> generateOptions(Query query){
        return new FirestoreRecyclerOptions.Builder<Subject>()
                .setQuery(query, Subject.class)
                .setLifecycleOwner(this)
                .build();
    }


}