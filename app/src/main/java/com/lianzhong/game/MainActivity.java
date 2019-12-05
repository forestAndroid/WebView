package com.lianzhong.game;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.lianzhong.game.bean.BannerBean;
import com.lianzhong.game.net.HostUrl;
import com.lianzhong.game.ui.activity.EasyWebActivity;
import com.lianzhong.game.utils.GlideImageLoader;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.banner)
    Banner banner;
//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;

    private List<BannerBean.DataBean.ImagesBean> bannerList = new ArrayList<>();

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ImmersionBar.with(this)
                .statusBarDarkFont(false)
                .statusBarView(R.id.status_view)
                .barColor("#ff0000")
                .keyboardEnable(true)
                .navigationBarColor("#ffffff")
                .init();
        initView();
        getBanner();
    }

    private void initView() {
        //806*456
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(outMetrics);
        int widthPixel = outMetrics.widthPixels;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) banner.getLayoutParams();
        layoutParams.height = widthPixel * 456 / 806;
        banner.setLayoutParams(layoutParams);

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                startActivity(
                        new Intent(MainActivity.this, EasyWebActivity.class)
                                .putExtra("webUrl", bannerList.get(position)
                                        .getDown_url()
                                )
                );
            }
        });
        banner.setVisibility(View.GONE);
    }

    private void getBanner() {
        OkGo.<String>get(HostUrl.Banner).tag(this).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                BannerBean bannerBean = new Gson().fromJson(response.body(), BannerBean.class);
                bannerBean.getData().getImages();
                bannerList.clear();
                bannerList.addAll(bannerBean.getData().getImages());
                //设置图片集合
                banner.setImages(bannerList);
                //banner设置方法全部调用完毕时最后调用
                banner.start();
            }
        });
    }

}
