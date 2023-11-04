package com.example.myapplication;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.database.database;
import com.example.myapplication.model.TaiKhoan;
import com.example.myapplication.model.Tin;
import com.example.myapplication.model.chuyenmuc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import  com.example.myapplication.adapter.*;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    FloatingActionButton fab;
    RecyclerView listViewNew2,listViewNew;


    String email;
    String tentaikhoan;

    ArrayList<Tin> tinArraylist;

    ArrayList<Tin> tinArraylist2;

    adapterTintucV2 adapterTruyen;

    ArrayList<chuyenmuc> chuyenmucArrayList;

    ArrayList<TaiKhoan> taiKhoanArrayList;

    database database;

    adapterchuyenmuc adapterchuyenmuc;
    adapterthongtin adapterthongtin;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        database =new database(getContext());
        fab = getActivity().findViewById(R.id.fab_btn);
        Intent intentpq = getActivity().getIntent();
        int i= intentpq.getIntExtra("phanq",0);
        int idd=intentpq.getIntExtra("idd",0);
        email=intentpq.getStringExtra("email");
        tentaikhoan = intentpq.getStringExtra("tentaikhoan");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MainTimKiem.class);
                startActivity(intent);
            }
        });

        AnhXa();
        ActionBar();
        ActionViewFlipper();
    }

    private void ActionBar() {
       // setSupportActionBar(toolbar);

      //  getContext().setDisplayHomeAsUpEnabled(true);

//        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

      //  toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        //    @Override
          //  public void onClick(View v) {
            //    drawerLayout.openDrawer(GravityCompat.START);
            //}
        //});


    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://www.google.com.vn/imgres?imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fthumb%2F5%2F59%2FThanh_Ni%25C3%25AAn_logo.svg%2F2560px-Thanh_Ni%25C3%25AAn_logo.svg.png&tbnid=PLBCC9CaRtXp9M&vet=12ahUKEwjiy_CS4LOAAxW6ulYBHZT3DtYQMygAegUIARCYAQ..i&imgrefurl=https%3A%2F%2Fcommons.wikimedia.org%2Fwiki%2FFile%3AThanh_Ni%25C3%25AAn_logo.svg&docid=kJMqMeSY1LWLjM&w=2560&h=731&q=logo%20b%C3%A1o%20thanh%20ni%C3%AAn&hl=vi&authuser=0&ved=2ahUKEwjiy_CS4LOAAxW6ulYBHZT3DtYQMygAegUIARCYAQ");
        mangquangcao.add("https://www.google.com.vn/imgres?imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Ff%2Ffd%2FVnExpress_logo.png&tbnid=F67IZd91aiVrmM&vet=12ahUKEwin8eGi4LOAAxV9qFYBHfFRDxoQMygBegUIARDJAQ..i&imgrefurl=https%3A%2F%2Fvi.wikipedia.org%2Fwiki%2FT%25E1%25BA%25ADp_tin%3AVnExpress_logo.png&docid=yk6ff_SvUU3x0M&w=5000&h=968&q=logo%20vnexpress&hl=vi&authuser=0&ved=2ahUKEwin8eGi4LOAAxV9qFYBHfFRDxoQMygBegUIARDJAQ");
        mangquangcao.add("https://www.google.com.vn/imgres?imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2F1%2F1f%2FTu%25E1%25BB%2595i_Tr%25E1%25BA%25BB_Logo.svg&tbnid=ypZ47q76ULVNNM&vet=12ahUKEwjMm_my4LOAAxWvsVYBHSv3A_YQMygAegUIARCiAQ..i&imgrefurl=https%3A%2F%2Fcommons.wikimedia.org%2Fwiki%2FFile%3ATu%25E1%25BB%2595i_Tr%25E1%25BA%25BB_Logo.svg&docid=bTPb8Y768mHLWM&w=1345&h=486&q=logo%20b%C3%A1o%20tu%E1%BB%95i%20tr%E1%BA%BB&hl=vi&authuser=0&ved=2ahUKEwjMm_my4LOAAxWvsVYBHSv3A_YQMygAegUIARCiAQ");


        for (int i=0; i<mangquangcao.size();i++)
        {
            ImageView imageView=new ImageView(getContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            viewFlipper.addView(imageView);
        }

        viewFlipper.setFlipInterval(4000);

        viewFlipper.setAutoStart(true);

        Animation animation_slide_in= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_right);
        Animation animation_slide_out= AnimationUtils.loadAnimation(getContext(),R.anim.slide_out_right);

        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);
    }

    private void AnhXa()
    {
       // toolbar = getActivity().findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper= getActivity().findViewById(R.id.viewflipper);
        listViewNew= getActivity().findViewById(R.id.listviewNew);
        listViewNew2= getActivity().findViewById(R.id.listviewNew2);
       // listView= getActivity().findViewById(R.id.listviewmanhinhchinh);
        //listviewThongtin=getActivity().findViewById(R.id.listviewThongTin);
        //navigationView= getActivity().findViewById(R.id.navigationview);
        //drawerLayout= getActivity().findViewById(R.id.drawerlayout);

        tinArraylist =new ArrayList<>();

        Cursor cursor1 = database.getData1();
        Cursor cursor2 = database.getData2();
        while (cursor1.moveToNext())
        {
            int id=cursor1.getInt(0);
            String tentruyen=cursor1.getString(1);
            String noidung=cursor1.getString(2);
            String anh=cursor1.getString(3);
            int id_tk=cursor1.getInt(4);

            tinArraylist.add(new Tin(id,tentruyen,noidung,anh,id_tk));


        }
        tinArraylist2 = new ArrayList<>();
        while (cursor2.moveToNext())
        {
            int id=cursor2.getInt(0);
            String tentruyen=cursor2.getString(1);
            String noidung=cursor2.getString(2);
            String anh=cursor2.getString(3);
            int id_tk=cursor2.getInt(4);

            tinArraylist2.add(new Tin(id,tentruyen,noidung,anh,id_tk));


        }
        listViewNew.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        listViewNew.setLayoutManager(gridLayoutManager);

        adapterTruyen=new adapterTintucV2(getContext(), tinArraylist);
        listViewNew.setAdapter(adapterTruyen);
        //LISTVIEW2
        listViewNew2.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 2);
        gridLayoutManager2.setOrientation(GridLayoutManager.VERTICAL);
        listViewNew2.setLayoutManager(gridLayoutManager2);

        adapterTruyen=new adapterTintucV2(getContext(), tinArraylist2);
        listViewNew2.setAdapter(adapterTruyen);


        cursor1.moveToFirst();
        cursor1.close();
        cursor2.moveToFirst();
        cursor2.close();

        taiKhoanArrayList=new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tentaikhoan,email));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Tăng bố cục cho fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}
