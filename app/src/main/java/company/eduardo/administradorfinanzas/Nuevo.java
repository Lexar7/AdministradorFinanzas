package company.eduardo.administradorfinanzas;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;

import company.eduardo.administradorfinanzas.Adapters.ViewPagerAdapter;
import company.eduardo.administradorfinanzas.Fragments.NuevoCuentasFragment;
import company.eduardo.administradorfinanzas.Fragments.NuevoEntradaFragment;
import company.eduardo.administradorfinanzas.Fragments.NuevoSalidasFragment;

public class Nuevo extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);



        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>"+"Nuevo"+"</font>"));
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tabLayout = (TabLayout)findViewById(R.id.tabLayout_id);
        //appBarLayout = (AppBarLayout)findViewById(R.id.appBarId);
        viewPager = (ViewPager)findViewById(R.id.viewpaper_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //Adding Fragments
        adapter.AddFragment(new NuevoEntradaFragment(), "Entradas");
        adapter.AddFragment(new NuevoSalidasFragment(), "Salidas");
        adapter.AddFragment(new NuevoCuentasFragment(), "Cuentas");
        //Adapter Set
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
