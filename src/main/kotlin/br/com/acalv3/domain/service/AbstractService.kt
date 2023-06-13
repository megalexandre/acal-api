package br.com.acalv3.domain.service

import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.repository.AbstractRepository
import br.com.acalv3.domain.service.strategies.AbstractStrategy
import org.springframework.data.domain.Page

abstract class AbstractService <Type, Pagination>{
    abstract fun strategies(): List<AbstractStrategy<Type>>
    abstract fun repository(): AbstractRepository<Type, Pagination>

    fun getById(id: String): Type = repository().getById(id)
    fun findAll(): List<Type> = repository().findAll()
    fun save(q: Type): Type = strategies().first{ it.action() == SAVE }.can(q).let { repository().save(q) }
    fun update(q: Type): Type = strategies().first{ it.action() == UPDATE }.can(q).let { repository().save(q) }
    fun delete(id: String) = strategies().first{ it.action() == DELETE }.can(getById(id)).let { repository().delete(id)}
    fun paginate(page: Pagination): Page<Type> = repository().paginate(page)
    fun count(): Long = repository().count()
}
