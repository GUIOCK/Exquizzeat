package com.guiock.exquizzeat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("choix de la difficulté");
                String[] entries = {"Facile", "Moyen", "Difficile"};
                builder.setItems(entries,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("HomePage","TestDialog" + which);
                        Question.difficulty choosenDifficulty = Question.difficulty.values()[which];
                        QuestionManager.setActualDifficulty(choosenDifficulty);
                        QuestionManager.generateShuffledList(choosenDifficulty);
                        Intent quizz = new Intent(HomePageActivity.this,MainActivity.class);
                        quizz.putExtra("idQuestion",0);
                        startActivity(quizz);
                    }
                });
                builder.create().show();
                break;
            case R.id.aboutButton:
                Intent about = new Intent(this,AboutActivity.class);
                startActivity(about);
                break;
        }
    }

}
