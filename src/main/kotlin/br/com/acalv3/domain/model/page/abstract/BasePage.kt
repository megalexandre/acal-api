package br.com.acalv3.domain.model.page.abstract

abstract class BasePage (
    open val page: Int,
    open val pageSize: Int = 10,
    open val sortedField: String = "id",
    open val direction: String = "ASC",
)
