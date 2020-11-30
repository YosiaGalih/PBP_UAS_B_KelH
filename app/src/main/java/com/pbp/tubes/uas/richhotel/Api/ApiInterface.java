package com.pbp.tubes.uas.richhotel.Api;

import com.pbp.tubes.uas.richhotel.Response.KamarResponse;
import com.pbp.tubes.uas.richhotel.Response.TransaksiResponse;
import com.pbp.tubes.uas.richhotel.Response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("kamar_hotels")
    Call<KamarResponse> getAllKamar(@Query("data")String data);

    @GET("transaksis/{id_pemesan}")
    Call<TransaksiResponse> gettTransaksiById(@Path("id_pemesan")String id,
                                              @Query("data")String data);

//    @GET("registrasi")
//    Call<UserResponse> getUserById(@Path("id")String id,
//                                   @Query("data")String data);
    @POST("registrasi")
    @FormUrlEncoded
    Call<UserResponse> createUser(@Field("nama")String nama, @Field("age")String age,
                                  @Field("email")String email, @Field("password")String password);

    @POST("registrasi/update/{id}")
    @FormUrlEncoded
    Call<UserResponse> updateUser(@Field("nama")String nama, @Field("age")String age,
                                  @Field("email")String email, @Field("password")String password);

    @POST("kamar_hotels")
    @FormUrlEncoded
    Call<KamarResponse> createKamar(@Field("nama_kamar")String nama_kamar, @Field("kapasitas")String kapasitas,
                                    @Field("harga")String harga, @Field("imageURL")String image);

    @POST("kamar_hotels/update/{id}")
    @FormUrlEncoded
    Call<KamarResponse> updateKamar(@Field("nama_kamar")String nama_kamar, @Field("kapasitas")String kapasitas,
                                    @Field("harga")String harga, @Field("imageURL")String image);

    @POST("kamar_hotels/delete/{id}")
    @FormUrlEncoded
    Call<KamarResponse> deleteKamar(@Field("nama_kamar")String nama_kamar, @Field("kapasitas")String kapasitas,
                                    @Field("harga")String harga, @Field("imageURL")String image);

    @POST("transaksis")
    @FormUrlEncoded
    Call<TransaksiResponse> createTransaksi(@Field("nama_pemesan")String nama_pemesan, @Field("id_pemesan")String id,
                                            @Field("alamat")String alamat, @Field("pilihan_kamar")String pilihan,
                                            @Field("tgl_check_in")String tglCheckIn, @Field("tgl_check_out")String tglCheckOut);

    @POST("transaksis/update/{id_pemesan}")
    @FormUrlEncoded
    Call<TransaksiResponse> updateTransaksi(@Field("nama_pemesan")String nama_pemesan, @Field("id_pemesan")String id,
                                            @Field("alamat")String alamat, @Field("pilihan_kamar")String pilihan,
                                            @Field("tgl_check_in")String tglCheckIn, @Field("tgl_check_out")String tglCheckOut);

    @POST("transaksis/delete/{id_pemesan}")
    @FormUrlEncoded
    Call<TransaksiResponse> deleteTransaksi(@Field("nama_pemesan")String nama_pemesan, @Field("id_pemesan")String id,
                                            @Field("alamat")String alamat, @Field("pilihan_kamar")String pilihan,
                                            @Field("tgl_check_in")String tglCheckIn, @Field("tgl_check_out")String tglCheckOut);

}
