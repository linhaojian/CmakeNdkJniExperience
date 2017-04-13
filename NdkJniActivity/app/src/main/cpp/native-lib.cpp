#include <jni.h>
#include <string>

extern "C"
{
JNIEXPORT jstring JNICALL
    Java_com_lhj_ndkjniactivity_MainActivity_stringFromJNI(
            JNIEnv *env,
            jobject /* this */) {
        std::string hello = "Hello from C++";
        return env->NewStringUTF(hello.c_str());
    }


    void
    Java_com_lhj_ndkjniactivity_MainActivity_testJNI(
            JNIEnv *env,
            jobject instance) {
        jclass  myClazz = (env)->FindClass("com/lhj/ndkjniactivity/MainActivity");
        jmethodID myMedthod = (env)->GetMethodID(myClazz,"cppInvokingJava","()V");
        (env)->CallVoidMethod(instance,myMedthod);
    }


}