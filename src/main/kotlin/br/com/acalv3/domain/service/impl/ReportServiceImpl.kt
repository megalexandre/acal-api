package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.service.LinkService
import br.com.acalv3.domain.service.ReportService
import org.springframework.stereotype.Service

@Service
class ReportServiceImpl(
    val linkService: LinkService
    ): ReportService {

    override fun link(): ByteArray = linkService.report()
}