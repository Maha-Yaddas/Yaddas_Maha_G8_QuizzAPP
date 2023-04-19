package com.yaddas.maps;

import androidx.appcompat.app.AppCompatActivity;
import static com.yaddas.maps.MainActivity.questionsList;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class QActivity extends AppCompatActivity {
    ArrayList<Modelclass> QList;
    RadioGroup rg;
    RadioButton rb1,rb2;
    Button next;
    Modelclass Modelclass;
    TextView qstText,qzText;
    int score; int n=1;
    int index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qactivity);


        qstText = findViewById(R.id.textViewQst);
        rg = findViewById(R.id.rg);
        rb1 = findViewById(R.id.rB1);
        rb2 = findViewById(R.id.rB2);
        qzText = findViewById(R.id.textView2);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        next = findViewById(R.id.button2);

        QList = questionsList;
        Collections.shuffle(QList);
        Modelclass = questionsList.get(index);

        next.setClickable(false);
        //modifier le contenu de la 1ere question
        setAllData();

    }
    private void setAllData(){
        qstText.setText(Modelclass.getQuestion());
        rb1.setText(Modelclass.getOA());
        rb2.setText(Modelclass.getOB());
    }
    private void initialColor(){
        rb1.setBackground(getResources().getDrawable(R.drawable.rectangle));
        rb2.setBackground(getResources().getDrawable(R.drawable.rectangle));
    }

    public void Disabled(){
        rb1.setClickable(false);
        rb2.setClickable(false);
    }
    public void Enabled(){
        rb1.setClickable(true);
        rb2.setClickable(true);
    }

    private void finishQuiz(){
        finish();
    }

    public void isIncorrect(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index<questionsList.size()-1){
                    index++;
                    Modelclass =questionsList.get(index);
                    initialColor();
                    setAllData();
                    Enabled();
                    n++;
                    qzText.setText("Quiz "+n);
                    next.setClickable(false);
                }else{
                    Intent i=new Intent(QActivity.this,login.class);
                    i.putExtra("score",score);
                    startActivity(i);
                    finishQuiz();
                }
            }
        });
    }
    public void isCorrect(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index<questionsList.size()-1){
                    index++;score++;
                    Modelclass =questionsList.get(index);
                    initialColor();
                    setAllData();
                    Enabled();
                    n++;
                    qzText.setText("Quiz "+n);
                    next.setClickable(false);
                }else{
                    Intent i=new Intent(QActivity.this,login.class);
                    i.putExtra("score",score);
                    startActivity(i);
                    finishQuiz();
                }

            }
        });

    }
    public void Option1Click(View view) {
        next.setClickable(true);
        Disabled();
        if (Modelclass.getOA().equals(Modelclass.getAns())) {
            rb1.setBackgroundColor(getResources().getColor(R.color.green));
            isCorrect();


        }else{
            rb1.setBackgroundColor(getResources().getColor(R.color.red));
            isIncorrect();

        }
    }


    public void Option2Click(View view) {
        next.setClickable(true);
        Disabled();
        if (Modelclass.getOB().equals(Modelclass.getAns())) {
            rb2.setBackgroundColor(getResources().getColor(R.color.green));
            isCorrect();


        }else{
            rb2.setBackgroundColor(getResources().getColor(R.color.red));
            isIncorrect();


        }



    }
}