package com.guiock.exquizzeat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        findViewById(R.id.startQuizzButton).setOnClickListener(this);
        findViewById(R.id.aboutButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startQuizzButton:
                //TODO: dialog to setup difficulty
                Question.getQuestions();
                Intent quizz = new Intent(this,MainActivity.class);
                quizz.putExtra("idQuestion",0);
                startActivity(quizz);
                break;
            case R.id.aboutButton:
                Intent about = new Intent(this,AboutActivity.class);
                startActivity(about);
                break;
        }
    }

}
