package company.eduardo.administradorfinanzas;

import android.app.Application;
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

import java.util.ArrayList;
import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaEntradas;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriasCuentas;
import company.eduardo.administradorfinanzas.DataContext.Repositories.CategoriasCuentasRepository;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CategoriaEntradasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CategoriasCuentasViewModel;

public class CategoriaCuentasFragment extends Fragment {


    View view;
    private ListView lista;
    private CategoriasCuentas mCategoriasCuentas;
    private CategoriasCuentasRepository categoriasCuentasRepository;
    private ListView lv1;

    Button btnNew;

    public String categorias [] = {"Salarios", "Publicidad", "Compras", "Arrendamientos", "Canones", "Reparaciones",
    "Auditorias", "Servicios Independientes", "Suministros", "Tributos", "Gastos Financieros", "Otros Gastos"};

    List<CategoriasCuentas> categoriasCuentas;
    ArrayAdapter adapter;

    private CategoriasCuentasViewModel mCuentasViewModel;


    public CategoriaCuentasFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_categoria_cuentas, container, false);
        btnNew = (Button)view.findViewById(R.id.btnNuevo);
        lv1 = (ListView)view.findViewById(R.id.lv);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final CategoriaCuentaListAdapter adapter = new CategoriaCuentaListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mCuentasViewModel = ViewModelProviders.of(this).get(CategoriasCuentasViewModel.class);
        mCuentasViewModel.getAll().observe(this, new Observer<List<CategoriasCuentas>>() {
            @Override
            public void onChanged(@Nullable List<CategoriasCuentas> categoriasCuentas) {
                // Update the cached copy of the words in the adapter.
                adapter.setCategoria(categoriasCuentas);
            }
        });

        //adapter = new ArrayAdapter(getActivity(), R.layout.list_item_categorias, categoriasCuentas);
        //lv1.setAdapter(adapter);


        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CrearCategorias.class);
                startActivity(intent);
            }
        });

        /*
        CategoriasCuentasViewModel mViewModel = ViewModelProviders.of(getActivity()).get(CategoriasCuentasViewModel.class);
        mViewModel.getAll().observe(getActivity(), new Observer<List<CategoriasCuentas>>() {
            @Override
            public void onChanged(@Nullable List<CategoriasCuentas> categoriaCuentas) {
                adapter = new ArrayAdapter(getActivity(), R.layout.list_item_categorias, categoriaCuentas);
                lv1.setAdapter(adapter);
            }
        });*/

        return view;
    }
    private void mostrar1() {
        List<CategoriasCuentas> notas = (List<CategoriasCuentas>) categoriasCuentasRepository.getAll().getValue();
        AdapterCat adapter = new AdapterCat(getActivity(), notas);
        lv1.setAdapter(adapter);
    }
}
