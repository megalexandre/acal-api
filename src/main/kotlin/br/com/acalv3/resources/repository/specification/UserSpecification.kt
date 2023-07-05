package br.com.acalv3.resources.repository.specification

import br.com.acalv3.domain.model.page.UserPage
import br.com.acalv3.resources.model.security.UserEntity
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

class UserSpecification(private val user: UserPage) {

    fun getSpecification(): Specification<UserEntity> =
        Specification<UserEntity> { root, _, builder ->
            val predicate = builder.conjunction()
            getPredicates(predicate, root, builder)
            predicate
        }

    private fun getPredicates(predicate: Predicate, root: Root<UserEntity>, builder: CriteriaBuilder) {

        if (user.username != null) {
            predicate.expressions.add(likeName(root, builder))
        }
    }

    private fun likeName(root: Root<UserEntity>, builder: CriteriaBuilder): Predicate =
        builder.like(builder.upper(root.get("username")),"%${user.username}%")

}

