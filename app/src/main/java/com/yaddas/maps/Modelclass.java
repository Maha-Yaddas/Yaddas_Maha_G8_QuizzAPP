package com.yaddas.maps;

public class Modelclass {
    String Question;
    String OA,OB;
    String Ans;

    public Modelclass(String question,String OA,String OB, String Ans) {
        Question = question;
        this.OA=OA;
        this.OB=OB;
        this.Ans=Ans;
    }

    public Modelclass() {
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getOA() {
        return OA;
    }

    public void setOA(String OA) {
        this.OA = OA;
    }

    public String getOB() {
        return OB;
    }

    public void setOB(String OB) {
        this.OB = OB;
    }

    public String getAns() {
        return Ans;
    }

    public void setAns(String ans) {
        Ans = ans;
    }


}
