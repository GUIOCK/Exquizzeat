package com.guiock.exquizzeat;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question {

    private static List<Question> questionsListEasy = null;
    private static List<Question> questionsListNormal = null;
    private static List<Question> questionsListHard = null;
    private static List<Question> questionsListImpossibru = null;
    private static difficulty actualDifficulty;
    private static List<Question> questionsList = getQuestions();

    private int id;
    private int imgId;
    private List<String> possibleAnswers;
    private String answer;
    private boolean isVegetable;
    private static Resources res;
    enum difficulty{
        easy,
        normal,
        hard,
        impossibru
    }

    private Question(String name, List<String> possibleAnswers, boolean isVegetable, int imgId){
        this.answer = name;
        this.isVegetable = isVegetable;
        this.possibleAnswers = possibleAnswers;
        this.imgId = imgId;
    }

    public List<String> getPossibleAnswers(){
       Collections.shuffle(possibleAnswers);
       return possibleAnswers;
    }

    public boolean verifyAnswer(String answer){
        return answer == this.answer;
    }

    public int getImgId(){
        return imgId;
    }

    private void setId(int id){
        this.id = id;
    }

    public int getNextQuestionIndex (){
        if(questionsList.size() > this.id + 1){
            return this.id + 1;
        } else {
            shuffleQuestions();
            return 0;
        }
    }

    public static List<Question> getQuestions() {
        if(questionsList == null) {
            generateList(actualDifficulty);
            shuffleQuestions();
        }
        return questionsList;
    }

    public static void shuffleQuestions(){
        Collections.shuffle(questionsList);
        for (int i = 0; i < questionsList.size(); i++) {
            questionsList.get(i).setId(i);
        }
    }

    public static Question getQuestion (int id){
        return questionsList.get(id);
    }

    public boolean isVegetable(){
        return isVegetable;
    }

    public int getId (){
        return this.id;
    }

    private static void generateList(difficulty difficultyList){
        res = Resources.getSystem();
        questionsList = new ArrayList<>();
        if(difficultyList == difficulty.normal) {
            questionsList.add(new Question(
                    "Bokit",
                    new ArrayList<>(Arrays.asList("Bokit", "Bahn mi", "Bocadillo", "Bratwurst")),
                    false,
                    R.drawable.bokit
            ));
            questionsList.add(new Question(
                    "Loukoum",
                    new ArrayList<>(Arrays.asList("Loukoum", "Berlingot", "Praline", "Sucre d'orge")),
                    false,
                    R.drawable.loukoums
            ));
            questionsList.add(new Question(
                    "Topinambour",
                    new ArrayList<>(Arrays.asList("Topinambour", "Patate douce", "Radis jaune", "Manioc")),
                    true,
                    R.drawable.topinambour
            ));
            questionsList.add(new Question(
                    "Poutine",
                    new ArrayList<>(Arrays.asList("Poutine", "Fondue", "Gratin", "Mac & Cheese")),
                    true,
                    R.drawable.poutine
            ));
            questionsList.add(new Question(
                    "Fruit du dragon",
                    new ArrayList<>(Arrays.asList("Fruit du dragon", "Grenade", "Raisin géant", "Pêche")),
                    true,
                    R.drawable.fruit_du_dragon
            ));
        }
    }
    public static void setActualDifficulty(difficulty difficulty){
        Question.actualDifficulty = difficulty;
    }
}
