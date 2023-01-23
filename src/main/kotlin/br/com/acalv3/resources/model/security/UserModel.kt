package br.com.acalv3.resources.model.security

import br.com.acalv3.domain.model.AbstractModel
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "user_model")
class UserModel (

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
    )
    private var id: Long? = null,

    @Column(name="username", unique=true)
    private var username: String? = "",

    private var password: String? = "",

    @OneToMany(cascade = [CascadeType.PERSIST])
    var roles: List<RoleModel>? = mutableListOf(),

    ): UserDetails {

    override fun getAuthorities(): List<RoleModel>? {
        return roles
    }

    override fun getPassword(): String {
        return password.orEmpty()
    }

    fun setPassword(password: String){
        this.password = password
    }

    override fun getUsername(): String {
        return username.orEmpty()
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
       return true
    }
}