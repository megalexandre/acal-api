package br.com.acalv3.resources.model.business

import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "Hydrometer")
class HydrometerEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    val reference: Int,

    val lastMonth: Long,

    val actualMonth: Long,

    @ManyToOne(cascade = [CascadeType.DETACH])
    @JoinColumn(name="link_id",nullable=false)
    val link: LinkEntity,

)