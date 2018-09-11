#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_ir_prime_utils_Apps_getSaltString(
        JNIEnv *env,
        jobject /* this */) {
    std::string salt = "vasl-jikopick-1";
    return env->NewStringUTF(salt.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_ir_prime_utils_Apps_getAppIDString(
        JNIEnv *env,
        jobject /* this */) {
    std::string appid= "27687eb5-6853-11e6-b325-005056a96c03";
    return env->NewStringUTF(appid.c_str());
}
