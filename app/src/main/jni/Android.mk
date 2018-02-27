
LOCAL_PATH := $(call my-dir)


include $(CLEAR_VARS)
LOCAL_CPP_EXTENSION := cpp
LOCAL_MODULE    := hello-test
LOCAL_SRC_FILES := helloTest.cpp
include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_LDLIBS += -llog
 #  jnigraphics库是 android-8 新增的一个库，提供对Java中的 bitmap 对象的操作。
LOCAL_LDLIBS += -ljnigraphics
LOCAL_CPP_EXTENSION := cpp
LOCAL_MODULE    := hello-basis
LOCAL_SRC_FILES := helloBasisFun.cpp
include $(BUILD_SHARED_LIBRARY)


# 两组 就可以生成 两个
include $(CLEAR_VARS)
LOCAL_CPP_EXTENSION := cpp
#  加载的时候需要 链接 的 log 库 哪个so 文件里面使用到了，就得放在那个so的include 里面
LOCAL_LDLIBS += -llog
#  添加 GLes2 的库
LOCAL_LDLIBS += -lGLESv2
LOCAL_MODULE    := hello-gl2
LOCAL_SRC_FILES := helloGl2.cpp
include $(BUILD_SHARED_LIBRARY)

# openCV 的 so 配置文件
include $(CLEAR_VARS)
LOCAL_LDLIBS += -llog   # log 库
LOCAL_CPP_EXTENSION := cpp
LOCAL_MODULE    := hello-openCV
LOCAL_SRC_FILES := helloOpenCV.cpp
include $(BUILD_SHARED_LIBRARY)