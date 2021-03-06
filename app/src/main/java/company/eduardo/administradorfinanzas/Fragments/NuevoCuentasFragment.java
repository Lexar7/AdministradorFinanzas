package company.eduardo.administradorfinanzas.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import company.eduardo.administradorfinanzas.Adapters.CategoriaCuentaListAdapter;
import company.eduardo.administradorfinanzas.Adapters.CuentaAdapter;
import company.eduardo.administradorfinanzas.CrearCuentas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Cuentas;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CategoriasCuentasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CuentasViewModel;
import company.eduardo.administradorfinanzas.R;

public class NuevoCuentasFragment extends Fragment {

    View view;
    FloatingActionButton fab;
    private CuentasViewModel viewModel;
    public NuevoCuentasFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_nuevo_cuentas, container, false);

        // Define the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final CuentaAdapter adapter = new CuentaAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Get a new or existing ViewModel from the ViewModelProvider.
        viewModel = ViewModelProviders.of(this).get(CuentasViewModel.class);
        viewModel.getAll().observe(this, new Observer<List<Cuentas>>() {
            @Override
            public void onChanged(@Nullable List<Cuentas> categoriasCuentas) {
                // Update the cached copy of the words in the adapter.
                adapter.setCategoria(categoriasCuentas);
            }
        });
        fab = (FloatingActionButton)view.findViewById(R.id.fab_btn2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CrearCuentas.class));
            }
        });
        return view;
    }
}
