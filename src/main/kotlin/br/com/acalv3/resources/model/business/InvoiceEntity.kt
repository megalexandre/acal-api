package br.com.acalv3.resources.model.business

import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.resources.model.DefaultEntity
import java.util.UUID
import javax.persistence.CascadeType.ALL
import javax.persistence.CascadeType.DETACH
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import org.springframework.data.domain.Page

@Entity(name = "invoice")
class InvoiceEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    val reference: String,

    @ManyToOne(cascade = [DETACH])
    @JoinColumn(name="link_id",nullable=false)
    val link: LinkEntity,

) : DefaultEntity() {

    @OneToMany(fetch = EAGER, mappedBy="invoice", cascade = [ALL])
    var invoiceDetails: List<InvoiceDetailEntity>? = null
}

fun Invoice.toInvoiceEntity() = InvoiceEntity(
    id = id,
    reference = reference,
    link = link.toLinkEntity(),

).also {
    it.invoiceDetails = invoiceDetails?.toInvoiceDetailEntity(it)
}

fun InvoiceEntity.toInvoice() = Invoice(
    id = id,
    reference = reference,
    link = link.toLink(),
).also {
    it.invoiceDetails = invoiceDetails?.toInvoiceDetail()
}

fun Page<InvoiceEntity>.toInvoicePage() = map { it.toInvoice() }
fun List<InvoiceEntity>.toInvoice() = map { it.toInvoice() }
fun List<Invoice>.toInvoiceEntity() = map { it.toInvoiceEntity() }