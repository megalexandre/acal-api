package br.com.acalv3.domain.service

import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.model.page.GroupPage
import br.com.acalv3.domain.model.page.QualityPage
import br.com.acalv3.domain.repository.QualityRepository
import br.com.acalv3.domain.service.strategies.quality.QualityStrategy
import org.springframework.data.domain.Page

abstract class QualityService{
    abstract fun strategies(): List<QualityStrategy>
    abstract fun repository(): QualityRepository

    fun getById(id: String) = repository().getById(id)
    fun getAll(): List<Quality> = repository().getAll()
    fun save(q: Quality): Quality = strategies().first{ it.action() == SAVE }.can(q).let { repository().save(q) }
    fun update(q: Quality): Quality = strategies().first{ it.action() == UPDATE }.can(q).let { repository().save(q) }
    fun delete(id: String) = strategies().first{ it.action() == DELETE }.can(getById(id)).let { repository().delete(id)}
    abstract fun paginate(page: QualityPage): Page<Quality>

}
