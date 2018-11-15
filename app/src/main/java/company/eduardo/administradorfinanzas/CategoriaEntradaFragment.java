package company.eduardo.administradorfinanzas;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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

import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaEntradas;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CategoriaEntradasViewModel;

public class CategoriaEntradaFragment extends Fragment {

    View view;

    Button btnNew;

    private ListView lv1;

    public String categorias [] = {"Salarios", "Publicidad", "Compras", "Arrendamientos", "Canones", "Reparaciones",
            "Auditorias", "Servicios Independientes", "Suministros", "Tributos", "Gastos Financieros", "Otros Gastos"};


    public CategoriaEntradaFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_categoria_entradas, container, false);

        btnNew = (Button)view.findViewById(R.id.btnNuevo);

        lv1 = (ListView)view.findViewById(R.id.lv);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item_categorias, categorias);
        lv1.setAdapter(adapter);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CrearCategoriaEntradas.class);
                startActivity(intent);
            }
        });

       /* CategoriaEntradasViewModel mViewModel = ViewModelProviders.of(this).get(CategoriaEntradasViewModel.class);
        mViewModel.getAll().observe(this, new Observer<List<CategoriaEntradas>>() {
            @Override
            public void onChanged(@Nullable List<CategoriaEntradas> categoriaEntradas) {
                adapter = new R.layout.listcategoria(getActivity(), categoriaEntradas);
                lv1.setAdapter(adapter);
            }
        });*/

        return view;
    }
}
