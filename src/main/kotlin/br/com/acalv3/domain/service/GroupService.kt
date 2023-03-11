package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.model.page.CustomerPage
import br.com.acalv3.domain.model.page.GroupPage
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.domain.repository.GroupRepository
import br.com.acalv3.domain.service.strategies.customer.CustomerStrategy
import br.com.acalv3.domain.service.strategies.group.GroupStrategy
import org.springframework.data.domain.Page


abstract class GroupService: AbstractService<Group, GroupPage>() {
    abstract override fun strategies(): List<GroupStrategy<Group>>
    abstract override fun repository(): GroupRepository

    abstract fun findByName(name: String): Group
}
