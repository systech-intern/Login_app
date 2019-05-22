package intern.systechd.com.location_pointer.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
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
                Toast.makeText(getApplicationContext(),"Waiting for Login Activity",Toast.LENGTH_LONG).show();
            }
        });
    }

}
