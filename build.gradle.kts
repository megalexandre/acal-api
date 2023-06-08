import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.jetbrains.kotlin.plugin.noarg") version "1.6.0"
	kotlin("plugin.spring") version "1.6.0"
	kotlin("plugin.jpa") version "1.6.0"
	kotlin("jvm") version "1.6.0"
}

buildscript {
	dependencies {
		classpath ("org.jetbrains.kotlin:kotlin-noarg:1.3.41")
	}
}

group = "br.com.acal-v3"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-security")

	implementation("org.testng:testng:7.1.0")
	implementation("io.jsonwebtoken:jjwt:0.9.1")

	//Reports
	implementation("com.lowagie:itext:2.1.7")

	implementation("net.sf.jasperreports:jasperreports:6.19.1")
	implementation("net.sf.jasperreports:jasperreports-fonts:6.19.1"){
		exclude(module = "com.lowagie")
	}
	implementation("net.sf.barcode4j:barcode4j:2.1")
	implementation("com.google.zxing:core:3.5.1")
	implementation("com.google.zxing:javase:3.5.1")
	implementation("org.apache.xmlgraphics:batik-bridge:1.16")


	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testImplementation("org.junit.jupiter:junit-jupiter-engine")
	testImplementation("org.junit.platform:junit-platform-launcher")

	testImplementation("io.mockk:mockk:1.13.4")
	testImplementation("com.ninja-squad:springmockk:2.0.1")
	testImplementation("com.h2database:h2:2.1.214")

	testCompileOnly("org.springframework.boot:spring-boot-starter-test"){
		exclude(module = "junit")
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(module = "mockito-core")
	}
	testImplementation("org.springframework.security:spring-security-test")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	implementation("junit:junit:4.13.1")
	implementation("mysql:mysql-connector-java")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	testImplementation("io.rest-assured:spring-web-test-client:5.3.0")
	testImplementation("io.rest-assured:rest-assured:5.3.0")
	testImplementation("io.rest-assured:json-path:5.3.0")
	testImplementation("io.rest-assured:xml-path:5.3.0")
	testImplementation("io.rest-assured:kotlin-extensions:5.3.0")

	implementation("org.testcontainers:testcontainers-bom:1.17.6")
	implementation("org.testcontainers:junit-jupiter:1.17.6")
	implementation("org.testcontainers:mysql:1.17.6")

	testImplementation("org.junit.jupiter:junit-jupiter")
	testImplementation("com.tngtech.archunit:archunit-junit5-engine:0.23.1")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

