package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.model.page.GroupPage
import org.springframework.data.domain.Page

interface GroupService {
    fun getById(id: String): Group
    fun save(group: Group): Group
    fun update(group: Group): Group
    fun paginate(pageRequest: GroupPage): Page<Group>
    fun findByName(name: String): Group
    fun getAll(): List<Group>
    fun delete(id: String)

}