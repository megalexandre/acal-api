package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.page.LinkPage

interface LinkRepository:  AbstractRepository<Link, LinkPage>  {
    fun update(link: Link): Link
    fun findByName(name: String): Link?
    fun findByCustomerId(id: String): Link?
    fun findByGroupId(id: String): Link?
    fun findByPlaceId(id: String): Link?
    fun findActiveByPlaceId(id: String): Link?
    fun countActive(): Long
    fun invoicing(): Long
    fun inactivate(id: String): Link
    fun linkWithHydrometerByMonth(reference: String): List<Link>?
    fun findAll(reference: String): List<Link>
}