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
import android.view.MenuItem;
import android.view.View;
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
import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaEntradas;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaSalidas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Cuentas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Entradas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Salidas;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CategoriaEntradasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CategoriaSalidasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CuentasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.EntradasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.SalidasViewModel;

public class CrearSalidasF extends AppCompatActivity {
    private Button btnCalendario, btnCrear;
    private EditText etNombre, etSaldo;
    private TextView etFecha;
    private CategoriaSalidasViewModel viewModel;
    private CuentasViewModel viewModel1;
    private SalidasViewModel viewModel2;
    Spinner spinner, spinner1;
    List<CategoriaSalidas> categoriaSalidas1;
    List<Cuentas> cuentas1;
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
        setContentView(R.layout.activity_crear_salidas_f);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>"+"Nueva Salida"+"</font>"));
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Context ctx = this;
        etNombre= findViewById(R.id.editText);
        etSaldo= findViewById(R.id.editText4);
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

        spinner = findViewById(R.id.spinner2);
        spinner1= findViewById(R.id.spinner);

        viewModel = ViewModelProviders.of((FragmentActivity) ctx).get(CategoriaSalidasViewModel.class);
        viewModel2 = ViewModelProviders.of((FragmentActivity) ctx).get(SalidasViewModel.class);

        viewModel.getAll().observe(this, new Observer<List<CategoriaSalidas>>() {

            @Override

            public void onChanged(@Nullable List<CategoriaSalidas> categoriaSalidas) {

                List<String> salidas = new ArrayList<>();

                for (CategoriaSalidas salidas1: categoriaSalidas){

                    salidas.add(salidas1.getName());

                }
                if(categoriaSalidas!= null){
                    categoriaSalidas1 = categoriaSalidas;
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_item, salidas);

                //ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_item, categoriasCuentas.stream().map(c -> c.getName()).toArray());
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(adapter);

            }

        });

        viewModel1 = ViewModelProviders.of((FragmentActivity) ctx).get(CuentasViewModel.class);

        viewModel1.getAll().observe(this, new Observer<List<Cuentas>>() {

            @Override

            public void onChanged(@Nullable List<Cuentas> Cuentas) {

                List<String> cuentas = new ArrayList<>();

                for (Cuentas cuentas1: Cuentas){

                    cuentas.add(cuentas1.getNombreCuenta() );

                }

                if(Cuentas!= null){
                    cuentas1 = Cuentas;
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_item, cuentas);

                //ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_item, categoriasCuentas.stream().map(c -> c.getName()).toArray());
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner1.setAdapter(adapter);

            }

        });



        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int idCat = spinner.getSelectedItemPosition();
                    int idCu = spinner1.getSelectedItemPosition();
                    String nombre = etNombre.getText().toString();
                    Double saldo = Double.parseDouble(etSaldo.getText().toString());
                    int idCa = categoriaSalidas1.get(idCat).getIdCategoria();
                    int idCuent= cuentas1.get(idCu).getIdCategoria();
                    Salidas salidas = new Salidas(nombre,saldo,c,idCa, idCuent);
                    viewModel2.insert(salidas);
                    Toast.makeText(getApplicationContext(),"Entrada agregada exitosamente.",Toast.LENGTH_SHORT).show();
                    finish();
                }catch(Exception e){
                    System.out.print(e.getMessage().toString());
                    Toast.makeText(getApplicationContext(),"Ha ocurrido un error.",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
