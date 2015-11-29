-dontwarn com.annotatedsql.**
-dontwarn com.google.common.**
-dontwarn freemarker.core.**
-dontwarn freemarker.ext.**
-dontwarn freemarker.log.**
-dontwarn freemarker.debug.**
-dontwarn freemarker.cache.**
-dontwarn freemarker.template.**
-dontwarn org.apache.log4j.**

# ButterKnife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# Retrofit
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# OkHttp
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**
-dontwarn okio.**

# Retrolambda
-dontwarn java.lang.invoke.*
