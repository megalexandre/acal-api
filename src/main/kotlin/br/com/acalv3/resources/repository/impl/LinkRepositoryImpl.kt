package br.com.acalv3.resources.repository.impl

import br.com.acalv3.commons.ReportUtils
import br.com.acalv3.commons.formatDocument
import br.com.acalv3.domain.enumeration.Report.LINK
import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.page.LinkPage
import br.com.acalv3.domain.repository.LinkRepository
import br.com.acalv3.resources.model.business.PlaceEntity
import br.com.acalv3.resources.model.business.toLink
import br.com.acalv3.resources.model.business.toLinkEntity
import br.com.acalv3.resources.model.business.toLinkPage
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

    override fun getAll(): List<Link> = repository.findAll().toLink()

    override fun save(link: Link): Link = repository.save(link.toLinkEntity()).toLink()

    override fun delete(id: String) = repository.deleteById(UUID.fromString(id))

    override fun update(link: Link): Link = repository.save(link.toLinkEntity()).toLink()

    override fun findByName(name: String): Link? = repository.findByName(name)?.toLink()

    override fun findByCustomerId(id: String): Link? =
        repository.findByCustomerId(UUID.fromString(id))?.firstOrNull()?.toLink()

    override fun findByGroupId(id: String): Link? =
        repository.findByGroupId(UUID.fromString(id))?.firstOrNull()?.toLink()

    override fun findByPlaceId(id: String): Link? =
        repository.findByPlaceId(UUID.fromString(id))?.firstOrNull()?.toLink()

    override fun findActiveByPlaceId(id: String): Link? =
        repository.findByPlaceIdAndActive(UUID.fromString(id), true)?.firstOrNull()?.toLink()

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

    override fun invoicing(): Long = 0

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

        return ReportUtils().print(
            data = data,
            report = LINK,
            param = hashMapOf(
                CUSTOMER to valid(link.customer?.name),
                DOCUMENT to valid(link.customer?.document).formatDocument(),
                ADDRESS to valid(link.place?.address?.name),
                STATUS to valid(link.active),
                GROUP to valid(link.group?.name),
                CATEGORY to valid(link.group?.category?.value),
                TOTAL to data.size.toString()
            )
        )
    }

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
    }
}

