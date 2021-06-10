// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("kotlinVersion", "1.5.10")
        set("hiltVersion", "2.35.1")
    }
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${rootProject.extra.get("kotlinVersion")}")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:${rootProject.extra.get("hiltVersion")}")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}


tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}
