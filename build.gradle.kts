import org.flywaydb.gradle.FlywayExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("org.flywaydb.flyway") version "6.3.1"
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"

    id("groovy")
    `maven-publish`
}

group = "dev.caspar"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.postgresql:postgresql:42.2.8")
    implementation("org.flywaydb:flyway-core")

    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.codehaus.groovy:groovy-all:2.5.7")
    testImplementation("org.spockframework:spock-core:1.3-groovy-2.5")
    testImplementation("org.spockframework:spock-spring:1.3-groovy-2.5")
}

configure<FlywayExtension> {
    url = "jdbc:postgresql://localhost:5432/corona-app"
    user = "root"
    password = "pw"
    schemas = arrayOf("public")
}

//tasks.withType<Test> {
//    useJUnitPlatform()
//}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/WirVsVirus-Arvato/WirVsVirus-Server")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("default") {
            from(components["java"])
        }
    }
}
