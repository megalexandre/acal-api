package br.com.acalv3.domain.model.v3

import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.model.AbstractNamedModel
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

const val SEQ_NAME = "seq_customer"

@Entity(name = "customer_model")
class CustomerModel (

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
    )
    override var id: Long? = null,

    @Column(nullable = false)
    override var name: String? = null,

    var document: String? = null,

    var businessName: String? = null,

    var phoneNumber: String? = null,

    @Enumerated(EnumType.STRING)
    var personType: PersonTypeEnum? = null,

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd")
    var birthDate: LocalDate? = null,

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

) : AbstractNamedModel