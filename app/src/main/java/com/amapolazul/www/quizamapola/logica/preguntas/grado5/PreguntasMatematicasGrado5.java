package com.amapolazul.www.quizamapola.logica.preguntas.grado5;

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
import com.amapolazul.www.quizamapola.logica.preguntas.grado4.LenguajeIntroduccionGrado4;
import com.amapolazul.www.quizamapola.persistencia.Pregunta;
import com.amapolazul.www.quizamapola.persistencia.QuizDAO;
import com.amapolazul.www.quizamapola.servicios.image_loader.ImageLoader;

import java.sql.SQLException;
import java.util.List;

public class PreguntasMatematicasGrado5 extends Activity {

    private QuizDAO quizDao;
    private int indicePrguntaActual;
    private Pregunta preguntaActual;
    private List<Pregunta> preguntasMatematicasGradoCinco;
    private Contador contador;

    private TextView contadorCorrectas;
    private TextView contadorIncorrectas;

    private MediaPlayer sonidoCorrecto;
    private MediaPlayer sonidoIncorrecta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_matematicas_grado5);

        indicePrguntaActual = 0;
        contador = Contador.getInstance();
        contadorCorrectas = (TextView) findViewById(R.id.contadorCorrectasMatematicasG5);
        contadorIncorrectas = (TextView) findViewById(R.id.contadorIncorrectasMatematicasG5);

        sonidoCorrecto = MediaPlayer.create(this, R.raw.correcta);
        sonidoIncorrecta = MediaPlayer.create(this, R.raw.incorrecta);

        contadorCorrectas.setText(String.valueOf(contador.getCorrectasMatematicas()));
        contadorIncorrectas.setText(String.valueOf(contador.getIncorrectaGeografia()));

        TextView enunciado = (TextView) findViewById(R.id.enunciadoMatematicasG5);
        enunciado.setMovementMethod(new ScrollingMovementMethod());
        try {
            quizDao = new QuizDAO(this);
            quizDao.open();
            preguntasMatematicasGradoCinco = quizDao.darPreguntas(CategoriasEnum.MATEMATICAS.getValor(), "5");
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
        preguntaActual = preguntasMatematicasGradoCinco.get(indicePrguntaActual);
        TextView enunciado = (TextView) findViewById(R.id.enunciadoMatematicasG5);
        TextView respuestaA = (TextView) findViewById(R.id.respuestaAMatematicasG5);
        TextView respuestaB = (TextView) findViewById(R.id.respuestaBMatematicasG5);
        TextView respuestaC = (TextView) findViewById(R.id.respuestaCMatematicasG5);
        TextView respuestaD = (TextView) findViewById(R.id.respuestaDMatematicasG5);

        enunciado.setText(preguntaActual.getEnunciado().replace("<br>", Html.fromHtml("<br />")));
        respuestaA.setText(preguntaActual.getRespuestaA());
        respuestaB.setText(preguntaActual.getRespuestaB());
        respuestaC.setText(preguntaActual.getRespuestaC());
        respuestaD.setText(preguntaActual.getRespuestaD());
        inicializarImagen(preguntaActual.getImagen());
    }

    private void inicializarImagen(String url) {
        int loader = R.drawable.vacia;
        ImageView image = (ImageView) findViewById(R.id.imagenMatematicasG5);
        String image_url = url;
        ImageLoader imgLoader = new ImageLoader(getApplicationContext());
        imgLoader.DisplayImage(image_url, loader, image);
    }

    public void responderConA(View view) throws InterruptedException {
        ImageView blancoA = (ImageView) view;
        if (preguntaActual.getRespuestaCorrecta().equals("A")) {
            contador.aumentarMatematicasCorrecta();
            contadorCorrectas.setText(String.valueOf(contador.getCorrectasMatematicas()));
            blancoA.setImageResource(R.drawable.verde1);
            sonidoCorrecto.start();
        } else {
            contador.aumentarMatematicasInCorrecta();
            contadorIncorrectas.setText(String.valueOf(contador.getIncorrectasMatematicas()));
            blancoA.setImageResource(R.drawable.rojo1);
            sonidoIncorrecta.start();
        }

        reiniciarRespuestasYAvanzar();
    }

    public void responderConB(View view) throws InterruptedException {
        ImageView blancoB = (ImageView) view;
        if (preguntaActual.getRespuestaCorrecta().equals("B")) {
            contador.aumentarMatematicasCorrecta();
            contadorCorrectas.setText(String.valueOf(contador.getCorrectasMatematicas()));
            blancoB.setImageResource(R.drawable.verde1);
            sonidoCorrecto.start();
        } else {
            contador.aumentarMatematicasInCorrecta();
            contadorIncorrectas.setText(String.valueOf(contador.getIncorrectasMatematicas()));
            blancoB.setImageResource(R.drawable.rojo1);
            sonidoIncorrecta.start();
        }

        reiniciarRespuestasYAvanzar();
    }

    public void responderConC(View view) throws InterruptedException {
        ImageView blancoC = (ImageView) view;
        if (preguntaActual.getRespuestaCorrecta().equals("C")) {
            contador.aumentarMatematicasCorrecta();
            contadorCorrectas.setText(String.valueOf(contador.getCorrectasMatematicas()));
            blancoC.setImageResource(R.drawable.verde1);
            sonidoCorrecto.start();
        } else {
            contador.aumentarMatematicasInCorrecta();
            contadorIncorrectas.setText(String.valueOf(contador.getIncorrectasMatematicas()));
            blancoC.setImageResource(R.drawable.rojo1);
            sonidoIncorrecta.start();
        }

        reiniciarRespuestasYAvanzar();
    }

    public void responderConD(View view) throws InterruptedException {
        ImageView blancoD = (ImageView) view;
        if (preguntaActual.getRespuestaCorrecta().equals("D")) {
            contador.aumentarMatematicasCorrecta();
            contadorCorrectas.setText(String.valueOf(contador.getCorrectasMatematicas()));
            blancoD.setImageResource(R.drawable.verde1);
            sonidoCorrecto.start();
        } else {
            contador.aumentarMatematicasInCorrecta();
            contadorIncorrectas.setText(String.valueOf(contador.getIncorrectasMatematicas()));
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

        if (indicePrguntaActual < preguntasMatematicasGradoCinco.size()) {
            inicializarQuiz();
        } else {
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(PreguntasMatematicasGrado5.this, LenguajeIntroduccionG5.class);
                    startActivity(intent);
                }
            }, 500);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preguntas_matematicas_grado5, menu);
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

    @Override
    public void onBackPressed() {
        regresar(null);
    }
}
