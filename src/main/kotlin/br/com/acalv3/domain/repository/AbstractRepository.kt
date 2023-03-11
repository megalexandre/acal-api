package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.page.abstract.BasePage
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

interface AbstractRepository<Type, Pagination> {
    fun getById(id: String): Type
    fun getAll(): List<Type>
    fun save(type: Type): Type
    fun delete(id: String)
    fun count(): Long
    fun paginate(page: Pagination): Page<Type>
    fun pageable(request: BasePage): PageRequest =
        PageRequest.of(request.page, request.pageSize)
            .withSort(
                Sort.by(
                    when (request.direction) {
                        Sort.Direction.DESC.name -> {
                            Sort.Direction.DESC
                        }
                        else -> Sort.Direction.ASC

                    }, request.sortedField
                )
            )
}