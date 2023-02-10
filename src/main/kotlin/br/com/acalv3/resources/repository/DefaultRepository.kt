package br.com.acalv3.resources.repository

import br.com.acalv3.domain.model.page.DefaultPage
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.data.domain.Sort.Direction.DESC
import org.springframework.stereotype.Repository

@Repository
interface DefaultRepository {

    fun pageable(request: DefaultPage): PageRequest =
        PageRequest.of(request.page, request.pageSize)
            .withSort(
                Sort.by(
                    when (request.direction) {
                        DESC.name -> {
                            DESC
                        }

                        else -> ASC
                    }, request.sortedField
                )
            )
}