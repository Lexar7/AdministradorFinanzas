package company.eduardo.administradorfinanzas;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Dao.CategoriasCuentasDao;
import company.eduardo.administradorfinanzas.DataContext.Database;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriasCuentas;
import company.eduardo.administradorfinanzas.DataContext.Repositories.CategoriasCuentasRepository;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CategoriasCuentasViewModel;


public class CrearCategorias extends AppCompatActivity {
    private EditText etCategoria;
    private Button btGuardar, btMostrar;
    private TextView etMostrar;
    private CategoriasCuentas mCategoriasCuentas;
    private CategoriasCuentasRepository categoriasCuentasRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_categorias);
        categoriasCuentasRepository = new CategoriasCuentasRepository(getApplication());
        etCategoria = findViewById(R.id.txtCrearCate);
        etMostrar= findViewById(R.id.txtMostrar);
        btGuardar = findViewById(R.id.btnCrear);
        btMostrar = findViewById(R.id.btnMostrar);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crear();
            }
        });

btMostrar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mostrar();
    }
});

        categoriasCuentasRepository.getAll().observe(this, new Observer<List<CategoriasCuentas>>() {
            @Override
            public void onChanged(@Nullable List<CategoriasCuentas> notes) {
                for(CategoriasCuentas note : notes) {
                    System.out.println("-----------------------");
                    System.out.println(note.getIdCategoria());
                    System.out.println(note.getName());
                    System.out.println(note.getImage());
                }
            }
        });

    }

    private void mostrar(){
       /*
        List<CategoriasCuentas> list = notas.getValue();
        if(list.size()> 0) {
            String mensaje ="";
            for(int i =0; i<list.size(); i++){
                mensaje+="\n "+list.get(i).getName();
            }
            etMostrar.setText(mensaje);
        }*/

    }
  private void crear() {
      String textoCategoria = etCategoria.getText().toString();
              mCategoriasCuentas = new CategoriasCuentas(textoCategoria, "@drawable/money.png");
              categoriasCuentasRepository.insert(mCategoriasCuentas);
      Toast toast1 = Toast.makeText(getApplicationContext(), "Ingreso la categoria correctamente!", Toast.LENGTH_SHORT);
      toast1.show();
          }

  /*  private void crear() {
        String textoCategoria = etCategoria.getText().toString();
        if (!etCategoria.equals("")) {
            if (mCategoriasCuentas == null) {
                mCategoriasCuentas = new CategoriasCuentas(textoCategoria, "@drawable/money");
                DaoCategoriaCuentas.insert(mCategoriasCuentas);
                //.addNota(mNota);

            }
        }
    }*/
}

