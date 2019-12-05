package com.lianzhong.game.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.lianzhong.game.MainActivity;
import com.lianzhong.game.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class StartUpActivity extends AppCompatActivity implements Observer<Long> {

    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    @BindView(R.id.tv_time)
    TextView tvTime;


    private long mTotalTime = 3;

    private Disposable mSubscribe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.icon_splash).into(ivSplash);
        startCountDown();
        initView();
    }

    private void initView() {
        tvTime.setOnClickListener(v -> {
            startActivity(new Intent(StartUpActivity.this, MainActivity.class));
            finish();
        });
    }

    private void startCountDown() {
        Observable.interval(1, TimeUnit.SECONDS)
                .map(aLong -> mTotalTime - aLong)
                .take(mTotalTime + 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    /**
     * 关闭倒计时
     */
    private void stopCountDown() {
        if (mSubscribe != null && !mSubscribe.isDisposed()) {
            mSubscribe.dispose();
            mSubscribe = null;
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        mSubscribe = d;
    }

    @Override
    public void onNext(Long aLong) {
        tvTime.setText(aLong + "s欢迎页，点击跳过");
    }

    @Override
    public void onError(Throwable e) {
        finish();
    }

    @Override
    public void onComplete() {
        startActivity(new Intent(StartUpActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopCountDown();
    }
}
