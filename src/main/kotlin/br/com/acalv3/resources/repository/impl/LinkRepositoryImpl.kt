package br.com.acalv3.resources.repository.impl

import br.com.acalv3.application.comunicate.model.request.link.LinkPageRequest
import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.repository.LinkRepository
import br.com.acalv3.resources.model.business.toLink
import br.com.acalv3.resources.model.business.toLinkEntity
import br.com.acalv3.resources.model.business.toLinkPage
import br.com.acalv3.resources.repository.DefaultRepository
import br.com.acalv3.resources.repository.interfaces.LinkRepositoryJpa
import br.com.acalv3.resources.repository.specification.LinkSpecification
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class LinkRepositoryImpl(
    private val repository: LinkRepositoryJpa,
) : LinkRepository, DefaultRepository<Link> {

    override fun getById(id: String): Link =
        repository.findByIdOrNull(UUID.fromString(id))?.toLink() ?: throw NotFoundException()

    override fun save(link: Link): Link =
        repository.save(link.toLinkEntity()).toLink()

    override fun update(link: Link): Link =
        repository.save(link.toLinkEntity()).toLink()

    override fun findByName(name: String): Link =
        repository.findByName(name).toLink()

    override fun paginate(request: LinkPageRequest): Page<Link> =
        repository.findAll(
            LinkSpecification(request).getSpecification(),
            super.pageable(request)
        ).toLinkPage()

}