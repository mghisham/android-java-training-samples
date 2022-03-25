package com.example.app_06_async_task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mBtnStart;
    private Button mBtnEnd;
    private TextView mTxtBanner;
    private MovingBannerTask movingBannerTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setClickListner();
    }

    private void initView() {
        mBtnStart = findViewById(R.id.btnStart);
        mBtnEnd = findViewById(R.id.btnEnd);
        mTxtBanner = findViewById(R.id.txtBanner);
    }

    private void init() {
        movingBannerTask = new MovingBannerTask(false);
    }

    private void setClickListner() {
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
                if (null != movingBannerTask) {
                    movingBannerTask.execute();
                    mBtnStart.setClickable(false);
                    mBtnStart.setEnabled(false);
                }
            }
        });

        mBtnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != movingBannerTask) {
                    movingBannerTask.setFinish(true);
                }
                if (movingBannerTask.getStatus() != AsyncTask.Status.FINISHED) {
                    movingBannerTask.cancel(true);
                }
                mBtnStart.setClickable(true);
                mBtnStart.setEnabled(true);
            }
        });
    }

    private class MovingBannerTask extends AsyncTask<Void, Void, Void> {

        private boolean isFinish;

        public MovingBannerTask(boolean finish) {
            isFinish = finish;
        }

        private void setFinish(boolean flag) {
            isFinish = flag;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 1500.0f); // new TranslateAnimation (float fromXDelta,float toXDelta, float fromYDelta, float toYDelta)
            animation.setDuration(1500); // animation duration
            animation.setRepeatCount(4); // animation repeat count
            animation.setRepeatMode(2); // repeat animation (left to right, right to left)

            animation.setFillAfter(true);
            mTxtBanner.startAnimation(animation);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while (!isFinish) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            mTxtBanner.clearAnimation();
        }
    }


}