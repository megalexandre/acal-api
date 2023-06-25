package br.com.acalv3.application.comunication.model.request.pagination

import br.com.acalv3.domain.enumeration.Direction

abstract class BasePageRequest(

     val page: Int = 0,
     val pageSize: Int = 10,
     val direction: Direction = Direction.ASC,
     val sortedField: String = "id",

)
