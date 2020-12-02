package com.ggf.ggfzpas468;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DetailData extends AppCompatActivity {

    TextView SPK,tanggalSpk,tanggalRealisasi,kawil,mandor,kepalaRombong,KIT,NamaTK,CodeAktifitas,SatuanHasil,Lokasi,Status,LuasHasil,TanamHasil,jumlahTK,JenisUpah,KodeNote,HKOKarom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        kawil = findViewById(R.id.DtKawil);
        mandor = findViewById(R.id.Dtmandor);
        Lokasi = findViewById(R.id.DtLokasi);
        CodeAktifitas = findViewById(R.id.DtCodeAktifitas);
        SPK = findViewById(R.id.DtSPK);
        tanggalSpk = findViewById(R.id.DttanggalSPK);
        tanggalRealisasi = findViewById(R.id.DtTanggalRealisasi);
        kepalaRombong = findViewById(R.id.DtKepalaRombong);
        KIT =findViewById(R.id.DtKit);
        NamaTK = findViewById(R.id.DtNamaTK);
        SatuanHasil = findViewById(R.id.DtSatuanHasil);
        Status = findViewById(R.id.DtStatus);
        LuasHasil = findViewById(R.id.DtLuasHasil);
        TanamHasil = findViewById(R.id.DtTanamHasil);
        jumlahTK = findViewById(R.id.DtJumlahTK);
        JenisUpah = findViewById(R.id.DtJenisUpah);
        KodeNote = findViewById(R.id.DtKodeNote);
        HKOKarom = findViewById(R.id.DtHkoKarom);

        Bundle bundle = getIntent().getExtras();
        mandor.setText(bundle.getString("mandor"));
        kawil.setText(bundle.getString("kawil"));
        Lokasi.setText(bundle.getString("lokasi"));
        CodeAktifitas.setText(bundle.getString("codeaktifitas"));
        SPK.setText(bundle.getString("spk"));
        tanggalSpk.setText(bundle.getString("tanggalspk"));
        tanggalRealisasi.setText(bundle.getString("tanggalrealisasi"));
        kepalaRombong.setText(bundle.getString("kepalarombong"));
        KIT.setText(bundle.getString("kit"));
        NamaTK.setText(bundle.getString("namatk"));
        SatuanHasil.setText(bundle.getString("satuanhasil"));
        Status.setText(bundle.getString("status"));
        LuasHasil.setText(bundle.getString("luashasil"));
        TanamHasil.setText(bundle.getString("tanamhasil"));
        jumlahTK.setText(bundle.getString("jumlahtk"));
        JenisUpah.setText(bundle.getString("jenisupah"));
        KodeNote.setText(bundle.getString("kodenote"));
        HKOKarom.setText(bundle.getString("hkokarom"));

    }
}