package com.amapolazul.www.quizamapola.logica.preguntas.grado2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amapolazul.www.quizamapola.R;
import com.amapolazul.www.quizamapola.logica.constantes.Constantes;
import com.amapolazul.www.quizamapola.logica.enums.CategoriasEnum;
import com.amapolazul.www.quizamapola.logica.menu.MenuCategorias;
import com.amapolazul.www.quizamapola.logica.preguntas.grado1.PreguntasLenguajeGradoUno;
import com.amapolazul.www.quizamapola.persistencia.Pregunta;
import com.amapolazul.www.quizamapola.persistencia.QuizDAO;

import java.sql.SQLException;
import java.util.List;

public class LenguajeIntroduccionGradoDos extends Activity {

    private Dialog busyDialog;
    private QuizDAO quizDao;
    private Pregunta preguntaActual;
    private String indicePreguntaString;
    private int indicePregunta;
    private List<Pregunta> preguntasLenguajeGradoDos;

    public void regresar(View view){
        Intent intent = new Intent(this, MenuCategorias.class);
        startActivity(intent);
    }

    private void inicializarModal(){
        preguntaActual = preguntasLenguajeGradoDos.get(indicePregunta);
    }

    public void irAPreguntas(View view) {
        Intent intent = new Intent(this, PreguntasLenguajeGrado2.class);
        intent.putExtra(Constantes.INDICE_PREGUNTA_PARAMETRO, indicePreguntaString);
        startActivity(intent);
    }

    public void mostrarLecturaModal(View view) {
        busyDialog = new Dialog(this, R.style.lightbox_dialog);
        busyDialog.setContentView(R.layout.lightbox_dialog);

        TextView lecturaGradoUno = (TextView) busyDialog.findViewById(R.id.textoModal);
        lecturaGradoUno.setMovementMethod(new ScrollingMovementMethod());
        lecturaGradoUno.setText(preguntaActual.getLectura().replace("<br>", Html.fromHtml("<br />")));

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lenguaje_introduccion_grado_dos);

        Intent intent = getIntent();
        indicePreguntaString = intent.getStringExtra(Constantes.INDICE_PREGUNTA_PARAMETRO);

        TextView textoLectura = (TextView) findViewById(R.id.textoLecturaPreguntasG1);
        textoLectura.setText(Constantes.MENSAJE_LECTURAS);

        if(indicePreguntaString == null || indicePreguntaString.equals("") ){
            indicePregunta = 0;
        } else {
            indicePregunta = Integer.parseInt(indicePreguntaString);
        }

        try {
            quizDao = new QuizDAO(this);
            quizDao.open();
            preguntasLenguajeGradoDos = quizDao.darPreguntas(CategoriasEnum.LENGUAJE.getValor(), "2");
            inicializarModal();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lenguaje_introduccion_grado_dos, menu);
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
