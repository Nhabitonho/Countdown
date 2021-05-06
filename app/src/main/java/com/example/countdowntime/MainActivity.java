package com.example.countdowntime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    ImageSwitcher mImageSwitcher;
    Button mBtnStart;
    String[] mArrNameImages = {"hinh1","hinh2","hinh3","hinh4","hinh5"};
    int mCount = 0;
    CountDownTimer mCountDownTimer ;
    boolean mIsRunning;
    @Override   
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageSwitcher = findViewById(R.id.imageSwitcher);
        mBtnStart = findViewById(R.id.buttonStart);

        Animation animationIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation animationOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);

        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                int idImage = getResources().getIdentifier(mArrNameImages[mCount],"drawable",getPackageName());
                imageView.setImageResource(idImage);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }
        });
        mImageSwitcher.setInAnimation(animationIn);
        mImageSwitcher.setOutAnimation(animationOut);

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mIsRunning){
                    mIsRunning = true;
                    mCountDownTimer = new CountDownTimer(1200, 1000) {
                        @Override
                        public void onTick(long l) {
                            if ( l >= 1000){
                                if (mCount >= mArrNameImages.length){
                                    mCount = 0;
                                }
                                int idImage = getResources().getIdentifier(mArrNameImages[mCount],"drawable",getPackageName());
                                mImageSwitcher.setImageResource(idImage);
                                mCount++;
                            }
                        }

                        @Override
                        public void onFinish() {
                            this.start();
                        }
                    };

                    mCountDownTimer.start();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCountDownTimer.cancel();
        mCountDownTimer = null;
    }
}