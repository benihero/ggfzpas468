package com.ggf.ggfzpas468;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InputData extends AppCompatActivity {
    DatePickerDialog picker,picker2;
    EditText SPK,tanggalSpk,tanggalRealisasi,kawil,mandor,kepalaRombong,KIT,NamaTK,CodeAktifitas,SatuanHasil,Lokasi,Status,LuasHasil,TanamHasil,jumlahTK,JenisUpah,KodeNote,HKOKarom;
    Button ButtonPickDate,ButtonPickDateTwo;
    ProgressDialog progressDialog;
    String URL = "http://192.168.43.38/zpas648api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        tanggalRealisasi = findViewById(R.id.dateEditText);
        tanggalRealisasi.setInputType(InputType.TYPE_NULL);
        tanggalRealisasi.setEnabled(false);
        SPK = findViewById(R.id.SPK);
        Lokasi = findViewById(R.id.Lokasi);
        tanggalSpk = findViewById(R.id.tanggalSPK);
        tanggalSpk.setEnabled(false);
        tanggalSpk.setInputType(InputType.TYPE_NULL);
        kawil = findViewById(R.id.Kawil);
        mandor = findViewById(R.id.mandor);
        kepalaRombong = findViewById(R.id.KepalaRombong);
        KIT  = findViewById(R.id.Kit);
        NamaTK= findViewById(R.id.NamaTK);
        CodeAktifitas= findViewById(R.id.CodeAktifitas);
        SatuanHasil= findViewById(R.id.SatuanHasil);
        Status= findViewById(R.id.Status);
        LuasHasil= findViewById(R.id.LuasHasil);
        TanamHasil= findViewById(R.id.TanamHasil);
        jumlahTK= findViewById(R.id.JumlahTK);
        JenisUpah= findViewById(R.id.JenisUpah);
        KodeNote   = findViewById(R.id.KodeNote);
        HKOKarom = findViewById(R.id.HkoKarom);

        ButtonPickDateTwo = findViewById(R.id.Buttondatepickspk);
        ButtonPickDateTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int moth = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                picker2 = new DatePickerDialog(InputData.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tanggalSpk.setText(year+"-"+(month+1)+"-"+ dayOfMonth);

                    }
                },year,moth,day);
                picker2.show();



            }
        });

        ButtonPickDate = findViewById(R.id.Buttondatepick);

        ButtonPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int moth = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);


                // get Date pick

                picker = new DatePickerDialog(InputData.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tanggalRealisasi.setText(year+"-"+(month+1)+"-"+ dayOfMonth);

                    }
                },year,moth,day);
                picker.show();
            }
        });

    }

    public void SaveData( View view){


        // mengambil data dari edittext

        String spk = SPK.getText().toString();
        String tanggalspk = tanggalSpk.getText().toString();
        String tanggalrealisasi = tanggalRealisasi.getText().toString();
        String Kawil = kawil.getText().toString();
        String Mandor = mandor.getText().toString();
        String kepalarombong = kepalaRombong.getText().toString();
        String kit = KIT.getText().toString();
        String namatk = NamaTK.getText().toString();
        String codeaktivitas = CodeAktifitas.getText().toString();
        String satuanhasil = SatuanHasil.getText().toString();
        String lokasi = Lokasi.getText().toString();
        String status = Status.getText().toString();
        String luashasil = LuasHasil.getText().toString();
        String tanamhasil = TanamHasil.getText().toString();
        String jenisupah = JenisUpah.getText().toString();
        String jumlahtk = jumlahTK.getText().toString();
        String kodenote = KodeNote.getText().toString();
        String hkorombong = HKOKarom.getText().toString();

        if(spk.isEmpty()){
            Toast.makeText(getApplicationContext(),"SPK tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else if(Kawil.isEmpty()){
            Toast.makeText(getApplicationContext(),"Kawil tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else if(tanggalspk.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tanggal SPK tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else if(tanggalrealisasi.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tanggal Realisasi tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else if(Mandor.isEmpty()){
            Toast.makeText(getApplicationContext(),"Mandor tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else if(kepalarombong.isEmpty()){
            Toast.makeText(getApplicationContext(),"Kepala Rombong tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else if(namatk.isEmpty()){
            Toast.makeText(getApplicationContext(),"Nama TK tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else if(codeaktivitas.isEmpty()){
            Toast.makeText(getApplicationContext(),"Code Aktivitas tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else if(satuanhasil.isEmpty()){
            Toast.makeText(getApplicationContext(),"Satuan Hasil tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else if(lokasi.isEmpty()){
            Toast.makeText(getApplicationContext(),"Lokasi tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else if(status.isEmpty()){
            Toast.makeText(getApplicationContext(),"Status tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else if(jumlahtk.isEmpty()){
            Toast.makeText(getApplicationContext(),"Jumlah TK tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else if(tanamhasil.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tanam hasil tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else if(jenisupah.isEmpty()){
            Toast.makeText(getApplicationContext(),"Jenis Upah tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else if(kodenote.isEmpty()){
            Toast.makeText(getApplicationContext(),"Kode Note tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else if(hkorombong.isEmpty()){
            Toast.makeText(getApplicationContext(),"HKO Rombong tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else {

            //membuat progres dialog
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading..");
            progressDialog.show();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            InputDataApi api = retrofit.create(InputDataApi.class);
            Call<Value> call = api.Simpan(spk,tanggalspk,tanggalrealisasi,Kawil,Mandor,kepalarombong,kit,namatk,codeaktivitas,satuanhasil,lokasi,status,luashasil,tanamhasil,jenisupah,jumlahtk,kodenote,hkorombong);
            call.enqueue(new Callback<Value>() {
                @Override
                public void onResponse(Call<Value> call, Response<Value> response) {
                    String value = response.body().getValue();
                    String message = response.body().getMessage();
                    progressDialog.dismiss();
                    if(value.equals("1")){
                        Toast.makeText(getApplicationContext(),"Data Berhasil Dimasukan",Toast.LENGTH_SHORT).show();

                        SPK.setText("");
                        tanggalSpk.setText("");
                        tanggalRealisasi.setText("");
                        kawil.setText("");
                        mandor.setText("");
                        kepalaRombong.setText("");
                        KIT.setText("");
                        NamaTK.setText("");
                        CodeAktifitas.setText("");
                        SatuanHasil.setText("");
                        Lokasi.setText("");
                        Status.setText("");
                        LuasHasil.setText("");
                        TanamHasil.setText("");
                        jumlahTK.setText("");
                        JenisUpah.setText("");
                        KodeNote.setText("");
                        HKOKarom.setText("");

                    } else {
                        Toast.makeText(getApplicationContext(),"Data Gagal Dimasukan",Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<Value> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Jaringan error",Toast.LENGTH_SHORT).show();

                }


            });



        }





    }
}