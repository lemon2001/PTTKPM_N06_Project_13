package com.example.myapplication.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.CapNhat;
import com.example.myapplication.R;
import com.example.myapplication.database.database;
import com.example.myapplication.model.Tin;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterDangBai extends RecyclerView.Adapter<adapterDangBai.ViewHolder> {

    private Context context;
    private ArrayList<Tin> listTin;

    public adapterDangBai(Context context, ArrayList<Tin> listTin) {
        this.context = context;
        this.listTin = listTin;
    }

    public void filterList(ArrayList<Tin> filteredList) {
        listTin =filteredList;
        notifyDataSetChanged();
    }


    public void setdata(Context context,ArrayList<Tin> listTin){
        this.context = context;
        this.listTin = listTin;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public adapterDangBai.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newtintuc, parent, false);
        adapterDangBai.ViewHolder viewHolder = new adapterDangBai.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapterDangBai.ViewHolder holder,  int position) {
        position = holder.getAdapterPosition();
        Tin tin =(Tin) listTin.get(position);
        holder.txtTenTintuc.setText(tin.getTenTin());

        Picasso.get().load(tin.getAnh()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(holder.imgtruyen);

        int finalPosition = position;
        holder.imgtruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDelete(finalPosition);
            }
        });

        int finalPosition1 = position;
        holder.txtTenTintuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDelete(finalPosition1);
            }
        });
    }


    //Dialog Delete
    private void DialogDelete(int position) {
        Tin tin =(Tin) listTin.get(position);
        database database;
        database = new database(context);

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog  =  new Dialog(context);

        //Nạp layout vào
        dialog.setContentView(R.layout.dialogdelete);
        /*Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);*/

        //Ánh xạ
        Button btnDelete = dialog.findViewById(R.id.buttonDelete);
        Button btnUpdate = dialog.findViewById(R.id.buttonUpdate);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idtintuc = listTin.get(position).getID();
                //Xóa trong SQL
                database.Delete(idtintuc);
                listTin.remove(position);
                //Cập nhật lại listview
                setdata(context, listTin);
                //finish();
               // startActivity(intent);
                Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent=new Intent(context, CapNhat.class);
                String tent= tin.getTenTin();
                String noidungt= tin.getNoiDung();
                String imgtruyen = tin.getAnh();
                intent.putExtra("tentin",tent);
                intent.putExtra("noidung",noidungt);
                intent.putExtra("imgtin",imgtruyen);
                int idtruyen = listTin.get(position).getID();
                //Xóa trong SQL
                database.Delete(idtruyen);
                listTin.remove(position);
                //Cập nhật lại listview
                setdata(context, listTin);

                context.startActivity(intent);


            }
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return listTin.size();
    }


    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenTintuc;
        ImageView imgtruyen;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTenTintuc =  itemView.findViewById(R.id.textviewTenTin);
            //viewHolder.txtTenTruyen=convertView.findViewById(R.id.textviewTenTruyen);
            imgtruyen=itemView.findViewById(R.id.imgNewTin);
        }
    }
}
