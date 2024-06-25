# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

@dependencies {
    def room_version = "2.4.1"

    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"

     optional - RxJava2 support for Room
    implementation "androidx.room:room-rxjava2:$room_version"

     optional - RxJava3 support for Room
    implementation "androidx.room:room-rxjava3:$room_version"

     optional - Guava support for Room, including Optional and ListenableFuture
    implementation "androidx.room:room-guava:$room_version"

     optional - Test helpers
    testImplementation "androidx.room:room-testing:$room_version"

     optional - Paging 3 Integration
    implementation "androidx.room:room-paging:2.4.1"
}