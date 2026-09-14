plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
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
    implementation("com.h2database:h2:1.4.197")
}

javafx {
    version = "25.0.4"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("pll.desktop.hyprtasks.MainView")
}

tasks.test {
    useJUnitPlatform()
}