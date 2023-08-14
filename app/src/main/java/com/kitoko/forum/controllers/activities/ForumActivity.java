package com.kitoko.forum.controllers.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.kitoko.forum.R;

public class ForumActivity extends AppCompatActivity {

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        //Gets the host fragment
        NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager()
                .findFragmentById(R.id.navHost);

        //Next, extracts the navController from the navHostFragment
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        //Builds an app bar config from the navigation graph
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();

        //Inflates the toolbar
        Toolbar appTB = findViewById(R.id.appTB);

        setSupportActionBar(appTB);

        //Binds the toolbar, the app bar configuration and the nav controller so that
        //They work together
        NavigationUI.setupWithNavController(appTB, navController, appBarConfiguration);

    }
}