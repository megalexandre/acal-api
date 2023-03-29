package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.page.LinkPage
import br.com.acalv3.domain.service.LinkService
import br.com.acalv3.domain.service.ReportService
import org.springframework.stereotype.Service

@Service
class ReportServiceImpl(
    val linkService: LinkService
    ): ReportService {

    override fun link(link: LinkPage): ByteArray = linkService.report(link)
}