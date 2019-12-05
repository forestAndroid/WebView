package com.lianzhong.game.utils;

import android.view.KeyEvent;
import android.webkit.WebView;

import com.just.agentweb.EventHandlerImpl;
import com.just.agentweb.EventInterceptor;
import com.just.agentweb.IEventHandler;

public class MyEventHandlerImpl implements IEventHandler {
    private WebView mWebView;
    private EventInterceptor mEventInterceptor;

    public static final EventHandlerImpl getInstantce(WebView view, EventInterceptor eventInterceptor) {
        return new EventHandlerImpl(view, eventInterceptor);
    }

    public MyEventHandlerImpl(WebView webView, EventInterceptor eventInterceptor) {
        this.mWebView = webView;
        this.mEventInterceptor = eventInterceptor;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return back();
        }
        return false;
    }

    @Override
    public boolean back() {
        if (this.mEventInterceptor != null && this.mEventInterceptor.event()) {
            return true;
        }
        if (mWebView != null && mWebView.canGoBack()) {
            String url = mWebView.getUrl().toString();
            mWebView.goBack();
            // 如果遇到迷之无法返回上一页
            if (mWebView.getUrl().toString().equals(url)) {
                mWebView.goBack();
            }
            return true;
        }
        return false;
    }
}
