package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.database.database;
import com.example.myapplication.model.Tin;
import com.example.myapplication.model.main.MainAdmin;

public class CapNhat extends AppCompatActivity {
    database database;

    // TextView txtNoidung;
    EditText edtTieuDe,edtNoiDung,edtAnh;
    Button btnCapNhat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cap_nhat);
        edtTieuDe = findViewById(R.id.dbtieude);
        edtNoiDung = findViewById(R.id.dbnoidung);
        btnCapNhat = findViewById(R.id.buttonCapNhat);
        edtAnh = findViewById(R.id.dbimg);


        Intent intent = getIntent();
        String tenTin = intent.getStringExtra("tentin");
        String noidung = intent.getStringExtra("noidung");
        String img = intent.getStringExtra("imgtin");
        edtTieuDe.setText(tenTin);
        edtNoiDung.setText(noidung);
        edtAnh.setText(img);

        database = new database(this);

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentruyen = edtTieuDe.getText().toString();
                String noidung = edtNoiDung.getText().toString();
                String img = edtAnh.getText().toString();
                Tin tin = CreatTruyen();

                if(tentruyen.equals("") || noidung.equals("") || img.equals("")){
                    Toast.makeText(CapNhat.this,"Yêu cầu nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else{
                    database.AddTruyen(tin);
                    Intent intent = new Intent(CapNhat.this, MainAdmin.class);
                    finish();
                    startActivity(intent);
                    Toast.makeText(CapNhat.this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                    Log.e("Cập nhật tin : ","Thành công");
                }
                //Cuộn textview
                edtNoiDung.setMovementMethod(new ScrollingMovementMethod());
            }
        });


    }

    private Tin CreatTruyen(){
        String tentin = edtTieuDe.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();

        Intent intent = getIntent();
        int id = intent.getIntExtra("Id",0);
        Tin tin = new Tin(tentin,noidung,img,id);
        return tin;
    }




}