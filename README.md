# android-viewbadger
Fork of [android-viewbadger][1] updated to use the Gradle build system for Android Studio support and setup on [JitPack][2] for ease of inclusion in your projects.

# Gradle
Add the jitpack repo to your your project's build.gradle at the end of repositories

/build.gradle
```groovy
allprojects {
	repositories {
		jcenter()
		maven { url "https://jitpack.io" }
	}
}
```

Then add the dependency to your module's build.gradle:

/app/build.gradle
```groovy
compile 'com.github.deano2390:android-viewbadger:1.0.0'
```

NOTE: Some people have mentioned that they needed to add the @aar suffix to get it to resolve from JitPack:
```groovy
compile 'com.github.deano2390:android-viewbadger:1.0.0@aar'
```

[1]: https://github.com/jgilfelt/android-viewbadger
[2]: https://jitpack.io/#deano2390/android-viewbadger
