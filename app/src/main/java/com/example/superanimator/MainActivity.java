package com.example.superanimator;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.superanimtorlib.LinearInterpolator;
import com.example.superanimtorlib.MyObjectAnimator;

public class MainActivity extends AppCompatActivity {

  private Button button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    button = findViewById(R.id.button);
//    ObjectAnimator animator = ObjectAnimator.ofFloat(button, "ScaleX", 1f, 2f, 1f, 3f);
//    animator.setInterpolator(new LinearInterpolator());
//    animator.setDuration(3000);
//    animator.start();
//    animator.addListener(new Animator.AnimatorListener() {
//      @Override
//      public void onAnimationStart(Animator animator) {
//
//      }
//
//      @Override
//      public void onAnimationEnd(Animator animator) {
//        animator.start();
//      }
//
//      @Override
//      public void onAnimationCancel(Animator animator) {
//
//      }
//
//      @Override
//      public void onAnimationRepeat(Animator animator) {
//
//      }
//    });
    MyObjectAnimator animator = MyObjectAnimator.ofFloat(button, "ScaleX", 1f, 2f, 1f, 3f, 1f);
    animator.setInterpolator(new LinearInterpolator());
    animator.setDuration(3000);
    animator.start();
  }
}
