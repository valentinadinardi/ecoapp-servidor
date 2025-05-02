package org.example.entities.daos;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.example.utils.Operador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// Implementacion del CRUDGenerico
public class CRUDGenericoImpl implements CRUDGenerico{

    // Se declara la coleccion
    private final MongoCollection<Document> collection;

    /**
     * Constructor
     * @param db Base de datos
     * @param collectionName Nombre de la coleccion
     */
    public CRUDGenericoImpl(MongoDatabase db, String collectionName) {
        this.collection = db.getCollection(collectionName); // Se inicializa la coleccion
    }

    /**
     * Metodo que se encarga de insertar un documento en la base de datos
     * @param data Mapa con los datos a insertar
     */
    public void insert(Map<String, Object> data) {
        Document doc = new Document(data); // Se crea un documento
        this.collection.insertOne(doc); // Se inserta el documento
        System.out.println("Se han insertado los documentos exitosamente (insertOne)");
    }

    /**
     * Metodo que se encarga de insertar varios documentos en la base de datos
     * @param data Lista de mapas con los datos a insertar
     */
    @Override
    public void insertMany(List<Map<String, Object>> data) {
        List<Document> docs = data.stream().map(Document::new).toList(); // Se crea una lista de documentos
        this.collection.insertMany(docs); // Se inserta la lista de documentos
        System.out.println("Se han insertado los documentos exitosamente (insertMany)");
    }

    /**
     * Metodo para obtener todos los documentos
     * @return Lista de documentos
     */
    @Override
    public List<Document> find() {
        // Se crea una lista de documentos
        List<Document> docs = new ArrayList<>();
        // Se recorren los documentos con un cursor
        try(MongoCursor<Document> cursor = collection.find().iterator()) {
            while(cursor.hasNext()){ // Mientras haya un siguiente
                Document doc = cursor.next(); // Se obtiene el siguiente
                docs.add(doc); // Se agrega a la lista
            }
        }
        return docs; // Se retorna la lista
    }

    /**
     * Metodo para obtener valores en un campo
     * @param campo Campo a buscar
     * @return Lista de valores
     */
    @Override
    public List<Object> find(String campo) {

        List<Document> docs = this.find();
        List<Object> valores = new ArrayList<>();
        for(Document doc : docs) {
            // Obtenemos el valor sin saber qué tipo es
            Object valor = doc.get(campo);

            if (valor instanceof String) {
                System.out.println("Es un String: " + valor);
                valores.add(valor);
            } else if (valor instanceof Integer) {
                System.out.println("Es un Integer: " + valor);
                valores.add(valor);
            } else if (valor instanceof Boolean) {
                System.out.println("Es un Boolean: " + valor);
                valores.add(valor);
            } else if (valor instanceof List) {
                System.out.println("Es una Lista: " + valor);
                valores.add(valor);
            } else if (valor instanceof Document) {
                System.out.println("Es un Subdocumento: " + valor);
                valores.add(valor);
            }
        }
        return valores; // Se retorna la lista
    }

    @Override
    public Object findOneValue(ObjectId objectID, String campo) {

        Bson filtro = Operador.EQ.crearFiltro("_id", objectID);
        List<Document> docs = this.findFiltro(filtro);
        for(Document doc : docs) {
            // Obtenemos el valor sin saber qué tipo es
            Object valor = doc.get(campo);

            if (valor instanceof String) {
                System.out.println("Se ha encontrado este valor: " + valor);
                return valor;
            } else if (valor instanceof Integer) {
                System.out.println("Se ha encontrado este valor: " + valor);
                return valor;
            } else if (valor instanceof Boolean) {
                System.out.println("Se ha encontrado este valor: " + valor);
                return valor;
            } else if (valor instanceof List) {
                System.out.println("Se han encontrado estos valores: " + valor);
                return valor;
            } else if (valor instanceof Document) {
                System.out.println("Se ha encontrado este valor: " + valor);
                return valor;
            }
        }
        return null; // Se retorna la lista
    }

