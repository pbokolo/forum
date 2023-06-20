package com.kitoko.forum.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.kitoko.forum.databinding.BottomSheetUpdateBinding;

public class UpdateBtmSheet extends BottomSheetDialogFragment {

   private BottomSheetUpdateBinding vBinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       vBinder = BottomSheetUpdateBinding.inflate(inflater);

        return vBinder.getRoot();
    }
}
