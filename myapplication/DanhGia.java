package com.example.myapplication;

import static com.example.myapplication.MainActivity.tentaikhoan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.database.database;


public class DanhGia extends AppCompatActivity {

    EditText edtNoiDungDanhGia;
    Button btnDanhGia;
    Button btnXemDanhGia;
    EditText edttentk;
    database database;
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danh_gia);

        edttentk = findViewById(R.id.edttentk);
        edtNoiDungDanhGia = findViewById(R.id.dbnoidungdanhgia);
        btnDanhGia = findViewById(R.id.dbdanhgia);
        btnXemDanhGia=findViewById(R.id.dbxemdanhgia);

        database = new database(this);

        btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noidungdanhgia = edtNoiDungDanhGia.getText().toString();
                //String tentaikhoan = edttentk.getText().toString();
                edttentk.setText(tentaikhoan);
                com.example.myapplication.model.DanhGia danhGia = CreatDanhGia();
                danhGia.setTenTin(getIntent().getStringExtra("tentin"));
                if(noidungdanhgia.equals("") ){
                    Toast.makeText(DanhGia.this,"Yêu cầu nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else{
                    database.AddDanhGia(danhGia);


                  //  String noidungdanhgia = edtNoiDungDanhGia.getText().toString();
                    Intent intent_danh_gia = new Intent(DanhGia.this,MainXemDanhGia.class);
                    finish();
                    startActivity(intent_danh_gia);
                    Toast.makeText(DanhGia.this,"Thêm đánh giá thành công",Toast.LENGTH_SHORT).show();
                    Log.e("Thêm đánh giá : ","Thành công");
                }
            }
        });

        btnXemDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_danh_gia = new Intent(DanhGia.this,MainXemDanhGia.class);
                startActivity(intent_danh_gia);
                intent_danh_gia.putExtra("dulieu",0);
                startActivity(intent_danh_gia);
            }
        });

    }


    private com.example.myapplication.model.DanhGia CreatDanhGia(){
        String noiDungDanhGia = edtNoiDungDanhGia.getText().toString();
        edttentk.setText(tentaikhoan);

        Intent intent = getIntent();
        int id = intent.getIntExtra("Id",0);
        com.example.myapplication.model.DanhGia danhGia = new com.example.myapplication.model.DanhGia(tentaikhoan,noiDungDanhGia);
        return danhGia;

    }
}