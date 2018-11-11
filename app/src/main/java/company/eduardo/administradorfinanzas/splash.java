package company.eduardo.administradorfinanzas;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends AppCompatActivity {

    private Boolean botonBackPresionado=false;
    private static final int DURACION_SPLASF=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        Handler manejador = new Handler();
        manejador.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                if (!botonBackPresionado){
                    Intent intento = new Intent(splash.this, MainActivity.class);
                    startActivity(intento);
                }
            }

        }, DURACION_SPLASF);
    }

    public void OnBackPressed(){
        botonBackPresionado=true;
        super.onBackPressed();
    }
}
