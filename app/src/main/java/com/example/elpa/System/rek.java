package com.example.elpa.System;

public class rek {
    private String rek,pin;


    public rek(String rek, String pin){
        this.rek = rek;
        this.pin = pin;
    }

    public String getRek(){ return rek; }
    public void setRek(String rek){ this.rek = rek; }
    public String getPin() { return pin; }
    public void setPin(String pin){ this.pin = pin; }

    @Override
    public String toString(){
        return ""+rek+"\n" +
                ""+pin;
    }
}
