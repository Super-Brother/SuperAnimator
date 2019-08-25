package com.example.superanimtorlib;

import java.util.ArrayList;
import java.util.List;

public class VSYNCManager {

  private static final VSYNCManager purInstance = new VSYNCManager();

  private List<AnimationFrameCallBack> list = new ArrayList<>();

  public static VSYNCManager getInstance() {
    return purInstance;
  }

  private VSYNCManager() {
    new Thread(runnable).start();
  }

  Runnable runnable = new Runnable() {
    @Override
    public void run() {
      while (true) {//循环执行
        try {
          Thread.sleep(16);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        for (AnimationFrameCallBack animationFrameCallBack : list) {
          animationFrameCallBack.doAnimationFrame(System.currentTimeMillis());
        }
      }
    }
  };

  interface AnimationFrameCallBack {

    boolean doAnimationFrame(long currentTime);
  }

  public void add(AnimationFrameCallBack animationFrameCallBack) {
    list.add(animationFrameCallBack);
  }
}
