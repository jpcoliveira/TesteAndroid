package jpcoliveira.com.br.testeandroid.util

import android.content.Context
import java.io.FileNotFoundException
import java.util.*


object FileReader {

    fun readFile(context: Context, fileName: String): String {

        val result = StringBuilder()
        val file = context.assets.open(fileName)

        try {
            val scanner = Scanner(file)
            while (scanner.hasNextLine()) {
                val line = scanner.nextLine()
                result.append(line)
            }

            scanner.close()
            return result.toString()

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        throw RuntimeException("Cannot read file" + fileName)
    }
}