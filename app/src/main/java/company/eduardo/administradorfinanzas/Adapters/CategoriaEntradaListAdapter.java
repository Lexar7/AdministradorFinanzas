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

import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriaEntradas;
import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriasCuentas;
import company.eduardo.administradorfinanzas.R;

public class CategoriaEntradaListAdapter extends RecyclerView.Adapter<CategoriaEntradaListAdapter.ViewHolder>{


    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView categoriaItemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoriaItemView = itemView.findViewById(R.id.textView);
        }
    }

    public final LayoutInflater mInflater;
    public List<CategoriaEntradas> mEntradas = Collections.emptyList();

    public CategoriaEntradaListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoriaEntradas current = mEntradas.get(position);
        holder.categoriaItemView.setText(current.getName());
    }

    public void setCategoria(List<CategoriaEntradas> categorias) {
        mEntradas = categorias;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mEntradas.size();
    }








}
