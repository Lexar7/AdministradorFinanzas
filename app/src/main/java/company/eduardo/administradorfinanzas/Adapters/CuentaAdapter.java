package company.eduardo.administradorfinanzas.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.Cuentas;
import company.eduardo.administradorfinanzas.R;

public class CuentaAdapter extends RecyclerView.Adapter<CuentaAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView cuentaItemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cuentaItemView = itemView.findViewById(R.id.textView);
        }
    }

    public final LayoutInflater mInflater;
    public List<Cuentas> mCuentas = Collections.emptyList();

    public CuentaAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CuentaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new CuentaAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CuentaAdapter.ViewHolder holder, int position) {
        Cuentas current = mCuentas.get(position);
        holder.cuentaItemView.setText(current.getNombreCuenta());
    }

    public void setCategoria(List<Cuentas> categorias) {
        mCuentas = categorias;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCuentas.size();
    }






}
