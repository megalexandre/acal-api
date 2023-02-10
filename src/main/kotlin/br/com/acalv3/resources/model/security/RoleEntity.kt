package br.com.acalv3.resources.model.security

import br.com.acalv3.domain.model.security.RoleDomain
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.CascadeType.DETACH
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity(name = "role_model")
data class RoleEntity (

    @Id
    @GeneratedValue(
        strategy = IDENTITY,
    )
    private var id: Long? = null,

    val authority: String,

    @JsonIgnore
    @ManyToOne(optional = false, cascade = [DETACH])
    private var user: UserEntity? = null,

)

fun RoleEntity.toRoleDomain() = RoleDomain(
    authority = authority
)
