package com.example.elpa.System;

public class Request {
    private String username, pass, email, meteran, key;

    public Request(){

    }
    public Request(String username, String pass, String email,String meteran){
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.meteran = meteran;
    }
    public String getUsername(){ return username;}
    public void setUsernmae(String username){ this.username = username;}
    public String getPass(){return pass;}
    public void setPass(String pass){this.pass =  pass; }
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
    public String getMeteran(){return meteran;}
    public void setMeteran(String meteran){this.meteran = meteran; }
    public String getKey(){return key;}
    public void setKey(String key){this.key = key;}

    @Override
    public String toString(){
        return ""+username+"\n" +
                ""+pass+"\n"+
                ""+email+"\n"+
                ""+meteran;
    }

}
