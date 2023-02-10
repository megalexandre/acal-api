package br.com.acalv3.domain.model.page

 open class DefaultPage(

     open val page: Int = 0,
     open val pageSize: Int = 10,
     open val direction: String = "ASC",
     open val sortedField: String = "id",

)
