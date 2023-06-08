package br.com.acalv3.resources.model.business

import br.com.acalv3.application.comunication.Fixture.Companion.DATE_TIME_FORMAT
import br.com.acalv3.domain.enumeration.Reason
import br.com.acalv3.domain.model.InvoiceDetail
import br.com.acalv3.resources.model.DefaultEntity
import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.FetchType.EAGER
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME

@Entity(name = "invoice_detail")
class InvoiceDetailEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    @Enumerated(STRING)
    val reason: Reason,

    val value: BigDecimal,

    val isPayed: Boolean,

    @DateTimeFormat(pattern = DATE_TIME_FORMAT, iso = DATE_TIME)
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    val dataPayed: LocalDateTime? = null,

    ) : DefaultEntity(){

    @ManyToOne(cascade = [ALL], fetch = EAGER)
    @JoinColumn(name="invoice_id")

    var invoice: InvoiceEntity? = null
}

fun InvoiceDetail.toInvoiceDetailEntity(invoice: InvoiceEntity?) = InvoiceDetailEntity(
    id = id,
    reason = reason,
    value = value,
    isPayed = isPayed,
    dataPayed = dataPayed,
).also {
    it.invoice = invoice
}

fun InvoiceDetailEntity.toInvoiceDetail() = InvoiceDetail(
    id = id,
    reason = reason,
    value = value,
    isPayed = isPayed,
    dataPayed = dataPayed,
)

fun List<InvoiceDetail>.toInvoiceDetailEntity(invoice: InvoiceEntity?) = map{ it.toInvoiceDetailEntity(invoice) }
fun List<InvoiceDetailEntity>.toInvoiceDetail() = map{ it.toInvoiceDetail() }