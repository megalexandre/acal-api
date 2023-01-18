package br.com.acalv3.domain.model

import java.time.LocalDateTime

interface AbstractNamedModel : AbstractModel {

    override var id: Long?
    var name: String?

    override var createdAt: LocalDateTime?
    override var createdBy: Long?

    override var lastModifiedAt: LocalDateTime?

    override var deletedAt: LocalDateTime?
    override var deletedBy: Long?
    override var deleted: Boolean?
}

