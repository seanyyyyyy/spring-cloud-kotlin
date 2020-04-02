rootProject.name = "spring-cloud-kotlin-producer"

pluginManagement {
    repositories {
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()
    }
    resolutionStrategy {
        val springBootPluginVersion: String by settings
        val springDependencyManagementPluginVersion: String by settings
        val verifierVersion: String by settings

        eachPlugin {
            val id = requested.id.id
            when {
                id == "org.springframework.boot" -> useModule("org.springframework.boot:spring-boot-gradle-plugin:$springBootPluginVersion")
                id == "spring-cloud-contract" -> useModule("org.springframework.cloud:spring-cloud-contract-gradle-plugin:$verifierVersion")
                id == "io.spring.dependency-management" -> useVersion(springDependencyManagementPluginVersion)
            }
        }
    }
}