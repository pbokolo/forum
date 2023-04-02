package com.kitoko.forum.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.kitoko.forum.R;
import com.kitoko.forum.databinding.ActivityMainBinding;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding vBinder;

    private ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            (result) -> onSignInResult(result));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vBinder = ActivityMainBinding.inflate(getLayoutInflater());
        vBinder.conBtn.setOnClickListener((v) -> startSignIn());
        if (isSignedIn()) {
            startForum();
        } else {
            startSignIn();
        }
    }

    private void startForum(){
        startActivity(new Intent(this, ForumActivity.class));
        this.finish();
    }
    private void startSignIn() {
        Intent signInIntent =
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.GoogleBuilder().build(),
                                new AuthUI.IdpConfig.EmailBuilder().build(),
                                new AuthUI.IdpConfig.PhoneBuilder().build()))
                        .build();
        signInLauncher.launch(signInIntent);
    }

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Succes de l'opération
            startForum();
            finish();
        } else {
            // Echec de l'opération
            if (response == null) {
                // L'utilisateur a appuyé le boutton retour
                Toast.makeText(this, "Annulée", Toast.LENGTH_LONG).show();
                return;
            }

            // Pas de connexion Internet
            if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                Toast.makeText(this, "Pas de connexion Internet", Toast.LENGTH_LONG).show();
                return;
            }
            // Autre chose
            Toast.makeText(this, "Erreur inconnue", Toast.LENGTH_LONG).show();
        }
    }

   private boolean isSignedIn(){
       return FirebaseAuth.getInstance().getCurrentUser() != null;
    }
}