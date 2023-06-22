package br.com.acalv3.resources.model.report

import br.com.acalv3.domain.model.Gathering

class GatheringReport(
    val param: String,
    var required: String = "0",
    var analyzed: String = "0",
    var conformity: String = "0"
)

fun Gathering.toReport() = GatheringReport(
    param = param.value,
    required = required.toString(),
    analyzed = analyzed.toString(),
    conformity =  conformity.toString(),
)

fun List<Gathering>.toReport() = map{ it.toReport() }