package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.service.ReportService
import br.com.acalv3.resources.model.report.DefaultReport
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperExportManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

@Service
class ReportServiceImpl: ReportService {

    override fun print (
        default: DefaultReport
    ): ByteArray = JasperExportManager.exportReportToPdf(
        JasperFillManager.fillReport(
        JasperCompileManager.compileReport(ClassPathResource(default.report.location).inputStream),
        default.param,
        JRBeanCollectionDataSource(default.dataList)
    ))
}