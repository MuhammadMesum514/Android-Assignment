package pk.edu.uiit.mesum.assignment2.Arid2527;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StudentActivity extends AppCompatActivity {
    TextView name, email, Arid_no, password;
    Button btnchoose;
    ImageView img;
    databasehelper db;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        initialized();
        performAction();
    }
    private void initialized(){
        db = new databasehelper(this);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        Arid_no = (TextView) findViewById(R.id.Arid_no);
        password = (TextView) findViewById(R.id.pass);
        btnchoose = (Button) findViewById(R.id.btnChoose);
        img = (ImageView) findViewById(R.id.img);
    }
    private void performAction(){
        Bundle bundle = getIntent().getExtras();
        String getEmail = bundle.getString("email");
        String getPass = bundle.getString("pass");
        Cursor res = db.getData(getEmail,getPass);
        if(res.getCount() == 0){
            Toast.makeText(StudentActivity.this, "No data found", Toast.LENGTH_SHORT ).show();
        }else{
            while(res.moveToNext()){
                name.setText(res.getString(1));
                email.setText(res.getString(2));
                Arid_no.setText(res.getString(3));
                password.setText(res.getString(4));
            }
        }
        btnchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
            }

        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            img.setImageURI(imageUri);
        }
    }

}
