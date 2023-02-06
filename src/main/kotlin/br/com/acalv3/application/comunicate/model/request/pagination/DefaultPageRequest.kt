package br.com.acalv3.application.comunicate.model.request.pagination

 open class DefaultPageRequest(

     open val page: Int? = 0,
     open val pageSize: Int? = 10,
     open val direction: String? = "ASC",
     open val sortedField: String? = "id",

)
