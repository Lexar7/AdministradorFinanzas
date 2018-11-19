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
import company.eduardo.administradorfinanzas.DataContext.Repositories.CategoriaEntradasRepository;
public class CrearCategoriaEntradas extends AppCompatActivity {
    private EditText etCategoria;
    private Button btGuardar, btMostrar;
    private TextView etMostrar;
    private CategoriaEntradas mCategoriasEntradas;
    private CategoriaEntradasRepository categoriaEntradasRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_categoria_entradas);
        categoriaEntradasRepository = new CategoriaEntradasRepository(getApplication());
        etCategoria = findViewById(R.id.txtCrearCate);
        etMostrar= findViewById(R.id.txtCrearCate);
        btGuardar = findViewById(R.id.btnCrear);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crear();
                etCategoria.setText("");
            }
        });


        categoriaEntradasRepository.getAll().observe(this, new Observer<List<CategoriaEntradas>>() {
            @Override
            public void onChanged(@Nullable List<CategoriaEntradas> notes) {
                for(CategoriaEntradas note : notes) {
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
        mCategoriasEntradas = new CategoriaEntradas(textoCategoria, "@drawable/money.png");
        categoriaEntradasRepository.insert(mCategoriasEntradas);
        Toast toast1 = Toast.makeText(getApplicationContext(), "Ingreso la categoria correctamente!", Toast.LENGTH_SHORT);
        toast1.show();

    }
    private void mostrar(){

    }
}
