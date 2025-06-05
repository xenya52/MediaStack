group = "com.MediaStack"
version = "0.0.1-SNAPSHOT"

plugins {
	java
	id("org.springframework.boot") version "3.5.0"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.vaadin") version "24.7.6"
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

springBoot {
	mainClass.set("com.MediaStack.MediaStack.MediaStackApplication")
}

repositories {
	mavenCentral()
}

extra["vaadinVersion"] = "24.7.6"

dependencies {
	implementation("com.vaadin:vaadin-spring-boot-starter")
	implementation("org.springframework:spring-test")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// Lombok for reducing boilerplate code
	compileOnly("org.projectlombok:lombok:1.18.38")
	annotationProcessor("org.projectlombok:lombok:1.18.38")

	testCompileOnly("org.projectlombok:lombok:1.18.38")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.38")

	// For database usage
	implementation("org.springframework.boot:spring-boot-starter-validation:3.5.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.5.0")

	// For the acutal database connection
	implementation("org.xerial:sqlite-jdbc:3.49.1.0")
	implementation("org.hibernate.orm:hibernate-community-dialects:7.0.0.Final")
}

dependencyManagement {
	imports {
		mavenBom("com.vaadin:vaadin-bom:${property("vaadinVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
