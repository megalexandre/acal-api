package br.com.acalv3.domain.enumeration

enum class Reason(val translate: String) {

	HYDROMETER("Hidrômetro"),
	DUE("Taxa"),
	CATEGORY("Categoria"),
	WATER("Água (Hidrômetro)"),
	ACCOUNT_PAYMENT("Pagamento de fatura")
	;

	companion object {
		fun byName(input: String?): Reason?  =
			Reason.values().firstOrNull { it.name.equals(input?.trim(), true) }
	}
}