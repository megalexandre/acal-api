package br.com.acalv3.domain.service.strategies.quality

import br.com.acalv3.domain.enumeration.Action
import br.com.acalv3.domain.model.Quality

interface QualityStrategy  {
    fun action(): Action
    fun can(quality: Quality)
}