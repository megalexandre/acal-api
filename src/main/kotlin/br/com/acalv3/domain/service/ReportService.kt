package br.com.acalv3.domain.service

import br.com.acalv3.commons.DefaultReport

interface ReportService {
     fun print(defaultReport: DefaultReport): ByteArray
}
