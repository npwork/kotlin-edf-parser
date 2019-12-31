# Kotlin EDF parser 
Kotlin library to work with EDF files 
[![Build Status](https://travis-ci.org/npwork/kotlin-edf-parser.svg?branch=master)](https://travis-ci.org/npwork/kotlin-edf-parser)
[![Apache License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

----
EDF format description - https://www.teuniz.net/edfbrowser/edf%20format%20description.html

EDF viewer - https://www.teuniz.net/edfbrowser/


### What does it look like? (Code snippets)
```kotlin
data class EdfFile(
        val header: EdfHeader,          // Header record
        val signal: EdfSignal           // Data record
) {
    val samples: List<Int> = ...        // Amount of samples        
    val sampleRate: List<Double> = ...  // Sample rate (second) 
}
```
### How to use? (Code snippets)
You can work with File 
```kotlin
val file = File("/example.edf")
val edfFile: EdfFile = EdfParser.parse(file)
```

Or stream 
```kotlin
class EdfParserExample
val stream: InputStream = EdfParserExample::class.java.getResourceAsStream("/example.edf")
val edfFile: EdfFile = EdfParser.parse(stream)
```


### Add to your project
#### Step 1 - Add jitpack repository (https://jitpack.io/)
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
#### Step 2 - Add dependency
#### Maven
```xml
<dependency>
    <groupId>com.github.npwork</groupId>
    <artifactId>kotlin-edf-parser</artifactId>
    <version>0.1.1</version>
</dependency>
```

#### Gradle
```groovy
implementation 'com.github.npwork:kotlin-edf-parser:0.1.1'
```