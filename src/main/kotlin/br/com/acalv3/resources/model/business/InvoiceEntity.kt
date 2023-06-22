package br.com.acalv3.resources.model.business

import br.com.acalv3.application.comunication.Fixture.Companion.DATE_TIME_FORMAT
import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.resources.model.DefaultEntity
import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.LocalDateTime
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
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME

@Entity(name = "invoice")
class InvoiceEntity (

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    val id: UUID,

    val reference: String,

    @Column(name = "link_id", nullable = false, columnDefinition = "BINARY(16)")
    val linkId: UUID,

    val isPayed: Boolean,

    @Column(precision = 10, scale = 2, nullable = false)
    val value: BigDecimal,

    @ManyToOne(cascade = [DETACH])
    @JoinColumn(name="link_id", insertable = false, updatable = false)
    val link: LinkEntity? = null,

    @DateTimeFormat(pattern = DATE_TIME_FORMAT, iso = DATE_TIME)
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    val emission: LocalDateTime,

    @DateTimeFormat(pattern = DATE_TIME_FORMAT, iso = DATE_TIME)
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    val dueDate: LocalDateTime,

) : DefaultEntity() {

    @OneToMany(fetch = EAGER, mappedBy="invoice", cascade = [ALL])
    var invoiceDetails: List<InvoiceDetailEntity>? = null
}

fun Invoice.toInvoiceEntity() = InvoiceEntity(
    id = id,
    reference = reference,
    linkId = linkId,
    value = value,
    isPayed = isPayed,
    link = link?.toLinkEntity(),
    emission = emission,
    dueDate = dueDate,
).also {
    it.invoiceDetails = invoiceDetails?.toInvoiceDetailEntity(it)
}

fun InvoiceEntity.toInvoice() = Invoice(
    id = id,
    reference = reference,
    isPayed = isPayed,
    link = link?.toLink(),
    value = value,
    linkId = linkId,
    emission = emission,
    dueDate = dueDate,
).also {
    it.invoiceDetails = invoiceDetails?.toInvoiceDetail()
}

fun Page<InvoiceEntity>.toInvoicePage() = map { it.toInvoice() }
fun List<InvoiceEntity>.toInvoice() = map { it.toInvoice() }
fun List<Invoice>.toInvoiceEntity() = map { it.toInvoiceEntity() }