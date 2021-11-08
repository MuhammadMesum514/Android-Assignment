package pk.edu.uiit.mesum.assignment2.Arid2527;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, email, Arid_no, password, cpassword;
    Button btnSubmit, btngoLogin;
    databasehelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialized();
        performAction();
    }

    private void initialized() {
        db = new databasehelper(this);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        Arid_no = (EditText) findViewById(R.id.Arid_no);
        password = (EditText) findViewById(R.id.password);
        cpassword = (EditText) findViewById(R.id.cpass);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btngoLogin = (Button) findViewById(R.id.btngoLogin);
    }

    private void performAction() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = name.getText().toString();
                String getEmail = email.getText().toString();
                String getPhone = Arid_no.getText().toString();
                String getPass = password.getText().toString();
                String getcpass = cpassword.getText().toString();
                if (getName.isEmpty() || getEmail.isEmpty() || getPhone.isEmpty() || getPass.isEmpty() || getcpass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All Fields are Required", Toast.LENGTH_SHORT).show();
                } else {
                    if (getPass.equals(getcpass)) {
                        long user_data = db.signUp(getName, getEmail, getPhone, getPass);

                        if (user_data == -1) {
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);

                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Password Not Matched", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        btngoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}