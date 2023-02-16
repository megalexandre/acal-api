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

    @ManyToOne(cascade = [ALL])
    @JoinColumn(name="invoice_id")
    val invoice: InvoiceEntity?,

) : DefaultEntity()

fun InvoiceDetail.toInvoiceDetailEntity(invoice: InvoiceEntity?) = InvoiceDetailEntity(
    id = UUID.fromString(id),
    reason = reason,
    value = value,
    invoice = invoice,
)

fun InvoiceDetailEntity.toInvoiceDetail() = InvoiceDetail(
    id = id.toString(),
    reason = reason,
    value = value,
)

fun List<InvoiceDetail>.toInvoiceDetailEntity(invoice: InvoiceEntity?) = map{ it.toInvoiceDetailEntity(invoice) }
fun List<InvoiceDetailEntity>.toInvoiceDetail() = map{ it.toInvoiceDetail() }