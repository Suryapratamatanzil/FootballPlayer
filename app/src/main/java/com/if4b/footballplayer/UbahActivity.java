package com.if4b.footballplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahActivity extends AppCompatActivity {
    private EditText etNama, etNomor, etKlub;
    private Button btnUbah;
    MyDatabaseHelper myDB = new MyDatabaseHelper(UbahActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        etNama = findViewById(R.id.et_nama);
        etNomor = findViewById(R.id.et_nomor);
        etKlub = findViewById(R.id.et_club);
        btnUbah = findViewById(R.id.btn_ubah);

        Intent intent = getIntent();
        String id = intent.getStringExtra("varId");
        String nama = intent.getStringExtra("varNama");
        String nomor = intent.getStringExtra("varNomor");
        String klub = intent.getStringExtra("varKlub");

        etNama.setText(nama);
        etNomor.setText(nomor);
        etKlub.setText(klub);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getNama, getNomor, getKlub;

                getNama = etNama.getText().toString();
                getNomor = etNomor.getText().toString();
                getKlub = etKlub.getText().toString();
                if(getNama.trim().equals("")){
                    etNama.setError("Please Insert Your Name!");
                }
                if(getNomor.trim().equals("")){
                    etNomor.setError("Please Insert Your Jersey Number!");
                }
                if(getKlub.trim().equals("")){
                    etKlub.setError("Please Insert Your Name!");
                }
                else {
                    long eks = myDB.ubahPlayer(id, getNama, getNomor, getKlub );
                    if(eks ==-1){
                        Toast.makeText(UbahActivity.this, "Change data fail", Toast.LENGTH_SHORT).show();
                        etNama.requestFocus();
                    }
                    else{
                        Toast.makeText(UbahActivity.this, "Change data success", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

    }
}