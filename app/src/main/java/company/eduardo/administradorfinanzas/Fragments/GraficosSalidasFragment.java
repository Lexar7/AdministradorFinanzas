package company.eduardo.administradorfinanzas.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import company.eduardo.administradorfinanzas.R;

public class GraficosSalidasFragment extends Fragment {

    View view;

    private BarChart barChart;

    public GraficosSalidasFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_graficos_salidas, container, false);

        barChart = (BarChart)view.findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);

        setData(10);
        barChart.setFitBars(true);

        return view;
    }

    private void setData(int count){
        ArrayList<BarEntry> yVals = new ArrayList<>();

        for (int i = 0; i < count; i++){
            float value = (float)(Math.random()*100);
            yVals.add(new BarEntry(i, (int) value));
        }

        BarDataSet set = new BarDataSet(yVals, "Gastos");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setDrawValues(true);

        BarData data = new BarData(set);

        barChart.setData(data);
        barChart.invalidate();
        barChart.animateY(500);
    }
}
