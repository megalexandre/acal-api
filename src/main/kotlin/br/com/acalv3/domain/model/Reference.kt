package br.com.acalv3.domain.model

import java.time.LocalDate.now

class Reference (
    val reference: String,
){
    val month: Int = reference.substring(0,2).toInt()
    val year: Int = reference.substring(2,6).toInt()

    override fun toString(): String  =
        """${month.toString().padStart(2,'0')}${year.toString().padStart(4,'0')}"""
}

fun Reference.previous(): Reference {

    val actual = now()
        .withDayOfMonth(1)
        .withYear(year)
        .withMonth(month)
        .minusMonths(1 )

    return Reference(reference =
        actual.monthValue.toString().padStart(2,'0') +
        actual.year.toString().padStart(4,'0')
    )
}
