package com.lianzhong.game.ui.activity;

import androidx.appcompat.app.AppCompatActivity;



public class WebActivity extends AppCompatActivity {
//    private static final String TAG = "WebActivity";
//    public static final int MSG_OPEN_TEST_URL = 0;
//    public static final int MSG_INIT_UI = 1;
//    @BindView(R.id.progressBar)
//    ProgressBar progressBar;
//    @BindView(R.id.webView)
//    FrameLayout frameLayout;
//    @BindView(R.id.logView)
//    TextView logView;
//
//    private ValueCallback<Uri> uploadFile;
//    private X5WebView mWebView;
//    private URL mIntentUrl;
//    private boolean mNeedTestPage = false;
//    private final int mUrlStartNum = 0;
//    private int mCurrentUrl = mUrlStartNum;
//    private String mHomeUrl = "http://ikhanju.tiankongxiazheyu.com:12369/D-ikhanju.html";
//    private Handler mTestHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case MSG_OPEN_TEST_URL:
//                    if (!mNeedTestPage) {
//                        return;
//                    }
//
//                    String testUrl = "file:///sdcard/outputHtml/html/"
//                            + Integer.toString(mCurrentUrl) + ".html";
//                    if (mWebView != null) {
//                        mWebView.loadUrl(testUrl);
//                    }
//
//                    mCurrentUrl++;
//                    break;
//                case MSG_INIT_UI:
//                    init();
//                    break;
//            }
//            super.handleMessage(msg);
//        }
//    };
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Intent intent = getIntent();
//        if (intent != null) {
//            try {
//                mIntentUrl = new URL(intent.getStringExtra("webUrl"));
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (NullPointerException e) {
//
//            } catch (Exception e) {
//            }
//        }
//        /*
//         * getWindow().addFlags(
//         * android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
//         */
//        setContentView(R.layout.activity_web);
//        ButterKnife.bind(this);
//        ImmersionBar.with(this)
//                .statusBarDarkFont(true)
//                .statusBarView(R.id.status_view)
//                .barColor("#ffffff")
//                .keyboardEnable(true)
//                .navigationBarColor("#ffffff")
//                .init();
//        mTestHandler.sendEmptyMessageDelayed(MSG_INIT_UI, 10);
//        initView();
//
//
//    }
//
//    private void initView() {
//
//    }
//
//
//    private void init() {
//
//        mWebView = new X5WebView(this, null);
//
//        frameLayout.addView(mWebView, new FrameLayout.LayoutParams(
//                FrameLayout.LayoutParams.FILL_PARENT,
//                FrameLayout.LayoutParams.FILL_PARENT));
//
//        initProgressBar();
//
//        mWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return false;
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                // mTestHandler.sendEmptyMessage(MSG_OPEN_TEST_URL);
//                mTestHandler.sendEmptyMessageDelayed(MSG_OPEN_TEST_URL, 5000);// 5s?
////                    changGoForwardButton(view);
//                /* mWebView.showLog("test Log"); */
//            }
//        });
//
//        mWebView.setWebChromeClient(new WebChromeClient() {
//
//            @Override
//            public boolean onJsConfirm(WebView arg0, String arg1, String arg2,
//                                       JsResult arg3) {
//                return super.onJsConfirm(arg0, arg1, arg2, arg3);
//            }
//
//            View myVideoView;
//            View myNormalView;
//            IX5WebChromeClient.CustomViewCallback callback;
//            /**
//             * 全屏播放配置
//             */
//            @Override
//            public void onShowCustomView(View view,
//                                         IX5WebChromeClient.CustomViewCallback customViewCallback) {
//                FrameLayout normalView = (FrameLayout) findViewById(R.id.web_filechooser);
//                ViewGroup viewGroup = (ViewGroup) normalView.getParent();
//                viewGroup.removeView(normalView);
//                viewGroup.addView(view);
//                myVideoView = view;
//                myNormalView = normalView;
//                callback = customViewCallback;
//            }
//
//            @Override
//            public void onHideCustomView() {
//                if (callback != null) {
//                    callback.onCustomViewHidden();
//                    callback = null;
//                }
//                if (myVideoView != null) {
//                    ViewGroup viewGroup = (ViewGroup) myVideoView.getParent();
//                    viewGroup.removeView(myVideoView);
//                    viewGroup.addView(myNormalView);
//                }
//            }
//
//            @Override
//            public boolean onJsAlert(WebView arg0, String arg1, String arg2,
//                                     JsResult arg3) {
//                /**
//                 * 这里写入你自定义的window alert
//                 */
//                return super.onJsAlert(null, arg1, arg2, arg3);
//            }
//        });
//
//        mWebView.setDownloadListener(new DownloadListener() {
//
//            @Override
//            public void onDownloadStart(String arg0, String arg1, String arg2,
//                                        String arg3, long arg4) {
//                TbsLog.d(TAG, "url: " + arg0);
//                new AlertDialog.Builder(WebActivity.this)
//                        .setTitle("allow to download？")
//                        .setPositiveButton("yes",
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog,
//                                                        int which) {
//                                        Toast.makeText(
//                                                WebActivity.this,
//                                                "fake message: i'll download...",
//                                                1000).show();
//                                    }
//                                })
//                        .setNegativeButton("no",
//                                new DialogInterface.OnClickListener() {
//
//                                    @Override
//                                    public void onClick(DialogInterface dialog,
//                                                        int which) {
//                                        // TODO Auto-generated method stub
//                                        Toast.makeText(
//                                                WebActivity.this,
//                                                "fake message: refuse download...",
//                                                Toast.LENGTH_SHORT).show();
//                                    }
//                                })
//                        .setOnCancelListener(
//                                new DialogInterface.OnCancelListener() {
//
//                                    @Override
//                                    public void onCancel(DialogInterface dialog) {
//                                        // TODO Auto-generated method stub
//                                        Toast.makeText(
//                                                WebActivity.this,
//                                                "fake message: refuse download...",
//                                                Toast.LENGTH_SHORT).show();
//                                    }
//                                }).show();
//            }
//        });
//
//        WebSettings webSetting = mWebView.getSettings();
//        webSetting.setAllowFileAccess(true);
//        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        webSetting.setSupportZoom(true);
//        webSetting.setBuiltInZoomControls(true);
//        webSetting.setUseWideViewPort(true);
//        webSetting.setSupportMultipleWindows(false);
//         webSetting.setLoadWithOverviewMode(true);
//        webSetting.setAppCacheEnabled(true);
//        // webSetting.setDatabaseEnabled(true);
//        webSetting.setDomStorageEnabled(true);
//        webSetting.setJavaScriptEnabled(true);
//        webSetting.setGeolocationEnabled(true);
//        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
//        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
//        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
//        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
//                .getPath());
//        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
//        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
//        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
//        // webSetting.setPreFectch(true);
//        long time = System.currentTimeMillis();
//        if (mIntentUrl == null) {
//            mWebView.loadUrl(mHomeUrl);
//        } else {
//            mWebView.loadUrl(mIntentUrl.toString());
//        }
//        TbsLog.d("time-cost", "cost time: "
//                + (System.currentTimeMillis() - time));
//        CookieSyncManager.createInstance(this);
//        CookieSyncManager.getInstance().sync();
//    }
//
//
//    private void initProgressBar() {
//
//        progressBar.setMax(100);
//        progressBar.setProgressDrawable(this.getResources()
//                .getDrawable(R.drawable.color_progressbar));
//    }
//
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (mWebView != null && mWebView.canGoBack()) {
//                mWebView.goBack();
//
//                return true;
//            } else
//                return super.onKeyDown(keyCode, event);
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        TbsLog.d(TAG, "onActivityResult, requestCode:" + requestCode
//                + ",resultCode:" + resultCode);
//
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case 0:
//                    if (null != uploadFile) {
//                        Uri result = data == null || resultCode != RESULT_OK ? null
//                                : data.getData();
//                        uploadFile.onReceiveValue(result);
//                        uploadFile = null;
//                    }
//                    break;
//                default:
//                    break;
//            }
//        } else if (resultCode == RESULT_CANCELED) {
//            if (null != uploadFile) {
//                uploadFile.onReceiveValue(null);
//                uploadFile = null;
//            }
//
//        }
//
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        if (intent == null || mWebView == null || intent.getData() == null)
//            return;
//        mWebView.loadUrl(intent.getData().toString());
//    }
//
//    @Override
//    protected void onDestroy() {
//        if (mTestHandler != null)
//            mTestHandler.removeCallbacksAndMessages(null);
//        if (mWebView != null)
//            mWebView.destroy();
//        super.onDestroy();
//    }

}
