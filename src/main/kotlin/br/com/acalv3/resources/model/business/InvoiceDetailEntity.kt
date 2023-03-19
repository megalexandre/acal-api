package br.com.acalv3.resources.model.business

import br.com.acalv3.domain.enumeration.Reason
import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.InvoiceDetail
import br.com.acalv3.resources.model.DefaultEntity
import java.math.BigDecimal
import java.util.UUID
import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.FetchType.EAGER
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import org.springframework.data.domain.Page

@Entity(name = "invoice_detail")
class InvoiceDetailEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    @Enumerated(STRING)
    val reason: Reason,

    val value: BigDecimal,

) : DefaultEntity(){

    @ManyToOne(cascade = [ALL], fetch = EAGER)
    @JoinColumn(name="invoice_id")

    var invoice: InvoiceEntity? = null
}

fun InvoiceDetail.toInvoiceDetailEntity(invoice: InvoiceEntity?) = InvoiceDetailEntity(
    id = id,
    reason = reason,
    value = value,
).also {
    it.invoice = invoice
}

fun InvoiceDetailEntity.toInvoiceDetail() = InvoiceDetail(
    id = id,
    reason = reason,
    value = value,
)

fun List<InvoiceDetail>.toInvoiceDetailEntity(invoice: InvoiceEntity?) = map{ it.toInvoiceDetailEntity(invoice) }
fun List<InvoiceDetailEntity>.toInvoiceDetail() = map{ it.toInvoiceDetail() }