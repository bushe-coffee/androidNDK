

#include <string.h>
#include <jni.h>

extern "C" {

    JNIEXPORT jstring JNICALL Java_com_dressplus_androidndk_ndkUtils_HelloTest_helloJni(JNIEnv* env, jclass zclass);

};

jstring Java_com_dressplus_androidndk_ndkUtils_HelloTest_helloJni(JNIEnv* env, jclass zclass)
{
    return env->NewStringUTF("Hello from JNI !  Compiled with ABI .");
}