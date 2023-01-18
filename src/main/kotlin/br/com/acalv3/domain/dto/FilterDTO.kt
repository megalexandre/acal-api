package br.com.acalv3.domain.dto

class FilterDTO<T> (

	var model: T,
	var page: Page,
	var sort: SortField?= null,

)