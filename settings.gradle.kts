pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.neoforged.net/releases") {
            name = "NeoForge"
        }
        exclusiveContent {
            forRepository {
                maven("https://maven.fabricmc.net") {
                    name = "Fabric"
                }
            }
            filter {
                includeGroupAndSubgroups("net.fabricmc")
                includeGroup("fabric-loom")
            }
        }
    }

    includeBuild("build-logic")
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

// This should match the mod ID so that the published coordinates can be sensible
rootProject.name = "kirismodtemplate"
listOf("common", "fabric", "neoforge").forEach {
    include(it)
    project(":$it").name = "${rootProject.name}-$it"
}
