package br.com.acalv3.integration

import br.com.acalv3.domain.model.AbstractNamedModel
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

abstract class AbstractTest<U: AbstractNamedModel>{

	abstract fun getMockMvcInstance() : MockMvc
	abstract fun getUrl(): String
	abstract fun getModel(): U
	abstract fun getClassType(): Class<U>

	@Autowired
	open lateinit var objectMapper: ObjectMapper

	fun save(): U {
		val result = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.post(getUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(getModel()))
		)
		.andDo(print())
		.andExpect(status().isOk)
		.andReturn()

		return castToAbstractModel(result = result)
	}

	fun trySaveDuplicated(){
		getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.post(getUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(getModel()))
		)
		.andDo(print())
		.andExpect(status().isBadRequest)
		.andReturn()
	}

	fun update(model: U): U {
		val result = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.put(getUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(model))
		)
		.andExpect(status().isOk)
		.andReturn()

		return castToAbstractModel(result = result)
	}

	fun getById(id: Long): U {
		val result = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.get(getUrl()+"/"+id)
				.contentType(MediaType.APPLICATION_JSON)
		)
		.andDo(print())
		.andExpect(status().isOk)
		.andReturn()

		return castToAbstractModel(result = result)
	}

	fun getterByName(): U {
		val result = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.get(getUrl()+"/name/"+ getModel().name )
				.contentType(MediaType.APPLICATION_JSON)
		)
		.andDo(print())
		.andExpect(status().isOk)
		.andReturn()

		return castToAbstractModel(result = result)
	}

	fun count(): String {
		val result = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.get(getUrl()+"/count")
				.contentType(MediaType.APPLICATION_JSON)
		)
		.andDo(print())
		.andExpect(status().isOk)
		.andReturn()

		return result.response.contentAsString
	}

	fun delete(u: AbstractNamedModel) {
		getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.delete("${getUrl()}/${u.id}")
				.contentType(MediaType.APPLICATION_JSON)
		)
		.andDo(print())
		.andExpect(status().isOk)
		.andReturn()
	}


	fun castResponseToMap(value: String): Map<String, String> {
		return ObjectMapper().readerFor(MutableMap::class.java).readValue(value)
	}

	private fun castToAbstractModel (result: MvcResult): U {
		return objectMapper.readValue(result.response.contentAsString, getClassType())
	}
}
