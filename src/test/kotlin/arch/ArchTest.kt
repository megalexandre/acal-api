package arch

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import org.junit.jupiter.api.Test

class ArchTest {

    private val importedClassesRoot = ClassFileImporter().importPackages("br.com.acalv3")

    @Test
    fun `package application should not depend on classes from resources package`() {
        noClasses().that().resideInAPackage("..application..")
            .should().dependOnClassesThat().resideInAPackage("..resources..")
            .check(importedClassesRoot)}

    @Test
    fun `package resources should not depend on classes from application package`() {
        noClasses().that().resideInAPackage("..resources..")
            .should().dependOnClassesThat().resideInAPackage("..application..").allowEmptyShould(true)
            .check(importedClassesRoot)
    }

    @Test
    fun `package domain should not depend on classes from application or resources`() {
        noClasses().that().resideInAPackage("..domain..")
            .should().dependOnClassesThat().resideInAnyPackage("..application..", "..resources..")
            .allowEmptyShould(true).check(importedClassesRoot)}


    @Test
    fun `layers of the application should be respected`() {
        layeredArchitecture()
            .layer("application").definedBy("..application..")
            .layer("domain").definedBy("..domain..")
            .layer("resources").definedBy("..resources..")
        .whereLayer("application").mayNotBeAccessedByAnyLayer()
        .whereLayer("domain").mayOnlyBeAccessedByLayers("application", "resources")
        .whereLayer("resources")
            .mayOnlyBeAccessedByLayers("domain")}
}