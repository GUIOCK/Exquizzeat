package com.guiock.exquizzeat;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuestionManager {

    private static Question.difficulty actualDifficulty;

    //Stock lists from different difficulties to avoid remaking them
    private static List<Question> questionsList; //actual difficulty list and only accessible list outside of class
    private static List<Question> questionsListNormal;
    private static List<Question> questionsListEasy;
    private static List<Question> questionsListHard;

    public static  void generateShuffledList(Question.difficulty difficultyList){
        generateList(difficultyList);
        shuffleQuestions();
    }

    private static void generateList(Question.difficulty difficultyList){
        questionsList = new ArrayList<>();
        if(difficultyList == Question.difficulty.easy){
            if(questionsListEasy == null){
                questionsList.add(new Question(
                    "Topinambour",
                    new ArrayList<>(Arrays.asList("Topinambour", "Patate douce", "Radis jaune", "Manioc")),
                    true,
                    R.drawable.topinambour
                ));
                questionsList.add(new Question(
                        "Durian",
                        new ArrayList<String>(Arrays.asList("Durian", "Kiwi", "Fruit de la passion", "Grenade")),
                        true,
                        R.drawable.durian
                ));
                questionsList.add(new Question(
                        "Canne à sucre",
                        new ArrayList<String>(Arrays.asList("Canne à sucre", "Roseau", "Papyrus", "Bambou")),
                        true,
                        R.drawable.canne_a_sucre
                ));
                questionsListEasy = questionsList;
            } else {
                questionsList = questionsListEasy;
            }
        }

        if(difficultyList == Question.difficulty.normal) {
            if(questionsListNormal == null) {
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
                        "Poutine",
                        new ArrayList<>(Arrays.asList("Poutine", "Fondue", "Gratin", "Mac & Cheese")),
                        false,
                        R.drawable.poutine
                ));
                questionsListNormal = questionsList;
            } else {
                questionsList = questionsListNormal;
                return;
            }
        }

        if(difficultyList == Question.difficulty.hard){
            if(questionsListHard == null){
                questionsList.add(new Question(
                        "Oeuf de 100 ans",
                        new ArrayList<>(Arrays.asList("Oeuf de 100 ans", "Oeuf en gelée","Oeuf Ranchero", "Oeufs cocotte au pesto")),
                        false,
                        R.drawable.oeuf_de_100_ans
                ));
                questionsList.add(new Question(
                        "Fruit du dragon",
                        new ArrayList<>(Arrays.asList("Fruit du dragon", "Grenade", "Raisin géant", "Pêche")),
                        true,
                        R.drawable.fruit_du_dragon
                ));
                questionsList.add(new Question(
                        "Rutabaga",
                        new ArrayList<>(Arrays.asList("Rutabaga","Manioc","Patate douce","Radis blanc")),
                        true,
                        R.drawable.rutabaga
                ));
                questionsListHard = questionsList;
            } else {
                questionsList = questionsListHard;
            }
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
