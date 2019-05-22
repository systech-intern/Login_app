package intern.systechd.com.location_pointer;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;
    private Button loginButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.editEmailId);
        password = findViewById(R.id.editPassword);
        loginButton = findViewById(R.id.loginButtonId);
        textView = findViewById(R.id.textViewId);


        //textView.setOnClickListener(new View.OnClickListener() {
        //@Override
        //public void onClick(View v) {
        //Intent intent = new Intent(LoginActivity.this,Registration.class);
        //startActivity(intent);

        //}


        //);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Email = email.getText().toString();
                String Password = password.getText().toString();

                if (Email.isEmpty()) {
                    email.setError("Required field");
                    email.requestFocus();
                    return;
                }
                if (Password.isEmpty())
                {
                    password.setError("Required field");
                    password.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                        email.setError("Please Enter Correct Email..");
                        email.requestFocus();
                        return;
                    }
                    if(Password.length()<6) {
                        password.setError("At least 6 character needed");
                        password.requestFocus();
                        return;

                    }
                   // else{
                        //Intent intent1 = new Intent(LoginActivity.this,Main.class)
                    //startActivity(intent1);
                //}




            }
        });


    }
}
