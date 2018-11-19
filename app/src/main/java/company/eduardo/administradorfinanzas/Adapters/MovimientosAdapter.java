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

import company.eduardo.administradorfinanzas.Models.Movimientos;
import company.eduardo.administradorfinanzas.R;

public class MovimientosAdapter extends RecyclerView.Adapter<MovimientosAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView movimientosItem, tiposItem, cantidadItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movimientosItem = itemView.findViewById(R.id.textView);
            tiposItem = itemView.findViewById(R.id.txtTipo);
            cantidadItem= itemView.findViewById(R.id.txtCantidad);
        }
    }

    public final LayoutInflater mInflater;
    public List<Movimientos> mMovimientos = Collections.emptyList();

    public MovimientosAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MovimientosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item2, parent, false);
        return new MovimientosAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MovimientosAdapter.ViewHolder holder, int position) {
        Movimientos current = mMovimientos.get(position);
        holder.movimientosItem.setText(current.getNombre());
        holder.cantidadItem.setText(current.getCantidad().toString());
        holder.tiposItem.setText(current.getTipo());

    }

    public void setMovimientos(List<Movimientos> movimientos) {
        mMovimientos=movimientos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mMovimientos.size();
    }


}

