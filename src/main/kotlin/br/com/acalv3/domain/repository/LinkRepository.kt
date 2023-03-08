package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.page.LinkPage
import org.springframework.data.domain.Page

interface LinkRepository {
    fun getById(id: String): Link
    fun save(link: Link): Link
    fun update(link: Link): Link
    fun findByName(name: String): Link?
    fun findByCustomerId(id: String): Link?
    fun findByGroupId(id: String): Link?
    fun findByPlaceId(id: String): Link?
    fun findActiveByPlaceId(id: String): Link?
    fun paginate(request: LinkPage): Page<Link>
    fun count(): Long
    fun sumValues(): Long
    fun inactivate(id: String): Link
}