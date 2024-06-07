implugins {
    kotlin("jvm") version "1.9.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


repositories {
    mavenCentral()
    // other repositories
}

dependencies {
    implementation(kotlin("stdlib", "1.6.10"))
    implementation("com.google.code.gson:gson:2.8.9")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.6.10")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    // other dependencies
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}