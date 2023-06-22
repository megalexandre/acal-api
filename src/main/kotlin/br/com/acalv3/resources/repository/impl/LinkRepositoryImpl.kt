package br.com.acalv3.resources.repository.impl

import br.com.acalv3.commons.formatDocument
import br.com.acalv3.domain.enumeration.Report.LINK
import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.page.LinkPage
import br.com.acalv3.domain.repository.LinkRepository
import br.com.acalv3.domain.service.ReportService
import br.com.acalv3.resources.model.business.PlaceEntity
import br.com.acalv3.resources.model.business.toLink
import br.com.acalv3.resources.model.business.toLinkEntity
import br.com.acalv3.resources.model.business.toLinkPage
import br.com.acalv3.resources.model.report.DefaultReport
import br.com.acalv3.resources.model.report.toLinkReport
import br.com.acalv3.resources.repository.interfaces.LinkRepositoryJpa
import br.com.acalv3.resources.repository.specification.LinkSpecification
import java.time.LocalDateTime
import java.util.UUID
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class LinkRepositoryImpl(
    private val repository: LinkRepositoryJpa,
    private val reportService: ReportService,
) : LinkRepository {

    override fun linkWithHydrometerByMonth(reference: String): List<Link>? =
        repository.findAll { root, _, builder ->
            builder.and(
                builder.equal(root.get<Boolean>("active"), true),
                builder.equal(root.get<PlaceEntity>("place").get<Boolean>("hasHydrometer"), true)
            )
        }.toLink()

    override fun findAll(reference: String): List<Link> =
        repository.findAllById(repository.invoicing(reference)).toLink()

    override fun getById(id: String): Link =
        repository.findByIdOrNull(UUID.fromString(id))?.toLink() ?: throw NotFoundException()

    override fun save(type: Link): Link = repository.save(type.toLinkEntity()).toLink()

    override fun delete(id: String) = repository.deleteById(UUID.fromString(id))

    override fun update(link: Link): Link = repository.save(link.toLinkEntity()).toLink()

    override fun findByName(name: String): Link? = repository.findByName(name)?.toLink()

    override fun findByCustomerId(id: UUID): Link? =
        repository.findByCustomerId(id)?.firstOrNull()?.toLink()

    override fun findByGroupId(id: UUID): Link? =
        repository.findByGroupId(id)?.firstOrNull()?.toLink()

    override fun findByPlaceId(id: UUID): Link? =
        repository.findByPlaceId(id)?.firstOrNull()?.toLink()

    override fun findActiveByPlaceId(id: UUID): Link? =
        repository.findByPlaceIdAndActive(id, true)?.firstOrNull()?.toLink()

    override fun paginate(page: LinkPage): Page<Link> =
        repository.findAll(
            LinkSpecification(page).getSpecification(),
            super.pageable(page)
        ).toLinkPage()

    override fun findAll(page: LinkPage): List<Link> = repository.findAll(
        LinkSpecification(page).getSpecification()
    ).toLink()

    override fun findAll(): List<Link> = repository.findAll().toLink()

    override fun count(): Long = repository.count()

    override fun countActive(): Long = repository.countActive()

    override fun inactivate(id: String) = this.update(
        this.getById(id).copy(
            active = false,
            finishedAt = LocalDateTime.now()
        ))

    override fun report(link: LinkPage): ByteArray {
        val data = repository.findAll(
            LinkSpecification(link).getSpecification(),
            super.sort(link)
        ).toLink().toLinkReport()

        return reportService.print( DefaultReport(
            dataList = data,
            report = LINK,
            param = hashMapOf(with(link){
                CUSTOMER to valid(customer?.name)
                DOCUMENT to valid(customer?.document).formatDocument()
                ADDRESS to valid(place?.address?.name)
                STATUS to valid(active)
                GROUP to valid(group?.name)
                CATEGORY to valid(group?.category?.value)
                TOTAL to data.size.toString()
                }
            )
        ))
    }

    override fun findHydrometerByReference(reference: String): List<Link> =
        repository.findHydrometerByReference(reference).toLink()

    private fun valid(text: String?) = when(text) { null,EMPTY -> SEPARATOR else -> text}

    private fun valid(status: Boolean?) = when(status) {
        null -> ALL
        true -> ACTIVE
        false -> INACTIVE
    }

    companion object{
        private const val EMPTY = ""
        private const val ACTIVE = "Ativos"
        private const val INACTIVE = "Inativos"
        private const val SEPARATOR = " - "
        private const val ALL = "todos"
        private const val CUSTOMER = "customer"
        private const val DOCUMENT = "document"
        private const val ADDRESS = "address"
        private const val STATUS = "status"
        private const val GROUP = "group"
        private const val CATEGORY = "category"
        private const val TOTAL = "total_row"
        private const val SUBREPORT_DIR = "SUBREPORT_DIR"
    }
}

