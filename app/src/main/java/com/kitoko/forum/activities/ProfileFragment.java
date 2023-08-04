package com.kitoko.forum.activities;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import android.Manifest;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kitoko.forum.databinding.FragmentProfileBinding;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    //The code for image choosing intent
    private static final int RC_CHOOSE_IMG = 1000;
    private FragmentProfileBinding vBinder;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImg;
        //Checks if the request code is the one of choosing image and if everything went well
        if(requestCode == RC_CHOOSE_IMG && resultCode == RESULT_OK) {
            if(data != null){
                selectedImg = data.getData();
                renderImage(selectedImg);
                uploadPhotoInFirebase(selectedImg);
            }
        }
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

        renderImage(Uri.parse("https://images.freeimages.com/images/large-previews/f98/black-coffee-1185883.jpg"));

        vBinder.usrnmLbl.setOnClickListener(v -> Navigation.findNavController(v)
                .navigate(ProfileFragmentDirections.profile2update(UpdateBtmSheet.USERNAME_TEXT)));

        vBinder.profileImage.setOnClickListener(v -> {
            if(isStoragePermissionGranted()){
                startActivityForResult(new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
                        RC_CHOOSE_IMG);
            }else{
                requestStoragePermission();
            }
        });

        vBinder.signoutBtn.setOnClickListener(v ->
            FirebaseAuth.getInstance().signOut()
        );

        vBinder.delAccBtn.setOnClickListener(v -> FirebaseAuth.getInstance().getCurrentUser().delete());

    }

    private void renderImage(Uri uri){
        Glide.with(getContext())
                .load(uri)
                .apply(RequestOptions.circleCropTransform()).into(vBinder.profileImage);
    }

    /**
     * Checks if the access to local storage is granted
     * @return A boolean
     */
    private boolean isStoragePermissionGranted(){

        return ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;


    }

    /**
     * Displays a prompt to request for permission
     */
    private void requestStoragePermission() {

        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

    }

    private void uploadPhotoInFirebase(Uri uri) {
        String me = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        //The path
        String path = me+"/profile/"+ me+".png";
        //The reference to the bucket
        final StorageReference storageReference = firebaseStorage.getReference(path);
        //Some meta data
        StorageMetadata storageMetadata = new StorageMetadata.Builder().
                setCustomMetadata("caption", "Profile picture").build();
        //Sends the file to the cloud
        UploadTask uploadTask = storageReference.putFile(uri, storageMetadata);
        //Creates a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Veuillez patienter");
        progressDialog.setCancelable(false);
        //Listen to the progress
        uploadTask.addOnProgressListener(snapshot -> {
            //Displays the progress dialog
            progressDialog.show();
        });

        //Gets the url of the file
        Task<Uri> getDownloadUrl = uploadTask.continueWithTask(task -> {
            if(!task.isSuccessful()){
                Toast.makeText(getContext(), "Echec",
                        Toast.LENGTH_SHORT).show();
                throw Objects.requireNonNull(task.getException());
            }
            return  storageReference.getDownloadUrl();
        });

        //Listen to the completion of the task
        getDownloadUrl.addOnCompleteListener(getActivity(), task -> {

            //Gets the result
            Uri downloadUri = task.getResult();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setPhotoUri(Uri.parse(downloadUri+""))
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            }
                        }
                    });

        });


    }
}