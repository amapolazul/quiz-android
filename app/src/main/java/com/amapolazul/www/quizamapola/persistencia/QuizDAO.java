package com.amapolazul.www.quizamapola.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jsmartinez on 21/08/2015.
 */
public class QuizDAO {

    private SQLiteDatabase database;
    private QuizSQLiteHelper dbHelper;
    private String[] allColumns = {
            QuizSQLiteHelper.NOMBRE_COLUMNA_ID,
            QuizSQLiteHelper.NOMBRE_COLUMNA_ENUNCIADO,
            QuizSQLiteHelper.NOMBRE_COLUMNA_RESPUESTA_A,
            QuizSQLiteHelper.NOMBRE_COLUMNA_RESPUESTA_B,
            QuizSQLiteHelper.NOMBRE_COLUMNA_RESPUESTA_C,
            QuizSQLiteHelper.NOMBRE_COLUMNA_RESPUESTA_D,
            QuizSQLiteHelper.NOMBRE_COLUMNA_RESPUESTA_CORRECTA,
            QuizSQLiteHelper.NOMBRE_COLUMNA_CATEGORIA,
            QuizSQLiteHelper.NOMBRE_COLUMNA_ID_LECTURA,
            QuizSQLiteHelper.NOMBRE_COLUMNA_GRADO,
            QuizSQLiteHelper.NOMBRE_COLUMNA_IMAGEN
    };

    public QuizDAO(Context context) {
        dbHelper = new QuizSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long crearPregunta(Pregunta pregunta) {
        ContentValues values = new ContentValues();
        values.put(QuizSQLiteHelper.NOMBRE_COLUMNA_ENUNCIADO, pregunta.getEnunciado());
        values.put(QuizSQLiteHelper.NOMBRE_COLUMNA_RESPUESTA_A, pregunta.getRespuestaA());
        values.put(QuizSQLiteHelper.NOMBRE_COLUMNA_RESPUESTA_B, pregunta.getRespuestaB());
        values.put(QuizSQLiteHelper.NOMBRE_COLUMNA_RESPUESTA_C, pregunta.getRespuestaC());
        values.put(QuizSQLiteHelper.NOMBRE_COLUMNA_RESPUESTA_D, pregunta.getRespuestaD());
        values.put(QuizSQLiteHelper.NOMBRE_COLUMNA_RESPUESTA_CORRECTA, pregunta.getRespuestaCorrecta());
        values.put(QuizSQLiteHelper.NOMBRE_COLUMNA_CATEGORIA, pregunta.getCategoria());
        values.put(QuizSQLiteHelper.NOMBRE_COLUMNA_ID_LECTURA, pregunta.getLectura());
        values.put(QuizSQLiteHelper.NOMBRE_COLUMNA_GRADO, pregunta.getGrado());
        values.put(QuizSQLiteHelper.NOMBRE_COLUMNA_IMAGEN, pregunta.getImagen());

        long insertId = database.insert(QuizSQLiteHelper.NOMBRE_TABLA, null,
                values);

        return insertId;
    }

    /**
     * Devuelvelas preguntas por categoria y grado
     * @param categoria
     * @param grado
     * @return
     */
    public List<Pregunta> darPreguntas(String categoria, String grado) {
        String query = "SELECT * FROM " + QuizSQLiteHelper.NOMBRE_TABLA + " WHERE "
                + QuizSQLiteHelper.NOMBRE_COLUMNA_CATEGORIA + " = ? " +
                "AND " +
                QuizSQLiteHelper.NOMBRE_COLUMNA_GRADO + " = ? ORDER BY " + QuizSQLiteHelper.NOMBRE_COLUMNA_ID_LECTURA;

        Cursor c = database.rawQuery(query, new String[]{categoria, grado});
        List<Pregunta> preguntasList = new ArrayList<Pregunta>();
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                Pregunta pretuntaResultado = cursorToPregunta(c);
                preguntasList.add(pretuntaResultado);
                c.moveToNext();
            }
            return preguntasList;
        } else {
            return null;
        }
    }

    public void removeAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(QuizSQLiteHelper.NOMBRE_TABLA, null, null);
    }

    private Pregunta cursorToPregunta(Cursor cursor) {
        Pregunta pregunta = new Pregunta();
        pregunta.setId(cursor.getLong(0));
        pregunta.setEnunciado(cursor.getString(1));
        pregunta.setRespuestaA(cursor.getString(2));
        pregunta.setRespuestaB(cursor.getString(3));
        pregunta.setRespuestaC(cursor.getString(4));
        pregunta.setRespuestaD(cursor.getString(5));
        pregunta.setRespuestaCorrecta(cursor.getString(6));
        pregunta.setCategoria(cursor.getString(7));
        pregunta.setLectura(cursor.getString(8));
        pregunta.setGrado(cursor.getString(9));
        pregunta.setImagen(cursor.getString(10));
        return pregunta;
    }
}
