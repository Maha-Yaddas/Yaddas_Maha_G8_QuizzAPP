package com.yaddas.maps;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {



    Button bLogout,bTry;
    ProgressBar progressBar;
    TextView tvScore;
    FirebaseAuth auth;
    int score;


    Button mapS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        tvScore =(TextView) findViewById(R.id.tvScore);
        Intent intent=getIntent();
        score=intent.getIntExtra("score",0) ;
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(100*score/5);
        tvScore.setText(100*score/5+"%");
        bLogout=(Button)findViewById(R.id.bLogout);
        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Merci de votre Participation !", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(login.this,MainActivity.class);
                startActivity(i);
                auth.signOut();



            }
        });
        bTry=(Button)findViewById(R.id.bTry);
        bTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(login.this,QActivity.class);
                startActivity(i);


            }
        });


        mapS=findViewById(R.id.map);

        mapS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,Maps.class);
                intent.putExtra("score ",score);
                startActivity(intent);
            }
        });



    }

    }
