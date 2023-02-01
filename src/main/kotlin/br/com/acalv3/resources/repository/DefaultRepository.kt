package br.com.acalv3.resources.repository

import br.com.acalv3.application.comunicate.model.request.pagination.DefaultPageRequest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.data.domain.Sort.Direction.DESC
import org.springframework.stereotype.Repository

@Repository
interface DefaultRepository {

    fun paginate(request: DefaultPageRequest): PageRequest =
        PageRequest.of(
            request.page?.let { if (it < 0) 0 else it } ?: 0,
            request.pageSize?.let { if (it < 0) 10 else it } ?: 10)
            .withSort(
                Sort.by(
                    when (request.direction) {
                        DESC.name -> {
                            DESC
                        }

                        else -> ASC
                    }, request.sortedField ?: "id"
                )
            )

}