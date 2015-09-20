package com.amapolazul.www.quizamapola.logica.menu;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.amapolazul.www.quizamapola.R;
import com.amapolazul.www.quizamapola.logica.contador.Contador;
import com.amapolazul.www.quizamapola.logica.preguntas.grado1.LenguajeIntroduccionPrimerGrado;
import com.amapolazul.www.quizamapola.logica.preguntas.grado1.PreguntasCienciasGradoUno;
import com.amapolazul.www.quizamapola.logica.preguntas.grado1.PreguntasGeografiaGradoUno;
import com.amapolazul.www.quizamapola.logica.preguntas.grado1.PreguntasLenguajeGradoUno;
import com.amapolazul.www.quizamapola.logica.preguntas.grado1.PreguntasMatematicasGradoUno;
import com.amapolazul.www.quizamapola.logica.preguntas.grado2.PreguntasCienciasGrado2;
import com.amapolazul.www.quizamapola.logica.preguntas.grado2.PreguntasGeografiaGrado2;
import com.amapolazul.www.quizamapola.logica.preguntas.grado2.PreguntasMatematicasGrado2;
import com.amapolazul.www.quizamapola.logica.preguntas.grado3.PreguntasCienciasGrado3;
import com.amapolazul.www.quizamapola.logica.preguntas.grado3.PreguntasMatematicasGrado3;
import com.amapolazul.www.quizamapola.logica.preguntas.grado4.PreguntasCienciasGrado4;
import com.amapolazul.www.quizamapola.logica.preguntas.grado4.PreguntasMatematicasGrado4;
import com.amapolazul.www.quizamapola.logica.preguntas.grado5.PreguntasCienciasGrado5;
import com.amapolazul.www.quizamapola.logica.preguntas.grado5.PreguntasMatematicasGrado5;

public class MenuCategorias extends Activity {

    private Contador contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_categorias);

        contador = Contador.getInstance();
        contador.reiniciarContador();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_categorias, menu);
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

    public void irAPreguntasGradoUno(View view) {
        Intent intent = new Intent(this, PreguntasMatematicasGradoUno.class);
        startActivity(intent);
    }

    public void irAPreguntasGradoDos(View view) {
        Intent intent = new Intent(this, PreguntasMatematicasGrado2.class);
        startActivity(intent);
    }

    public void irAPreguntasGradoTres(View view) {
        Intent intent = new Intent(this, PreguntasMatematicasGrado3.class);
        startActivity(intent);
    }

    public void irAPreguntasGradoCuatro(View view) {
        Intent intent = new Intent(this, PreguntasMatematicasGrado4.class);
        startActivity(intent);
    }

    public void irAPreguntasGradoCinco(View view) {
        Intent intent = new Intent(this, PreguntasMatematicasGrado5.class);
        startActivity(intent);
    }
}
