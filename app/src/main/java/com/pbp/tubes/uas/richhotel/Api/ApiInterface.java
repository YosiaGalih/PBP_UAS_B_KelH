package com.pbp.tubes.uas.richhotel.Api;

import com.pbp.tubes.uas.richhotel.Response.KamarResponse;
import com.pbp.tubes.uas.richhotel.Response.KamarResponseObject;
import com.pbp.tubes.uas.richhotel.Response.TransaksiResponse;
import com.pbp.tubes.uas.richhotel.Response.TransaksiResponseObject;
import com.pbp.tubes.uas.richhotel.Response.UserResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    //--------------------------------------------API User--------------------------------------------//
    @GET("user")
    Call<UserResponse> getUser(@Query("data")String data);

    @GET("user/{email}")
    Call<UserResponse> getUserByEmail(@Path("email")String email,
                                       @Query("data")String data);

    @POST("registrasi")
    @FormUrlEncoded
    Call<UserResponse> createUser(@Field("name")String nama, @Field("age")String age,
                                  @Field("email")String email, @Field("password")String password);

    @PUT("user/{id}")
    @FormUrlEncoded
    Call<UserResponse> updateUser(@Path("id")String id,
                                  @Field("name")String nama, @Field("age")String age,
                                  @Field("email")String email);

    //--------------------------------------------API Kamar--------------------------------------------//

    @GET("kamar")
    Call<KamarResponse> getAllKamar(@Query("data")String data);

    @GET("kamar/{id}")
    Call<KamarResponseObject> getKamarById(@Path("id")String id,
                                           @Query("data")String data);

    @POST("kamar")
    @FormUrlEncoded
    Call<KamarResponseObject> createKamar(@Field("nama_kamar")String nama_kamar, @Field("kapasitas")String kapasitas,
                                    @Field("harga")String harga, @Field("imageURL")String image);

    @PUT("kamar/{id}")
    @FormUrlEncoded
    Call<KamarResponseObject> updateKamar(@Path("id")String id,
                                    @Field("nama_kamar")String nama_kamar, @Field("kapasitas")String kapasitas,
                                    @Field("harga")String harga, @Field("imageURL")String image);

    @DELETE("kamar/{id}")
    Call<KamarResponseObject> deleteKamar(@Path("id")String id);

    //--------------------------------------------API Transaksi--------------------------------------------//

    @GET("transaksi")
    Call<TransaksiResponse> getAllTransaksi(@Query("data")String data);

    @GET("transaksi/{id_pemesan}")
    Call<TransaksiResponse> getTransaksiByIdPemesan(@Path("id_pemesan")String id_pemesan,
                                                          @Query("data")String data);

    @POST("transaksi")
    @FormUrlEncoded
    Call<TransaksiResponseObject> createTransaksi(@Field("nama")String nama_pemesan, @Field("id_pemesan")String id,
                                                  @Field("alamat")String alamat, @Field("pilihan_kamar")String pilihan,
                                                  @Field("tgl_check_in")String tglCheckIn, @Field("tgl_check_out")String tglCheckOut);

    @PUT("transaksi/{id}")
    @FormUrlEncoded
    Call<TransaksiResponse> updateTransaksi(@Path("id")String id,
                                            @Field("nama_pemesan")String nama_pemesan, @Field("id_pemesan")String id_pemesan,
                                            @Field("alamat")String alamat, @Field("pilihan_kamar")String pilihan,
                                            @Field("tgl_check_in")String tglCheckIn, @Field("tgl_check_out")String tglCheckOut);

    @DELETE("transaksi/{id}")
    Call<TransaksiResponse> deleteTransaksi(@Path("id")String id);
}
