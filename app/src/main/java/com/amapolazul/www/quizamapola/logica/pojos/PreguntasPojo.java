package com.amapolazul.www.quizamapola.logica.pojos;

import java.util.List;

/**
 * Created by jsmartinez on 14/09/2015.
 */
public class PreguntasPojo {
    private String mensaje;
    private List<PreguntaPojo> preguntas;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<PreguntaPojo> getPreguntaPojos() {
        return preguntas;
    }

    public void setPreguntaPojos(List<PreguntaPojo> preguntaPojos) {
        this.preguntas = preguntaPojos;
    }

    @Override
    public String toString() {
        return "Preguntas{" +
                "mensaje='" + mensaje + '\'' +
                ", preguntas=" + preguntas +
                '}';
    }
}
