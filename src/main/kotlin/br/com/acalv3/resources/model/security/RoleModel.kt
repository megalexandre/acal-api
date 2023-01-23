package br.com.acalv3.resources.model.security

import br.com.acalv3.domain.model.AbstractModel
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.core.GrantedAuthority
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

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
