package com.example.basadatos;

public class Articulo {

    private int aID;
    private String aNombre;
    private String aPrecio;

    public Articulo() {}

    public int getID() {
        return this.aID;
    }
    public void setID(int id) {
        this.aID = id;
    }

    public String getNombre() {
        return this.aNombre;
    }
    public void setNombre(String nombre) {
        this.aNombre = nombre;
    }

    public String getPrecio() {
        return this.aPrecio;
    }
    public void setPrecio(String precio) {
        this.aPrecio = precio;
    }

}
