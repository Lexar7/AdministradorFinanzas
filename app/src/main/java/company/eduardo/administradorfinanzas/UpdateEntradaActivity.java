package company.eduardo.administradorfinanzas;

import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Dao.EntradasDao;
import company.eduardo.administradorfinanzas.DataContext.Database;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaEntradas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Cuentas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Entradas;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CategoriaEntradasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CuentasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.EntradasViewModel;

public class UpdateEntradaActivity extends AppCompatActivity {

    public static String EXTRA_ENTRADA_ID = "IdEntrada";

    private Button btnCalendario, btnUpdate;
    private EditText etNombre, etSaldo;
    private TextView etFecha;
    Calendar c;
    DatePickerDialog dpd;
    private CategoriaEntradasViewModel viewModel;
    private CuentasViewModel viewModel1;
    private EntradasViewModel viewModel2;
    Spinner spinnerCategory, spinnerCuenta;
    List<CategoriaEntradas> categoriaEntradas1;
    List<Cuentas> cuentas1;
    private EntradasDao mEntradasDAO;
    private Entradas ENTRADAS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_entrada);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>"+"Editar Entrada"+"</font>"));
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mEntradasDAO = Room.databaseBuilder(this, Database.class, "Database")
                .allowMainThreadQueries()       //Allows room to do operation on main thread
                .build()
                .entradasDao();

        final Context ctx = this;
        etNombre= findViewById(R.id.editText);
        etSaldo= findViewById(R.id.editText4);
        etFecha = (TextView) findViewById(R.id.txtFecha);
        btnCalendario= (Button) findViewById(R.id.btnFecha);
        btnUpdate = (Button)findViewById(R.id.btnCrear);
        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategoria);
        spinnerCuenta = (Spinner) findViewById(R.id.spinnerCuenta);


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

                }, year, month, day);
                dpd.show();
            }
        });


        String idString = getIntent().getStringExtra(EXTRA_ENTRADA_ID);
        int idInt = Integer.parseInt(idString);
        ENTRADAS = mEntradasDAO.getOne(idInt).getValue();

        initViews();
    }

    private void initViews() {

        etNombre.setText(ENTRADAS.getDescripcion());
        etSaldo.setText(ENTRADAS.getSaldo().toString());
        etFecha.setText(ENTRADAS.getFecha().toString());
        spinnerCategory.setSelection(ENTRADAS.getIdCategoria());
        spinnerCuenta.setSelection(ENTRADAS.getIdCuenta());


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idCat = spinnerCategory.getSelectedItemPosition();
                int idCu = spinnerCuenta.getSelectedItemPosition();
                String descripcion = etNombre.getText().toString();
                Double saldo = Double.parseDouble(etSaldo.getText().toString());
                int idCa = categoriaEntradas1.get(idCat).getIdCategoria();
                int idCuent= cuentas1.get(idCu).getIdCategoria();

                ENTRADAS = new Entradas(descripcion, saldo, c, idCa, idCuent);

                //Insert to database
                mEntradasDAO.update(ENTRADAS);
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
