package org.example.entities.models;

import org.bson.types.ObjectId;

public class Comentario {

    private ObjectId id;
    private String comentario;
    private ObjectId id_usuario;
    private ObjectId id_post;

    public Comentario(String comentario, ObjectId id, ObjectId id_usuario, ObjectId id_post) {
        this.comentario = comentario;
        this.id_usuario = id_usuario;
        this.id_post = id_post;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public ObjectId getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(ObjectId id_usuario) {
        this.id_usuario = id_usuario;
    }

    public ObjectId getId_post() {
        return id_post;
    }

    public void setId_post(ObjectId id_post) {
        this.id_post = id_post;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "comentario='" + comentario + '\'' +
                ", id_usuario=" + id_usuario +
                ", id_post=" + id_post +
                '}';
    }
}
