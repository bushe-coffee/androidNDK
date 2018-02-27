#include <jni.h>
#include <Android/bitmap.h>

#include <android/log.h>
#define  LOG_TAG    "yangGL2"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)

extern "C" {
    JNIEXPORT jint JNICALL
        Java_com_dressplus_androidndk_ndkUtils_HelloBasisFun_handleArray(JNIEnv* env, jclass zclass,jintArray data);
}

jint Java_com_dressplus_androidndk_ndkUtils_HelloBasisFun_handleArray(JNIEnv* env, jclass zclass,jintArray arrayDatas)
{
    //获得Java传递进来数组的长度
    jsize length = env->GetArrayLength(arrayDatas);
    LOGI("array datas length  %d ", length);
    //定义一个C数组
    jint array[length];
    jint sum = 0;
    //将Java数组区复制到C数组中
    env->GetIntArrayRegion(arrayDatas, 0, length, array);
    // 求和
    for(int i=0;i<length;i++){
            sum+=array[i];
    }
    LOGI("array sum length  %d ", sum);

    return sum;
}

