package com.example.superanimtorlib;

import android.view.View;

import java.lang.ref.WeakReference;

public class MyObjectAnimator implements VSYNCManager.AnimationFrameCallBack {

  private static final String LOG_TAG = "MyObjectAnimator";
  long mStartTime = -1;
  private long mDuration = 0;
  private WeakReference<View> mTarget;

  //属性管理器
  MyPropertyValuesHolder myPropertyValuesHolder;
  private float index = 0;
  private TimeInterpolator interpolator;

  public MyObjectAnimator(View target, String propertyName, float... values) {
    mTarget = new WeakReference<>(target);
    myPropertyValuesHolder = new MyPropertyValuesHolder(propertyName, values);
  }

  public void setDuration(long mDuration) {
    this.mDuration = mDuration;
  }

  public void setInterpolator(TimeInterpolator interpolator) {
    this.interpolator = interpolator;
  }

  public static MyObjectAnimator ofFloat(View target, String propertyName, float... values) {
    MyObjectAnimator animator = new MyObjectAnimator(target, propertyName, values);
    return animator;
  }

  //每隔16ms调用一次
  @Override
  public boolean doAnimationFrame(long currentTime) {
    //当前百分比
    float total = mDuration / 16;
    //动画执行百分比(index++)/total
    float fraction = (index++) / total;
    if (interpolator != null) {
      fraction = interpolator.getInterpolator(fraction);
    }
    if (index >= total) {
      index = 0;
    }
    myPropertyValuesHolder.setAnimatorValue(mTarget.get(), fraction);
    return false;
  }

  public void start() {
    myPropertyValuesHolder.setupSetter();
    mStartTime = System.currentTimeMillis();
    VSYNCManager.getInstance().add(this);
  }

}
