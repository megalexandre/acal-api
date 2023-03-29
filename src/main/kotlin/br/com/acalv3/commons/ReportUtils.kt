package br.com.acalv3.commons

import br.com.acalv3.domain.enumeration.Report
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperExportManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import org.springframework.core.io.ClassPathResource


class ReportUtils{

    fun print (
        data: List<*>,
        param: HashMap<String, Any>,
        report: Report
    ): ByteArray = JasperExportManager.exportReportToPdf(JasperFillManager.fillReport(
        JasperCompileManager.compileReport(ClassPathResource(report.location).inputStream),
        param,
        JRBeanCollectionDataSource(data)
    ))
}

