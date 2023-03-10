package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Quality

interface QualityRepository {
    fun getById(id: String): Quality
    fun save(quality: Quality): Quality
}