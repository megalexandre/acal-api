package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.model.page.DefaultPage
import br.com.acalv3.domain.model.page.QualityPage
import br.com.acalv3.domain.model.page.abstract.BasePage
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

interface QualityRepository {
    fun getById(id: String): Quality
    fun save(quality: Quality): Quality
    fun delete(id: String)
    fun getAll(): List<Quality>
    fun paginate(pageRequest: QualityPage): Page<Quality>

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
