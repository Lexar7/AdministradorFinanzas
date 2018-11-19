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

import company.eduardo.administradorfinanzas.Adapters.EntradasAdapter;
import company.eduardo.administradorfinanzas.Adapters.SalidasAdapter;
import company.eduardo.administradorfinanzas.CrearSalidasF;
import company.eduardo.administradorfinanzas.DataContext.Entities.Entradas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Salidas;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.EntradasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.SalidasViewModel;
import company.eduardo.administradorfinanzas.R;

public class NuevoSalidasFragment extends Fragment {

    View view;
    private SalidasViewModel viewModel;
    FloatingActionButton fab;

    public NuevoSalidasFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_nuevo_salidas, container, false);
        // Define the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final SalidasAdapter adapter = new SalidasAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Get a new or existing ViewModel from the ViewModelProvider.
        viewModel = ViewModelProviders.of(this).get(SalidasViewModel.class);
        viewModel.getAll().observe(this, new Observer<List<Salidas>>() {
            @Override
            public void onChanged(@Nullable List<Salidas> salidas) {
                // Update the cached copy of the words in the adapter.
                adapter.setCategoria(salidas);
            }
        });
        fab = (FloatingActionButton)view.findViewById(R.id.fab_btn2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),CrearSalidasF.class));
            }
        });
        return view;
    }
}
