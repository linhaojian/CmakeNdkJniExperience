#include <jni.h>
#include <math.h>

JNIEXPORT jint JNICALL
Java_com_lhj_ndkjniactivity_CTest_ctest(JNIEnv *env, jobject instance, jint a, jint b) {
    double sqd = 0;
    for (int i = 0; i < 1000; i++) {
        sqd += sqrt((double) (a * b));
    }
    return (int) sqd;
}

JNIEXPORT void JNICALL
Java_com_lhj_ndkjniactivity_CTest_CInvokingJava(
        JNIEnv *env,
        jobject instance) {
    jclass jclazz = (*env)->FindClass(env, "com/lhj/ndkjniactivity/CTest");
    jmethodID methodID = (*env)->GetMethodID(env, jclazz, "ctestInvokingJava",
                                             "(Ljava/lang/String;)V");
    jstring re=(*env)->NewStringUTF(env,"c invoking java");
    (*env)->CallVoidMethod(env,instance,methodID,re);
}