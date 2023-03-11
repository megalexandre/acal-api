package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.repository.legacy.LegacyRepository
import br.com.acalv3.domain.service.LegacyService
import org.springframework.stereotype.Service

@Service
class LegacyServiceImpl(
    val repository: LegacyRepository,
): LegacyService {

	override fun person() {
		repository.person()
	}

	override fun address() {
		repository.address()
	}

}
