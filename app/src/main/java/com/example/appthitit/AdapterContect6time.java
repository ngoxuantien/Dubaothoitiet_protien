package com.example.appthitit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterContect6time extends RecyclerView.Adapter<AdapterContect6time.Viewhoder6time> {
    private List<Contect> Contacts;
    private IconClick6time iconClick6time;

    public AdapterContect6time(List<Contect> Contacts) {
        this.Contacts = Contacts;
    }

    public void setIconClick6time(IconClick6time iconClick6time) {
        this.iconClick6time = iconClick6time;
    }

    @NonNull
    @Override
    public AdapterContect6time.Viewhoder6time onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.icon_recyclerview, parent, false);
        Viewhoder6time viewhoder6time = new Viewhoder6time(view);
        return viewhoder6time;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterContect6time.Viewhoder6time holder, int position) {
        Contect contect=Contacts.get(position);
        holder.doam.setText(contect.getDoam()+"%");
        holder.wheather.setImageResource(contect.getIcon());
        holder.nhietdo.setText(contect.getNhietdo()+"");
        holder.time.setText(contect.getTime()+"");
    }

    @Override
    public int getItemCount() {
        return  Contacts.size();
    }

    public class Viewhoder6time extends RecyclerView.ViewHolder {
        TextView time,nhietdo,doam;
        ImageView wheather;
        public Viewhoder6time(@NonNull View itemView) {
            super(itemView);
            doam=itemView.findViewById(R.id.huminity6time);
            time=itemView.findViewById(R.id.time6day);
            nhietdo=itemView.findViewById(R.id.temp6time);
            wheather=itemView.findViewById(R.id.weather6time);
        }
    }
}
