package br.com.acalv3.application.comunicate.model.request

data class PageRequest(

    var page: Int? = 0,
    var pageSize: Int? = 10,
    var direction: String? = "ASC",
    var sortedField: String? = "ID",

)
