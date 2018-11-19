package company.eduardo.administradorfinanzas.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import company.eduardo.administradorfinanzas.Adapters.CuentaAdapter;
import company.eduardo.administradorfinanzas.Adapters.EntradasAdapter;
import company.eduardo.administradorfinanzas.CrearEntradasF;
import company.eduardo.administradorfinanzas.DataContext.Entities.Cuentas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Entradas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Salidas;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CuentasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.EntradasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.SalidasViewModel;
import company.eduardo.administradorfinanzas.R;
import company.eduardo.administradorfinanzas.UpdateEntradaActivity;

public class NuevoEntradaFragment extends Fragment {

    View view;
    private EntradasViewModel viewModel;
    FloatingActionButton fab;

    public NuevoEntradaFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_nuevo_entradas, container, false);
        // Define the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final EntradasAdapter adapter = new EntradasAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Get a new or existing ViewModel from the ViewModelProvider.
        viewModel = ViewModelProviders.of(this).get(EntradasViewModel.class);
        viewModel.getAll().observe(this, new Observer<List<Entradas>>() {
            @Override
            public void onChanged(@Nullable List<Entradas> entradas) {
                // Update the cached copy of the words in the adapter.
                adapter.setCategoria(entradas);
            }
        });


        fab = (FloatingActionButton)view.findViewById(R.id.fab_btn2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CrearEntradasF.class));
            }
        });

        adapter.addActionCallback(new EntradasAdapter.ActionCallback() {
            @Override
            public void onLongClickListener(Entradas entradas) {
                Intent intent = new Intent(getActivity(), UpdateEntradaActivity.class);

                intent.putExtra(UpdateEntradaActivity.EXTRA_ENTRADA_ID,  entradas.getIdEntrada());
                startActivity(intent);
            }
        });
        return view;
    }
}
