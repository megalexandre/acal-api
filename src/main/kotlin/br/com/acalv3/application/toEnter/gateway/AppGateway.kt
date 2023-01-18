package br.com.acalv3.application.toEnter.gateway

import br.com.acalv3.domain.dto.FilterDTO
import br.com.acalv3.domain.model.AbstractNamedModel
import br.com.acalv3.domain.service.AppService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
abstract class AppGateway<U: AbstractNamedModel> (
    private val appService: AppService<U>
    ) {

    @PostMapping
    fun save(@RequestBody u: U) =
        appService.save(u)

    @PutMapping
    fun update(@RequestBody u: U) =
        appService.update(u)

    @GetMapping("{id}")
    fun get(@PathVariable id: Long): U =
        appService.get(id)

    @GetMapping("count")
    fun count(): Long =
        appService.count()

    @GetMapping
    fun getAll(): List<U> =
        appService.getAll()

    @GetMapping("/name/{name}")
    fun getByName(@PathVariable name: String): U? =
        appService.findByName(name)

    @PostMapping("/pageable")
    fun pageable(@RequestBody filter: FilterDTO<U>) =
        appService.pageable(filter)

    @PostMapping("/filter")
    fun filterByExample(@RequestBody filter: FilterDTO<U>) =
        appService.filterByExample(filter)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) =
        appService.delete(id)
}