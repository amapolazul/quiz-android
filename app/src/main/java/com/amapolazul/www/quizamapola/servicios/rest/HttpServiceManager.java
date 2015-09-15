package com.amapolazul.www.quizamapola.servicios.rest;

import com.amapolazul.www.quizamapola.logica.constantes.Constantes;
import com.amapolazul.www.quizamapola.logica.pojos.PreguntasPojo;
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

/**
 * Created by jsmartinez on 14/09/2015.
 */
public class HttpServiceManager {

    public PreguntasPojo llamarServicioPreguntas() throws IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(Constantes.URL_SERVICIOS);
        ResponseHandler handler = darResponseHandler();
        PreguntasPojo preguntasPojo = (PreguntasPojo)httpclient.execute(httpget, handler);
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
