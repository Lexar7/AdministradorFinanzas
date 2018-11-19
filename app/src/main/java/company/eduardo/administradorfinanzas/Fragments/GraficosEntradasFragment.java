package company.eduardo.administradorfinanzas.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.media.audiofx.AudioEffect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.Cuentas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Entradas;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CuentasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.EntradasViewModel;
import company.eduardo.administradorfinanzas.Models.InformacionGrafico;
import company.eduardo.administradorfinanzas.R;

public class GraficosEntradasFragment extends Fragment {

    View view;
    PieChart pieChart;
    ArrayList<PieEntry> yValues;
    private EntradasViewModel viewModelEntradas;
    Spinner spinner;
    private CuentasViewModel viewModel1;

    List<Cuentas> cuentas1;
    public GraficosEntradasFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_graficos_entradas, container, false);

        pieChart = (PieChart) view.findViewById(R.id.pieChart);

        spinner = view.findViewById(R.id.spinnerCE);
        viewModel1 = ViewModelProviders.of( this).get(CuentasViewModel.class);

        viewModel1.getAll().observe(this, new Observer<List<Cuentas>>() {

            @Override

            public void onChanged(@Nullable List<Cuentas> Cuentas) {

                List<String> cuentas = new ArrayList<>();

                for (Cuentas cuentas1: Cuentas){

                    cuentas.add(cuentas1.getNombreCuenta() );

                }

                if(Cuentas!= null){
                    cuentas1 = Cuentas;
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, cuentas);

                //ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_item, categoriasCuentas.stream().map(c -> c.getName()).toArray());
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(adapter);

            }

        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setData(spinner.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

       /* viewModelEntradas.getGraphic(f).observe(this, new Observer<List<InformacionGrafico>>() {
            @Override
            public void onChanged(@Nullable List<IndexOutOfBoundsException> listaEntradas) {


                List<String> cuentas = new ArrayList<>();
                if (listaEntradas == null) throw new AssertionError();
                for (InformacionGrafico cuentas1 : listaEntradas) {
                    cuentas.add(new PieEntry(cuentas1.getSaldo().floatValue(),cuentas1.getDescripcion()));

                }

       /* ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(34f, "Italy"));
        yValues.add(new PieEntry(23f, "USA"));
        yValues.add(new PieEntry(14f, "Germany"));
        yValues.add(new PieEntry(35, "Japan"));
        yValues.add(new PieEntry(40, "El Salvador"));
        yValues.add(new PieEntry(23, "Canada"));*/



        return view;
    }

    public void setData(int cData){
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(true);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.99f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        int f = cuentas1.get(cData).getIdCuenta();
        viewModelEntradas = ViewModelProviders.of(this).get(EntradasViewModel.class);
        viewModelEntradas.getGraphic(f).observe(this, new Observer<List<InformacionGrafico>>() {
            @Override
            public void onChanged(@Nullable List<InformacionGrafico> informacionGraficos) {
                if(informacionGraficos!=null && !(yValues!=null)){
                    yValues = new ArrayList<>();
                }else if(informacionGraficos!=null && yValues.size()>0){
                    yValues.clear();
                }
                for (InformacionGrafico informacionGrafico:informacionGraficos){
                    yValues.add(new PieEntry(informacionGrafico.getCantidad().floatValue(), informacionGrafico.getDescripcion()));
                }

                Description description = new Description();
                description.setText("Entradas");
                description.setTextSize(30);
                pieChart.setDescription(description);

                // animar grafico
                pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);


                PieDataSet dataSet = new PieDataSet(yValues, "Categorias");
                dataSet.setSliceSpace(3f);
                dataSet.setSelectionShift(5f);
                dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

                PieData data = new PieData((dataSet));
                data.setValueTextSize(10f);
                data.setValueTextColor(Color.YELLOW);

                pieChart.setData(data);
            }
        });
    }
}
