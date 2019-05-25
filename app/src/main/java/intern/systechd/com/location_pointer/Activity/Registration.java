package intern.systechd.com.location_pointer.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import intern.systechd.com.location_pointer.R;

public class Registration extends AppCompatActivity {

    private ImageView userImage,completeImage;
    private Button uploadButton, registerButton;
    private TextView showPhotoNameText,loginHereText;
    private EditText userName,userEmail,userPassword, re_typePassword;

    private static final int PICK_IMAGE = 1;
    private static final int REQUEST_CODE = 12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        userImage = findViewById(R.id.profile_image);
        completeImage = findViewById(R.id.completeImageId);

        uploadButton = findViewById(R.id.uploadButtonId);
        registerButton = findViewById(R.id.registerButtonId);

        showPhotoNameText = findViewById(R.id.txtPhotoNameId);
        loginHereText = findViewById(R.id.loginHereId);

        userName = findViewById(R.id.editNameId);
        userEmail = findViewById(R.id.editEmailId);
        userPassword = findViewById(R.id.editPassword);
        re_typePassword = findViewById(R.id.editRe_typePassId);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name =userName.getText().toString();
                String email =userEmail.getText().toString();
                String password =userPassword.getText().toString();
                String re_typePass =re_typePassword.getText().toString();

                if (TextUtils.isEmpty(name)){
                    userName.setError("Required Field...");
                    userName.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    userEmail.setError("Required Field...");
                    userEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    userEmail.setError("Please Enter Correct Email..");
                    userEmail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    userPassword.setError("Required Field...");
                    userPassword.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(re_typePass)){
                    re_typePassword.setError("Required Field...");
                    re_typePassword.requestFocus();
                    return;
                }
                if (userPassword.length() < 6){
                    userPassword.setError("Password at least 6 digit");
                    userPassword.requestFocus();
                    return;
                }

                if (!password.equals(re_typePass)){
                    re_typePassword.setError("password not match...");
                    re_typePassword.requestFocus();
                    return;

                }
            }
        });

        loginHereText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(Registration.this,LoginActivity.class));
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check runtime permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){

                        //permission not granted,request it.
                        String [] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

                        //show pop up for runtime permission
                        requestPermissions(permissions,REQUEST_CODE);
                    }else {
                        //permission is already granted
                        pickImageFromGallary();
                    }
                }else {
                    //system os is less then marshmallow
                    pickImageFromGallary();
                }

            }
        });
    }

    private void pickImageFromGallary() {

        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,PICK_IMAGE);
    }

    // handle result of runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case REQUEST_CODE: {
                if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission was granted
                    pickImageFromGallary();
                }else {
                    //permission was denied
                    Toast.makeText(getApplicationContext(),"Permission Denied....",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    // handled result for pick image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){

            //set image to image view
            userImage.setImageURI(data.getData());

            //get selected image name
            Uri selectedImageUri = data.getData();
            String s= getRealPathFromURI(selectedImageUri);
            String imageName = s.substring(s.lastIndexOf("/") + 1);
            // instead of "/" you can also use File.sepearator

            ///set imageName to textView
            showPhotoNameText.setText(imageName);

            //set green complete image to image view
            completeImage.setVisibility(View.VISIBLE);
        }
    }

    ///get image real path from Uri
    public String getRealPathFromURI(Uri contentUri) {
        // can post image
        String [] proj={MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery( contentUri,
                proj, // Which columns to return
                null, // WHERE clause; which rows to return (all rows)
                null, // WHERE clause selection arguments (none)
                null); // Order-by clause (ascending by name)
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);

    }
}
