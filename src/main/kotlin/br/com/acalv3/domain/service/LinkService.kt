package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.page.LinkPage
import org.springframework.data.domain.Page

interface LinkService {
    fun report(): ByteArray
    fun getById(id: String): Link
    fun save(link: Link): Link
    fun update(link: Link): Link
    fun paginate(linkPageRequest: LinkPage): Page<Link>
    fun findAll(linkPageRequest: LinkPage): List<Link>
    fun findAll(reference: String): List<Link>
    fun findAll(): List<Link>
    fun findByName(name: String): Link?
    fun findByCustomerId(customerId: String): Link?
    fun findByPlaceId(placeId: String): Link?
    fun findByGroupId(groupId: String): Link?
    fun count(): Long
    fun countActive(): Long
    fun invoicing(): Long
    fun inactivate(id: String): Link
    fun linkWithHydrometerByMonth(reference: String): List<Link>?
}