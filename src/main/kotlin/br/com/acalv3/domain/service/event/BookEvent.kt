package br.com.acalv3.domain.service.event

import br.com.acalv3.domain.model.Book
import org.springframework.context.ApplicationEvent

class BookEvent(source: Any, val book: Book) : ApplicationEvent(source)
