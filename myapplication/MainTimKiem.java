package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.adapterTintuc;
import com.example.myapplication.database.database;
import com.example.myapplication.model.Tin;

import java.util.ArrayList;

//import android.widget.SearchView;
//import android.widget.Toolbar;
//import android.widget.SearchView;

public class MainTimKiem extends AppCompatActivity {

    RecyclerView listView;
    //Toolbar toolbar;
    //SearchView searchView;
    EditText edt;

    ArrayList<Tin> tinArrayList;
    //
    ArrayList<Tin> arrayList;

    adapterTintuc adaptertintuc;
    database database;

//    ArrayAdapter<Tin> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tim_kiem);

        listView = findViewById(R.id.listviewtimkiem);
        //toolbar = findViewById(R.id.toolbartimkiem);
        edt = findViewById(R.id.timkiem);

        //ActionBar();
        initList();

        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }

        });


    }

    //search
    private void filter(String text){

        //xóa sau mỗi lần gọi tới filter
        arrayList.clear();

        ArrayList<Tin> filteredList = new ArrayList<>();

        for(Tin item : tinArrayList){
            if (item.getTenTin().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

                //Thêm dữ liệu để hiển thị ra item nội dung
                arrayList.add(item);
            }
        }
        adaptertintuc.filterList(filteredList);
    }

    //Hàm  gán dữ liệu từ CSDL vào listview
    public void initList(){
        tinArrayList = new ArrayList<>();
        //
        arrayList = new ArrayList<>();
        database = new database(this);

        Cursor cursor1 = database.getData2();
        while (cursor1.moveToNext()){

            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            tinArrayList.add(new Tin(id,tentruyen,noidung,anh,id_tk));

            //Thêm dữ liệu vào mảng
            arrayList.add(new Tin(id,tentruyen,noidung,anh,id_tk));


        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        listView.setLayoutManager(linearLayoutManager);

        adaptertintuc =new adapterTintuc(getApplicationContext(), tinArrayList);
        listView.setAdapter(adaptertintuc);
        cursor1.moveToFirst();
        cursor1.close();
    }

//    //Tạo thanh action bar với toolbar
//    private void ActionBar() {
//        //Hàm hỗ trợ toolBar
//        setSupportActionBar(toolbar);
//        //set nút của toolbar là true
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//    }
}