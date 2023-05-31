package br.com.acalv3.application.comunication.gateway

import br.com.acalv3.application.comunication.model.request.place.PlacePageRequest
import br.com.acalv3.application.comunication.model.request.place.PlaceSaveRequest
import br.com.acalv3.application.comunication.model.request.place.PlaceUpdateRequest
import br.com.acalv3.application.comunication.model.request.place.toPlace
import br.com.acalv3.application.comunication.model.request.place.toPlacePage
import br.com.acalv3.application.comunication.model.response.place.PlaceGetResponse
import br.com.acalv3.application.comunication.model.response.place.PlacePageResponse
import br.com.acalv3.application.comunication.model.response.place.SaveUpdatePlaceResponse
import br.com.acalv3.application.comunication.model.response.place.toGetPlaceResponse
import br.com.acalv3.application.comunication.model.response.place.toPlacePageResponse
import br.com.acalv3.application.comunication.model.response.place.toPlaceResponse
import br.com.acalv3.domain.service.PlaceService
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
@RequestMapping("place",
    produces=[APPLICATION_JSON_VALUE],
)
class PlaceController(
    val service: PlaceService
) {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun save(@Valid @RequestBody request: PlaceSaveRequest): SaveUpdatePlaceResponse =
        service.save(request.toPlace()).toPlaceResponse().also{
            logger.info("save place: $request")
        }

    @PutMapping("/update")
    fun update(@Valid @RequestBody request: PlaceUpdateRequest) =
        service.update(request.toPlace()).toPlaceResponse().also{
            logger.info("update place: $request")
        }

    @PostMapping("/paginate")
    fun paginate(@Valid @RequestBody request: PlacePageRequest): Page<PlacePageResponse> =
        service.paginate(request.toPlacePage()).toPlacePageResponse().also{
            logger.info("paginate place: $request")
        }

    @GetMapping("/{id}")
    fun find(@PathVariable id: String): PlaceGetResponse =
        service.getById(id).toGetPlaceResponse().also{
            logger.info("find place: $id")
        }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) = service.delete(id).also{
        logger.info("delete place $id")
    }

}