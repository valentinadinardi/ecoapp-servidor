package org.example.utils;

import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

public enum Operador {

    EQ {
        public Bson crearFiltro(String campo, Object valor) {
            return Filters.eq(campo, valor);
        }
    },
    LT {
        public Bson crearFiltro(String campo, Object valor) {
            return Filters.lt(campo, valor);
        }
    },
    LTE {
        public Bson crearFiltro(String campo, Object valor) {
            return Filters.lte(campo, valor);
        }
    },
    GT {
        public Bson crearFiltro(String campo, Object valor) {
            return Filters.gt(campo, valor);
        }
    },
    GTE {
        public Bson crearFiltro(String campo, Object valor) {
            return Filters.gte(campo, valor);
        }
    },
    NE {
        public Bson crearFiltro(String campo, Object valor) {
            return Filters.ne(campo, valor);
        }
    },
    IN {
        public Bson crearFiltro(String campo, Object valor) {
            return Filters.in(campo, (Iterable<?>) valor).toBsonDocument();
        }
    },
    NIN {
        public Bson crearFiltro(String campo, Object valor) {
            return Filters.nin(campo, (Iterable<?>) valor);
        }
    },
    EXISTS{
        public Bson crearFiltro(String campo, Object valor) {
            return Filters.exists(campo, (boolean) valor);
        }
    };

    public abstract Bson crearFiltro(String campo, Object valor);

}
