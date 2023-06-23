package br.com.acalv3.commons

import br.com.acalv3.domain.enumeration.Param.CHLORINE
import br.com.acalv3.domain.enumeration.Param.COLOR
import br.com.acalv3.domain.enumeration.Param.ESCHERICHIA
import br.com.acalv3.domain.enumeration.Param.TOTAL_COLIFORMS
import br.com.acalv3.domain.enumeration.Param.TURBIDITY
import br.com.acalv3.domain.enumeration.Report
import br.com.acalv3.domain.model.Quality
import br.com.acalv3.resources.model.report.GatheringReport

class DefaultReport(
    val dataList: List<*>,
    val report: Report,
    val param: HashMap<String, Any>,
)

class DataQualityReport(
    val reference: String,
    val color: GatheringReport = GatheringReport(COLOR.name),
    val turbidity: GatheringReport = GatheringReport(TURBIDITY.name),
    val chlorine: GatheringReport = GatheringReport(CHLORINE.name),
    val escherichia: GatheringReport = GatheringReport(ESCHERICHIA.name),
    val totalColiforms: GatheringReport = GatheringReport(TOTAL_COLIFORMS.name),
)

fun Quality.toDataQualityReport() = DataQualityReport(
    reference = reference,
    color = GatheringReport(COLOR.name),
    turbidity = GatheringReport(TURBIDITY.name),
    chlorine = GatheringReport(CHLORINE.name),
    escherichia = GatheringReport(ESCHERICHIA.name),
    totalColiforms = GatheringReport(TOTAL_COLIFORMS.name),
)