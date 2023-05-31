package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.page.LinkPage

fun interface ReportService {

     fun link(link: LinkPage): ByteArray?

}
