package com.example.flappybird;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public FlappyBird flappyBird;

    public void refresh() {
        flappyBird.gameloop();
    }

    private Timer timer;
    private TimerTask timerTask;
    private Handler handler = new Handler();

    //To stop timer
    private void stopTimer(){
        if(timer != null){
            timer.cancel();
            timer.purge();
        }
    }

    //To start timer
    private void startTimer(){
        timer = new Timer();
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run(){
                        refresh();
                    }
                });
            }
        };
        timer.schedule(timerTask, 30, 30);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flappyBird = new FlappyBird(this);
        flappyBird.setBackgroundColor(Color.WHITE);
        setContentView(flappyBird);

        int delaytime = 10000;

        startTimer();

        System.out.println("exit");
    }


}
