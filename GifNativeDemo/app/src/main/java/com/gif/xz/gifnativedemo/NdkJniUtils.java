package com.gif.xz.gifnativedemo;

/**
 * author: zhulunjun
 * time:   2017/5/25
 * about: jni方法定义，工具类
 */

public class NdkJniUtils  {
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
