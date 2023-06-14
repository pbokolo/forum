package com.kitoko.forum.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.kitoko.forum.R;
import com.kitoko.forum.databinding.ActivityForumBinding;

public class ForumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

       /* Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(v -> startActivity(new Intent(ForumActivity.this,
                ProfileActivity.class)));*/

    }
}