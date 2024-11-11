package dora.ad.http

import dora.ad.model.DoraBannerAd
import dora.http.retrofit.ApiService
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AdService : ApiService {

    @GET("ad/banner/list")
    fun getBannerAds(): Call<ApiResult<MutableList<DoraBannerAd>>>

    @GET("ad/banner/enable")
    fun isShowBannerAds(@Query("productName") productName: String): Call<ApiResult<Boolean>>
}