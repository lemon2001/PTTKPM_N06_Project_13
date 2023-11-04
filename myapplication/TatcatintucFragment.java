package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.adapterTintuc;
import com.example.myapplication.database.database;
import com.example.myapplication.model.Tin;

import java.util.ArrayList;

public class TatcatintucFragment extends Fragment {
    RecyclerView listView;


    ArrayList<Tin> tinArrayList;
    //
    ArrayList<Tin> arrayList;

    adapterTintuc adaptertintuc;
    database database;

    public TatcatintucFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = getView().findViewById(R.id.listviewtatcatintuc);

        //ActionBar();
        initList();


    }


    private void filter(String text){


        arrayList.clear();

        ArrayList<Tin> filteredList = new ArrayList<>();

        for(Tin item : tinArrayList){
            if (item.getTenTin().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

                arrayList.add(item);
            }
        }
        adaptertintuc.filterList(filteredList);
    }

    public void initList(){
        tinArrayList = new ArrayList<>();
        //
        arrayList = new ArrayList<>();
        database = new database(getContext());

        Cursor cursor1 = database.getData2();
        while (cursor1.moveToNext()){

            int id = cursor1.getInt(0);
            String tentin = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            tinArrayList.add(new Tin(id,tentin,noidung,anh,id_tk));

            arrayList.add(new Tin(id,tentin,noidung,anh,id_tk));

           
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        listView.setLayoutManager(linearLayoutManager);

        adaptertintuc =new adapterTintuc(getContext(),arrayList);
        listView.setAdapter(adaptertintuc);

        cursor1.moveToFirst();
        cursor1.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tatcatintuc, container, false);
    }

}
