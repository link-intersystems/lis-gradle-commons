# Lis Gradle Project Builder

## Create a project structure

```java
java.nio.file.Path somePath = Path.of("<ENTER_PATH>");

GradleProjectBuilder projectBuilder = GradleProjectBuilder.rootProject(somePath);
GradleSubprojectBuilder subProjectA = projecBuilder.createSubproject("subProjectA");
GradleSubprojectBuilder subProjectB = projecBuilder.createSubproject("subProjectB");
GradleProjectBuilder compositeBuildA = projectBuilder.createCompositeBuild("compositeBuildA");
```
The code above will create this Gradle project structure:

```text
somePath/
├─ subProjectA/
│  ├─ build.gradle.kts
├─ subProjectB/
│  ├─ build.gradle.kts
├─ compositeBuildA/
│  ├─ build.gradle.kts
│  ├─ settings.gradle.kts
├─ build.gradle.kts
├─ settings.gradle.kts
```

### Write Gradle file content

Once you created a GradleProjectBuilder you can write 
content to the build and settings files using the convenience methods.

```java
FileContent fileBuilder = projectBuilder.buildFile();
fileBuilder.append(printWriter -> {
        printWriter.println("plugins {");
        printWriter.println("    `java-library`");
        printWriter.println("}");
});
```

The code above will append the content that is generated within the lambda to the build file.
Since the build file is usually empty, the build file will contain the following content after 
the method finished.

```kotlin
// build.gradle.kts
plugins {
    `java-library`
}
```

There are also methods for the settings file and gradle properties.