package com.example.butcetakipuygulamasi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class AnaSayfaa extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ListView listView;
    CustomAdapter adapter;
    List<Icerikler> mList = new ArrayList<>();
    AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // title bar kaldirma
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //tüm ekran
        setContentView(R.layout.activity_ana_sayfaa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dialog = new AlertDialog.Builder(AnaSayfaa.this);

        listView = findViewById(R.id.listview_icerik);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mList.add(new Icerikler("GELİRLER", R.drawable.gelir));
        mList.add(new Icerikler("GİDERLER", R.drawable.gider));
        mList.add(new Icerikler("ÖZET", R.drawable.ozet));

        adapter = new CustomAdapter(this, mList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    Intent intentGelir = new Intent(AnaSayfaa.this, GelirClass.class);
                    startActivity(intentGelir);
                }else if(position==1){
                    Intent intentGider = new Intent(AnaSayfaa.this, GiderClass.class);
                    startActivity(intentGider);
                }else if(position==2){
                    Intent intentOzet = new Intent(AnaSayfaa.this, HesapOzet.class);
                    startActivity(intentOzet);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ana_sayfaa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         if(item.getItemId()==R.id.gun)
        {
            Toast.makeText(this, "Günlük hesaplama", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.hafta)
        {
            Toast.makeText(this, "Haftalık hesaplama", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.ay)
        {
            Toast.makeText(this, "Aylık hesaplama", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.yil)
        {
            Toast.makeText(this, "Yıllık hesaplama", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ana_sayfagit) {

        } else if (item.getItemId()== R.id.gelir_hesapla) {
            Intent intentGelir = new Intent(AnaSayfaa.this, GelirClass.class);
            startActivity(intentGelir);
        } else if (item.getItemId() == R.id.gider_hesapla) {
            Intent intentGider = new Intent(AnaSayfaa.this, GiderClass.class);
            startActivity(intentGider);
        } else if (item.getItemId() == R.id.hesap_özet) {
            Intent intentOzet = new Intent(AnaSayfaa.this, HesapOzet.class);
            startActivity(intentOzet);
        } else if (item.getItemId() == R.id.paylas) {

        } else if (item.getItemId()== R.id.cikis_yap) {
            dialog.setTitle("ÇIKIŞ YAP!");
            dialog.setMessage("Çıkış yapmak istediğinize emin misiniz?");
            dialog.setIcon(R.drawable.logoproje);
            dialog.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    System.exit(0);
                }
            });
            dialog.setNegativeButton("İPTAL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            dialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}