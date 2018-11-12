package company.eduardo.administradorfinanzas;

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

public class GraficosCuentasFragment extends Fragment {

    View view;

    private BarChart nChart;

    public GraficosCuentasFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_graficos_cuentas, container, false);

        nChart = (BarChart)view.findViewById(R.id.Chart1);

        nChart.setDrawBarShadow(false);
        nChart.setDrawValueAboveBar(false);
        nChart.getDescription().setEnabled(false);

        nChart.setDrawGridBackground(false);

        setData(360);

        return view;
    }

    private void setData(int count){
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i<count; i++){
            float radians = (float)Math.toRadians(i);
            float val = (float)Math.sin(radians);
            entries.add(new BarEntry(i, val));
        }

        BarDataSet set;

        set = new BarDataSet(entries, "Ganancias y Perdidas");
        set.setColor(Color.MAGENTA);

        BarData data = new BarData(set);
        data.setDrawValues(false);
        data.setBarWidth(1);

        nChart.setData(data);
    }
}
