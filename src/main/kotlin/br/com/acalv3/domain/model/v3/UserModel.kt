package br.com.acalv3.domain.model.v3

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
    override var id: Long? = null,

    @Column(name="username", unique=true)
    private var username: String? = "",

    private var password: String? = "",

    @OneToMany(cascade = [CascadeType.PERSIST])
    var roles: List<RoleModel>? = mutableListOf(),

    @Transient
    var token: String? = "",

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    override var createdAt: LocalDateTime? = null,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    override var lastModifiedAt: LocalDateTime? = null,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    override var deletedAt: LocalDateTime? = null,

    override var createdBy: Long? = null,

    override var deletedBy: Long? = null,

    override var deleted: Boolean? = false,

): AbstractModel, UserDetails {

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