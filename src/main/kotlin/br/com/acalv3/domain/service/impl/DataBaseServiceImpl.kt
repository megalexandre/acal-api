package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.service.DataBaseService
import java.io.File
import java.io.IOException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class DataBaseServiceImpl(

    @Value("\${spring.datasource.username}")
    private val username: String,

    @Value("\${spring.datasource.password}")
    private val password: String,

    @Value("\${app.output}")
    private val output: String,

    ) : DataBaseService {

    @Throws(IOException::class, InterruptedException::class)
    override fun backup(): Boolean {
        val file = File(output)
        file.isFile

        val command = String.format(
            "mysqldump -u%s -p%s --add-drop-table --databases %s -r %s", username, password, "acaldb", output
        )
        val process = Runtime.getRuntime().exec(command)
        val processComplete = process.waitFor()
        return processComplete == 0
    }

}
