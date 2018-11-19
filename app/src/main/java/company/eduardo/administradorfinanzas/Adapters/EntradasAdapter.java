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
import company.eduardo.administradorfinanzas.DataContext.Entities.Entradas;
import company.eduardo.administradorfinanzas.R;

public class EntradasAdapter extends RecyclerView.Adapter<EntradasAdapter.ViewHolder> {

    public ActionCallback mActionCallbacks;

    //Interface for callbacks
    public interface ActionCallback {
        void onLongClickListener(Entradas entradas);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        public final TextView entradasItemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnLongClickListener(this);
            entradasItemView = itemView.findViewById(R.id.textView);
        }

        @Override
        public boolean onLongClick(View view) {
            if (mActionCallbacks != null) {
                mActionCallbacks.onLongClickListener(mEntradas.get(getAdapterPosition()));
            }
            return true;
        }
    }

    public void addActionCallback(ActionCallback actionCallbacks) {
        mActionCallbacks = actionCallbacks;
    }

    public final LayoutInflater mInflater;
    public List<Entradas> mEntradas = Collections.emptyList();

    public EntradasAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EntradasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new EntradasAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull EntradasAdapter.ViewHolder holder, int position) {
        Entradas current = mEntradas.get(position);
        holder.entradasItemView.setText(current.getDescripcion());
    }

    public void setCategoria(List<Entradas> categorias) {
        mEntradas = categorias;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mEntradas.size();
    }


}
