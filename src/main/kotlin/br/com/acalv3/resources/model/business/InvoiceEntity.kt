package br.com.acalv3.resources.model.business

import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.resources.model.DefaultEntity
import java.util.UUID
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

    @OneToMany(fetch = EAGER, mappedBy="invoice")
    val invoiceDetails: List<InvoiceDetailEntity>,

) : DefaultEntity()

fun Invoice.toInvoiceEntity() = InvoiceEntity(
    id = UUID.fromString(id),
    reference = reference,
    link = link.toLinkEntity(),
    invoiceDetails = invoiceDetails.toInvoiceDetailEntity(null),
)

fun InvoiceEntity.toInvoice() = Invoice(
    id = id.toString(),
    reference = reference,
    link = link.toLink(),
    invoiceDetails = invoiceDetails.toInvoiceDetail(),
)

fun Page<InvoiceEntity>.toInvoicePage() = map{ it.toInvoice() }
fun List<InvoiceEntity>.toInvoice() = map{ it.toInvoice() }