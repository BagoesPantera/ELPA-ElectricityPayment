package com.example.elpa.System;

public class pbpinput {
    private String input,email;

    public pbpinput(){

    }

    public pbpinput(String input, String email){

        this.input = input;
        this.email = email;
    }

    public String getInput(){return input;}
    public void setInput(String input){this.input = input;}
    public String getEMail(){return email;}
    public void setEmail(String email){this.email = email;}

    @Override
    public String toString(){

        return ""+input+"\n" +
                ""+email;
    }
}
