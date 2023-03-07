package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.Dashboard
import br.com.acalv3.domain.service.DashboardService
import org.springframework.stereotype.Service

@Service
class DashboardServiceImpl(
	val customerServiceImpl: CustomerServiceImpl,
	val linkServiceImpl: LinkServiceImpl,
): DashboardService {

	override fun get(): Dashboard =
		Dashboard(
			totalCustomer = customerServiceImpl.count(),
			totalLink = linkServiceImpl.count(),
			invoicing = linkServiceImpl.sumValues(),
		)
}
