package com.example.edt34;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 3000;
    private ImageView imageS1;
    private ImageView imageS2;

    private TextView textTitle;
    private TextView textSub;


    private ObjectAnimator toRight;
    private ObjectAnimator toLeft1;
    private ObjectAnimator toLeft2;
    private ObjectAnimator alpha1;

    private ObjectAnimator alpha2;
    private ObjectAnimator alpha3;
    private ObjectAnimator alpha4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageS1 = findViewById(R.id.imageS1);
        imageS2 = findViewById(R.id.imageS2);
        textTitle = findViewById(R.id.textTitle);
        textSub = findViewById(R.id.textSub);

        toRight = ObjectAnimator.ofFloat(imageS1,"translationX", -1000f,0f);
        alpha1 = ObjectAnimator.ofFloat(imageS1,"alpha", 0f,1f);

        toLeft1 = ObjectAnimator.ofFloat(textTitle,"translationX", 1000f,0f);
        toLeft2 = ObjectAnimator.ofFloat(textSub,"translationX", 1000f,0f);

        alpha2 = ObjectAnimator.ofFloat(textTitle,"alpha", 0f,1f);
        alpha3 = ObjectAnimator.ofFloat(textSub,"alpha", 0f,1f);
        alpha4 = ObjectAnimator.ofFloat(imageS2,"alpha", 0f,1f);

        alpha4.setStartDelay(2000);
        alpha4.setDuration(1000);

        alpha1.setDuration(2000);
        alpha2.setDuration(2000);
        alpha3.setDuration(2000);
        toRight.setDuration(2000);
        toLeft1.setDuration(2000);
        toLeft2.setDuration(2000);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(toRight,alpha1,alpha2,alpha3,toLeft1,toLeft2,alpha4);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });

        Runnable r = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                Pair[] pair = new Pair[2];
                pair[0]  = new Pair<View,String>(imageS1,"imageapp");
                pair[1]  = new Pair<View,String>(textTitle,"textapp");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        MainActivity.this,
                        pair
                );
                startActivity(intent,options.toBundle());
                finish();
            }
        };
        Handler h = new Handler(Looper.getMainLooper());
// The Runnable will be executed after the given delay time
        h.postDelayed(r, SPLASH_SCREEN);
        /*
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
                Pair[] pair = new Pair[];
                pair[0] = new Pair<View, String>(imageS1, "image_origin");
                pair[1] = new Pair<View, String>(textTitle, "Text_origin");
                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(pair);
                }
            }
        });

        Handler h= new Handler();
        Runnable r= new Runnable(){
            @Override
            public void run(){
                //TODO
            }
        };*/

    }
}