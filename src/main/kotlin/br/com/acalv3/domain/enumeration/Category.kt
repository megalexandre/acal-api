package br.com.acalv3.domain.enumeration

enum class Category{

	FOUNDER,
	EFFECTIVE,
	TEMPORARY;

	companion object {

		fun byValue(input: String?): Category?  =
			values().firstOrNull { it.name.equals(input?.trim(), true) }
	}
}