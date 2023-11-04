package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.database.database;
import com.example.myapplication.model.Tin;
import com.example.myapplication.model.main.MainAdmin;


public class DangBai extends AppCompatActivity {

    EditText edtTieuDe,edtNoiDung,edtAnh;
    Button btnDangBai;
    database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_bai);
        edtTieuDe = findViewById(R.id.dbtieude);
        edtNoiDung = findViewById(R.id.dbnoidung);
        btnDangBai = findViewById(R.id.dbdangbai);
        edtAnh = findViewById(R.id.dbimg);

        database = new database(this);

        btnDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentin = edtTieuDe.getText().toString();
                String noidung = edtNoiDung.getText().toString();
                String img = edtAnh.getText().toString();

                Tin tin = CreatTruyen();

                if(tentin.equals("") || noidung.equals("") || img.equals("")){
                    Toast.makeText(DangBai.this,"Yêu cầu nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else{
                    database.AddTruyen(tin);
                    Intent intent = new Intent(DangBai.this, MainAdmin.class);
                    finish();
                    startActivity(intent);
                    Toast.makeText(DangBai.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                    Log.e("Thêm công thức : ","Thành công");
                }
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