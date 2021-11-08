package pk.edu.uiit.mesum.assignment2.Arid2527;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button btnLogin, btngoSubmit;
    databasehelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialized();
        performAction();
    }

    private void initialized(){
        db = new databasehelper(this);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btngoSubmit = (Button) findViewById(R.id.btngoSignup);
    }

    private void performAction(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmail = email.getText().toString();
                String getPass = password.getText().toString();
                if(getEmail.isEmpty() || getPass.isEmpty()){
                    Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkUser = db.checkUser(getEmail,getPass);
                    if (checkUser== true)
                    {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
                        intent.putExtra("email",getEmail);
                        intent.putExtra("pass",getPass);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT ).show();
                    }
                }
            }
        });
        btngoSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }}