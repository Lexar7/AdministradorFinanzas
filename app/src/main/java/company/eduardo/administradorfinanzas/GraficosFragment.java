package company.eduardo.administradorfinanzas;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraficosFragment extends Fragment {


    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graficos, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_id);
        //appBarLayout = (AppBarLayout) view.findViewById(R.id.i);
        viewPager = (ViewPager) view.findViewById(R.id.viewpaper_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        //Adding Fragments
        adapter.AddFragment(new GraficosEntradasFragment(), "Entradas");
        adapter.AddFragment(new GraficosSalidasFragment(), "Salidas");
        adapter.AddFragment(new GraficosCuentasFragment(), "Cuentas");
        //Adapter Set
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



        return view;
    }
}
