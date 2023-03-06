package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.page.AddressPage
import org.springframework.data.domain.Page

interface AddressService {
    fun getById(id: String): Address
    fun save(address: Address): Address
    fun update(address: Address): Address
    fun paginate(pageRequest: AddressPage): Page<Address>
    fun getAll(): List<Address>
}