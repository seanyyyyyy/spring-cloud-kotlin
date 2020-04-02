import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	id("spring-cloud-contract")
	id("org.asciidoctor.convert") version "1.5.8"
	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
	//Kotlin script generates when these downgraded to 1.3.31, investigating...
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")
extra["springCloudVersion"] = "Hoxton.SR3"

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(group = "junit", module = "junit")
	}
	testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
	testImplementation("org.springframework.cloud:spring-cloud-contract-spec-kotlin") //:2.2.2.RELEASE
	testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
	testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0") //:2.2.0
	testImplementation("io.mockk:mockk:1.9.3") //:1.9.3
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

contracts {
	packageWithBaseClasses.set("com.example.cloudkotlin")
	baseClassMappings {
		baseClassMapping(".*hello.*", "com.example.cloudkotlin.hello.HelloBase")
		baseClassMapping(".*greeting.*", "com.example.cloudkotlin.greeting.GreetingBase")
	}
}

val snippetsDir by extra { file("build/generated-snippets") }

tasks.test {
	outputs.dir(snippetsDir)
}

tasks.asciidoctor {
	inputs.dir(snippetsDir)
	dependsOn(tasks.test)
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
