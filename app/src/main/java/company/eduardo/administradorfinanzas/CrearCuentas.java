package company.eduardo.administradorfinanzas;
import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.DragAndDropPermissions;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import company.eduardo.administradorfinanzas.DataContext.Database;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriasCuentas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Cuentas;
import company.eduardo.administradorfinanzas.DataContext.Repositories.CategoriasCuentasRepository;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CategoriasCuentasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CuentasViewModel;

public class CrearCuentas extends AppCompatActivity {

    private Button btnCalendario, btnCrear;
    private TextView etFecha;
    private EditText etNombre, etSaldo;
    private Spinner sp1;
    List<CategoriasCuentas> categoriasCuentas1;
    CategoriasCuentasViewModel repository;
    CuentasViewModel viewModel;
    Calendar c;
    DatePickerDialog dpd;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuentas);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>"+"Nueva Cuenta"+"</font>"));
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Context ctx = this;
        etNombre= findViewById(R.id.editText);
        etSaldo= findViewById(R.id.editText4);
        repository = ViewModelProviders.of((FragmentActivity) ctx).get(CategoriasCuentasViewModel.class);
        viewModel =ViewModelProviders.of((FragmentActivity) ctx).get(CuentasViewModel.class);
        sp1 = (Spinner) findViewById(R.id.spinner2);
        etFecha = (TextView) findViewById(R.id.txtFecha);
        btnCalendario= (Button) findViewById(R.id.btnFecha);
        btnCrear = findViewById(R.id.btnCrear);

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
                        //etFecha.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
                        c.set(mYear, mMonth, mDay);
                        String dateString = sdf.format(c.getTime());
                        etFecha.setText(dateString);
                    }
                }, year, month, day);
                dpd.show();
            }
        });

        repository.getAll().observe(this, new Observer<List<CategoriasCuentas>>() {
            @Override
            public void onChanged(@Nullable List<CategoriasCuentas> categoriasCuentas) {
                List<String> cuentas = new ArrayList<>();

                for (CategoriasCuentas cuentas1: categoriasCuentas){

                    cuentas.add(cuentas1.getName());

                }
                if(categoriasCuentas!= null){
                    categoriasCuentas1 = categoriasCuentas;
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_item, cuentas);

                //ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_item, categoriasCuentas.stream().map(c -> c.getName()).toArray());
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                sp1.setAdapter(adapter);
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int idS = sp1.getSelectedItemPosition();
                    String nombre = etNombre.getText().toString();
                    Double saldo = Double.parseDouble(etSaldo.getText().toString());
                    int id = categoriasCuentas1.get(idS).getIdCategoria();
                    Cuentas cuentas = new Cuentas(nombre,saldo,id,c);
                    viewModel.insert(cuentas);
                    Toast.makeText(getApplicationContext(),"Cuenta agregada exitosamente.",Toast.LENGTH_SHORT).show();
                    finish();
                }catch(Exception e){
                    System.out.print(e.getMessage().toString());
                    Toast.makeText(getApplicationContext(),"Ha ocurrido un error.",Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}

