package com.example.superanimtorlib;

import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 属性管理器
 *
 * @author zhang
 */
public class MyPropertyValuesHolder {

  //属性名
  String mPropertyName;
  Class mValueType;
  //反射
  Method mSetter = null;
  //关键帧管理类
  MyKeyframeSet myKeyframeSet;

  public MyPropertyValuesHolder(String propertyName, float... values) {
    this.mPropertyName = propertyName;
    mValueType = float.class;
    myKeyframeSet = MyKeyframeSet.ofFloat(values);
  }

  public void setupSetter() {
    char firstLetter = Character.toUpperCase(mPropertyName.charAt(0));
    String theRest = mPropertyName.substring(1);
    String methodName = "set" + firstLetter + theRest;
    //反射
    try {
      mSetter = View.class.getMethod(methodName, float.class);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
  }

  public void setAnimatorValue(View view, float fraction) {
    Object value = myKeyframeSet.getValue(fraction);
    //反射方式进行赋值
    try {
      mSetter.invoke(view, value);
    } catch (IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }

}
