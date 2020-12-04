package com.example.bloquiapp.Logic;


public class Usuario {


    String nombre,ubicacion,usuario,contraseña;
    int edad;
    boolean mWhatsapp,mTelegram,mMsm,llamada;

    boolean Telegram, Whatsapp;

    public Usuario(String nombre, String ubicacion, String usuario, String contraseña, int edad, boolean telegram,
                   boolean whatsapp) {
        super();
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.edad = edad;
        Telegram = telegram;
        Whatsapp = whatsapp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isTelegram() {
        return Telegram;
    }

    public void setTelegram(boolean telegram) {
        Telegram = telegram;
    }

    public boolean isWhatsapp() {
        return Whatsapp;
    }

    public void setWhatsapp(boolean whatsapp) {
        Whatsapp = whatsapp;
    }



}

