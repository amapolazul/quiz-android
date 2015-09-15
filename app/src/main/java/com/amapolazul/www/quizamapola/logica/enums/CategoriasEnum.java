package com.amapolazul.www.quizamapola.logica.enums;

/**
 * Created by jsmartinez on 22/08/2015.
 */
public enum CategoriasEnum {
    CIENCIAS("ciencias"),
    MATEMATICAS("matematicas"),
    GEOGRAFIA("geografia"),
    LENGUAJE("lenguaje");

    private String valor;

    CategoriasEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
