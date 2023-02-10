package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.model.page.GroupPage
import org.springframework.data.domain.Page

interface GroupRepository {
    fun getById(id: String): Group
    fun save(group: Group): Group
    fun update(group: Group): Group
    fun findByName(name: String): Group
    fun paginate(pageRequest: GroupPage): Page<Group>
    fun getAll(): List<Group>
}