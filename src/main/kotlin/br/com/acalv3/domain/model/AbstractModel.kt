package br.com.acalv3.domain.model

import java.time.LocalDateTime

interface AbstractModel {

    var id: Long?

    var createdAt: LocalDateTime?
    var createdBy: Long?

    var lastModifiedAt: LocalDateTime?

    var deletedAt: LocalDateTime?
    var deletedBy: Long?
    var deleted: Boolean?

}

