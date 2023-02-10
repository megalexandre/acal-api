package br.com.acalv3.resources.model.security

import br.com.acalv3.domain.model.security.UserDomain
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "user_model")
class UserEntity (

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
    )
    val id: String? = null,

    @Column(name="username", unique=true)
    val username: String,

    var password: String,

    @OneToMany(cascade = [CascadeType.PERSIST])
    val authorities: List<RoleEntity>? = listOf(),

)

fun UserEntity.toUserDomain() = UserDomain(
    username = username,
    password = password,
    authorities = authorities?.map { it.toRoleDomain() },
)
