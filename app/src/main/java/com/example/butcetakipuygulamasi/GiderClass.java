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

public class GiderClass extends AppCompatActivity {
    EditText et_gidermiktar,et_giderad;
    Button btn_giderekle;
    FileOutputStream file_output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // title bar kaldirma
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //tüm ekran
        setContentView(R.layout.activity_gider_class);

        et_gidermiktar=(EditText)findViewById(R.id.et_gidermiktari);
        et_giderad=(EditText)findViewById(R.id.et_gideradi);
        btn_giderekle=(Button)findViewById(R.id.button_giderekle);
        btn_giderekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GiderClass.this, "Gider eklendi", Toast.LENGTH_SHORT).show();
                try {
                    file_output=openFileOutput("gelir yada gider.txt",MODE_APPEND);
                    if(!(et_gidermiktar.getText().toString().equals("")) && !(et_giderad.getText().toString().equals("")))
                    {
                        String gider = et_giderad.getText().toString()+ " -" + et_gidermiktar.getText().toString()+ " TL  /";
                        file_output.write(gider.getBytes());
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
