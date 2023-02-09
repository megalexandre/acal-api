package br.com.acalv3.resources.repository.specification

import br.com.acalv3.application.comunicate.model.request.address.AddressPageRequest
import br.com.acalv3.resources.model.business.AddressEntity
import org.springframework.data.jpa.domain.Specification

class AddressSpecification(private val address: AddressPageRequest) {

    fun getSpecification(): Specification<AddressEntity> =
        Specification<AddressEntity> { _, _, builder ->
            val predicate = builder.conjunction()
            //getPredicates(predicate, root, builder)
            predicate
        }

}

