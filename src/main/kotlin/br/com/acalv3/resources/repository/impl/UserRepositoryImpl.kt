package br.com.acalv3.resources.repository.impl

import br.com.acalv3.domain.model.page.UserPage
import br.com.acalv3.domain.model.security.UserDomain
import br.com.acalv3.domain.repository.UserRepository
import br.com.acalv3.resources.model.security.toDomain
import br.com.acalv3.resources.model.security.toEntity
import br.com.acalv3.resources.repository.interfaces.UserRepositoryJpa
import br.com.acalv3.resources.repository.specification.UserSpecification
import java.util.UUID
import org.springframework.data.domain.Page
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val repository: UserRepositoryJpa,
) : UserRepository {

    override fun findByUsername(name: String): UserDomain? = repository.findByUsername(name)?.toDomain()

    override fun save(user: UserDomain): UserDomain = repository.save(user.toEntity()).toDomain()

    override fun saveAll(type: List<UserDomain>) {
        repository.saveAll(type.toEntity())
    }

    override fun delete(id: String) = repository.deleteById(UUID.fromString(id))

    override fun count(): Long = repository.count()

    override fun paginate(page: UserPage): Page<UserDomain> = repository.findAll(
        UserSpecification(page).getSpecification(),
        super.pageable(page)
    ).toDomain()

    override fun existByUsername(name: String):Boolean = repository.existsByUsername(name)

    override fun getById(id: String): UserDomain = repository.getById(UUID.fromString(id)).toDomain()

    override fun findAll(): List<UserDomain> = repository.findAll().toDomain()

    override fun findAll(page: UserPage) = repository.findAll(
        UserSpecification(page).getSpecification(),
    ).toDomain()

}