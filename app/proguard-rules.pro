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

# Keep Hilt-generated components and injects
-keep class dagger.hilt.** { *; }
-keep class androidx.hilt.** { *; }
-dontwarn dagger.hilt.**
-dontwarn androidx.hilt.**

# Room Database classes
-keep class androidx.room.** { *; }
-dontwarn androidx.room.**

# Retrofit and Gson
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }
-keep class com.google.gson.** { *; }
-keep interface com.google.gson.** { *; }
-keep class retrofit2.converter.gson.GsonConverterFactory { *; }

# Gson (to ensure correct serialization/deserialization)
-keep class com.google.gson.stream.** { *; }
-dontwarn com.google.gson.**
-keep class com.kawler.effmobile.domain.models.** { *; }

# AppIntro
-keep class me.relex.circleindicator.** { *; }
-keep class com.github.paolorotolo.appintro.** { *; }
-dontwarn me.relex.circleindicator.**
-dontwarn com.github.paolorotolo.appintro.**