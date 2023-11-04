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

public class adapterTintuc extends RecyclerView.Adapter<adapterTintuc.ViewHolder> {

    private Context context;
    private ArrayList<Tin> listTin;

    public adapterTintuc(Context context, ArrayList<Tin> listTin) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newtintuc, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tin tin =(Tin) listTin.get(position);
        holder.txtTenTintuc.setText(tin.getTenTin());

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

        holder.txtTenTintuc.setOnClickListener(new View.OnClickListener() {
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
        TextView txtTenTintuc;
        ImageView imgtin;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTenTintuc =  itemView.findViewById(R.id.textviewTenTin);
            //viewHolder.txtTenTruyen=convertView.findViewById(R.id.textviewTenTruyen);
            imgtin =itemView.findViewById(R.id.imgNewTin);
        }
    }
}
