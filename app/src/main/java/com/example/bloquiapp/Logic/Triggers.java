package com.example.bloquiapp.Logic;

public class Triggers extends Actions {

    private boolean llamadaContacto, llamada123, llamadaPolicia;


    public Triggers(boolean whatsapp, boolean telegram, boolean sms, boolean llamada, boolean llamadaContacto,
                    boolean llamada123, boolean llamadaPolicia) {
        super(whatsapp, telegram, sms, llamada);
        this.llamadaContacto = llamadaContacto;
        this.llamada123 = llamada123;
        this.llamadaPolicia = llamadaPolicia;
    }

    public void volumenMas() {

    }
    public void volumenMenos() {

    }

    public void onoff(){

    }

    public void  botonHome() {

    }

    public void agitar () {

    }




}

