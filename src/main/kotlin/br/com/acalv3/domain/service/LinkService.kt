package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.page.LinkPage
import org.springframework.data.domain.Page

interface LinkService {
    fun getById(id: String): Link
    fun save(link: Link): Link
    fun update(link: Link): Link
    fun paginate(linkPageRequest: LinkPage): Page<Link>
    fun findByName(name: String): Link?
    fun findByCustomerId(customerId: String): Link?
    fun findByPlaceId(placeId: String): Link?
    fun findByGroupId(groupId: String): Link?
    fun count(): Long
    fun sumValues(): Long
    fun inactivate(id: String): Link
}