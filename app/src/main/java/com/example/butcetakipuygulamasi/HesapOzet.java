package com.example.butcetakipuygulamasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HesapOzet extends AppCompatActivity {
    ListView lw_gelir, lw_gider;
    FileInputStream file_input;
    String[] arrayToplam;
    List<String> listGelir = new ArrayList<>();
    List<String> listGider = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // title bar kaldirma
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //tüm ekran
        setContentView(R.layout.activity_hesap_ozet);

        lw_gelir = findViewById(R.id.list_gelir);
        lw_gider = findViewById(R.id.list_gider);

        String metin = " ";
        int okunan;
        try {
            file_input = openFileInput("gelir yada gider.txt");
            while ((okunan = file_input.read()) != -1) {
                metin += Character.toString((char) okunan);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        arrayToplam = metin.split("/");

        for (int i = 0; i < arrayToplam.length; i++)
        {
            for(int j = 0; j < arrayToplam[i].length(); j++)
            {
                String s = Character.toString(arrayToplam[i].charAt(j));
                if (s.equals("+"))
                {
                    listGelir.add(arrayToplam[i]);
                }
                else if (s.equals("-"))
                {
                    listGider.add(arrayToplam[i]);
                }
            }
        }

        ArrayAdapter<String> adapterGelir = new ArrayAdapter<String>(HesapOzet.this,
                android.R.layout.simple_list_item_1, listGelir);
        lw_gelir.setAdapter(adapterGelir);

        ArrayAdapter<String> adapterGider = new ArrayAdapter<String>(HesapOzet.this,
                android.R.layout.simple_list_item_1, listGider);
        lw_gider.setAdapter(adapterGider);
    }
}