package br.com.acalv3.resources.model.security

import br.com.acalv3.domain.model.security.RoleDomain
import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.UUID
import javax.persistence.CascadeType.DETACH
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity(name = "role_model")
data class RoleEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID? = null,

    val authority: String,

    @JsonIgnore
    @ManyToOne(optional = false, cascade = [DETACH])
    private var user: UserEntity? = null,

)

fun RoleEntity.toRoleDomain() = RoleDomain(
    authority = authority
)


fun RoleDomain.toRoleEntity() = RoleEntity(
    authority = authority
)
