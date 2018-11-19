package company.eduardo.administradorfinanzas.Fragments;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.gms.common.util.NumberUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import javax.xml.parsers.SAXParser;

import company.eduardo.administradorfinanzas.Adapters.CategoriaEntradaListAdapter;
import company.eduardo.administradorfinanzas.Adapters.MovimientosAdapter;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaEntradas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Cuentas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Entradas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Salidas;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CategoriaEntradasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CuentasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.EntradasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.SalidasViewModel;
import company.eduardo.administradorfinanzas.Models.Movimientos;
import company.eduardo.administradorfinanzas.Nuevo;
import company.eduardo.administradorfinanzas.R;

public class HomeFragment extends Fragment {

    FloatingActionButton fab;
    String currentDate;
    TextView tvDate, tvGastos, tvIngresos, tvSaldo;
    Calendar calendar, calendar2,calendar3;
    DatePickerDialog dpd;
    private SalidasViewModel viewModel;
    private EntradasViewModel viewModel1;
    private CuentasViewModel viewModel2;
    List<Movimientos> movimientos;
    Double saldo, gastos,ingrsos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        calendar = Calendar.getInstance();
        currentDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
        calendar2= Calendar.getInstance();
        calendar2.add(calendar2.DATE,-1);
        calendar3= Calendar.getInstance();

        tvDate = (TextView)view.findViewById(R.id.txtDate);
        fab = (FloatingActionButton)view.findViewById(R.id.fab_btn);

        tvGastos= view.findViewById(R.id.txtGastos);
        tvIngresos= view.findViewById(R.id.txtIngresos);
        tvSaldo= view.findViewById(R.id.txtSaldo);

        tvDate.setText(currentDate);

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        final MovimientosAdapter adapter = new MovimientosAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        llenarRecycler(adapter, recyclerView);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                int day = calendar.get(Calendar.DAY_OF_WEEK);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int nYear, int nMonth, int nDay) {

                        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
                        calendar.set(nYear, nMonth, nDay);
                        String dateString = sdf.format(calendar.getTime());
                        tvDate.setText(dateString);
                        calendar2.set(nYear, nMonth, nDay,00,00,00);
                        calendar3.set(nYear, nMonth, nDay,00,00,00);
                        calendar3.add(calendar3.DATE,1);
                        llenarRecycler(adapter, recyclerView);

                    }
                }, year, month, day);
                dpd.show();
            }
        });

        //adapter.setMovimientos(movimientos);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Nuevo.class));
            }
        });

        return view;
    }

    public void llenarRecycler(MovimientosAdapter adapter, RecyclerView recyclerView){


        final MovimientosAdapter adapter1= adapter;

        if(movimientos!=null){
            for(int i=0; i<movimientos.size(); i++){
                movimientos.remove(i);
                recyclerView.removeViewAt(i);
                adapter1.notifyItemRemoved(i);
                adapter1.notifyItemRangeChanged(i, movimientos.size());
                adapter1.notifyDataSetChanged();
            }
        }
        saldo =0.0;
        gastos=0.0;
        ingrsos=0.0;
        // Get a new or existing ViewModel from the ViewModelProvider.
        viewModel = ViewModelProviders.of(this).get(SalidasViewModel.class);
        viewModel.getAll(calendar3).observe(this, new Observer<List<Salidas>>() {
            @Override
            public void onChanged(@Nullable List<Salidas> salidas) {
                // Update the cached copy of the words in the adapter
                if(salidas !=null){
                    movimientos= new ArrayList<>();
                }
                for(Salidas salidas1:salidas){
                    if(salidas1.getFecha().compareTo(calendar2)>=0){
                        movimientos.add(new Movimientos(salidas1.getDescripcion(),"Gastos", salidas1.getSaldo()));
                    }
                    saldo-=salidas1.getSaldo();
                    gastos+=salidas1.getSaldo();
                }
                tvGastos.setText(gastos.toString());
                tvSaldo.setText(saldo.toString());
                if(movimientos!=null && movimientos.size()>0){

                    adapter1.setMovimientos(movimientos);
                    adapter1.notifyDataSetChanged();
                }
            }
        });


        viewModel1 = ViewModelProviders.of(this).get(EntradasViewModel.class);
        viewModel1.getAll(calendar3).observe(this, new Observer<List<Entradas>>() {
            @Override
            public void onChanged(@Nullable List<Entradas> entradas) {
                // Update the cached copy of the words in the adapter.
                //  adapter.setCategoria(salidas);
                String fecha = ""+(calendar2.getTimeInMillis()/1000);
                calendar2.setTimeInMillis(NumberUtils.parseHexLong(fecha)*1000);
                if(entradas!=null){
                    movimientos= new ArrayList<>();
                }
                for(Entradas entradas1:entradas){
                    if(entradas1.getFecha().compareTo(calendar2)>=0){
                        movimientos.add(new Movimientos(entradas1.getDescripcion(),"Ingresos", entradas1.getSaldo()));
                    }
                    saldo+=entradas1.getSaldo();
                    ingrsos+= entradas1.getSaldo();
                }
                tvSaldo.setText(saldo.toString());
                tvIngresos.setText(ingrsos.toString());
                if(movimientos!=null && movimientos.size()>0){

                    adapter1.setMovimientos(movimientos);
                    adapter1.notifyDataSetChanged();
                }
            }
        });
        viewModel2 = ViewModelProviders.of(this).get(CuentasViewModel.class);
        viewModel2.getAll().observe(this, new Observer<List<Cuentas>>() {
            @Override
            public void onChanged(@Nullable List<Cuentas> cuentas) {
                // Update the cached copy of the words in the adapter.
                //  adapter.setCategoria(salidas);
                for(Cuentas cuentas1:cuentas){
                    saldo+=cuentas1.getSaldoInicial();
                }
                tvSaldo.setText(saldo.toString());
            }
        });
    }
}
