package com.lianzhong.game.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lianzhong.game.bean.BannerBean;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */
        //Glide 加载图片简单用法
        BannerBean.DataBean.ImagesBean imagesBean = null;
        if (path instanceof BannerBean.DataBean.ImagesBean) {
            imagesBean = (BannerBean.DataBean.ImagesBean) path;
        }
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(imagesBean.getBanner_url()).into(imageView);
    }

    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
    @Override
    public ImageView createImageView(Context context) {
        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        ImageView imageView = new ImageView(context);
        return imageView;
    }
}