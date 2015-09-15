package com.amapolazul.www.quizamapola.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jsmartinez on 17/03/2015.
 */
public class QuizSQLiteHelper extends SQLiteOpenHelper {

    public static final String NOMBRE_TABLA = "pregunta_quiz";
    public static final String NOMBRE_COLUMNA_ID = "_id";
    public static final String NOMBRE_COLUMNA_ENUNCIADO = "enunciado";
    public static final String NOMBRE_COLUMNA_RESPUESTA_A = "respuesta_a";
    public static final String NOMBRE_COLUMNA_RESPUESTA_B = "respuesta_b";
    public static final String NOMBRE_COLUMNA_RESPUESTA_C = "respuesta_c";
    public static final String NOMBRE_COLUMNA_RESPUESTA_D = "respuesta_d";
    public static final String NOMBRE_COLUMNA_RESPUESTA_CORRECTA = "respuesta_correcta";
    public static final String NOMBRE_COLUMNA_CATEGORIA = "categoria";
    public static final String NOMBRE_COLUMNA_ID_LECTURA = "lectura";
    public static final String NOMBRE_COLUMNA_GRADO = "grado";
    public static final String NOMBRE_COLUMNA_IMAGEN = "imagen";


    private static final String DATABASE_NAME = "quiz.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + NOMBRE_TABLA + "(" + NOMBRE_COLUMNA_ID
            + " integer primary key autoincrement, " + NOMBRE_COLUMNA_ENUNCIADO
            + " text not null," + NOMBRE_COLUMNA_RESPUESTA_A
            + " text not null," + NOMBRE_COLUMNA_RESPUESTA_B
            + " text not null," + NOMBRE_COLUMNA_RESPUESTA_C
            + " text not null," + NOMBRE_COLUMNA_RESPUESTA_D
            + " text not null," + NOMBRE_COLUMNA_RESPUESTA_CORRECTA
            + " text not null," + NOMBRE_COLUMNA_CATEGORIA
            + " text not null," + NOMBRE_COLUMNA_ID_LECTURA
            + " text not null," + NOMBRE_COLUMNA_GRADO
            + " text not null," + NOMBRE_COLUMNA_IMAGEN
            + " text not null" +
            ");";

    public QuizSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NOMBRE_TABLA);
        onCreate(db);
    }
}