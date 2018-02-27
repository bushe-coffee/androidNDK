
#include <string.h>
#include <jni.h>

#include <stdio.h>
#include <sys/time.h>
#include <stdlib.h>

#include <GLES2/gl2.h>

#include <android/log.h>
#define  LOG_TAG    "yangGL2"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)

extern "C" {

    JNIEXPORT jstring JNICALL Java_com_dressplus_androidndk_ndkUtils_HelloGl2_helloJni(JNIEnv* env, jclass zclass);
    JNIEXPORT void JNICALL Java_com_dressplus_androidndk_ndkUtils_HelloGl2_drawFrame(JNIEnv* env, jclass zclass);
    JNIEXPORT void JNICALL Java_com_dressplus_androidndk_ndkUtils_HelloGl2_initData(JNIEnv* env, jclass zclass, jint width, jint height);

};

GLuint gProgram;
GLuint gvPositionHandle;

// 获得当前 毫秒级别的时间
long getCurrentTime()
{
   struct timeval tv;
   gettimeofday(&tv,NULL);
   return tv.tv_sec * 1000 + tv.tv_usec / 1000;
}

GLuint loadShader(GLenum shaderType, const char* pSource) {
    GLuint shader = glCreateShader(shaderType);
    if (shader) {
        glShaderSource(shader, 1, &pSource, NULL);
        glCompileShader(shader);
        GLint compiled = 0;
        glGetShaderiv(shader, GL_COMPILE_STATUS, &compiled);
        if (!compiled) {
            GLint infoLen = 0;
            glGetShaderiv(shader, GL_INFO_LOG_LENGTH, &infoLen);
            if (infoLen) {
                char* buf = (char*) malloc(infoLen);
                if (buf) {
                    glGetShaderInfoLog(shader, infoLen, NULL, buf);
                    LOGE("Could not compile shader %d:\n%s\n",
                            shaderType, buf);
                    free(buf);
                }
                glDeleteShader(shader);
                shader = 0;
            }
        }
    }
    return shader;
}

GLuint createProgram(const char* pVertexSource, const char* pFragmentSource) {
    GLuint vertexShader = loadShader(GL_VERTEX_SHADER, pVertexSource);
    if (!vertexShader) {
        return 0;
    }

    GLuint pixelShader = loadShader(GL_FRAGMENT_SHADER, pFragmentSource);
    if (!pixelShader) {
        return 0;
    }

    //创建一个程序容器  返回一个正整数作为该着色器程序的id
    GLuint program = glCreateProgram();
    if (program) {
        // 向 程序(program 作为id 指向着色器程序) 中加入 顶点着色器
        glAttachShader(program, vertexShader);
        // 向程序中加入片元着色器
        glAttachShader(program, pixelShader);
        // 链接程序  在链接操作执行以后，可以任意修改shader的源代码，对shader重新编译不会影响整个程序，除非重新链接程序
        glLinkProgram(program);

        GLint linkStatus = GL_FALSE;
        glGetProgramiv(program, GL_LINK_STATUS, &linkStatus);
        if (linkStatus != GL_TRUE) {
            GLint bufLength = 0;
            glGetProgramiv(program, GL_INFO_LOG_LENGTH, &bufLength);
            if (bufLength) {
                char* buf = (char*) malloc(bufLength);
                if (buf) {
                    glGetProgramInfoLog(program, bufLength, NULL, buf);

                    free(buf);
                }
            }
            glDeleteProgram(program);
            program = 0;
        }
    }

    return program;
}

// 定点着色器
char gVertexShader[] =
    "attribute vec4 vPosition;\n"
    "void main() {\n"
    "  gl_Position = vPosition;\n"
    "}\n";

// 片源着色器
char gFragmentShader[] =
    "precision mediump float;\n"
    "void main() {\n"
    "  gl_FragColor = vec4(0.0, 1.0, 0.0, 1.0);\n"
    "}\n";

// 绘制的 图形的 点
const GLfloat gTriangleVertices[] = { 0.0f, 0.5f, -0.5f, -0.5f,
            0.5f, -0.5f };

jstring Java_com_dressplus_androidndk_ndkUtils_HelloGl2_helloJni(JNIEnv* env, jclass zclass)
{
    LOGE("yangxinyu show text....");
    return env->NewStringUTF("Hello from JNI !  Compiled with ABI .");
}

void JNICALL Java_com_dressplus_androidndk_ndkUtils_HelloGl2_drawFrame(JNIEnv* env, jclass zclass)
{
    LOGE("yangxinyu show drawFrame time %d", getCurrentTime());
    static float grey;
    grey += 0.01f;
    if (grey > 1.0f) {
        grey = 0.0f;
    }

    glClearColor(0, 0, 0, 1.0f);
    glClear( GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);

    glUseProgram(gProgram);
    // glVertexAttribPointer用于传递顶点着色器的位置信息。
    glVertexAttribPointer(gvPositionHandle, 2, GL_FLOAT, GL_FALSE, 0, gTriangleVertices);

    glEnableVertexAttribArray(gvPositionHandle);
    // 绘制
    glDrawArrays(GL_TRIANGLES, 0, 3);

}

void JNICALL Java_com_dressplus_androidndk_ndkUtils_HelloGl2_initData(JNIEnv* env, jclass zclass, jint width, jint height)
{
    LOGE("yangxinyu show initData .... %d  %d  %d %d",GL_VERSION,GL_VENDOR,GL_RENDERER,GL_EXTENSIONS);

    LOGI("setupGraphics(%d, %d)", width, height);
    // 创建一个 着色器程序 的 容器  Program可以理解为一个跑在GPU上的小程序。
    gProgram = createProgram(gVertexShader, gFragmentShader);
    if (!gProgram) {
        LOGE("Could not create program.");
        return ;
    }

    gvPositionHandle = glGetAttribLocation(gProgram, "vPosition");

    LOGI("glGetAttribLocation(\"vPosition\") = %d\n",
            gvPositionHandle);

    glViewport(0, 0, width, height);

}
