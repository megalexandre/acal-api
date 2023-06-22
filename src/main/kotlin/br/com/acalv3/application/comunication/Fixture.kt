package br.com.acalv3.application.comunication

class Fixture {

    companion object{
        const val TIME_FORMAT = "HH:mm:ss"

        const val DATE_REPORT = "dd/MM/yyyy"
        const val DATE_TIME_REPORT = "$DATE_REPORT $TIME_FORMAT"

        const val REFERENCE = "MMYYYY"
        const val MONTH = "MMMM"
        const val DATE_FORMAT = "dd-MM-yyyy"
        const val DATE_TIME_FORMAT = "$DATE_FORMAT $TIME_FORMAT"
        const val REFERENCE_REGEX = "^(0[1-9]|1[0-2])+([0-9]{4})\$"
    }

}