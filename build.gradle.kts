plugins {
    kotlin("js") version "1.4.10"
}
group = "com.daanvandenbosch"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}
dependencies {
    testImplementation(kotlin("test-js"))
}
kotlin {
    js {
        browser {
            binaries.executable()
            webpackTask {
                cssSupport.enabled = true
            }
            runTask {
                cssSupport.enabled = true
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
}

val generateKarmaConfig = tasks.register("generateKarmaConfig") {
    val outputFile = file("karma.config.d/karma.config.generated.js")

    outputs.file(outputFile)

    outputFile.printWriter().use { writer ->
        writer.println("var PROJECT_PATH = '${projectDir.absolutePath.replace("\\", "\\\\")}';")
    }
}

tasks.getByName("browserTest") {
    dependsOn(generateKarmaConfig)
}
