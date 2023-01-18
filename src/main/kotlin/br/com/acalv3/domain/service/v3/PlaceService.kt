package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.dto.FilterDTO
import br.com.acalv3.domain.model.v3.PlaceModel
import br.com.acalv3.domain.repository.v3.PlaceRepository
import br.com.acalv3.domain.service.AppService
import br.com.acalv3.domain.spec.PlaceSpec
import org.springframework.stereotype.Service

@Service
class PlaceService(
	val repository: PlaceRepository,
): AppService<PlaceModel>(repository, repository) {

    override fun findByName(name: String) =
    	repository.findByName(name)


	override fun pageable(filter: FilterDTO<PlaceModel>)  =
		repository.findAll(

			PlaceSpec(
				filter.model
			),

			getPage(filter)
		)

	override fun prepareForSave(u: PlaceModel) {
		super.prepareForSave(u)
		u.name = "${u.address?.addressType?.name} ${u.address?.name} ${u.number}${u.letter} "
	}

}