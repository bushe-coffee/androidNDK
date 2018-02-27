#include <jni.h>


#include <android/log.h>
#define  LOG_TAG    "yangGL2"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)

extern "C" {
    JNIEXPORT jint JNICALL
        Java_com_dressplus_androidndk_ndkUtils_HelloOpenCV_helloOpenCVJni(JNIEnv* env, jclass zclass,jintArray data);
}

jint Java_com_dressplus_androidndk_ndkUtils_HelloOpenCV_helloOpenCVJni(JNIEnv* env, jclass zclass,jintArray data) {


}