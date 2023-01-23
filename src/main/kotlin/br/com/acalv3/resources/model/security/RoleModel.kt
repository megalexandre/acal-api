package br.com.acalv3.resources.model.security

import br.com.acalv3.application.configuration.dto.Role
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity(name = "role_model")
data class RoleModel (

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
    )
    private var id: Long? = null,

    private val authority: String? = "",

    @JsonIgnore
    @ManyToOne(optional = false, cascade = [CascadeType.DETACH])
    private var user: UserModel? = null,

    ) : GrantedAuthority {

    override fun getAuthority(): String {
        return authority.orEmpty()
    }
}
fun Role.toRoleModel() = RoleModel(
    authority = authority
)

fun RoleModel.toRole() = Role(
    name = authority
)