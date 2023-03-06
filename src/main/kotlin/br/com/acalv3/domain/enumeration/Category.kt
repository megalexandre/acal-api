package br.com.acalv3.domain.enumeration

enum class Category(val value: String) {

	FOUNDER("Fundador"),
	EFFECTIVE("Efetivo") ,
	TEMPORARY("Temporário");

	companion object {
		fun byValue(input: String?): Category?  =
			values().firstOrNull { it.name.equals(input?.trim(), true) }
	}
}