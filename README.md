## Cloud Firestore Testing Package 
A testing package of cloud-firestore.

### Adding as dependency

In Gradle:
```groovy

repositories {
  mavenCentral()
}

dependencies {
     compile 'com.clouway.testing:cloud-firestore-testing:0.0.9'
}
```

In Maven:

```xml

 <dependency>
    <groupId>com.clouway.testing</groupId>
    <artifactId>cloud-firestore-testing</artifactId>
    <version>0.0.9</version>
 </dependency>

```

### Usage
 * Unit Testing with JUnit 4.x
```kotlin
  @Rule
  @JvmField
  val context = JUnitRuleMockery()

  companion object {
    @ClassRule
    @JvmField
    val firestoreRule = FirestoreRule()
  }

  @Rule
  @JvmField
  var cleaner = FirestoreCleaner(firestoreRule.firestore)

  // ....
   
  @Test
  fun happyPath() {
    val firestore = firestoreRule.firestore
    // do firestore persistent logic
  }
   
```

 * Jenkins CI Usage   
 As cloud-firestore-testing library is using docker for running of the emulator it is required the following environment
 variables to be passed from the CI:
  
 ```bash
   export FIRESTORE_HOST=xxxx
   export FIRESTORE_PORT=5000
 ```  
 
 Here is a complete Jenkins example:
 ```groovy  
 docker.image('clouway/firestore-emulator:alpine') { c ->
    docker.image('yourimage').inside("-e CI=true -e FIRESTORE_HOST=datastore -e FIRESTORE_PORT=8080 --link ${c.id}:datastore") {
        // perform your test execution here  
    }
 }
```
 
 

### Contributing
If you would like to contribute code to cloud-firestore-testing you can do so through GitHub by forking the repository and sending
a pull request. When submitting code, please make every effort to follow existing conventions and style in order to
keep the code as readable as possible. Please also make sure your code compiles by running gradle clean build.
