package com.ggf.ggfzpas468;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    String URL = "http://192.168.43.38/zpas648api/";
    private List<Result> results = new ArrayList<>();
    private List<Result> results2 = new ArrayList<>();
    private RecyclerViewAdapter viewAdapter;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    EditText search;
    Button button;
    private ListPopupWindow statusPopupList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.reyview);
        search = findViewById(R.id.searchData);
        button = findViewById(R.id.btnsearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setText("");
            }
        });



        viewAdapter = new RecyclerViewAdapter(this,results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);

        LoadData();



        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onQueryTextChange(search.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        loadlistTanggal();
        //we need to show the list when clicking on the field
        setListeners();




    }

    private void LoadData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InputDataApi api = retrofit.create(InputDataApi.class);
        Call<Value> call = api.view();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                progressBar.setVisibility(View.GONE);
                String value = response.body().getValue();
                if(value.equals("1")){
                    results = response.body().getResult();
                    viewAdapter = new RecyclerViewAdapter(ViewActivity.this,results);
                    recyclerView.setAdapter(viewAdapter);

                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InputDataApi api = retrofit.create(InputDataApi.class);
        Call<Value> call = api.search(newText);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                if (value.equals("1")) {
                    results = response.body().getResult();
                    viewAdapter = new RecyclerViewAdapter(ViewActivity.this, results);
                    recyclerView.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
        return true;

    }

    public void loadlistTanggal(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InputDataApi api = retrofit.create(InputDataApi.class);
        Call<Value> call = api.tanggal();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                progressBar.setVisibility(View.GONE);
                int position = 0;
                String value = response.body().getValue();
                if(value.equals("1")){

                   results2 = response.body().getResult();

                    final List<String> status = new ArrayList<>();
                   for(int i =0; i< results2.size(); i++){

                       status.add(results2.get(i).getTanggalSpk());
                   }

                    statusPopupList = new ListPopupWindow(ViewActivity.this);
                    ArrayAdapter adapter = new ArrayAdapter<>(ViewActivity.this, R.layout.list_tanggal, R.id.tv_element, status);
                    statusPopupList.setAnchorView(search); //this let as set the popup below the EditText
                    statusPopupList.setAdapter(adapter);
                    statusPopupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            search.setText(results2.get(position).getTanggalSpk());//we set the selected element in the EditText
                            statusPopupList.dismiss();
                        }
                    });
                }

                }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });





    }

    private void setListeners() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusPopupList.show();
            }
        });
    }
}