package com.guiock.exquizzeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    List<RadioButton> buttonList = new ArrayList<>();
    Question question;
    boolean isQuestionAnswered = false;
    int correctAnsweredQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent srcIntent = getIntent();
        correctAnsweredQuestion = srcIntent.getIntExtra("valideAnswersNumber",0);
        question = QuestionManager.getQuestion(srcIntent.getIntExtra("idQuestion",0));
        buttonList.add((RadioButton)findViewById(R.id.proposition0Radio));
        buttonList.add((RadioButton)findViewById(R.id.proposition1Radio));
        buttonList.add((RadioButton)findViewById(R.id.proposition2Radio));
        buttonList.add((RadioButton)findViewById(R.id.proposition3Radio));
        for(RadioButton button : buttonList) {
            button.setOnClickListener(this);
        }
        fillQuestionViews();
        findViewById(R.id.ValidateButton).setOnClickListener(this);
        findViewById(R.id.questionImageView).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.proposition0Radio:
            case R.id.proposition1Radio:
            case R.id.proposition2Radio:
            case R.id.proposition3Radio:
                setRadioCheck(v.getId());
                break;
            case R.id.ValidateButton:
                if(isQuestionAnswered){
                    nextQuestion();
                } else {
                    compareAnswers();
                }
                break;
            case R.id.questionImageView:
                Intent zoom = new Intent(this,ImageViewActivity.class);
                zoom.putExtra("imgId",question.getImgId());
                startActivity(zoom);
                break;
        }
    }

    public void fillQuestionViews(){
        ImageView img = findViewById(R.id.questionImageView);
        img.setImageResource(question.getImgId());

        TextView questionLabel = findViewById(R.id.questionTextView);
        if(question.isVegetable()){
            questionLabel.setText(R.string.questionLabelVegetable);
        } else {
            questionLabel.setText(R.string.questionLabelDish);
        }
        List<String> possibleAnswers = question.getPossibleAnswers();
        List<RadioButton> answers = new ArrayList<>();
        answers.add((RadioButton)findViewById(R.id.proposition0Radio));
        answers.add((RadioButton)findViewById(R.id.proposition1Radio));
        answers.add((RadioButton)findViewById(R.id.proposition2Radio));
        answers.add((RadioButton)findViewById(R.id.proposition3Radio));
        for(int i = 0; i < possibleAnswers.size(); i++){
            answers.get(i).setText(possibleAnswers.get(i));
        }
        Button validateButton = findViewById(R.id.ValidateButton);
        validateButton.setText(R.string.validateAnswer);
        TextView questionIndexTextView = findViewById(R.id.indexQuestionTextView);
        questionIndexTextView.setText(getResources().getString(R.string.questionIndex) + (question.getId() + 1) + " / " + QuestionManager.getQuestions().size());
    }

    public void resetView(){
        isQuestionAnswered = false;
        TextView correction = findViewById(R.id.checkAnswerTextView);
        correction.setText("");
        correction.setTextColor(getResources().getColor(R.color.white));
        for (RadioButton button : buttonList){
            button.setChecked(false);
            button.setTextColor(getResources().getColor(R.color.black));
        }
    }

    public void setRadioCheck(int id){
        for(RadioButton button : buttonList){
            button.setChecked(false);
        }
        RadioButton selected = findViewById(id);
        selected.setChecked(true);
    }

    public void compareAnswers(){
        boolean isButtonPressed = false;
        for(RadioButton button : buttonList){
            if(button.isChecked()){
                isButtonPressed = true;
                TextView answerTextView = findViewById(R.id.checkAnswerTextView);
                if(question.verifyAnswer(button.getText().toString())){
                    answerTextView.setText(R.string.goodAnswer);
                    answerTextView.setTextColor(getResources().getColor(R.color.green_answer));
                    correctAnsweredQuestion++;
                } else {
                    answerTextView.setText(R.string.wrongAnswer);
                    answerTextView.setTextColor(getResources().getColor(R.color.red_answer));
                    button.setTextColor(getResources().getColor(R.color.red_answer));
                }
            }
            if(question.verifyAnswer(button.getText().toString())){
                button.setTextColor(getResources().getColor(R.color.green_answer));
            }
        }
        if(isButtonPressed){
            Button validateButton = findViewById(R.id.ValidateButton);
            validateButton.setText(R.string.nextQuestion);
            isQuestionAnswered = true;
        }
    }

    public void nextQuestion(){
        int idNextQuestion = question.getNextQuestionIndex();
        if(idNextQuestion != -1) {
            Intent nextQuestion = new Intent(this, MainActivity.class);
            nextQuestion.putExtra("idQuestion", idNextQuestion);
            nextQuestion.putExtra("valideAnswersNumber",correctAnsweredQuestion);
            startActivity(nextQuestion);
            finish();
        } else {
            Intent resultIntent = new Intent(this, ResultActivity.class);
            resultIntent.putExtra("valideAnswersNumber",correctAnsweredQuestion);
            startActivity(resultIntent);
        }
        /*if(question.getNextQuestionIndex() != -1) {
            question = Question.getQuestion(question.getNextQuestionIndex());
            resetView();
            fillQuestionViews();
        } else {

        }*/
    }
}
