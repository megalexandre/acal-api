package br.com.acalv3.domain.service

import br.com.acalv3.resources.model.report.DefaultReport

interface ReportService {

     fun print(defaultReport: DefaultReport): ByteArray

}
