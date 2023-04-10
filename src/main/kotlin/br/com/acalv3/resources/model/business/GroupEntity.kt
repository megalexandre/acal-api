package br.com.acalv3.resources.model.business

import br.com.acalv3.domain.enumeration.Category
import br.com.acalv3.domain.model.Group
import br.com.acalv3.resources.model.DefaultEntity
import java.math.BigDecimal
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.Id
import org.springframework.data.domain.Page

@Entity(name = "group_entity")
class GroupEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    @Column(nullable = false)
    val value: BigDecimal,

    @Column(nullable = false)
    @Enumerated(STRING)
    val category: Category,

    @Column(nullable = false)
    val name: String,

) : DefaultEntity()

fun Group.toGroupEntity() = GroupEntity(
    id = id,
    value = value,
    category = category,
    name = name,
)

fun GroupEntity.toGroup() = Group(
    id = id,
    value = value,
    category = category,
    name = name,
)

fun Page<GroupEntity>.toGroupPage() = map{ it.toGroup() }
fun List<GroupEntity>.toGroup() = map{ it.toGroup() }