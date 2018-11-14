package company.eduardo.administradorfinanzas;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriasCuentas;
import company.eduardo.administradorfinanzas.DataContext.Repositories.CategoriasCuentasRepository;

public class CategoriaCuentasFragment extends Fragment {


    View view;
    private ListView lista;
    private CategoriasCuentas mCategoriasCuentas;
    private CategoriasCuentasRepository categoriasCuentasRepository;
    private ListView lv1;

    Button btnNew;

    public String categorias [] = {"Salarios", "Publicidad", "Compras", "Arrendamientos", "Canones", "Reparaciones",
    "Auditorias", "Servicios Independientes", "Suministros", "Tributos", "Gastos Financieros", "Otros Gastos"};

    public CategoriaCuentasFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_categoria_cuentas, container, false);
        btnNew = (Button)view.findViewById(R.id.btnNuevo);
        lv1 = (ListView)view.findViewById(R.id.lv);
        categoriasCuentasRepository = new CategoriasCuentasRepository(this.getActivity().getApplication());
        mostrar1();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item_categorias, categorias);
        lv1.setAdapter(adapter);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CrearCategorias.class);
                startActivity(intent);
            }
        });

        return view;
    }
    private void mostrar1() {
        //con esta linea se recupera el Array de los datos de la base, se almacena en la Variable NOTAS
        List<CategoriasCuentas> notas = (List<CategoriasCuentas>) categoriasCuentasRepository.getAll().getValue();
    }
}
