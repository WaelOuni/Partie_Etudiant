package com.example.wael.student.models;

import java.util.List;

/**
 * Created by Wael on 30/03/2015.
 */
public class Question {
    private int num;
    private String statement;
    private String answer;
    private List<Choice> choices ;

    public Question(int num, String statement, String answer, List<Choice> choices) {
        this.num = num;
        this.statement = statement;
        this.answer = answer;
        this.choices = choices;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
