# EDF parser for Kotlin
Small and simple library to work with EDF files written in Kotlin

### Examples
You can pass file
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