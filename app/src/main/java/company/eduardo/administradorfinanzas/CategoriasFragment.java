package company.eduardo.administradorfinanzas;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CategoriasFragment extends Fragment {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_categorias, container, false);
        //((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_id);
        //appBarLayout = (AppBarLayout) view.findViewById(R.id.i);
        viewPager = (ViewPager) view.findViewById(R.id.viewpaper_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        //Adding Fragments
        adapter.AddFragment(new CategoriaEntradaFragment(), "Entradas");
        adapter.AddFragment(new CategoriaSalidasFragment(), "Salidas");
        adapter.AddFragment(new CategoriaCuentasFragment(), "Cuentas");
        //Adapter Set
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
