package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NoiDungTintuc extends AppCompatActivity {

    TextView txtTenTin,txtNoidung;
    Button btnDanhGia, btnYeuThich, btnChiaSe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_dung_tintuc);

        AnhXa();


        txtNoidung = findViewById(R.id.NoiDung);
        txtTenTin = findViewById(R.id.TenTintuc);

        btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoiDungTintuc.this, DanhGia.class);
                intent.putExtra("tentin", txtTenTin.getText().toString());
                startActivity(intent);
            }
        });

//        btnChiaSe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(NoiDungTintuc.this, ChiaSe.class);
//                startActivity(intent);
//            }
//        });

        btnYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NoiDungTintuc.this,"Đã thêm vào danh sách yêu thích",Toast.LENGTH_SHORT).show();
                Log.e("Yêu thích: ","Đã thêm vào danh sách yêu thích");
            }
        });




        Intent intent = getIntent();
        String tenTin = intent.getStringExtra("tentruyen");
        String noidung = intent.getStringExtra("noidung");

        txtTenTin.setText(tenTin);
        txtNoidung.setText(noidung);

        //Cuộn textview
        txtNoidung.setMovementMethod(new ScrollingMovementMethod());

    }

    private void AnhXa() {
        txtTenTin = findViewById(R.id.TenTintuc);
        txtNoidung = findViewById(R.id.NoiDung);
        btnDanhGia = findViewById(R.id.buttonDanhgia);
        btnYeuThich = findViewById(R.id.buttonYeuthich);
//        btnChiaSe = findViewById(R.id.buttonChiase);
    }
}