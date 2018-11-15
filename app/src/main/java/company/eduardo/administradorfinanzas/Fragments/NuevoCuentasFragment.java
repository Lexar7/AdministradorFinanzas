package company.eduardo.administradorfinanzas.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import company.eduardo.administradorfinanzas.CrearCuentas;
import company.eduardo.administradorfinanzas.R;

public class NuevoCuentasFragment extends Fragment {

    View view;
    FloatingActionButton fab;
    public NuevoCuentasFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_nuevo_cuentas, container, false);
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
