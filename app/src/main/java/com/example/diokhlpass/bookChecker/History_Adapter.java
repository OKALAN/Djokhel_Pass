package com.example.diokhlpass.bookChecker;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.example.diokhlpass.R;
import com.example.diokhlpass.entities.bookticket;

import java.util.ArrayList;
import java.util.List;

public class History_Adapter extends BaseAdapter {
    private List<bookticket> dtList = new ArrayList<bookticket>();
    private Activity context ;
    private LayoutInflater inflater;

    public History_Adapter(Activity context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public History_Adapter(List<bookticket> dtList, Activity context) {
        this.dtList = dtList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    private class ViewHolder{
        TextView dsp,arr,date,price;
        ImageView delete_icon;

    }
    public List<bookticket> getData() {
        return dtList;
    }

    public void set(List<bookticket> list) {
        dtList = list;
        notifyDataSetChanged();
    }
    public void remove(bookticket data) {
        dtList.remove(data);
        notifyDataSetChanged();
    }
    public void removeAll() {
        dtList = new ArrayList<bookticket>();
        notifyDataSetChanged();
    }

    public void removeByID(long id) {
        for (int i = 0; i < dtList.size(); i++) {
            if(dtList.get(i).getId() == id)
                dtList.remove(i);
        };
        notifyDataSetChanged();
    }

    public void addMany(List<bookticket>  dt) {
        for (int i = 0; i < dt.size(); i++)
        {
            dtList.add(dt.get(i));
        }

        notifyDataSetChanged();
    }

    public void add(bookticket data) {
        dtList.add(data);
        notifyDataSetChanged();
    }

    public void insert(bookticket data,int index) {
        dtList.add(index, data);
        notifyDataSetChanged();

        }

    @Override
    public int getCount() {
        return dtList.size();
    }

    @Override
    public Object getItem(int position) {
        return dtList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;
        if (convertView == null) {
            vi = inflater.inflate(R.layout.history_item, null);
            holder = new ViewHolder();

            holder.dsp= (TextView) vi.findViewById(R.id.point_history);
            holder.arr = (TextView) vi.findViewById(R.id.destination_history);
            holder.date = (TextView) vi.findViewById(R.id.date_history);
            holder.price= (TextView) vi.findViewById(R.id.price_history);
            holder.delete_icon = (ImageView) vi.findViewById(R.id.history_item_remove);


            vi.setTag(holder);
        } else {
            holder=(ViewHolder)vi.getTag();
        }

        final bookticket data = (bookticket) getItem(position);
        holder.dsp.setText(data.departure_point);
        holder.arr.setText(data.destination);
        holder.date.setText(data.date);
        holder.price.setText(new StringBuilder().append(data.price).append("F CFA"));
        holder.delete_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.setTitle(" Warning");
                    alertDialog.setMessage("Voulez-vous supprimer ce ticket");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                   data.delete();
                                   dtList.remove(position);
                                   notifyDataSetChanged();
                                    Toast.makeText(context,"suppression réussie", Toast.LENGTH_SHORT).show();
                                }
                            });
                    alertDialog.show();


            }
        });
        return vi;
    }




}
