package com.amapolazul.www.quizamapola.logica.contador;

/**
 * Created by jsmartinez on 22/08/2015.
 */
public class Contador {

    private static Contador contador;

    private int correctasMatematicas;
    private int incorrectasMatematicas;
    private int correctasCiencias;
    private int incorrectasCiencias;
    private int correctasLenguaje;
    private int incorrectasLenguaje;
    private int correctasGeografia;
    private int incorrectaGeografia;

    protected Contador(){
        this.correctasCiencias = 0;
        this.incorrectasCiencias = 0;
        this.correctasGeografia = 0;
        this.incorrectaGeografia = 0;
        this.correctasMatematicas = 0;
        this.incorrectasMatematicas = 0;
        this.correctasLenguaje = 0;
        this.incorrectasLenguaje = 0;
    }

    public void setCorrectasMatematicas(int correctasMatematicas) {
        this.correctasMatematicas = correctasMatematicas;
    }

    public void setIncorrectasMatematicas(int incorrectasMatematicas) {
        this.incorrectasMatematicas = incorrectasMatematicas;
    }

    public void setCorrectasCiencias(int correctasCiencias) {
        this.correctasCiencias = correctasCiencias;
    }

    public void setIncorrectasCiencias(int incorrectasCiencias) {
        this.incorrectasCiencias = incorrectasCiencias;
    }

    public void setCorrectasLenguaje(int correctasLenguaje) {
        this.correctasLenguaje = correctasLenguaje;
    }

    public void setIncorrectasLenguaje(int incorrectasLenguaje) {
        this.incorrectasLenguaje = incorrectasLenguaje;
    }

    public void setCorrectasGeografia(int correctasGeografia) {
        this.correctasGeografia = correctasGeografia;
    }

    public void setIncorrectaGeografia(int incorrectaGeografia) {
        this.incorrectaGeografia = incorrectaGeografia;
    }

    public void aumentarMatematicasCorrecta(){
        this.correctasMatematicas++;
    }

    public void aumentarMatematicasInCorrecta(){
        this.incorrectasMatematicas++;
    }

    public void aumentarCienciasCorrecta(){
        this.correctasCiencias++;
    }

    public void aumentarCieciasInCorrecta(){
        this.incorrectasCiencias++;
    }

    public void aumentarLenguajeCorrecta(){
        this.correctasLenguaje++;
    }

    public void aumentarLenguajeInCorrecta(){
        this.incorrectasLenguaje++;
    }

    public void aumentarGeografiaCorrecta(){
        this.correctasGeografia++;
    }

    public void aumetarGeogragiaIncorrectas(){
        this.incorrectaGeografia++;
    }

    public int getCorrectasMatematicas() {
        return correctasMatematicas;
    }

    public int getIncorrectasMatematicas() {
        return incorrectasMatematicas;
    }

    public int getCorrectasCiencias() {
        return correctasCiencias;
    }

    public int getIncorrectasCiencias() {
        return incorrectasCiencias;
    }

    public int getCorrectasLenguaje() {
        return correctasLenguaje;
    }

    public int getIncorrectasLenguaje() {
        return incorrectasLenguaje;
    }

    public int getCorrectasGeografia() {
        return correctasGeografia;
    }

    public int getIncorrectaGeografia() {
        return incorrectaGeografia;
    }

    public void reiniciarContador(){
        setCorrectasCiencias(0);
        setCorrectasGeografia(0);
        setCorrectasLenguaje(0);
        setCorrectasMatematicas(0);
        setIncorrectaGeografia(0);
        setIncorrectasCiencias(0);
        setIncorrectasMatematicas(0);
        setIncorrectasLenguaje(0);
    }

    public static Contador getInstance(){
        if(contador == null){
            contador = new Contador();
        }
        return contador;
    }
}
