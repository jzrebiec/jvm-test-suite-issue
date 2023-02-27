package jz.gradle.issue

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import org.springframework.context.annotation.Configuration

@AnalyzeClasses(packages = ["jz.gradle.issue"])
class NamingConventionTest {

    @ArchTest
    val configurations = ArchRuleDefinition.classes()
        .that().areAnnotatedWith(Configuration::class.java)
        .should().haveSimpleNameEndingWith("Configuration")

}
