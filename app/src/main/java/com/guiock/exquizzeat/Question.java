package com.guiock.exquizzeat;

import java.util.Collections;
import java.util.List;

public class Question {


    private int id;
    private int imgId;
    private List<String> possibleAnswers;
    private String answer;
    private boolean isVegetable; //To differentiate vegetable and dishes (for phrasing), possibility to enum it later for other kind of question (typical location of a dish, etc)
    enum difficulty{
        easy,
        normal,
        hard
    }

    public Question(String name, List<String> possibleAnswers, boolean isVegetable, int imgId){
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
        return answer.equals(this.answer);
    }

    public int getImgId(){
        return imgId;
    }

    public void setId(int id){
        this.id = id;
    }

    //Get next question's ID, if actual question is last of list, return -1
    public int getNextQuestionIndex (){
        if(QuestionManager.getQuestions().size() > this.id + 1){
            return this.id + 1;
        } else {
            return -1;
        }
    }

    public boolean isVegetable(){
        return isVegetable;
    }

    public int getId (){
        return this.id;
    }
}
