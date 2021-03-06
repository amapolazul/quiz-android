package com.amapolazul.www.quizamapola.logica.preguntas.grado2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.amapolazul.www.quizamapola.R;
import com.amapolazul.www.quizamapola.logica.contador.Contador;
import com.amapolazul.www.quizamapola.logica.menu.MenuCategorias;

public class ResultadosQuizGrado2 extends Activity {

    private Contador contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_quiz_grado2);

        contador = Contador.getInstance();

        TextView resultadosMatematicas = (TextView) findViewById(R.id.resQuizMateG1);
        TextView resultadosLenguaje = (TextView) findViewById(R.id.resQuizLengG1);
        TextView resultadosCiencias= (TextView) findViewById(R.id.resQuizCienG1);
        TextView resultadosGeografia = (TextView) findViewById(R.id.resQuizGeoG1);

        resultadosCiencias.setText(String.valueOf(contador.getCorrectasCiencias()));
        resultadosGeografia.setText(String.valueOf(contador.getCorrectasGeografia()));
        resultadosLenguaje.setText(String.valueOf(contador.getCorrectasLenguaje()));
        resultadosMatematicas.setText(String.valueOf(contador.getCorrectasMatematicas()));
    }

    public void volver(View view){
        Intent intent = new Intent(this, MenuCategorias.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resultados_quiz_grado2, menu);
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
        volver(null);
    }
}
