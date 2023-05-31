package br.com.acalv3.resources.model.security

import br.com.acalv3.domain.model.security.UserDomain
import java.util.UUID
import javax.persistence.CascadeType.PERSIST
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "user_model")
class UserEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    @Column(name="username", unique=true)
    val username: String,

    var password: String,

    @OneToMany(cascade = [PERSIST], fetch = EAGER, mappedBy = "id")
    val authorities: List<RoleEntity>? = listOf(),
)

fun UserEntity.toUserDomain() = UserDomain(
    username = username,
    password = password,
    authorities = authorities?.map { it.toRoleDomain() },
)

fun UserDomain.toUserEntity() = UserEntity(
    id = UUID.randomUUID(),
    username = username,
    password = password,
    authorities = authorities?.map { it.toRoleEntity() },
)
