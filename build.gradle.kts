// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    repositories{
        google()
        mavenCentral()
    }
    dependencies{
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.8.10")
            classpath("com.google.dagger:hilt-android-gradle-plugin:2.51")
        }
}
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
    id("com.google.dagger.hilt.android") version "2.51" apply false
    id("io.realm.kotlin") version "1.11.0" apply false
}