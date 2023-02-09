package br.com.acalv3.resources.repository.impl

import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.repository.LegacyRepository
import br.com.acalv3.resources.model.business.toAddressEntity
import br.com.acalv3.resources.model.business.toCustomerEntity
import br.com.acalv3.resources.model.dto.LegacyAddress
import br.com.acalv3.resources.model.dto.LegacyUser
import br.com.acalv3.resources.model.dto.toAddress
import br.com.acalv3.resources.model.dto.toCustomer
import br.com.acalv3.resources.repository.interfaces.AddressRepositoryJpa
import br.com.acalv3.resources.repository.interfaces.CustomerRepositoryJpa
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Repository
import org.springframework.util.StreamUtils
import java.nio.charset.Charset

@Repository
class LegacyRepositoryImpl(
    private val customerRepositoryJpa: CustomerRepositoryJpa,
    private val addressRepositoryJpa: AddressRepositoryJpa,
) : LegacyRepository {

    @Value("classpath:legacy/legacy_user.json")
    private lateinit var legacyUsers: Resource

    @Value("classpath:legacy/legacy_address.json")
    private lateinit var legacyAddress: Resource

    override fun person() {
        val postUser = StreamUtils.copyToString(legacyUsers.inputStream, Charset.defaultCharset())
        val users: Array<LegacyUser> =
            ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .registerKotlinModule()
                .readValue(postUser, Array<LegacyUser>::class.java)

        users.forEach {
            if(it.personType == PersonTypeEnum.PERSON){
                it.document.padStart(11,'0' )
            }else {
                it.document.padStart(14, '0')
            }
        }

        customerRepositoryJpa.saveAll( users.map { it.toCustomer().toCustomerEntity()})
    }

    override fun address() {
        val postAddress = StreamUtils.copyToString(legacyAddress.inputStream, Charset.defaultCharset())
        val address: Array<LegacyAddress> =
            ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .registerKotlinModule()
                .readValue(postAddress, Array<LegacyAddress>::class.java)

        addressRepositoryJpa.saveAll( address.map { it.toAddress().toAddressEntity()})
    }

}