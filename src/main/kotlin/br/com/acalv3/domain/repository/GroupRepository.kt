package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.model.page.GroupPage

interface GroupRepository:  AbstractRepository<Group, GroupPage>{
    fun findGroup(group: Group): Group?
    fun update(group: Group): Group
    fun findByName(name: String): Group
}