package company.eduardo.administradorfinanzas;

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

public class CategoriaCuentasFragment extends Fragment {

    View view;

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
}
