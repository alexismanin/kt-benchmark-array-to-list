import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.benchmark)
}

repositories {
    mavenCentral()
}

kotlin {
    targets {
        jvm {
            compilerOptions {
                jvmTarget = JvmTarget.JVM_21
            }
        }

        linuxX64()

        js {
            nodejs()
        }

        wasmJs {
            nodejs()
        }
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain {
            kotlin.srcDir("src/main/kotlin")
        }
        commonTest {
            kotlin.srcDir("src/test/kotlin")
        }
    }

    val benchmarkSourceSet = sourceSets.create("benchmark") {
        kotlin.srcDir("src/benchmark/kotlin")
        dependencies {
            implementation(libs.kotlinx.benchmark)
        }
    }

    targets.matching { it.name != "metadata"}.all {
        compilations.create(benchmarkSourceSet.name) {
            associateWith(this@all.compilations.getByName("main"))
            defaultSourceSet {
                dependencies {
                    implementation(libs.kotlinx.benchmark)
                }
                dependsOn(benchmarkSourceSet)
            }
        }
    }
}

dependencies {
    commonMainImplementation(libs.kotlinx.benchmark)
    commonTestImplementation(libs.kotlin.test)
}

benchmark {
    configurations {
        register("toMutableList") {
            include("\\.StringArrayToMutableList\\.")
        }

        register("flatten") {
            include("\\.flatten.*")
        }
        register("flattenEmpty") {
            include("\\.flatten.*")
            param("arraySize", "0", "1", "1000")
            param("innerSize", "0")
        }

        register("flattenEmptyOrSingle") {
            include("\\.flatten.*")
            param("arraySize", "0", "1")
            param("innerSize", "0", "3")
        }

        register("takeWhileSmallSize") {
            include("\\.takeWhile.*")
            param("arraySize", "0", "1", "3", "32")
        }

        register("takeWhileMiddleSize") {
            include("\\.takeWhile.*")
            param("arraySize", "256", "1000")
        }

        register("takeWhileBigSize") {
            include("\\.takeWhile.*")
            param("arraySize", "100000")
        }
    }
    targets {
        register("jvmBenchmark")
        register("linuxX64Benchmark")
        register("jsBenchmark")
        register("wasmJsBenchmark")
    }
}
