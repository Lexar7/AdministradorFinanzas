package company.eduardo.administradorfinanzas;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import company.eduardo.administradorfinanzas.DataContext.Entities.CategoriasCuentas;

public class AdapterCat extends BaseAdapter {
    protected Activity activity;
    protected List<CategoriasCuentas> items;
     public AdapterCat (Activity activity, List<CategoriasCuentas> items){
         this.activity=activity;
         this.items=items;

     }

     @Override
    public int getCount(){
         return items.size();
     }
     public void clear(){
         items.clear();
     }
     public void addAll(ArrayList<CategoriasCuentas> category){
         for (int i =0; i<category.size(); i++){
             items.add(category.get(i));
         }


     }
    @Override
    public Object getItem(int id){
        return items.get(id);
    }
    @Override
    public long getItemId(int id){
        return (id);
    }
    @Override
    public View getView(int id, View view, ViewGroup parent){
        View v = view;
        if(view== null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inf.inflate(R.layout.listcategoria,null);


        }
        CategoriasCuentas dir= items.get(id);
        TextView texto= v.findViewById(R.id.txtCate);
        texto.setText(dir.getName());
        return v;
    }

}