    /**
     * Metodo para obtener documentos con proyeccion
     * @param p Proyeccion de los campos
     * @return Lista de documentos
     */
    @Override
    public List<Document> findProyeccion(Bson filtro, List<String> p) {
        // Se crea un documento de proyeccion
        Document proyeccion = new Document();

        //Recorro los elementos de la lista
        for(String c : p){
            //Separo por el guion
            String[] partes = c.split("-");
            //Agrego al documento de proyeccion
            proyeccion.append(partes[0], Integer.parseInt(partes[1]));
        }

        // Se obtienen los documentos con la proyeccion
        FindIterable<Document> documents = collection.find(filtro).projection(proyeccion);

        // Se crea una lista de documentos
        List<Document> docs = new ArrayList<>();

        // Se recorren los documentos con un cursor
        try(MongoCursor<Document> cursor = documents.iterator()) {
            while(cursor.hasNext()){ // Mientras haya un siguiente
                Document d = cursor.next(); // Se obtiene el siguiente
                docs.add(d); // Se agrega a la lista
            }
        }

        // Se retorna la lista de documentos con la proyeccion aplicada
        return docs;
    }

    /**
     * Metodo para obtener documentos con filtro
     * @param filtro Filtro a aplicar
     * @return Lista de documentos
     */
    @Override
    public List<Document> findFiltro(Bson filtro) {
        // Se obtienen los documentos con el filtro
        FindIterable<Document> documents = collection.find(filtro);

        // Se crea una lista de documentos
        List<Document> docs = new ArrayList<>();
        // Se recorren los documentos con un cursor
        try(MongoCursor<Document> cursor = documents.iterator()) {
            while(cursor.hasNext()){ // Mientras haya un siguiente
                Document d = cursor.next(); // Se obtiene el siguiente
                docs.add(d); // Se agrega a la lista
            }
        }
        return docs;
    }

    @Override
    public void update(Bson filtro, String campo, Object valor) {
        Bson actualizacicon = Updates.set(campo, valor);
        collection.updateOne(filtro, actualizacicon);
        System.out.println("Actualizacion exitosa");
    }

    @Override
    public void updateMany(Bson filtro, String campo, Object valor) {
        Bson actualizacicon = Updates.set(campo, valor);
        collection.updateMany(filtro, actualizacicon);
        System.out.println("Actualizacion exitosa");
    }

    @Override
    public void updateAddToSetMany(Bson filtro, String campo, Object valor) {
        if (valor instanceof ArrayList<?>){
            Bson actualizaciones = Updates.addEachToSet(campo, List.of(valor));
            collection.updateMany(filtro, actualizaciones);
            System.out.println("Actualizacion exitosa");
        }else{
            Bson actualizaciones = Updates.addToSet(campo, valor);
            System.out.println("Actualizaciones" + actualizaciones);
            collection.updateMany(filtro, actualizaciones);
            System.out.println("Actualizacion exitosa");
        }


    }

    @Override
    public void convertToArray(Bson filtro, String campo) {
        Document setStage = new Document("$set", new Document(campo, List.of("$" + campo)));
        collection.updateMany(filtro, Arrays.asList(setStage));
        System.out.println("Se ha convertido correctamente a Array");
    }

    @Override
    public void updateCurrentDate(Bson filtro, String campo) {
        if(filtro !=null){
            Bson actualizaciones = Updates.currentDate(campo);
            collection.updateMany(filtro, actualizaciones);
            System.out.println("Actualizacion exitosa");
        }else{
            Bson actualizaciones = Updates.currentDate(campo);
            collection.updateMany(new Document(), actualizaciones);
            System.out.println("Actualizacion exitosa");
        }
    }

    @Override
    public void delete(Bson filtro) {
        collection.deleteOne(filtro);
        System.out.println("Se ha eliminado correctamente");
    }

    @Override
    public void deleteMany(Bson filtro) {
        collection.deleteMany(filtro);
        System.out.println("Se han eliminado correctamente");
    }

}

