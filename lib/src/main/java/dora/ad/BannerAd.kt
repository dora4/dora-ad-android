package dora.ad

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerAdapter
import dora.ad.activity.BrowserActivity
import dora.ad.http.AdService
import dora.ad.model.DoraBannerAd
import dora.http.DoraHttp
import dora.http.DoraHttp.result
import dora.http.retrofit.RetrofitManager

object BannerAd {

    fun loadBannerAds(activity: Activity, productName: String, bannerView: Banner<String, ImageAdapter>) {
        DoraHttp.netScope(activity) {
            val adEnable = result {
                RetrofitManager.getService(AdService::class.java).isShowBannerAds(productName)
            }?.data
            if (adEnable == true) {
                bannerView.visibility = View.VISIBLE
                val bannerAds = result {
                    RetrofitManager.getService(AdService::class.java).getBannerAds()
                }?.data
                val result = arrayListOf<String>()
                val banners: MutableList<DoraBannerAd>? = bannerAds
                if (banners != null) {
                    if (banners.size > 0) {
                        for (banner in banners) {
                            banner.imgUrl?.let { result.add(it) }
                        }
                    }
                }
                val imageAdapter = ImageAdapter(result)
                imageAdapter.setOnBannerListener { _, position ->
                    val intent = Intent(activity, BrowserActivity::class.java)
                    intent.putExtra("title", activity.getString(R.string.dora_browser))
                    intent.putExtra("url", banners?.get(position)?.detailUrl)
                    activity.startActivity(intent)
                }
                bannerView.setAdapter(imageAdapter)
            }
        }
    }

    fun loadBannerAds(fragment: Fragment, productName: String, bannerView: Banner<String, ImageAdapter>) {
        DoraHttp.netScope(fragment) {
            val adEnable = result {
                RetrofitManager.getService(AdService::class.java).isShowBannerAds(productName)
            }?.data
            if (adEnable == true) {
                bannerView.visibility = View.VISIBLE
                val bannerAds = result {
                    RetrofitManager.getService(AdService::class.java).getBannerAds()
                }?.data
                val result = arrayListOf<String>()
                val banners: MutableList<DoraBannerAd>? = bannerAds
                if (banners != null) {
                    if (banners.size > 0) {
                        for (banner in banners) {
                            banner.imgUrl?.let { result.add(it) }
                        }
                    }
                }
                val imageAdapter = ImageAdapter(result)
                imageAdapter.setOnBannerListener { _, position ->
                    val intent = Intent(fragment.requireActivity(), BrowserActivity::class.java)
                    intent.putExtra("title", fragment.requireActivity().getString(R.string.dora_browser))
                    intent.putExtra("url", banners?.get(position)?.detailUrl)
                    fragment.requireActivity().startActivity(intent)
                }
                bannerView.setAdapter(imageAdapter)
            }
        }
    }

    class ImageAdapter(banners: List<String>) : BannerAdapter<String, ImageAdapter.BannerViewHolder>(banners) {
        // 创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
        override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
            val imageView = ImageView(parent.context)
            // 注意，必须设置为match_parent，这个是viewpager2强制要求的
            imageView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return BannerViewHolder(imageView)
        }

        override fun onBindView(holder: BannerViewHolder, data: String, position: Int, size: Int) {
            // 图片加载自己实现
            Glide.with(holder.itemView)
                .load(data)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                .into(holder.imageView)
        }

        inner class BannerViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(
            imageView
        )
    }
}