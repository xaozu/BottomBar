#include <jni.h>
#include <string>
#include "com_gif_xz_gifnativedemo_NdkJniUtils.h"
#include <android/log.h>

#define  LOG_TAG    "【C_LOG】"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
extern "C"
JNIEXPORT jstring JNICALL
Java_com_gif_xz_gifnativedemo_NdkJniUtils_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    //打log
    LOGI("我打印了一个log");
    return env->NewStringUTF(hello.c_str());
}
