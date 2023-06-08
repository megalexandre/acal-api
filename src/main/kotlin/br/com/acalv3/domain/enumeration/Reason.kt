package br.com.acalv3.domain.enumeration

enum class Reason {

	HYDROMETER,
	DUE,
	CATEGORY,
	WATER,
	;

	companion object {
		fun byName(input: String?): Reason?  =
			Reason.values().firstOrNull { it.name.equals(input?.trim(), true) }
	}
}