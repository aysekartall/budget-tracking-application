package com.example.butcetakipuygulamasi;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    String kullaniciMail,kullaniciSifre;
    EditText et_kullaniciPosta,et_kullanicisifre;
    Button btn_girisyap,btn_yeniüye;
    SignInButton btn_google;
   // Button btn_cikis;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // title bar kaldirma
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //tüm ekran
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("803640634026-c2cca6betf6pcrmrc1eardtidfhj9gfg.apps.googleusercontent.com")
                .requestEmail()
                .build();

        btn_google = findViewById(R.id.btn_googlegiris);
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        et_kullanicisifre=(EditText)findViewById(R.id.et_sifre);
        et_kullaniciPosta=(EditText)findViewById(R.id.et_kullanici);

        //....LOG IN....
        btn_girisyap=(Button)findViewById(R.id.btn_giris);
        btn_girisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kullaniciMail=et_kullaniciPosta.getText().toString();
                kullaniciSifre=et_kullanicisifre.getText().toString();
                mAuth.signInWithEmailAndPassword(kullaniciMail,kullaniciSifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent intentAnaSayfa = new Intent(getApplicationContext(),AnaSayfaa.class);
                            startActivity(intentAnaSayfa);
                            overridePendingTransition(R.anim.anim_giris, R.anim.anim_cikis); //animasyon
                            Toast.makeText(getApplicationContext(), "Giriş işlemi gerçekleştirildi", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "E-posta veya şifre hatalı!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //....YENİ KAYIT....
        btn_yeniüye=(Button)findViewById(R.id.btn_yenikayit);
        btn_yeniüye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenyeniKayit = new Intent(MainActivity.this,YeniKayit.class);
                startActivity(intenyeniKayit);
                overridePendingTransition(R.anim.anim_giris, R.anim.anim_cikis); //animasyon
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intentGoogledanAnasayfa = new Intent(getApplicationContext(),AnaSayfaa.class);
                            startActivity(intentGoogledanAnasayfa);
                            overridePendingTransition(R.anim.anim_giris, R.anim.anim_cikis); //animasyon
                        } else {
                        }
                    }
                });
//        btn_cikis=(Button)findViewById(R.id.button);
//        btn_cikis.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAuth.signOut();
//            }
//        });
    }
}