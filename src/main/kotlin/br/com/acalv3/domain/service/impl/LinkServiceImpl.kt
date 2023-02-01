package br.com.acalv3.domain.service.impl

import br.com.acalv3.application.comunicate.model.request.link.LinkPageRequest
import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.repository.LinkRepository
import br.com.acalv3.domain.service.LinkService
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class LinkServiceImpl(
	val repository: LinkRepository,
): LinkService {
	override fun getById(id: UUID): Link =
		repository.getById(id)

	override fun save(link: Link): Link =
		repository.save(link)

	override fun update(link: Link): Link =
		repository.save(link)

	override fun findByName(name: String): Link =
        repository.findByName(name)

	override fun paginate(pageRequest: LinkPageRequest): Page<Link> =
		repository.paginate(pageRequest)

}
