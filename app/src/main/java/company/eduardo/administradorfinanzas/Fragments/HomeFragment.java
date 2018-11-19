package company.eduardo.administradorfinanzas.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import company.eduardo.administradorfinanzas.Nuevo;
import company.eduardo.administradorfinanzas.R;

public class HomeFragment extends Fragment {

    FloatingActionButton fab;
    String currentDate;
    TextView tvDate;
    Calendar calendar;
    DatePickerDialog dpd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        calendar = Calendar.getInstance();
        currentDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());

        tvDate = (TextView)view.findViewById(R.id.txtDate);
        fab = (FloatingActionButton)view.findViewById(R.id.fab_btn);

        tvDate.setText(currentDate);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                    }
                }, year, month, day);
                dpd.show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Nuevo.class));
            }
        });

        return view;
    }
}