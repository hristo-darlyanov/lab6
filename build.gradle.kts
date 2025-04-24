plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")

    dependencies {
        implementation("org.slf4j:slf4j-api:2.0.9")
        implementation("ch.qos.logback:logback-classic:1.4.11")
        implementation("jakarta.xml.bind:jakarta.xml.bind-api:3.0.1")
        implementation("org.glassfish.jaxb:jaxb-runtime:3.0.2")
    }
}

tasks.test {
    useJUnitPlatform()
}