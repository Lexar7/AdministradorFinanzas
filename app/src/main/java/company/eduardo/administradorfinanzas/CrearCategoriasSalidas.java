package company.eduardo.administradorfinanzas;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaEntradas;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaSalidas;
import company.eduardo.administradorfinanzas.DataContext.Repositories.CategoriaEntradasRepository;
import company.eduardo.administradorfinanzas.DataContext.Repositories.CategoriaSalidasRepository;

public class CrearCategoriasSalidas extends AppCompatActivity {
    private EditText etCategoria;
    private Button btGuardar, btMostrar;
    private TextView etMostrar;
    private CategoriaSalidas mCategoriasSalidas;
    private CategoriaSalidasRepository categoriaSalidasRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_categorias_salidas);
        categoriaSalidasRepository = new CategoriaSalidasRepository(getApplication());
        etCategoria = findViewById(R.id.txtCrearCate);
        etMostrar= findViewById(R.id.txtCrearCate);
        btGuardar = findViewById(R.id.btnCrear);
        btMostrar = findViewById(R.id.btnMostrar);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crear();
                etCategoria.setText("");
            }
        });

        btMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar();
            }
        });
//Verificar si  insertó en la BD
        categoriaSalidasRepository.getAll().observe(this, new Observer<List<CategoriaSalidas>>() {
            @Override
            public void onChanged(@Nullable List<CategoriaSalidas> notes) {
                for(CategoriaSalidas note : notes) {
                    System.out.println("-----------------------");
                    System.out.println(note.getIdCategoria());
                    System.out.println(note.getName());
                    System.out.println(note.getImage());
                }
            }
        });
    }
    private void crear() {
        String textoCategoria = etCategoria.getText().toString();
        mCategoriasSalidas = new CategoriaSalidas(textoCategoria, "@drawable/money.png");
        categoriaSalidasRepository.insert(mCategoriasSalidas);
        Toast toast1 = Toast.makeText(getApplicationContext(), "Ingreso la categoria correctamente!", Toast.LENGTH_SHORT);
        toast1.show();

    }
    private void mostrar(){

    }
}
