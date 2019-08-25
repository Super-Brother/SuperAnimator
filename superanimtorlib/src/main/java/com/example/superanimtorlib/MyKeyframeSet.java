package com.example.superanimtorlib;

import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;

import java.util.Arrays;
import java.util.List;

/**
 * 关键帧管理类
 *
 * @author zhang
 */
public class MyKeyframeSet {

  //存储关键帧
  List<MyFloatKeyFrame> myKeyFrames;
  //类型估值器
  TypeEvaluator mEvaluator;

  public MyKeyframeSet(MyFloatKeyFrame... keyFrames) {
    myKeyFrames = Arrays.asList(keyFrames);
    mEvaluator = new FloatEvaluator();
  }

  public static MyKeyframeSet ofFloat(float... values) {
    //关键帧初始化
    if (values.length < 0) {
      return null;
    }
    int numKeyframes = values.length;
    //关键帧初始化 for
    MyFloatKeyFrame keyFrames[] = new MyFloatKeyFrame[numKeyframes];
    keyFrames[0] = new MyFloatKeyFrame(0, values[0]);
    for (int i = 1; i < numKeyframes; i++) {
      //计算
      keyFrames[i] = new MyFloatKeyFrame(i / (numKeyframes - 1), values[i]);
    }
    return new MyKeyframeSet(keyFrames);
  }

  public Object getValue(float fraction) {
    MyFloatKeyFrame prevKeyframe = myKeyFrames.get(0);
    for (int i = 0; i < myKeyFrames.size(); i++) {
      MyFloatKeyFrame nextKeyframe = myKeyFrames.get(i);
      if (fraction < nextKeyframe.getFraction()) {
        //间隔百分比
        float intervalFraction = (fraction - prevKeyframe.getFraction()) / (nextKeyframe.getFraction() - prevKeyframe.getFraction());
        return mEvaluator == null ? prevKeyframe.getValue() + intervalFraction * (nextKeyframe.getValue() - prevKeyframe.getValue())
                : mEvaluator.evaluate(intervalFraction, prevKeyframe.getValue(), nextKeyframe.getValue());
      }
      prevKeyframe = nextKeyframe;
    }
    //完成
    return myKeyFrames.get(myKeyFrames.size() - 1).getValue();
  }

}
