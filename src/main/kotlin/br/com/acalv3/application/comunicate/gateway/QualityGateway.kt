package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.Fixture.Companion.APPLICATION_PRODUCES
import br.com.acalv3.application.comunicate.model.request.quality.QualityRequest
import br.com.acalv3.application.comunicate.model.request.quality.toQuality
import br.com.acalv3.application.comunicate.model.response.quality.QualityResponse
import br.com.acalv3.application.comunicate.model.response.quality.toQualityResponse
import br.com.acalv3.domain.service.QualityService
import javax.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("quality", produces=[ APPLICATION_PRODUCES ])
class QualityGateway(
    val service: QualityService
) {

    @PostMapping
    fun save(@Valid @RequestBody request: QualityRequest) =
        service.save(request.toQuality()).toQualityResponse()


    @GetMapping("/{id}")
    fun find(@PathVariable id: String): QualityResponse =
        service.getById(id).toQualityResponse()

    /*
   @GetMapping("/list")
   fun list(): List<QualityResponse> =
       service.getAll().toQualityResponse()


   @PostMapping("/paginate")
   fun paginate(@Valid @RequestBody request: AddressPageRequest): Page<AddressPageResponse> =
       service.paginate(request.toAddressPage()).toAddressPageResponse()

   @DeleteMapping("delete/{id}")
   fun delete(@PathVariable id: String) = service.delete(id)
   */
}