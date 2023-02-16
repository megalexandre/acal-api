package br.com.acalv3.domain.enumeration

enum class Reason {

	HYDROMETER,
	CATEGORY;

	companion object {
		fun byName(input: String?): Reason?  =
			Reason.values().firstOrNull { it.name.equals(input?.trim(), true) }
	}
}