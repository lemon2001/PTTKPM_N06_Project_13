package com.example.myapplication.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.database;
import com.example.myapplication.model.Tin;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterTintucYeuThich extends RecyclerView.Adapter<adapterTintucYeuThich.ViewHolder> {

    private Context context;
    private ArrayList<Tin> listTin;

    public adapterTintucYeuThich(Context context, ArrayList<Tin> listTin) {
        this.context = context;
        this.listTin = listTin;
        notifyDataSetChanged();
    }

    public void setdata(Context context,ArrayList<Tin> listTin){
        this.context = context;
        this.listTin = listTin;
        notifyDataSetChanged();

    }

    public void filterList(ArrayList<Tin> filteredList) {
        listTin =filteredList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public adapterTintucYeuThich.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tintucv2, parent, false);
        adapterTintucYeuThich.ViewHolder viewHolder = new adapterTintucYeuThich.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapterTintucYeuThich.ViewHolder holder, int position) {
        Tin tin =(Tin) listTin.get(position);
        holder.txtTenTin.setText(tin.getTenTin());

        Picasso.get().load(tin.getAnh()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(holder.imgtin);

        holder.imgtin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogDeleteYeuThic(position);
                return false;
            }
        });

       holder.txtTenTin.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               DialogDeleteYeuThic(position);
               return false;
           }
       });
    }
    private void DialogDeleteYeuThic(int position) {
        database database;
        database=new database(context);

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog  =  new Dialog(context);

        //Nạp layout vào
        dialog.setContentView(R.layout.dialogdeleteyeuthic);
        //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idtintuc = listTin.get(position).getID();
                //Xóa trong SQL
                database.Delete(idtintuc);
                //Cập nhật lại listview
                listTin.remove(position);
               setdata(context, listTin);
                Toast.makeText(context,"Bỏ yêu thích thành công",Toast.LENGTH_SHORT).show();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
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
            //viewHolder.txtTenTruyen=convertView.findViewById(R.id.textviewTen);
            imgtin =itemView.findViewById(R.id.imgNewTintucv2);
        }
    }
}
