package com.amapolazul.www.quizamapola.logica.preguntas.grado2;

import android.app.Activity;
import android.app.Dialog;
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
import com.amapolazul.www.quizamapola.logica.constantes.Constantes;
import com.amapolazul.www.quizamapola.logica.contador.Contador;
import com.amapolazul.www.quizamapola.logica.enums.CategoriasEnum;
import com.amapolazul.www.quizamapola.logica.menu.MenuCategorias;
import com.amapolazul.www.quizamapola.persistencia.Pregunta;
import com.amapolazul.www.quizamapola.persistencia.QuizDAO;

import java.sql.SQLException;
import java.util.List;

public class PreguntasLenguajeGrado2 extends Activity {

    private Dialog busyDialog;
    private QuizDAO quizDao;
    private int indicePrguntaActual;
    private Pregunta preguntaActual;
    private List<Pregunta> preguntasLenguajeGradoDos;
    private Contador contador;
    private String indicePreguntaString;
    private String lecturaPregunta;

    private MediaPlayer sonidoCorrecto;
    private MediaPlayer sonidoIncorrecta;

    private TextView contadorCorrectas;
    private TextView contadorIncorrectas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_lenguaje_grado2);

        Intent intent = getIntent();
        indicePreguntaString = intent.getStringExtra(Constantes.INDICE_PREGUNTA_PARAMETRO);

        if(indicePreguntaString == null || indicePreguntaString.equals("") ){
            indicePrguntaActual = 0;
        } else {
            indicePrguntaActual = Integer.parseInt(indicePreguntaString);
        }
        contador = Contador.getInstance();
        contadorCorrectas = (TextView) findViewById(R.id.contadorCorrectasLenguajeG2);
        contadorIncorrectas = (TextView) findViewById(R.id.contadorIncorrectasLenguajeG2);

        contadorCorrectas.setText(String.valueOf(contador.getCorrectasLenguaje()));
        contadorIncorrectas.setText(String.valueOf(contador.getIncorrectasLenguaje()));

        sonidoCorrecto = MediaPlayer.create(this, R.raw.correcta);
        sonidoIncorrecta = MediaPlayer.create(this, R.raw.incorrecta);

        TextView enunciado = (TextView) findViewById(R.id.enunciadoPreguntaLenguajeG2);
        enunciado.setMovementMethod(new ScrollingMovementMethod());
        try {
            quizDao = new QuizDAO(this);
            quizDao.open();
            preguntasLenguajeGradoDos = quizDao.darPreguntas(CategoriasEnum.LENGUAJE.getValor(), "2");
            lecturaPregunta = preguntasLenguajeGradoDos.get(indicePrguntaActual).getLectura();
            inicializarQuiz();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void regresar(View view){
        Intent intent = new Intent(this, MenuCategorias.class);
        startActivity(intent);
    }

    private void inicializarQuiz(){
        preguntaActual = preguntasLenguajeGradoDos.get(indicePrguntaActual);
        TextView enunciado = (TextView) findViewById(R.id.enunciadoPreguntaLenguajeG2);
        TextView respuestaA = (TextView) findViewById(R.id.respuestaALenguajeG2);
        TextView respuestaB = (TextView) findViewById(R.id.respuestaBLenguajeG2);
        TextView respuestaC = (TextView) findViewById(R.id.respuestaCLenguajeG2);
        TextView respuestaD = (TextView) findViewById(R.id.respuestaDLenguajeG2);

        enunciado.setText(preguntaActual.getEnunciado());
        respuestaA.setText(preguntaActual.getRespuestaA());
        respuestaB.setText(preguntaActual.getRespuestaB());
        respuestaC.setText(preguntaActual.getRespuestaC());
        respuestaD.setText(preguntaActual.getRespuestaD());
    }

    public void responderConA(View view) throws InterruptedException {
        ImageView blancoA = (ImageView) view;
        if(preguntaActual.getRespuestaCorrecta().equals("A")){
            contador.aumentarLenguajeCorrecta();
            contadorCorrectas.setText(String.valueOf(contador.getCorrectasLenguaje()));
            blancoA.setImageResource(R.drawable.verde1);
            sonidoCorrecto.start();
        } else {
            contador.aumentarLenguajeInCorrecta();
            contadorIncorrectas.setText(String.valueOf(contador.getIncorrectasLenguaje()));
            blancoA.setImageResource(R.drawable.rojo1);
            sonidoIncorrecta.start();
        }

        reiniciarRespuestasYAvanzar();
    }

    public void responderConB(View view) throws InterruptedException {
        ImageView blancoB = (ImageView) view;
        if(preguntaActual.getRespuestaCorrecta().equals("B")){
            contador.aumentarLenguajeCorrecta();
            contadorCorrectas.setText(String.valueOf(contador.getCorrectasLenguaje()));
            blancoB.setImageResource(R.drawable.verde1);
            sonidoCorrecto.start();
        } else {
            contador.aumentarLenguajeInCorrecta();
            contadorIncorrectas.setText(String.valueOf(contador.getIncorrectasLenguaje()));
            blancoB.setImageResource(R.drawable.rojo1);
            sonidoIncorrecta.start();
        }

        reiniciarRespuestasYAvanzar();
    }

    public void responderConC(View view) throws InterruptedException {
        ImageView blancoC = (ImageView) view;
        if(preguntaActual.getRespuestaCorrecta().equals("C")){
            contador.aumentarLenguajeCorrecta();
            contadorCorrectas.setText(String.valueOf(contador.getCorrectasLenguaje()));
            blancoC.setImageResource(R.drawable.verde1);
            sonidoCorrecto.start();
        } else {
            contador.aumentarLenguajeInCorrecta();
            contadorIncorrectas.setText(String.valueOf(contador.getIncorrectasLenguaje()));
            blancoC.setImageResource(R.drawable.rojo1);
            sonidoIncorrecta.start();
        }

        reiniciarRespuestasYAvanzar();
    }

    public void responderConD(View view) throws InterruptedException {
        ImageView blancoD = (ImageView) view;
        if(preguntaActual.getRespuestaCorrecta().equals("D")){
            contador.aumentarLenguajeCorrecta();
            contadorCorrectas.setText(String.valueOf(contador.getCorrectasLenguaje()));
            blancoD.setImageResource(R.drawable.verde1);
            sonidoCorrecto.start();
        } else {
            contador.aumentarLenguajeInCorrecta();
            contadorIncorrectas.setText(String.valueOf(contador.getIncorrectasLenguaje()));
            blancoD.setImageResource(R.drawable.rojo1);
            sonidoIncorrecta.start();
        }

        reiniciarRespuestasYAvanzar();
    }

    public void mostrarLecturaModalLenguajeG1(View view) {
        busyDialog = new Dialog(this, R.style.lightbox_dialog);
        busyDialog.setContentView(R.layout.lightbox_dialog);

        TextView lecturaGradoUno = (TextView) busyDialog.findViewById(R.id.textoModal);
        lecturaGradoUno.setText(lecturaPregunta);

        ImageView dismissDialogImageView = (ImageView)busyDialog.findViewById(R.id.cerrarDialogoLectura);
        dismissDialogImageView.setOnClickListener(new ImageView.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                busyDialog.dismiss();
            }
        });

        busyDialog.show();
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

        if(indicePrguntaActual < preguntasLenguajeGradoDos.size()){
            Pregunta pregunta = preguntasLenguajeGradoDos.get(indicePrguntaActual);
            String proximaLectura = pregunta.getLectura();
            if(proximaLectura.equals(lecturaPregunta)){
                inicializarQuiz();
            } else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(PreguntasLenguajeGrado2.this, LenguajeIntroduccionGradoDos.class);
                        intent.putExtra(Constantes.INDICE_PREGUNTA_PARAMETRO, String.valueOf(indicePrguntaActual));
                        startActivity(intent);
                    }
                }, 1000);
            }

        } else {
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(PreguntasLenguajeGrado2.this, PreguntasCienciasGrado2.class);
                    startActivity(intent);
                }
            }, 500);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preguntas_lenguaje_grado2, menu);
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
