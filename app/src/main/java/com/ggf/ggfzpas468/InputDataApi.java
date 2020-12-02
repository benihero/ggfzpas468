package com.ggf.ggfzpas468;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InputDataApi {

    @FormUrlEncoded
    @POST("insert.php")
    Call<Value> Simpan(@Field("SPK") String SPK,
                       @Field("TanggalSPK") String TanggalSPK,
                       @Field("TanggalRealisasi") String TanggalRealisasi,
                       @Field("Kawil") String Kawil,
                       @Field("Mandor") String Mandor,
                       @Field("KepalaRombong") String KepalaRombong,
                       @Field("KIT") String KIT,
                       @Field("NamaTK") String NamaTK,
                       @Field("CodeAktifitas") String CodeAktifitas,
                       @Field("SatuanHasil") String SatuanHasil,
                       @Field("Lokasi") String Lokasi,
                       @Field("Status") String Status,
                       @Field("LuasHasil") Double LuasHasil,
                       @Field("TanamHasil") int TanamHasil,
                       @Field("JumlahTK")   int JumlahTK,
                       @Field("JenisUpah") String JenisUpah,
                       @Field("KodeNote") String KodeNote,
                       @Field("HKOKarom") String HKOKarom
                        );

    @GET("view.php")
    Call<Value> view();

    @FormUrlEncoded
    @POST("search.php")
    Call<Value> search(@Field("search") String search);

    @GET("getTanggal.php")
    Call<Value> tanggal();

}
