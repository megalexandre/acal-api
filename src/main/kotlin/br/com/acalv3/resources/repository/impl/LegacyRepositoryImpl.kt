package br.com.acalv3.resources.repository.impl

import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.repository.LegacyRepository
import br.com.acalv3.resources.model.business.toCustomerModel
import br.com.acalv3.resources.model.dto.LegacyUser
import br.com.acalv3.resources.model.dto.toCustomer
import br.com.acalv3.resources.repository.CustomerRepositoryJpa
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Repository
import org.springframework.util.StreamUtils
import java.nio.charset.Charset

@Repository
class LegacyRepositoryImpl(
    private val customerRepositoryJpa: CustomerRepositoryJpa,
) : LegacyRepository {

    @Value("classpath:legacy/legacy_user.json")
    private lateinit var legacyUsers: Resource

    override fun person() {
        val postUser = StreamUtils.copyToString(legacyUsers.inputStream, Charset.defaultCharset())
        val users: Array<LegacyUser> =
            ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .registerModule(KotlinModule()).readValue(postUser, Array<LegacyUser>::class.java)

        users.forEach {
            if(it.personType == PersonTypeEnum.PERSON){
                it.document.padStart(11,'0' )
            }else {
                it.document.padStart(14, '0')
            }
        }

        customerRepositoryJpa.saveAll( users.map { it.toCustomer().toCustomerModel()})
    }


}