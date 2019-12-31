# Kotlin EDF parser
Small and simple library written in Kotlin to work with EDF files

### What does it look like? (Code snippets)
You can work with File 
```kotlin
val file = File("/example.edf")
val edfFile = EdfParser.parse(file)
```

Or stream 
```kotlin
class EdfParserExample
val stream: InputStream = EdfParserExample::class.java.getResourceAsStream("/example.edf")
val edfFile = EdfParser.parse(stream)
```


## Add to your project
#### First add jitpack repository (https://jitpack.io/)
##### Maven

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

##### Gradle
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

```
#### Then add dependency
#### Maven
```xml
<dependency>
    <groupId>com.github.npwork</groupId>
    <artifactId>kotlin-edf-parser</artifactId>
    <version>0.1</version>
</dependency>
```

#### Gradle
```groovy
implementation 'com.github.npwork:kotlin-edf-parser:0.1'
```