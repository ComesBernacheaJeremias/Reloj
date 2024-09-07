// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false

    val room_version = "2.6.1"
    id("androidx.room") version "$room_version" apply false
    id ("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false

    //Dagger Hilt
    id("com.google.dagger.hilt.android") version "2.44" apply false

}