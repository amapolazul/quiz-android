package com.amapolazul.www.quizamapola.logica.preguntas.grado4;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amapolazul.www.quizamapola.R;
import com.amapolazul.www.quizamapola.logica.contador.Contador;
import com.amapolazul.www.quizamapola.logica.enums.CategoriasEnum;
import com.amapolazul.www.quizamapola.logica.menu.MenuCategorias;
import com.amapolazul.www.quizamapola.logica.preguntas.grado3.LenguajeIntroduccionGradoTres;
import com.amapolazul.www.quizamapola.logica.preguntas.grado3.PreguntasGeografiaGrado3;
import com.amapolazul.www.quizamapola.persistencia.Pregunta;
import com.amapolazul.www.quizamapola.persistencia.QuizDAO;
import com.amapolazul.www.quizamapola.servicios.image_loader.ImageLoader;

import java.sql.SQLException;
import java.util.List;

public class PreguntasCienciasGrado4 extends Activity {

    private QuizDAO quizDao;
    private int indicePrguntaActual;
    private Pregunta preguntaActual;
    private List<Pregunta> preguntasCienciasGradoCuatro;
    private Contador contador;

    private MediaPlayer sonidoCorrecto;
    private MediaPlayer sonidoIncorrecta;

    private TextView contadorCorrectas;
    private TextView contadorIncorrectas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_ciencias_grado4);

        indicePrguntaActual = 0;
        contador = Contador.getInstance();
        contadorCorrectas = (TextView) findViewById(R.id.contadorCorrectasCienciasG4);
        contadorIncorrectas = (TextView) findViewById(R.id.contadorIncorrectasCienciasG4);

        contadorCorrectas.setText(String.valueOf(contador.getCorrectasCiencias()));
        contadorIncorrectas.setText(String.valueOf(contador.getIncorrectasCiencias()));

        TextView enunciado = (TextView) findViewById(R.id.enunciadoCienciasG4);
        enunciado.setMovementMethod(new ScrollingMovementMethod());

        sonidoCorrecto = MediaPlayer.create(this, R.raw.correcta);
        sonidoIncorrecta = MediaPlayer.create(this, R.raw.incorrecta);

        try {
            quizDao = new QuizDAO(this);
            quizDao.open();
            preguntasCienciasGradoCuatro = quizDao.darPreguntas(CategoriasEnum.CIENCIAS.getValor(), "4");
            inicializarQuiz();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void regresar(View view){
        Intent intent = new Intent(this, MenuCategorias.class);
        startActivity(intent);
    }

    private void inicializarQuiz() {
        preguntaActual = preguntasCienciasGradoCuatro.get(indicePrguntaActual);
        TextView enunciado = (TextView) findViewById(R.id.enunciadoCienciasG4);
        TextView respuestaA = (TextView) findViewById(R.id.respuestaACienciasG4);
        TextView respuestaB = (TextView) findViewById(R.id.respuestaBCienciasG4);
        TextView respuestaC = (TextView) findViewById(R.id.respuestaCCienciasG4);
        TextView respuestaD = (TextView) findViewById(R.id.respuestaDCienciasG4);

        enunciado.setText(preguntaActual.getEnunciado().replace("<br>", Html.fromHtml("<br />")));
        respuestaA.setText(preguntaActual.getRespuestaA());
        respuestaB.setText(preguntaActual.getRespuestaB());
        respuestaC.setText(preguntaActual.getRespuestaC());
        respuestaD.setText(preguntaActual.getRespuestaD());
    }

    public void responderConA(View view) throws InterruptedException {
        ImageView blancoA = (ImageView) view;
        if(preguntaActual.getRespuestaCorrecta().equals("A")){
            contador.aumentarCienciasCorrecta();
            contadorCorrectas.setText(String.valueOf(contador.getCorrectasCiencias()));
            blancoA.setImageResource(R.drawable.verde1);
            sonidoCorrecto.start();
        } else {
            contador.aumentarCieciasInCorrecta();
            contadorIncorrectas.setText(String.valueOf(contador.getIncorrectasCiencias()));
            blancoA.setImageResource(R.drawable.rojo1);
            sonidoIncorrecta.start();
        }

        reiniciarRespuestasYAvanzar();
    }

    public void responderConB(View view) throws InterruptedException {
        ImageView blancoB = (ImageView) view;
        if(preguntaActual.getRespuestaCorrecta().equals("B")){
            contador.aumentarCienciasCorrecta();
            contadorCorrectas.setText(String.valueOf(contador.getCorrectasCiencias()));
            blancoB.setImageResource(R.drawable.verde1);
            sonidoCorrecto.start();
        } else {
            contador.aumentarCieciasInCorrecta();
            contadorIncorrectas.setText(String.valueOf(contador.getIncorrectasCiencias()));
            blancoB.setImageResource(R.drawable.rojo1);
            sonidoIncorrecta.start();
        }

        reiniciarRespuestasYAvanzar();
    }

    public void responderConC(View view) throws InterruptedException {
        ImageView blancoC = (ImageView) view;
        if(preguntaActual.getRespuestaCorrecta().equals("C")){
            contador.aumentarCienciasCorrecta();
            contadorCorrectas.setText(String.valueOf(contador.getCorrectasCiencias()));
            blancoC.setImageResource(R.drawable.verde1);
            sonidoCorrecto.start();
        } else {
            contador.aumentarCieciasInCorrecta();
            contadorIncorrectas.setText(String.valueOf(contador.getIncorrectasCiencias()));
            blancoC.setImageResource(R.drawable.rojo1);
            sonidoIncorrecta.start();
        }

        reiniciarRespuestasYAvanzar();
    }

    public void responderConD(View view) throws InterruptedException {
        ImageView blancoD = (ImageView) view;
        if(preguntaActual.getRespuestaCorrecta().equals("D")){
            contador.aumentarCienciasCorrecta();
            contadorCorrectas.setText(String.valueOf(contador.getCorrectasCiencias()));
            blancoD.setImageResource(R.drawable.verde1);
            sonidoCorrecto.start();
        } else {
            contador.aumentarCieciasInCorrecta();
            contadorIncorrectas.setText(String.valueOf(contador.getIncorrectasCiencias()));
            blancoD.setImageResource(R.drawable.rojo1);
            sonidoIncorrecta.start();
        }

        reiniciarRespuestasYAvanzar();
    }

    private synchronized void reiniciarRespuestasYAvanzar() throws InterruptedException {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView blancoA = (ImageView) findViewById(R.id.blancoDosA);
                ImageView blancoB = (ImageView) findViewById(R.id.blancoDosB);
                ImageView blancoC = (ImageView) findViewById(R.id.blancoDosC);
                ImageView blancoD = (ImageView) findViewById(R.id.blancoDosD);

                blancoA.setImageResource(R.drawable.blanco2);
                blancoB.setImageResource(R.drawable.blanco2);
                blancoC.setImageResource(R.drawable.blanco2);
                blancoD.setImageResource(R.drawable.blanco2);
            }
        }, 500);

        indicePrguntaActual++;

        if(indicePrguntaActual < preguntasCienciasGradoCuatro.size()){
            inicializarQuiz();
        } else {
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(PreguntasCienciasGrado4.this, PreguntasGeografiaGrado4.class);
                    startActivity(intent);
                }
            }, 500);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preguntas_ciencias_grado4, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
