package br.com.acalv3.domain.repository

import br.com.acalv3.application.comunicate.model.request.link.LinkPageRequest
import br.com.acalv3.domain.model.Link
import org.springframework.data.domain.Page

interface LinkRepository {
    fun getById(id: String): Link
    fun save(link: Link): Link
    fun update(link: Link): Link
    fun findByName(name: String): Link
    fun paginate(request: LinkPageRequest): Page<Link>
}