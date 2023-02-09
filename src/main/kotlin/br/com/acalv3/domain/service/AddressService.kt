package br.com.acalv3.domain.service

import br.com.acalv3.application.comunicate.model.request.address.AddressPageRequest
import br.com.acalv3.domain.model.Address
import org.springframework.data.domain.Page

interface AddressService {
    fun getById(id: String): Address
    fun save(address: Address): Address
    fun update(address: Address): Address
    fun paginate(pageRequest: AddressPageRequest): Page<Address>
    fun findByName(name: String): Address
}