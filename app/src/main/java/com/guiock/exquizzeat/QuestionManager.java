package com.guiock.exquizzeat;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuestionManager {

    private static Question.difficulty actualDifficulty;
    private static List<Question> questionsList;
    private static List<Question> questionsListNormal;

    public static  void generateShuffledList(Question.difficulty difficultyList){
        generateList(difficultyList);
        shuffleQuestions();
    }

    private static void generateList(Question.difficulty difficultyList){
        questionsList = new ArrayList<>();
        //TODO : corriger la difficultée
        if(difficultyList == Question.difficulty.normal && questionsListNormal == null) {
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
            questionsListNormal = questionsList;
        } else {
            questionsList = questionsListNormal;
            return;
        }
    }

    public static void setActualDifficulty(Question.difficulty difficulty){
        actualDifficulty = difficulty;
    }

    public static List<Question> getQuestions() {
        generateList(actualDifficulty);
        return questionsList;
    }

    private static void shuffleQuestions(){
        Collections.shuffle(questionsList);
        for (int i = 0; i < questionsList.size(); i++) {
            questionsList.get(i).setId(i);
        }
    }

    public static Question getQuestion (int id){
        return questionsList.get(id);
    }

}
