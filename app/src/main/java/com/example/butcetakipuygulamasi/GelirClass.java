package com.example.butcetakipuygulamasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GelirClass extends AppCompatActivity {
    EditText et_gelirmiktar,et_gelirad;
    Button btn_gelirekle;
    FileOutputStream file_output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // title bar kaldirma
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //tüm ekran
        setContentView(R.layout.activity_gelir_class);

        et_gelirmiktar=(EditText)findViewById(R.id.et_gelirmiktari);
        et_gelirad=(EditText)findViewById(R.id.et_geliradi);
        btn_gelirekle=(Button)findViewById(R.id.button_gelirekle);
        btn_gelirekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GelirClass.this, "Gelir eklendi", Toast.LENGTH_SHORT).show();
                try {
                    file_output = openFileOutput("gelir yada gider.txt",MODE_APPEND);
                    if(!(et_gelirad.getText().toString().equals("")) && !(et_gelirmiktar.getText().toString().equals("")))
                    {
                        String gelir = et_gelirad.getText().toString()+ " +" +et_gelirmiktar.getText().toString()+ " TL  /";
                        file_output.write(gelir.getBytes());
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}