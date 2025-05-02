package org.example.models;

import org.bson.types.ObjectId;

public class Perfil {

    private ObjectId id;
    private String descripcion;
    private String tipo;
    private Foto foto;
    private ObjectId id_usuario;

    public Perfil(String descripcion, String tipo, Foto foto, ObjectId id_usuario) {
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.foto = foto;
        this.id_usuario = id_usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public ObjectId getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(ObjectId id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", foto=" + foto +
                ", id_usuario=" + id_usuario +
                '}';
    }
}
