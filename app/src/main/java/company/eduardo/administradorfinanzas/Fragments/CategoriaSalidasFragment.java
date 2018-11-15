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

import company.eduardo.administradorfinanzas.Adapters.CategoriaEntradaListAdapter;
import company.eduardo.administradorfinanzas.Adapters.CategoriaSalidaListAdapter;
import company.eduardo.administradorfinanzas.CrearCategoriasSalidas;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaEntradas;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaSalidas;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CategoriaEntradasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CategoriaSalidasViewModel;
import company.eduardo.administradorfinanzas.R;

public class CategoriaSalidasFragment extends Fragment {

    View view;

    Button btnNew;

    private ListView lv1;

    public String categorias [] = {"Salarios", "Publicidad", "Compras", "Arrendamientos", "Canones", "Reparaciones",
            "Auditorias", "Servicios Independientes", "Suministros", "Tributos", "Gastos Financieros", "Otros Gastos"};


    private CategoriaSalidasViewModel mSalidasViewModel;

    public CategoriaSalidasFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_categoria_salidas, container, false);

        btnNew = (Button)view.findViewById(R.id.btnNuevo);

        // Define the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final CategoriaSalidaListAdapter adapter = new CategoriaSalidaListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mSalidasViewModel = ViewModelProviders.of(this).get(CategoriaSalidasViewModel.class);
        mSalidasViewModel.getAll().observe(this, new Observer<List<CategoriaSalidas>>() {
            @Override
            public void onChanged(@Nullable List<CategoriaSalidas> categoriasSalidas) {
                // Update the cached copy of the words in the adapter.
                adapter.setCategoria(categoriasSalidas);
            }
        });


        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CrearCategoriasSalidas.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
