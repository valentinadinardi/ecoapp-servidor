package org.example.models;

import org.bson.types.ObjectId;

public class Post {

    private ObjectId id;
    private String descripcion;
    private ObjectId id_usuario;
    private Foto foto;

    public Post(String descripcion, ObjectId id_usuario, Foto foto) {
        this.descripcion = descripcion;
        this.id_usuario = id_usuario;
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ObjectId getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(ObjectId id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Post{" +
                "descripcion='" + descripcion + '\'' +
                ", id_usuario=" + id_usuario +
                ", foto=" + foto +
                '}';
    }
}
