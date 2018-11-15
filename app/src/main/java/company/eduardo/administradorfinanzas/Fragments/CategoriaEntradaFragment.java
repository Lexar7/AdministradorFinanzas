package company.eduardo.administradorfinanzas.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import company.eduardo.administradorfinanzas.Adapters.CategoriaCuentaListAdapter;
import company.eduardo.administradorfinanzas.Adapters.CategoriaEntradaListAdapter;
import company.eduardo.administradorfinanzas.CrearCategoriaEntradas;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaEntradas;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriasCuentas;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CategoriaEntradasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CategoriasCuentasViewModel;
import company.eduardo.administradorfinanzas.R;

public class CategoriaEntradaFragment extends Fragment {

    View view;

    Button btnNew;

    private ListView lv1;

    public String categorias [] = {"Salarios", "Publicidad", "Compras", "Arrendamientos", "Canones", "Reparaciones",
            "Auditorias", "Servicios Independientes", "Suministros", "Tributos", "Gastos Financieros", "Otros Gastos"};

    private CategoriaEntradasViewModel mEntradasViewModel;


    public CategoriaEntradaFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_categoria_entradas, container, false);
        btnNew = (Button)view.findViewById(R.id.btnNuevo);

        // Define the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final CategoriaEntradaListAdapter adapter = new CategoriaEntradaListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mEntradasViewModel = ViewModelProviders.of(this).get(CategoriaEntradasViewModel.class);
        mEntradasViewModel.getAll().observe(this, new Observer<List<CategoriaEntradas>>() {
            @Override
            public void onChanged(@Nullable List<CategoriaEntradas> categoriasEntradas) {
                // Update the cached copy of the words in the adapter.
                adapter.setCategoria(categoriasEntradas);
            }
        });


        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CrearCategoriaEntradas.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
