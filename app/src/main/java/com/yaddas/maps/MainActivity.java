package com.yaddas.maps;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    TextInputEditText etLoginEmail;
    TextInputEditText etLoginPassword;
    TextView tvRegisterHere;
    Button btnLogin;

    FirebaseAuth mAuth;
    public static ArrayList<Modelclass> questionsList;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Step2 recuperation des Ids

        questionsList=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("QuizC");

        etLoginEmail=(TextInputEditText)  findViewById(R.id.etLoginEmail);
        etLoginPassword=(TextInputEditText) findViewById(R.id.etLoginPass);
        tvRegisterHere= (TextView)findViewById(R.id.tvRegisterHere);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        mAuth=FirebaseAuth.getInstance();
        //Step3 associatio de listeners
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Step 4: traitement
                String login=etLoginEmail.getText().toString();
                String password=etLoginPassword.getText().toString();
                if(TextUtils.isEmpty(login)){
                    Toast.makeText(getApplicationContext(),"Please fill in the required fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Please fill in the required fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(getApplicationContext(),"Password must be at least 6 characters",Toast.LENGTH_SHORT).show();
                    return;
                }

                ///database
                Log.d("MainActivity", "Before adding listener to databaseReference");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        Log.d("MainActivity", "Inside onDataChange");
                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                            Modelclass modelclass =dataSnapshot.getValue(Modelclass.class);
                            questionsList.add(modelclass);
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });

                //authentification
                mAuth.signInWithEmailAndPassword(login,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"User Logged in Successfully!  connexion réussi! الدخول ناجح! 註冊成功  ",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, QActivity.class);
                            startActivity(intent);

                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(),"Log in Error!"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });





            }
        });
        tvRegisterHere.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });
    }
}