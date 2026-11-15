plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "pll.desktop.hyprtasks"
version = "0.1.2"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("com.oracle.database.jdbc:ojdbc11:21.9.0.0")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("org.slf4j:slf4j-nop:2.0.13")
}

javafx {
    version = "26"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("pll.desktop.hyprtasks.Main")
}

tasks.test {
    useJUnitPlatform()
}