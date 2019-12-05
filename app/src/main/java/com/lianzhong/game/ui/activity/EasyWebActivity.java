package com.lianzhong.game.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.download.library.DownloadImpl;
import com.download.library.DownloadListenerAdapter;
import com.download.library.Extra;
import com.download.library.ResourceRequest;
import com.gyf.immersionbar.ImmersionBar;
import com.just.agentweb.AbsAgentWebSettings;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultDownloadImpl;
import com.just.agentweb.IAgentWebSettings;
import com.just.agentweb.WebListenerManager;
import com.just.agentweb.WebViewClient;
import com.lianzhong.game.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by cenxiaozhong on 2017/7/22.
 * <p>
 */
public class EasyWebActivity extends BaseAgentWebActivity {

    private TextView mTitleTextView;
    private static final List<String> overrideUrls;

    static {
        overrideUrls = new ArrayList<>();
        overrideUrls.add("https://369wwe.com/?k=7376792321296C107772707778726C36332A773373");
        overrideUrls.add("https://xzhan.adaierdaisi.com/m.html?shareName=389.so&proxyAccount=10065");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ImmersionBar.with(this)
                .statusBarDarkFont(false)
                .statusBarView(R.id.status_view)
                .barColor("#ff0000")
                .keyboardEnable(true)
                .navigationBarColor("#ffffff")
                .init();
    }

    @NonNull
    @Override
    protected ViewGroup getAgentWebParent() {
        return (ViewGroup) this.findViewById(R.id.container);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebView webView = mAgentWeb.getWebCreator().getWebView();
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
//            String url = webView.getUrl().toString();
//            webView.goBack();
//            // 如果遇到迷之无法返回上一页
//            if (webView.getUrl().toString().equals(url)) {
//                webView.goBack();
//            }
//            return true;
//        } else {
//            finish();
//        }

        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            String url = webView.getUrl();
            webView.getOriginalUrl();
            Log.e("getUrl", webView.getUrl());
            Log.e("getOriginalUrl", webView.getOriginalUrl());
            if (!overrideUrls.contains(url)) {
                webView.goBack(); // goBack()表示返回WebView的上一页面
                return true;
            } else {
                finish();
            }
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
//        if (mAgentWeb != null && mAgentWeb.handleKeyEvent(keyCode, event)) {
//            return true;
//        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected int getIndicatorColor() {
        return Color.parseColor("#ff0000");
    }

    @Override
    protected void setTitle(WebView view, String title) {
        super.setTitle(view, title);
        if (!TextUtils.isEmpty(title)) {
            if (title.length() > 10) {
                title = title.substring(0, 10).concat("...");
            }
        }
    }

    @Override
    protected int getIndicatorHeight() {
        return 3;
    }

    @Nullable
    @Override
    protected String getUrl() {
        return getIntent().getStringExtra("webUrl");
    }

    @Nullable
    @Override
    public IAgentWebSettings getAgentWebSettings() {
        return new AbsAgentWebSettings() {
            private AgentWeb mAgentWeb;


            @Override
            public IAgentWebSettings toSetting(WebView webView) {
                super.toSetting(webView);
                getWebSettings().setBuiltInZoomControls(true);
                getWebSettings().setUseWideViewPort(true);
                getWebSettings().setLoadWithOverviewMode(true);
                return this;
            }

            @Override
            protected void bindAgentWebSupport(AgentWeb agentWeb) {
                this.mAgentWeb = agentWeb;
            }

            /**
             * AgentWeb 4.0.0 内部删除了 DownloadListener 监听 ，以及相关API ，将 Download 部分完全抽离出来独立一个库，
             * 如果你需要使用 AgentWeb Download 部分 ， 请依赖上 compile 'com.download.library:Downloader:4.1.1' ，
             * 如果你需要监听下载结果，请自定义 AgentWebSetting ， New 出 DefaultDownloadImpl
             * 实现进度或者结果监听，例如下面这个例子，如果你不需要监听进度，或者下载结果，下面 setDownloader 的例子可以忽略。
             * @param webView
             * @param downloadListener
             * @return WebListenerManager
             */
            @Override
            public WebListenerManager setDownloader(WebView webView, android.webkit.DownloadListener downloadListener) {
                return super.setDownloader(webView,
                        new DefaultDownloadImpl((Activity) webView.getContext(),
                                webView,
                                this.mAgentWeb.getPermissionInterceptor()) {

                            final ProgressDialog progressDialog = new ProgressDialog(EasyWebActivity.this);


                            @Override
                            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                                new AlertDialog.Builder(EasyWebActivity.this)
                                        .setTitle("允许下载吗？")
                                        .setPositiveButton("确定",
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog,
                                                                        int which) {
                                                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                                                            @Override
                                                            public void run() {

                                                                onDownloadStartInternal(url, userAgent, contentDisposition, mimetype, contentLength);
                                                                progressDialog.setTitle("温馨提示");
                                                                progressDialog.setMessage("正在下载...");
                                                                progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
                                                                progressDialog.setProgress(0);
                                                                progressDialog.show();
                                                            }
                                                        });
                                                    }
                                                })
                                        .setNegativeButton("取消",
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog,
                                                                        int which) {

                                                    }
                                                })
                                        .setOnCancelListener(
                                                new DialogInterface.OnCancelListener() {
                                                    @Override
                                                    public void onCancel(DialogInterface dialog) {

                                                    }
                                                }).show();

//                                super.onDownloadStart(url, userAgent, contentDisposition, mimetype, contentLength);

                            }

                            @Override
                            protected ResourceRequest createResourceRequest(String url) {
                                return DownloadImpl.getInstance()
                                        .with(getApplicationContext())
                                        .url(url)
                                        .quickProgress()
                                        .addHeader("", "")
                                        .setEnableIndicator(true)
                                        .autoOpenIgnoreMD5()
                                        .setRetry(5)
                                        .setBlockMaxTime(100000L);
                            }

                            @Override
                            protected void taskEnqueue(ResourceRequest resourceRequest) {
                                resourceRequest.enqueue(new DownloadListenerAdapter() {
                                    @Override
                                    public void onStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength, Extra extra) {
                                        super.onStart(url, userAgent, contentDisposition, mimetype, contentLength, extra);
                                    }

                                    @MainThread
                                    @Override
                                    public void onProgress(String url, long downloaded, long length, long usedTime) {
                                        super.onProgress(url, downloaded, length, usedTime);
                                        int progress = (int) (downloaded * 100f / length);
                                        Log.e("progress+++", progress + "%");
                                        if (progress < 100) {
                                            progressDialog.setProgress(progress);
                                        } else {
                                            progressDialog.dismiss();
                                        }
                                    }

                                    @Override
                                    public boolean onResult(Throwable throwable, Uri path, String url, Extra extra) {
                                        return super.onResult(throwable, path, url, extra);
                                    }
                                });
                            }
                        });
            }
        };
    }
}
