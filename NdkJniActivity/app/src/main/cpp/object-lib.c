#include <jni.h>

//返回一个复杂对象
JNIEXPORT jobject JNICALL
Java_com_lhj_ndkjniactivity_StudentJni_nativeGetStudentInfo(
        JNIEnv *env,
        jobject instance) {
    //寻找Student类
    jclass stucls = (*env)->FindClass(env,"com/lhj/ndkjniactivity/Student");
    //获取Student类的构造函数  或者  方法
    jmethodID constrocMID = (*env)->GetMethodID(env, stucls, "<init>","(ILjava/lang/String;)V");
    //创建一个String 字符窜
    jstring male = (*env)->NewStringUTF(env,"male");
    //根据类、构造函数、还有构造函数的参数进行初始化对象
    jobject stu_ojb = (*env)->NewObject(env,stucls,constrocMID,27,male);
    //获取类里的“sex”属性
    jfieldID  malefield = (*env)->GetFieldID(env,stucls,"sex","Ljava/lang/String;");
    //创建一个String 字符窜
    jstring female = (*env)->NewStringUTF(env,"female");
    //设置字段属性值
    (*env)->SetObjectField(env,stu_ojb ,malefield , female); // 设置该字段的值
    return stu_ojb;
}
