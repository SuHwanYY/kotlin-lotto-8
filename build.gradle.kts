plugins {
    kotlin("jvm") version "2.2.0"
}

group = "camp.nextstep.edu"
version = "1.0-SNAPSHOT"

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}

dependencies {
    implementation("com.github.woowacourse-projects:mission-utils:1.2.0")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")

    testImplementation("org.assertj:assertj-core:3.25.3")

    testImplementation("com.github.woowacourse-projects:mission-utils:1.2.0")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
