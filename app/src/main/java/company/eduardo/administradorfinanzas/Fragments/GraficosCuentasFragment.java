package company.eduardo.administradorfinanzas.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.Cuentas;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.CuentasViewModel;
import company.eduardo.administradorfinanzas.DataContext.ViewModel.EntradasViewModel;
import company.eduardo.administradorfinanzas.Models.InformacionGrafico;
import company.eduardo.administradorfinanzas.R;

public class GraficosCuentasFragment extends Fragment {

    View view;

    private BarChart nChart;
    PieChart pieChart;
    ArrayList<PieEntry> yValues;
    private CuentasViewModel viewModel;
    Spinner spinner;
    private CuentasViewModel viewModel1;
    List<Cuentas> cuentas1;

    public GraficosCuentasFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_graficos_cuentas, container, false);


        spinner = view.findViewById(R.id.spinnerCC);
        viewModel1 = ViewModelProviders.of( this).get(CuentasViewModel.class);

        pieChart = (PieChart) view.findViewById(R.id.pieChartC);
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


        return view;
    }

    private void setData(int count){


        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(true);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.99f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        int f = cuentas1.get(count).getIdCuenta();
        viewModel = ViewModelProviders.of(this).get(CuentasViewModel.class);
        viewModel.getGraphic(f).observe(this, new Observer<List<InformacionGrafico>>() {
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
                description.setText("Comparativo de Gastos");
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
