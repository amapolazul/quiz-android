package com.amapolazul.www.quizamapola.logica.preguntas.grado1;

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
import com.amapolazul.www.quizamapola.persistencia.Pregunta;
import com.amapolazul.www.quizamapola.persistencia.QuizDAO;

import java.sql.SQLException;
import java.util.List;

public class PreguntasGeografiaGradoUno extends Activity {

    private QuizDAO quizDao;
    private int indicePrguntaActual;
    private Pregunta preguntaActual;
    private List<Pregunta> preguntasGeografiaGradoUno;
    private Contador contador;

    private TextView contadorCorrectas;
    private TextView contadorIncorrectas;

    private MediaPlayer sonidoCorrecto;
    private MediaPlayer sonidoIncorrecta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_geografia_grado_uno);

        indicePrguntaActual = 0;
        contador = Contador.getInstance();
        contadorCorrectas = (TextView) findViewById(R.id.contadorCorrectasGeografiaGradoUno);
        contadorIncorrectas = (TextView) findViewById(R.id.contadorIncorrectasGeografiaGradoUno);

        contadorCorrectas.setText(String.valueOf(contador.getCorrectasGeografia()));
        contadorIncorrectas.setText(String.valueOf(contador.getIncorrectaGeografia()));

        sonidoCorrecto = MediaPlayer.create(this, R.raw.correcta);
        sonidoIncorrecta = MediaPlayer.create(this, R.raw.incorrecta);

        TextView enunciado = (TextView) findViewById(R.id.enunciadoGeografiaGradoUno);
        enunciado.setMovementMethod(new ScrollingMovementMethod());
        try {
            quizDao = new QuizDAO(this);
            quizDao.open();
            preguntasGeografiaGradoUno = quizDao.darPreguntas(CategoriasEnum.GEOGRAFIA.getValor(), "1");
            inicializarQuiz();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void regresar(View view){
        Intent intent = new Intent(this, MenuCategorias.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preguntas_geografia_grado_uno, menu);
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

    private void inicializarQuiz(){
        preguntaActual = preguntasGeografiaGradoUno.get(indicePrguntaActual);
        TextView enunciado = (TextView) findViewById(R.id.enunciadoGeografiaGradoUno);
        TextView respuestaA = (TextView) findViewById(R.id.respuestaAGeografiaGradoUno);
        TextView respuestaB = (TextView) findViewById(R.id.respuestaBGeografiaGradoUno);
        TextView respuestaC = (TextView) findViewById(R.id.respuestaCGeografiaGradoUno);
        TextView respuestaD = (TextView) findViewById(R.id.respuestaDGeografiaGradoUno);

        enunciado.setText(preguntaActual.getEnunciado().replace("<br>", Html.fromHtml("<br />")));
        enunciado.scrollTo(0,0);
        respuestaA.setText(preguntaActual.getRespuestaA());
        respuestaB.setText(preguntaActual.getRespuestaB());
        respuestaC.setText(preguntaActual.getRespuestaC());
        respuestaD.setText(preguntaActual.getRespuestaD());
    }

    public void responderConA(View view) throws InterruptedException {
        ImageView blancoA = (ImageView) view;
        if(preguntaActual.getRespuestaCorrecta().equals("A")){
            contador.aumentarGeografiaCorrecta();
            contadorCorrectas.setText(String.valueOf(contador.getCorrectasGeografia()));
            blancoA.setImageResource(R.drawable.verde1);
            sonidoCorrecto.start();
        } else {
            contador.aumetarGeogragiaIncorrectas();
            contadorIncorrectas.setText(String.valueOf(contador.getIncorrectaGeografia()));
            blancoA.setImageResource(R.drawable.rojo1);
            sonidoIncorrecta.start();
        }

        reiniciarRespuestasYAvanzar();
    }

    public void responderConB(View view) throws InterruptedException {
        ImageView blancoB = (ImageView) view;
        if(preguntaActual.getRespuestaCorrecta().equals("B")){
            contador.aumentarGeografiaCorrecta();
            contadorCorrectas.setText(String.valueOf(contador.getCorrectasGeografia()));
            blancoB.setImageResource(R.drawable.verde1);
            sonidoCorrecto.start();
        } else {
            contador.aumetarGeogragiaIncorrectas();
            contadorIncorrectas.setText(String.valueOf(contador.getIncorrectaGeografia()));
            blancoB.setImageResource(R.drawable.rojo1);
            sonidoIncorrecta.start();
        }

        reiniciarRespuestasYAvanzar();
    }

    public void responderConC(View view) throws InterruptedException {
        ImageView blancoC = (ImageView) view;
        if(preguntaActual.getRespuestaCorrecta().equals("C")){
            contador.aumentarGeografiaCorrecta();
            contadorCorrectas.setText(String.valueOf(contador.getCorrectasGeografia()));
            blancoC.setImageResource(R.drawable.verde1);
            sonidoCorrecto.start();
        } else {
            contador.aumetarGeogragiaIncorrectas();
            contadorIncorrectas.setText(String.valueOf(contador.getIncorrectaGeografia()));
            blancoC.setImageResource(R.drawable.rojo1);
            sonidoIncorrecta.start();
        }

        reiniciarRespuestasYAvanzar();
    }

    public void responderConD(View view) throws InterruptedException {
        ImageView blancoD = (ImageView) view;
        if(preguntaActual.getRespuestaCorrecta().equals("D")){
            contador.aumentarGeografiaCorrecta();
            contadorCorrectas.setText(String.valueOf(contador.getCorrectasGeografia()));
            blancoD.setImageResource(R.drawable.verde1);
            sonidoCorrecto.start();
        } else {
            contador.aumetarGeogragiaIncorrectas();
            contadorIncorrectas.setText(String.valueOf(contador.getIncorrectaGeografia()));
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

        if(indicePrguntaActual < preguntasGeografiaGradoUno.size()){
            inicializarQuiz();
        } else {
            Intent intent = new Intent(this, ResultadosQuizGrado1.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        regresar(null);
    }
}
