package org.example.services;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

// Clase que se encarga de la conexion a la base de datos
public class MongoConnection {

    // Constructor
    public MongoConnection() {
    }

    /**
     * Metodo que se encarga de realizar la conexion a la base de datos
     * @return MongoClient
     */
    public MongoClient connect() {
        // Definir la cadena de conexion
        ConnectionString connection = new ConnectionString(System.getenv("CONNECTION_STRING"));
        // Configurar las opciones de conexion
        MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connection).build();
        return MongoClients.create(settings);
    }

    /**
     * Metodo que se encarga de obtener la base de datos
     * @return base de datos
     */
    public MongoDatabase getDatabase() {
        try { // Intenta realizar la conexion
            MongoClient mongoClient = this.connect();
            return mongoClient.getDatabase(System.getenv("DATABASE_NAME")); // Retorna la base de datos
        } catch (Exception e) { // En caso de error
            System.out.println("Ocurrio un problema " + String.valueOf(e));
            return null; // Retorna nulo
        }
    }


}
