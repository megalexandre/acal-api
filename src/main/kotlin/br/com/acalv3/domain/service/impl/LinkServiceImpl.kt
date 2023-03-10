package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.enumeration.Action
import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.page.LinkPage
import br.com.acalv3.domain.repository.LinkRepository
import br.com.acalv3.domain.service.LinkService
import br.com.acalv3.domain.service.strategies.link.LinkStrategy
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class LinkServiceImpl(
	val repository: LinkRepository,
	val strategies: List<LinkStrategy>
): LinkService {

	override fun getById(id: String): Link = repository.getById(id)

	override fun save(link: Link): Link =
		strategies.first{ it.action() === Action.SAVE }.can(link).let {
			repository.save(link)
		}

	override fun update(link: Link): Link = repository.save(link)

	override fun findByName(name: String) = repository.findByName(name)

	override fun findByCustomerId(customerId: String): Link? = repository.findByCustomerId(customerId)

	override fun findByPlaceId(placeId: String): Link? = repository.findByPlaceId(placeId)

	override fun findByGroupId(groupId: String): Link? = repository.findByGroupId(groupId)

	override fun count(): Long = repository.count()

	override fun countActive(): Long = repository.countActive()

	override fun paginate(linkPageRequest: LinkPage): Page<Link> = repository.paginate(linkPageRequest)

	override fun findAll(linkPageRequest: LinkPage): List<Link> = repository.findAll(linkPageRequest)

	override fun invoicing(): Long = repository.invoicing()

	override fun inactivate(id: String) = repository.inactivate(id)

	override fun linkWithHydrometerByMonth(reference: String) = repository.linkWithHydrometerByMonth(reference)
}
