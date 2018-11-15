package company.eduardo.administradorfinanzas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriasCuentas;

public class CategoriaCuentaListAdapter extends RecyclerView.Adapter<CategoriaCuentaListAdapter.ViewHolder>{


    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView categoriaItemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoriaItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<CategoriasCuentas> mCuentas = Collections.emptyList();

    CategoriaCuentaListAdapter(Context context){
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
        CategoriasCuentas current = mCuentas.get(position);
        holder.categoriaItemView.setText(current.getName());
    }

    void setCategoria(List<CategoriasCuentas> categorias) {
        mCuentas = categorias;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCuentas.size();
    }








}
