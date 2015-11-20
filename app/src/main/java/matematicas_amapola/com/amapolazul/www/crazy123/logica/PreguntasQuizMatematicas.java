package matematicas_amapola.com.amapolazul.www.crazy123.logica;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.List;

import matematicas_amapola.com.amapolazul.www.crazy123.MainActivity;
import matematicas_amapola.com.amapolazul.www.crazy123.R;
import matematicas_amapola.com.amapolazul.www.crazy123.persistencia.Pregunta;
import matematicas_amapola.com.amapolazul.www.crazy123.persistencia.QuizDAO;

public class PreguntasQuizMatematicas extends Activity {

    private StringBuffer respuesta;
    private TextView campoRespuesta;

    private QuizDAO quizDao;
    private int indicePrguntaActual;
    private Pregunta preguntaActual;
    private List<Pregunta> preguntas;

    private Animation animation;

    private MediaPlayer sonidoCorrecto;
    private MediaPlayer sonidoIncorrecta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_quiz_matematicas);

        respuesta = new StringBuffer("");
        campoRespuesta = (TextView) findViewById(R.id.respuesta);
        campoRespuesta.setText(respuesta.toString());

        animation = AnimationUtils.loadAnimation(this, R.anim.escalar);

        sonidoCorrecto = MediaPlayer.create(this, R.raw.correcta);
        sonidoIncorrecta = MediaPlayer.create(this, R.raw.incorrecta);

        try {
            quizDao = new QuizDAO(this);
            quizDao.open();
            preguntas = quizDao.darPreguntas();
            indicePrguntaActual = quizDao.darIdPreguntaActual() - 1;
            inicializarQuiz();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void inicializarQuiz(){
        preguntaActual = preguntas.get(indicePrguntaActual);
        ImageView imagenPregunta = (ImageView) findViewById(R.id.imagenPReguntaActual);
        imagenPregunta.setImageResource(preguntaActual.getImagen());
        respuesta = new StringBuffer("");
        campoRespuesta.setText(respuesta.toString());
    }

    public void limpiarRespuesta(View view){
        respuesta = new StringBuffer("");
        campoRespuesta.setText(respuesta.toString());
    }

    public void comprobarRespuesta(View view){
        String respuestaFinal = campoRespuesta.getText().toString();
        if(preguntaActual.getRespuestaCorrecta().equals(respuestaFinal)){
            sonidoCorrecto.start();
            indicePrguntaActual++;
            if(indicePrguntaActual < preguntas.size()){
                quizDao.actualizarPreguntaActual(preguntas.get(indicePrguntaActual).getId().toString());
                inicializarQuiz();
            } else {
                Intent intent = new Intent(this, Finalizar.class);
                startActivity(intent);
            }
        } else {
            sonidoIncorrecta.start();
        }
    }

    public void cerrar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void presionaTeclado(View view) {
        view.startAnimation(animation);
        int viewId = view.getId();
        switch (viewId){
            case R.id.imagenCero :
                respuesta.append("0");
                break;
            case R.id.imagenUno :
                respuesta.append("1");
                break;
            case R.id.imagenDos :
                respuesta.append("2");
                break;
            case R.id.imagenTres :
                respuesta.append("3");
                break;
            case R.id.imagenCuatro :
                respuesta.append("4");
                break;
            case R.id.imagenCinco :
                respuesta.append("5");
                break;
            case R.id.imagenSeis :
                respuesta.append("6");
                break;
            case R.id.imagenSiete :
                respuesta.append("7");
                break;
            case R.id.imagenOcho :
                respuesta.append("8");
                break;
            case R.id.imagenNueve :
                respuesta.append("9");
                break;
        }
        campoRespuesta.setText(respuesta.toString());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preguntas_quiz_matematicas, menu);
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
