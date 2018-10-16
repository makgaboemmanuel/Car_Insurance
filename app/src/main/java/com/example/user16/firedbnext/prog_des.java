package com.example.user16.firedbnext;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class prog_des extends AppCompatActivity {

   private static  int progress;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prog_des);

        progress = 0;
        progressBar = (ProgressBar) findViewById(R.id.progDesigned);


                   new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (progress < 10)
                            {
                                progressStatus = doSomework();
                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                        }
                        private int doSomework()
                        {
                            try {
                                Thread.sleep(350);
                            }
                            catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }


                            return  ++progress;
                        }
                    }).start();


    }

}
