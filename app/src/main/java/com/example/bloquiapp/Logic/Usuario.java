package com.example.bloquiapp.Logic;

import android.content.SharedPreferences;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.SharedPreferencesCompat;

import com.example.bloquiapp.AdaptersAndModels.ViewPageAdapter;

public class Usuario{


    String nombre,ubicacion,usuario,contraseña;
    int edad;
    public Usuario(String nombre, String ubicacion, String usuario, String contraseña, int edad) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.edad = edad;
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


}

