package br.com.acalv3.resources.model.security

import br.com.acalv3.application.configuration.dto.Role
import br.com.acalv3.application.configuration.dto.UserLogin
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

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

fun UserModel.toUserLogin() =
    UserLogin(
        username = username,
        password = password,
        authorities = authorities?.map { it.toRole() },
        token = null
    )


