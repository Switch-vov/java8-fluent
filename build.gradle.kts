import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

group = "cn.codemao.switchvov"
version = "1.0.0"

repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/nexus/content/groups/public/")
    mavenCentral()
}

plugins {
    val kotlinVersion = "1.2.71"

    kotlin("jvm") version kotlinVersion
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("org.projectlombok:lombok:1.18.12")
    compile("org.apache.commons:commons-lang3:3.10")
    compile("javax.validation:validation-api:1.1.0.Final")
    compile("org.springframework:spring-context:5.2.3.RELEASE")
    compile("com.google.guava:guava:23.0")
    testCompile("junit:junit:4.12")
}

configure<JavaPluginConvention> {
    val javaVersion = JavaVersion.VERSION_1_8

    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

tasks.withType<KotlinJvmCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")
        allWarningsAsErrors = true
    }
}
