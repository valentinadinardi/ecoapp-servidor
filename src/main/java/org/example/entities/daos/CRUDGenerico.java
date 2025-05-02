package org.example.entities.daos;


import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

// Interfaz que declara los metodos
public interface CRUDGenerico {

    /**
     * Metodo que se encarga de insertar un documento en la base de datos
     * @param data Mapa con los datos a insertar
     */
    void insert(Map<String, Object> data);

    /**
     * Metodo que se encarga de insertar varios documentos en la base de datos
     * @param data Lista de mapas con los datos a insertar
     */
    void insertMany(List<Map<String,Object>> data);

    /**
     * Metodo para obtener todos los documentos
     * @return Lista de documentos
     */
    List<Document> find();

    /**
     * Metodo para obtener valores en un campo
     * @param campo Campo a buscar
     * @return Lista de valores
     */
    List<Object> find(String campo);

    Object findOneValue(ObjectId objectID, String campo);

    /**
     * Metodo para obtener documentos con proyeccion
     * @param proyeccion Proyeccion de los campos
     * @return Lista de documentos
     */
    List<Document> findProyeccion(Bson filtro, List<String> proyeccion);

    /**
     * Metodo para obtener documentos con filtro
     * @param filtro Filtro a aplicar
     * @return Lista de documentos
     */
    List<Document> findFiltro(Bson filtro);

    /**
     * Metodo para actualizar un documento
     * @param filtro Filtro para buscar el documento
     * @param campo Campo a actualizar
     * @param valor Nuevo valor
     */
    void update(Bson filtro, String campo, Object valor);

    void updateMany(Bson filtro, String campo, Object valor);

    void updateAddToSetMany(Bson filtro, String campo, Object valor);
    void convertToArray(Bson filtro, String campo);

    void updateCurrentDate(Bson filtro, String campo);

    void delete(Bson filtro);
    void deleteMany(Bson filtro);

}

