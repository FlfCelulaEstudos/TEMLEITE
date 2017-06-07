package br.edu.flf.alergia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import br.edu.flf.alergia1.R;

/**
 * Created by tiagosombra on 06/06/17.
 */

public class SplashActivity extends AppCompatActivity{

    private static int TEMPO_SPLASH = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            //Carrega a imagem LOGO
            @Override
            public void run() {
                // Este método será executado por 5 segundos
                // Antes de iniciar a MainActivity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                // Fecha a activity atual
                finish();
            }
        }, TEMPO_SPLASH);
    }

}
