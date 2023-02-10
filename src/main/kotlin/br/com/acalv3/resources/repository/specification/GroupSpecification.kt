package br.com.acalv3.resources.repository.specification

import br.com.acalv3.domain.model.page.GroupPage
import br.com.acalv3.resources.model.business.GroupEntity
import org.springframework.data.jpa.domain.Specification

class GroupSpecification(private val place: GroupPage) {

    fun getSpecification(): Specification<GroupEntity> =
        Specification<GroupEntity> { _, _, builder ->
            val predicate = builder.conjunction()
            predicate
        }

}

