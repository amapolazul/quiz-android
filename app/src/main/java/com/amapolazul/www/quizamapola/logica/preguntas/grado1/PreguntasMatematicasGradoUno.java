package com.amapolazul.www.quizamapola.logica.preguntas.grado1;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Bundle;
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
import com.amapolazul.www.quizamapola.persistencia.Pregunta;
import com.amapolazul.www.quizamapola.persistencia.QuizDAO;
import com.amapolazul.www.quizamapola.servicios.image_loader.ImageLoader;

import java.sql.SQLException;
import java.util.List;

public class PreguntasMatematicasGradoUno extends Activity {

    private QuizDAO quizDao;
    private int indicePrguntaActual;
    private Pregunta preguntaActual;
    private List<Pregunta> preguntasMatematicasGradoUno;
    private Contador contador;

    private TextView contadorCorrectas;
    private TextView contadorIncorrectas;

    private MediaPlayer sonidoCorrecto;
    private MediaPlayer sonidoIncorrecta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_matematicas_grado_uno);

        indicePrguntaActual = 0;
        contador = Contador.getInstance();
        contadorCorrectas = (TextView) findViewById(R.id.contadorCorrectasMatematicasG4);
        contadorIncorrectas = (TextView) findViewById(R.id.contadorIncorrectasMatematicasG1);

        sonidoCorrecto = MediaPlayer.create(this, R.raw.correcta);
        sonidoIncorrecta = MediaPlayer.create(this, R.raw.incorrecta);

        contadorCorrectas.setText(String.valueOf(contador.getCorrectasMatematicas()));
        contadorIncorrectas.setText(String.valueOf(contador.getIncorrectaGeografia()));

        TextView enunciado = (TextView) findViewById(R.id.enunciadoMatematicasG1);
        enunciado.setMovementMethod(new ScrollingMovementMethod());
        try {
            quizDao = new QuizDAO(this);
            quizDao.open();
            preguntasMatematicasGradoUno = quizDao.darPreguntas(CategoriasEnum.MATEMATICAS.getValor(), "1");
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
        preguntaActual = preguntasMatematicasGradoUno.get(indicePrguntaActual);
        TextView enunciado = (TextView) findViewById(R.id.enunciadoMatematicasG1);
        TextView respuestaA = (TextView) findViewById(R.id.respuestaMatematicasAG1);
        TextView respuestaB = (TextView) findViewById(R.id.respuestaBMatematicasG1);
        TextView respuestaC = (TextView) findViewById(R.id.respuestaCMatematicasG2);
        TextView respuestaD = (TextView) findViewById(R.id.respuestaDMatematicasG1);

        enunciado.setText(preguntaActual.getEnunciado());
        respuestaA.setText(preguntaActual.getRespuestaA());
        respuestaB.setText(preguntaActual.getRespuestaB());
        respuestaC.setText(preguntaActual.getRespuestaC());
        respuestaD.setText(preguntaActual.getRespuestaD());
        inicializarImagen(preguntaActual.getImagen());
    }

    private void inicializarImagen(String url) {
        int loader = R.mipmap.ic_launcher;
        ImageView image = (ImageView) findViewById(R.id.imagenMatematicasG1);
        String image_url = url;
        ImageLoader imgLoader = new ImageLoader(getApplicationContext());
        imgLoader.DisplayImage(image_url, loader, image);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preguntas_matematicas_grado_uno, menu);
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

        if (indicePrguntaActual < preguntasMatematicasGradoUno.size()) {
            inicializarQuiz();
        } else {
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(PreguntasMatematicasGradoUno.this, LenguajeIntroduccionPrimerGrado.class);
                    startActivity(intent);
                }
            }, 500);
        }
    }
}
