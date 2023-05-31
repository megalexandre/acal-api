package br.com.acalv3.application.comunication.gateway

import br.com.acalv3.application.comunication.model.request.quality.request.QualityPageRequest
import br.com.acalv3.application.comunication.model.request.quality.request.QualityRequest
import br.com.acalv3.application.comunication.model.request.quality.request.QualityUpdateRequest
import br.com.acalv3.application.comunication.model.request.quality.request.toPage
import br.com.acalv3.application.comunication.model.request.quality.request.toQuality
import br.com.acalv3.application.comunication.model.request.quality.response.QualityPageResponse
import br.com.acalv3.application.comunication.model.request.quality.response.QualityResponse
import br.com.acalv3.application.comunication.model.request.quality.response.toPageResponse
import br.com.acalv3.application.comunication.model.request.quality.response.toQualityResponse
import br.com.acalv3.domain.service.QualityService
import javax.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("quality", produces=[APPLICATION_JSON_VALUE])
class QualityController(
    val service: QualityService
) {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun save(@Valid @RequestBody request: QualityRequest) =
        service.save(request.toQuality()).toQualityResponse().also{
            logger.info("paginate quality: $request")
        }

    @GetMapping("/{id}")
    fun find(@PathVariable id: String) = service.getById(id).toQualityResponse().also{
        logger.info("find quality: $id")
    }

    @PutMapping("/update")
    fun update(@Valid @RequestBody request: QualityUpdateRequest) =
        service.update(request.toQuality()).toQualityResponse().also{
            logger.info("update quality: $request")
        }

    @GetMapping("/list")
    fun list(): List<QualityResponse> = service.getAll().toQualityResponse().also{
        logger.info("list quality")
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) = service.delete(id).also{
        logger.info("delete quality: $id")
    }

   @PostMapping("/paginate")
   fun paginate(@Valid @RequestBody request: QualityPageRequest): Page<QualityPageResponse> =
       service.paginate(request.toPage()).toPageResponse().also{
           logger.info("paginate quality: $request")
       }
}