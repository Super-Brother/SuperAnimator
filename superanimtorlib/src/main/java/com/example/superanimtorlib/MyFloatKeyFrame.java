package com.example.superanimtorlib;

/**
 * 关键帧
 */
public class MyFloatKeyFrame {

  float mFraction;//当前动画百分比
  Class mValueType;//float
  float mValue;//具体值

  public MyFloatKeyFrame(float mFraction, float mValue) {
    this.mFraction = mFraction;
    this.mValue = mValue;
    mValueType = float.class;
  }

  public float getFraction() {
    return mFraction;
  }

  public void setFraction(float mFraction) {
    this.mFraction = mFraction;
  }

  public Class getValueType() {
    return mValueType;
  }

  public void setValueType(Class mValueType) {
    this.mValueType = mValueType;
  }

  public float getValue() {
    return mValue;
  }

  public void setValue(float mValue) {
    this.mValue = mValue;
  }

}
