package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.NoiDungTintuc;
import com.example.myapplication.R;
import com.example.myapplication.model.Tin;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterTintucV2 extends RecyclerView.Adapter<adapterTintucV2.ViewHolder>{

    private Context context;
    private ArrayList<Tin> listTin;

    public adapterTintucV2(Context context, ArrayList<Tin> listTin) {
        this.context = context;
        this.listTin = listTin;
    }

    public void filterList(ArrayList<Tin> filteredList) {
        listTin =filteredList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tintucv2, parent, false);
        adapterTintucV2.ViewHolder viewHolder = new adapterTintucV2.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tin tin =(Tin) listTin.get(position);
        holder.txtTenTin.setText(tin.getTenTin());

        Picasso.get().load(tin.getAnh()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(holder.imgtin);

        holder.imgtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NoiDungTintuc.class);
                String tent= tin.getTenTin();
                String noidungt= tin.getNoiDung();
                intent.putExtra("tentin",tent);
                intent.putExtra("noidung",noidungt);
                context.startActivity(intent);
            }
        });

        holder.txtTenTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NoiDungTintuc.class);
                String tent= tin.getTenTin();
                String noidungt= tin.getNoiDung();
                intent.putExtra("tentin",tent);
                intent.putExtra("noidung",noidungt);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTin.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenTin;
        ImageView imgtin;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTenTin =  itemView.findViewById(R.id.textviewTenTintucv2);
            //viewHolder.txtTenTruyen=convertView.findViewById(R.id.textviewTenTin);
            imgtin =itemView.findViewById(R.id.imgNewTintucv2);
        }
    }
}
