package matematicas_amapola.com.amapolazul.www.crazy123.logica;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.sql.SQLException;

import matematicas_amapola.com.amapolazul.www.crazy123.MainActivity;
import matematicas_amapola.com.amapolazul.www.crazy123.R;
import matematicas_amapola.com.amapolazul.www.crazy123.persistencia.QuizDAO;

public class Finalizar extends Activity {

    private QuizDAO quizDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar);

        quizDao = new QuizDAO(this);


    }

    public void volver(View view) {
        try {
            quizDao.open();
            quizDao.removeAll();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            quizDao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_finalizar, menu);
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
