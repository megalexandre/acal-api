package br.com.acalv3.application.comunication.model.request.pagination

import br.com.acalv3.domain.enumeration.Direction
import br.com.acalv3.domain.enumeration.Direction.ASC

open class DefaultPageRequest(

     open val page: Int = 0,
     open val pageSize: Int = 10,
     open val direction: Direction = ASC,
     open val sortedField: String = "id",

     )
