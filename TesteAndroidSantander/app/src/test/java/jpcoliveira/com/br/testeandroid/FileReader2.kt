package jpcoliveira.com.br.testeandroid.util

import java.io.File
import java.io.FileNotFoundException
import java.util.*


object FileReader2 {

    fun readFile(fileName: String): String {

        val result = StringBuilder()
        val classLoader = FileReader2::class.java.classLoader
        val file = File(classLoader.getResource(fileName).file)

        try {
            val scanner = Scanner(file)
            while (scanner.hasNextLine()) {
                val line = scanner.nextLine()
                result.append(line)//.append("\n")
            }

            scanner.close()
            return result.toString()

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        throw RuntimeException("Cannot read file" + fileName)
    }
}