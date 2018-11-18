package company.eduardo.administradorfinanzas;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class CrearSalidasF extends AppCompatActivity {
    private Button btnCalendario;
    private TextView etFecha;
    Calendar c;
    DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_salidas_f);
        final Context ctx = this;
        etFecha = (TextView) findViewById(R.id.txtFecha);
        btnCalendario= (Button) findViewById(R.id.btnFecha);
        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c= Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month= c.get(Calendar.MONTH);
                int year=c.get(Calendar.YEAR);
                dpd= new DatePickerDialog(ctx, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        etFecha.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                    }

                },day,month,year);
                dpd.show();

            }
        });
    }
}
