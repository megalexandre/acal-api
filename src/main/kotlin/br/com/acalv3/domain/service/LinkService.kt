package br.com.acalv3.domain.service

import br.com.acalv3.application.comunicate.model.request.link.LinkPageRequest
import br.com.acalv3.domain.model.Link
import org.springframework.data.domain.Page
import java.util.*

interface LinkService {
    fun getById(id: UUID): Link
    fun save(link: Link): Link
    fun update(link: Link): Link
    fun paginate(linkPageRequest: LinkPageRequest): Page<Link>
    fun findByName(name: String): Link
}