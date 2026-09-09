plugins {
    id("java")
}

group = "pll.desktop.hyprtasks"
version = "0.1.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.java-gi:gtk:1.0.0-RC3")
}

tasks.test {
    useJUnitPlatform()
}