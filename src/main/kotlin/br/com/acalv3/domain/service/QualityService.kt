package br.com.acalv3.domain.service

import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.repository.QualityRepository
import br.com.acalv3.domain.service.strategies.quality.QualityStrategy

abstract class QualityService{
    abstract fun strategies(): List<QualityStrategy>
    abstract fun repository(): QualityRepository

    fun getById(id: String) = repository().getById(id)

    fun save(q: Quality): Quality = strategies().first{ it.action() == SAVE }.can(q).let {
        repository().save(q)
    }

    /*
    fun update(q: Quality): Quality = strategies().first{ it.action() == UPDATE }.can(q).let {
        repository().update(q)
    }

    fun delete(id: String) = strategies().first{ it.action() == DELETE }.can(getById(id)).let {
        repository().delete(id)
    }

    fun getAll(): List<Quality> = repository().getAll()
     */
}
