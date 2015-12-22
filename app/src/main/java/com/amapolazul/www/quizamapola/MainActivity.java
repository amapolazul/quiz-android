package com.amapolazul.www.quizamapola;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.amapolazul.www.quizamapola.logica.constantes.Constantes;
import com.amapolazul.www.quizamapola.logica.enums.CategoriasEnum;
import com.amapolazul.www.quizamapola.logica.menu.MenuCategorias;
import com.amapolazul.www.quizamapola.logica.pojos.PreguntaPojo;
import com.amapolazul.www.quizamapola.logica.pojos.PreguntasPojo;
//import com.amapolazul.www.quizamapola.logica.preguntas.grado1.LenguajeIntroduccionPrimerGrado;
//import com.amapolazul.www.quizamapola.logica.preguntas.grado1.PreguntasGeografiaGradoUno;
//import com.amapolazul.www.quizamapola.logica.preguntas.grado1.PreguntasMatematicasGradoUno;
//import com.amapolazul.www.quizamapola.persistencia.Pregunta;
import com.amapolazul.www.quizamapola.persistencia.Pregunta;
import com.amapolazul.www.quizamapola.persistencia.QuizDAO;
//import com.amapolazul.www.quizamapola.servicios.rest.HttpServiceManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;


public class MainActivity extends Activity {

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = new ProgressDialog(this);
        progress.setMessage("Cargando preguntas");
        progress.setTitle("Quiz");
        progress.show();
        new HttpServiceManager(this).execute();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void enviarAMenuPrincipal(View view) {
        Intent intent = new Intent(this, MenuCategorias.class);
        startActivity(intent);
    }

    private class HttpServiceManager extends AsyncTask<Void, Void, PreguntasPojo> {

        private QuizDAO quizDao;

        public HttpServiceManager(Context context){
            System.out.println(context);
            quizDao = new QuizDAO(context);
        }

        @Override
        protected PreguntasPojo doInBackground(Void... params) {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(Constantes.URL_SERVICIOS);
            httpget.addHeader("accept", "application/json");
            ResponseHandler handler = darResponseHandler();
            PreguntasPojo preguntasPojo = null;
            try {
                quizDao.open();
//                quizDao.removeAll();
                List<Pregunta> preguntas = quizDao.darPreguntas(CategoriasEnum.CIENCIAS.getValor(), "1");
                if(preguntas == null) {
                    preguntasPojo = (PreguntasPojo) httpclient.execute(httpget, handler);
                    for (PreguntaPojo preguntaPojo : preguntasPojo.getPreguntaPojos()) {
                        Pregunta pregunta = new Pregunta();
                        pregunta.setEnunciado(preguntaPojo.getEnunciado());
                        pregunta.setRespuestaA(preguntaPojo.getRespuestaA());
                        pregunta.setRespuestaB(preguntaPojo.getRespuestaB());
                        pregunta.setRespuestaC(preguntaPojo.getRespuestaC());
                        pregunta.setRespuestaD(preguntaPojo.getRespuestaD());
                        pregunta.setGrado(preguntaPojo.getGrado());
                        pregunta.setRespuestaCorrecta(preguntaPojo.getRespuestaCorrecta());
                        pregunta.setLectura(preguntaPojo.getLectura());
                        pregunta.setImagen(preguntaPojo.getUrlImagen());
                        pregunta.setCategoria(preguntaPojo.getCategoria());
                        System.out.println("agregando pregunta " + preguntaPojo);
                        quizDao.crearPregunta(pregunta);
                    }
                }
                quizDao.close();
                progress.dismiss();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return preguntasPojo;
        }

        private ResponseHandler darResponseHandler(){
            ResponseHandler<PreguntasPojo> rh = new ResponseHandler<PreguntasPojo>() {

                @Override
                public PreguntasPojo handleResponse(
                        final HttpResponse response) throws IOException {
                    StatusLine statusLine = response.getStatusLine();
                    HttpEntity entity = response.getEntity();
                    if (statusLine.getStatusCode() >= 300) {
                        throw new HttpResponseException(
                                statusLine.getStatusCode(),
                                statusLine.getReasonPhrase());
                    }
                    if (entity == null) {
                        throw new ClientProtocolException("Response contains no content");
                    }
                    Gson gson = new GsonBuilder().create();
                    ContentType contentType = ContentType.getOrDefault(entity);
                    Charset charset = contentType.getCharset();
                    Reader reader = new InputStreamReader(entity.getContent(), charset);
                    return gson.fromJson(reader, PreguntasPojo.class);
                }
            };

            return rh;
        }
    }
}
