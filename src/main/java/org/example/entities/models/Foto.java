package org.example.entities.models;

import org.bson.types.ObjectId;

public class Foto {

    private ObjectId id;
    private String nombre;
    private String tipo;
    private String url;

    public Foto(ObjectId id, String nombre, String tipo, String url) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Foto{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
