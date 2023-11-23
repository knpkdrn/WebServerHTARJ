plugins {
    id("java")
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation ("mysql:mysql-connector-java:8.0.29")
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-jdbc")
    implementation ("com.google.maps:google-maps-services:2.1.2")
    implementation ("javax.xml.bind:jaxb-api:2.3.1")
}

tasks.test {
    useJUnitPlatform()
}