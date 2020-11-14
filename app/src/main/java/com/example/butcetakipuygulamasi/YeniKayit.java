package com.example.butcetakipuygulamasi;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class YeniKayit extends AppCompatActivity {
    String kullaniciMail,kullaniciSifre;
    EditText et_yeniKayitPosta,et_yeniKayitSifre;
    ImageView errorimg;
    Button btnKayıt;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // title bar kaldirma
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //tüm ekran
        setContentView(R.layout.activity_yeni_kayit);

        mAuth = FirebaseAuth.getInstance();
        errorimg = findViewById(R.id.error);
        errorimg.setVisibility(View.INVISIBLE);

        et_yeniKayitPosta=(EditText)findViewById(R.id.et_kullaniciadkayit);
        et_yeniKayitSifre=(EditText)findViewById(R.id.et_kullanicisifrekayit);

        btnKayıt=(Button)findViewById(R.id.btn_kayıt);
        btnKayıt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kullaniciMail=et_yeniKayitPosta.getText().toString();
                kullaniciSifre=et_yeniKayitSifre.getText().toString();
                if(kullaniciSifre.length() < 6)
                {
                    Toast.makeText(YeniKayit.this, "Lütfen en az 6 karakter giriniz!", Toast.LENGTH_SHORT).show();
                    errorimg.setVisibility(View.VISIBLE);
                }
                else
                {
                    mAuth.createUserWithEmailAndPassword(kullaniciMail,kullaniciSifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Intent intentAnasayfa = new Intent(getApplicationContext(),AnaSayfaa.class);
                            startActivity(intentAnasayfa);
                            overridePendingTransition(R.anim.anim_giris, R.anim.anim_cikis); //animasyon
                            errorimg.setVisibility(View.INVISIBLE);
                            Toast.makeText(YeniKayit.this, "Kayıt başarıyla gerçekleşti", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        et_yeniKayitSifre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                if(!hasFocus){
                    if(et_yeniKayitSifre.getText().length()<6){
                        Resources resources = getResources();
                        Drawable drawable = resources.getDrawable(R.drawable.error); // hata mesajı iconu
                        int ht = drawable.getIntrinsicHeight();
                        int wt = drawable.getIntrinsicWidth();
                        drawable.setBounds(0,0,wt,ht);
                        et_yeniKayitSifre.setError("Şifre uzunluğu 6 karakterden küçük olamaz!",drawable);
                    }
                }
            }
        });
    }
}