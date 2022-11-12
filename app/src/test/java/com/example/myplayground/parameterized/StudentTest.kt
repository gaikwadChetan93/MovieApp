package com.example.myplayground.parameterized

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.util.Arrays
import kotlin.properties.Delegates

@RunWith(JUnitParamsRunner::class)
internal class StudentTest {

    @Parameters(method = "parametersForGetGradeTest")
    @Test
    fun `getGrade method Should return valid grade`(
        student: Student,
        grade: String
    ) {
        Assert.assertEquals(student.getGrade(), grade)
    }

    @Suppress("unused")
    private fun parametersForGetGradeTest(): Array<Any> {
        return arrayOf(
            arrayOf(
                Student("Don", 45),
                "C"
            ),
            arrayOf(
                Student("Jon", 55),
                "B"
            )
        )
    }

/*    @Parameters(
        "Don, 45, C",
        "Jon, 55, B",
        "Mon, 65, A",
        "Con, 85, A+",
        "Ron, 28, F",
    )
    @Test
    fun `getGrade method Should return valid grade`(
        name: String,
        marks: Float,
        grade: String
    ) {
        val student = Student(name, marks)
        Assert.assertEquals(student.getGrade(), grade)
    }*/


    /*@Test
    fun `getGrade method Should return valid grade`() {
        //Given
        val student = Student("Don", 45F)
        val student2 = Student("Jon", 55F)
        val student3 = Student("Mon", 65F)
        val student4 = Student("Con", 85F)
        val student5 = Student("Ron", 28F)

        //when
        val result = student.getGrade()
        val result2 = student2.getGrade()
        val result3 = student3.getGrade()
        val result4 = student4.getGrade()
        val result5 = student5.getGrade()

        //then
        Assert.assertEquals(result, "C")
        Assert.assertEquals(result2, "B")
        Assert.assertEquals(result3, "A")
        Assert.assertEquals(result4, "A+")
        Assert.assertEquals(result5, "F")
    }*/
}