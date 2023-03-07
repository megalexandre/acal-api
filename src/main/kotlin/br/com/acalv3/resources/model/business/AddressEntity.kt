package br.com.acalv3.resources.model.business

import br.com.acalv3.domain.model.Address
import br.com.acalv3.resources.model.DefaultEntity
import org.springframework.data.domain.Page
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "address")
class AddressEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    @Column(nullable = false, unique = true, length = 256)
    val name: String,

) : DefaultEntity()

fun Address.toAddressEntity() = AddressEntity(
    id = UUID.fromString(id),
    name = name,
)

fun AddressEntity.toAddress() = Address(
    id = id.toString(),
    name = name,
)

fun Page<AddressEntity>.toAddressPage() = map{ it.toAddress() }
fun List<AddressEntity>.toAddress() = map{ it.toAddress() }