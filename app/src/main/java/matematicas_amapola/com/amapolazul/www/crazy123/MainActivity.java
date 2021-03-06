package matematicas_amapola.com.amapolazul.www.crazy123;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.sql.SQLException;
import java.util.List;

import matematicas_amapola.com.amapolazul.www.crazy123.logica.PreguntasQuizMatematicas;
import matematicas_amapola.com.amapolazul.www.crazy123.persistencia.Pregunta;
import matematicas_amapola.com.amapolazul.www.crazy123.persistencia.QuizDAO;
import matematicas_amapola.com.amapolazul.www.crazy123.utilidades.Utils;


public class MainActivity extends Activity {

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pd = new ProgressDialog(this);
        pd.setMessage("Cargando preguntas");
        pd.setTitle("Crazy 123");
        pd.show();
        new CargarPreguntasThread(this).execute();

    }

    private class CargarPreguntasThread extends AsyncTask<Void, Void, Void> {

        private QuizDAO quizDao;

        public CargarPreguntasThread(Context context){
            System.out.println(context);
            quizDao = new QuizDAO(context);
        }

        protected Void doInBackground(Void... args) {
            try{
                quizDao.open();
                int[] respuestas = Utils.darRespuestasCorrectas();
                int[] preguntasIds = Utils.darImagenesId();
                //quizDao.removeAll();
                List<Pregunta> preguntas = quizDao.darPreguntas();
                if(preguntas == null || preguntas.isEmpty()){
                    for(int i = 0; i < respuestas.length; i++){
                        System.out.println("guardando pregunta " + i);
                        Pregunta pregunta = new Pregunta();
                        pregunta.setImagen(preguntasIds[i]);
                        pregunta.setRespuestaCorrecta(String.valueOf(respuestas[i]));
                        quizDao.crearPregunta(pregunta);
                    }
                    quizDao.insertarPreguntaActual("0");
                }
                pd.dismiss();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {

        }
    }

    public void irAPreguntas(View view){
        Intent intent = new Intent(this, PreguntasQuizMatematicas.class);
        startActivity(intent);
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
}
