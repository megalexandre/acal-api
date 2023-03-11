package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.Fixture.Companion.APPLICATION_PRODUCES
import br.com.acalv3.application.comunicate.Gateway.Companion.QUALITY
import br.com.acalv3.application.comunicate.model.quality.request.QualityPageRequest
import br.com.acalv3.application.comunicate.model.quality.request.QualityRequest
import br.com.acalv3.application.comunicate.model.quality.request.toPage
import br.com.acalv3.application.comunicate.model.quality.request.toQuality
import br.com.acalv3.application.comunicate.model.quality.response.QualityPageResponse
import br.com.acalv3.application.comunicate.model.quality.response.QualityResponse
import br.com.acalv3.application.comunicate.model.quality.response.toPageResponse
import br.com.acalv3.application.comunicate.model.quality.response.toQualityResponse
import br.com.acalv3.domain.service.QualityService
import javax.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(QUALITY, produces=[ APPLICATION_PRODUCES ])
class QualityGateway(
    val service: QualityService
) {

    @PostMapping
    fun save(@Valid @RequestBody request: QualityRequest) = service.save(request.toQuality()).toQualityResponse()

    @GetMapping("/{id}")
    fun find(@PathVariable id: String) = service.getById(id).toQualityResponse()

    @GetMapping("/list")
    fun list(): List<QualityResponse> = service.getAll().toQualityResponse()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) = service.delete(id)

   @PostMapping("/paginate")
   fun paginate(@Valid @RequestBody request: QualityPageRequest): Page<QualityPageResponse> =
       service.paginate(request.toPage()).toPageResponse()
}