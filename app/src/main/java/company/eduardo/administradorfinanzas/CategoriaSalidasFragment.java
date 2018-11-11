package company.eduardo.administradorfinanzas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CategoriaSalidasFragment extends Fragment {

    View view;
    FloatingActionButton fab;


    public CategoriaSalidasFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_categoria_salidas, container, false);
        fab = (FloatingActionButton)view.findViewById(R.id.fab_btn2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CrearCategoriasSalidas.class));
            }
        });
        return view;
    }
}
