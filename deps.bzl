load("@rules_jvm_external//:defs.bzl", "maven_install")

JACKSON_VERSION = "2.12.1"
SPRING_BOOT_VERSION = "2.4.3"
SPRING_VERSION = "5.3.4"

def mobile_app_store_spy_deps(maven_install_json):
    maven_install(
        name = "mobile_app_store_spy_maven",
        artifacts = [
            "com.fasterxml.jackson.core:jackson-annotations:%s" % JACKSON_VERSION,
            "com.fasterxml.jackson.core:jackson-core:%s" % JACKSON_VERSION,
            "com.fasterxml.jackson.core:jackson-databind:%s" % JACKSON_VERSION,
            "org.assertj:assertj-core:3.19.0",
            "org.junit.jupiter:junit-jupiter:5.7.1",
            "org.mockito:mockito-core:3.7.7",
            "org.springframework.boot:spring-boot-starter-web:%s" % SPRING_BOOT_VERSION,
            "org.springframework.boot:spring-boot-test-autoconfigure:%s" % SPRING_BOOT_VERSION,
            "org.springframework.boot:spring-boot-test:%s" % SPRING_BOOT_VERSION,
            "org.springframework.boot:spring-boot:%s" % SPRING_BOOT_VERSION,
            "org.springframework:spring-test:%s" % SPRING_VERSION,
        ],
        fetch_sources = True,
        maven_install_json = maven_install_json,
        repositories = [
            "https://repo1.maven.org/maven2",
            "https://jcenter.bintray.com",
        ],
    )
