package com.example.myapplication;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.adapterDangBai;
import com.example.myapplication.database.database;
import com.example.myapplication.model.Tin;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DangBaiFragment extends Fragment {

    RecyclerView listView;
    Button buttonThem;

    ArrayList<Tin> tinArrayList;
    adapterDangBai adaptertintuc;
    database database;
    ArrayList<Tin> tinArraylist;
    public DangBaiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (MainActivity.check_admin == 2){
            init();

        }else{

            Toast.makeText(getContext(),"Bạn không có quyền đăng công thức",Toast.LENGTH_SHORT).show();
            Log.e("Đăng công thức: ","Bạn không có quyền");
        }

    }



    private void init() {

        listView = getActivity().findViewById(R.id.listviewAdmin);
        buttonThem = getActivity().findViewById(R.id.buttonAddTin);
        initList();



        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getActivity().getIntent();
                int id = intent.getIntExtra("Id",0);
                Intent intent1 = new Intent(getContext(), DangBai.class);
                intent.putExtra("Id",id);
                startActivity(intent1);
            }
        });


    }




    //Gán DL vào listview
    public void initList(){
        tinArrayList = new ArrayList<>();
        database = new database(getContext());

        Cursor cursor1 = database.getData2();

        while (cursor1.moveToNext()){

            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);
            tinArrayList.add(new Tin(id,tentruyen,noidung,anh,id_tk));


        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        listView.setLayoutManager(linearLayoutManager);
        adaptertintuc = new adapterDangBai(getContext(), tinArrayList);
        listView.setAdapter(adaptertintuc);
        cursor1.moveToFirst();
        cursor1.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dangbai, container, false);
    }

}
