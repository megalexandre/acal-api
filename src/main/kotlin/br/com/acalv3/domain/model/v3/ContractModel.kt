package br.com.acalv3.domain.model.v3

import br.com.acalv3.domain.model.AbstractNamedModel
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity(name = "contract_model")
class ContractModel (

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null,

	@Column(nullable = false, unique = true)
	override var name: String? = null,

	var nameBusiness: String? = null,

	@ManyToOne(cascade = [CascadeType.DETACH])
	var group: GroupModel? = null,

	@ManyToOne(cascade = [CascadeType.DETACH])
    var placeResidence: PlaceModel? = null,

	@ManyToOne(cascade = [CascadeType.DETACH])
	var placeMail: PlaceModel? = null,

	@ManyToOne(cascade = [CascadeType.DETACH])
	var customer: CustomerModel? = null,

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