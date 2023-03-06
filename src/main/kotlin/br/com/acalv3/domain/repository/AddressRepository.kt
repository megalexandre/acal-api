package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.page.AddressPage
import org.springframework.data.domain.Page

interface AddressRepository {
    fun getById(id: String): Address
    fun save(address: Address): Address
    fun update(address: Address): Address
    fun delete(id: String)
    fun findByName(name: String): Address?
    fun paginate(pageRequest: AddressPage): Page<Address>
    fun getAll(): List<Address>
}