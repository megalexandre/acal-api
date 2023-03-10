package br.com.acalv3.application.comunicate.model.request.pagination

abstract class BasePageRequest(

     val page: Int = 0,
     val pageSize: Int = 10,
     val direction: String = "ASC",
     val sortedField: String = "id",

)
