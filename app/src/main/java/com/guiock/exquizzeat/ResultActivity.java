package com.guiock.exquizzeat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        findViewById(R.id.backHomeButton).setOnClickListener(this);
        findViewById(R.id.restartButton).setOnClickListener(this);

        Intent srcIntent = getIntent();
        int goodAnswers = srcIntent.getIntExtra("valideAnswersNumber",0);
        TextView goodAnswersTextView = findViewById(R.id.countGoodAnswerTextView);
        goodAnswersTextView.setText("Vous avez eu " + goodAnswers + " bonnes réponses sur " + QuestionManager.getQuestions().size());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backHomeButton:
                Intent mainMenu = new Intent(this,HomePageActivity.class);
                startActivity(mainMenu);
                finish();
                break;
            case R.id.restartButton:
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
                        Intent quizz = new Intent(ResultActivity.this,MainActivity.class);
                        quizz.putExtra("idQuestion",0);
                        startActivity(quizz);
                    }
                });
                builder.create().show();
                break;
        }
    }
}
