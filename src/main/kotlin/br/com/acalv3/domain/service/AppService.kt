package br.com.acalv3.domain.service

import br.com.acalv3.domain.dto.FilterDTO
import br.com.acalv3.domain.exception.DuplicatedFieldException
import br.com.acalv3.domain.exception.RequiredFieldException
import br.com.acalv3.domain.model.AbstractModel
import br.com.acalv3.domain.model.AbstractNamedModel
import br.com.acalv3.domain.spec.v3.AbstractSpec
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.time.LocalDateTime

abstract class AppService<U: AbstractModel>(
    private val appRepository : JpaRepository<U, Long>,
    private val appSpec: JpaSpecificationExecutor<U>,
) {
    private var logger: Logger = LoggerFactory.getLogger(AppService::class.java)

    fun delete(id: Long) {
        logger.info("delete by id $id")

        if(!appRepository.existsById(id)){
            throw NoSuchElementException(ENTITY_NOT_FOUND)
        }

        appRepository.deleteById(id)
    }

    fun update(u: U) : U = run {
        validEdit(u)
        prepareForSave(u)
        saveCommit(u)
    }

    fun save(u: U) : U = run {
        validSave(u)
        prepareForSave(u)

        saveCommit(u)
    }

    private fun saveCommit(u: U): U{

        try{

            return appRepository.save(u)

        } catch (ex: DataIntegrityViolationException){
            logger.info(NULL_FIELD, ex)
            throw RequiredFieldException(ex, NULL_FIELD)
        } catch (ex: Exception){

            logger.info(DUPLICATED, ex)
            throw DuplicatedFieldException(DUPLICATED)
        }
    }

    fun get(id: Long): U =
        appRepository.findById(id).orElseThrow {
            NoSuchElementException(ENTITY_NOT_FOUND)
        }

    fun getAll(): List<U> =
        appRepository.findAll()

    open fun filterByExample(filter: FilterDTO<U>): List<U> {
        val model = filter.model as AbstractNamedModel

        val sort = Sort.by(Sort.Direction.ASC, NAME)
        val spec = AbstractSpec<U>(model)

        return appSpec.findAll(spec, sort)
    }

    open fun pageable(filter: FilterDTO<U>): Page<U> {

        val model = filter.model as AbstractNamedModel
        return appSpec.findAll(
            AbstractSpec<U>(model),
                getPage(filter)
            )
    }

    fun count(): Long =
        appRepository.count()

    abstract fun findByName(name: String): U?

    open fun validSave(u: U) = Unit
    open fun validEdit(u: U) = Unit

    open fun prepareForSave(u: U) {

        if (u.createdAt == null || u.id == null) {
            u.createdAt = LocalDateTime.now()
        }

        u.lastModifiedAt = LocalDateTime.now()
    }

    fun getPage(filter: FilterDTO<U>) : PageRequest {

        return when (filter.sort){
            null -> PageRequest.of(
                filter.page.number,
                filter.page.size,
            )
            else -> {
                PageRequest.of(
                    filter.page.number,
                    filter.page.size,
                    getOrderDirection(filter)
                )
            }
        }

    }

    private fun getOrderDirection(filter: FilterDTO<U>): Sort {

        return when (filter.sort!!.asc) {
            null -> { Sort.by(Sort.Direction.ASC, NAME) }
            true -> { Sort.by(Sort.Direction.ASC, filter.sort!!.value) }
            false ->{ Sort.by(Sort.Direction.DESC, filter.sort!!.value )}
        }
    }

    companion object{
        const val NAME = "name"
        const val ENTITY_NOT_FOUND = "Entity not found"
        const val NULL_FIELD = "Campo nulo"
        const val DUPLICATED = "Duplicated"
    }
}


