package br.com.acalv3.domain.enumeration

enum class Param(val value: String) {

	COLOR("Cor aparente - 15UH"),
	TURBIDITY("Turbidez - 5.0 UT") ,
	CHLORINE("Cloro - Min 0,2 mg/l"),
	ESCHERICHIA("Eschirichia Coli"),
	TOTAL_COLIFORMS("Coliformes Totais");

	companion object {
		fun byValue(input: String?): Param?  = values().firstOrNull { it.name.equals(input?.trim(), true) }
	}
}
