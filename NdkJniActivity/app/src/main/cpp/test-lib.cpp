#include <jni.h>
#include <string>

extern "C"
{
JNIEXPORT jstring JNICALL
Java_com_lhj_ndkjniactivity_NdkJniTest_testString(JNIEnv *env, jobject instance) {
    std::string hello = "test-lib";
    return env->NewStringUTF(hello.c_str());
}
}