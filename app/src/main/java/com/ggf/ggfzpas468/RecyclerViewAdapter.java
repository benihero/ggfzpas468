package com.ggf.ggfzpas468;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    private Context context;
    private List<Result> results;

    public RecyclerViewAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }



    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
    Result result = results.get(position);
    holder.mandor.setText(result.getMandor());
    holder.kawil.setText(result.getKawil());
    holder.spk.setText(result.getSPK());
    holder.tanggalSpk.setText(result.getTanggalSpk());
    holder.lokasi.setText(result.getLokasi());
    holder.listid.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Result result = results.get(position);
            Intent i = new Intent(context,DetailData.class);
            i.putExtra("mandor",result.getMandor());
            i.putExtra("kawil",result.getKawil());
            i.putExtra("spk",result.getSPK());
            i.putExtra("lokasi",result.getLokasi());
            i.putExtra("tanggalspk",result.getTanggalSpk());
            i.putExtra("tanggalrealisasi",result.getTanggalRealisasi());
            i.putExtra("kepalarombong",result.getKepalaRombong());
            i.putExtra("kit",result.getKIT());
            i.putExtra("namatk",result.getNamaTK());
            i.putExtra("codeaktifitas",result.getCodeAktifitas());
            i.putExtra("satuanhasil",result.getSatuanHasil());
            i.putExtra("status",result.getStatus());
            i.putExtra("luashasil",result.getLuasHasil());
            i.putExtra("tanamhasil",result.getTanamHasil());
            i.putExtra("jumlahtk",result.getJumlahTK());
            i.putExtra("jenisupah",result.getJenisUpah());
            i.putExtra("kodenote",result.getKodeNote());
            i.putExtra("hkokarom",result.getHKOKarom());

            context.startActivity(i);
        }
    });


    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView spk,kawil,mandor,tanggalSpk,lokasi;
        LinearLayout listid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spk = itemView.findViewById(R.id.SPKData);
            listid= itemView.findViewById(R.id.listid);
            lokasi = itemView.findViewById(R.id.LokasiData);
            kawil = itemView.findViewById(R.id.KawilData);
            mandor = itemView.findViewById(R.id.MandorData);
            tanggalSpk = itemView.findViewById(R.id.TanggalSPKData);
        }
    }
}
