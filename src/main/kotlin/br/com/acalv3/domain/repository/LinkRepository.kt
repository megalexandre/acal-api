package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.page.LinkPage
import java.util.UUID

interface LinkRepository:  AbstractRepository<Link, LinkPage>  {
    fun update(link: Link): Link
    fun findByName(name: String): Link?
    fun findByCustomerId(id: UUID): Link?
    fun findByGroupId(id: UUID): Link?
    fun findByPlaceId(id: UUID): Link?
    fun findActiveByPlaceId(id: UUID): Link?
    fun countActive(): Long
    fun inactivate(id: String): Link
    fun linkWithHydrometerByMonth(reference: String): List<Link>?
    fun findAll(reference: String): List<Link>
    fun report(link: LinkPage): ByteArray
    fun findHydrometerByReference(reference: String): List<Link>
}