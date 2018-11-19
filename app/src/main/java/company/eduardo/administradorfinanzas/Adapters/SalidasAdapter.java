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

import company.eduardo.administradorfinanzas.DataContext.Entities.Entradas;
import company.eduardo.administradorfinanzas.DataContext.Entities.Salidas;
import company.eduardo.administradorfinanzas.R;

public class SalidasAdapter extends RecyclerView.Adapter<SalidasAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView salidasItemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            salidasItemView = itemView.findViewById(R.id.textView);
        }
    }

    public final LayoutInflater mInflater;
    public List<Salidas> mSalidas = Collections.emptyList();

    public SalidasAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SalidasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new SalidasAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull SalidasAdapter.ViewHolder holder, int position) {
        Salidas current = mSalidas.get(position);
        holder.salidasItemView.setText(current.getDescripcion());
    }

    public void setCategoria(List<Salidas> categorias) {
        mSalidas = categorias;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mSalidas.size();
    }


}

