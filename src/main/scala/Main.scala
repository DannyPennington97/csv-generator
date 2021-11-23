import java.io.{BufferedWriter, File, FileWriter}
import java.nio.file.Files

import com.univocity.parsers.csv.{CsvWriter, CsvWriterSettings}

object Main extends App {

  val filepath = "./src/main/scala/data/"

  def generateValidFile(filename: String, rows: Int, data: String): Unit = {
    val start = System.currentTimeMillis()
    Files.deleteIfExists(new File(filepath + s"${filename}.csv").toPath)
    val writer = new BufferedWriter(new FileWriter(filepath +s"${filename}.csv"))
    val csvWriter = new CsvWriter(writer , new CsvWriterSettings())
    for (_ <- 0 until rows) {
      csvWriter.writeRow(data)
    }
    csvWriter.close()
    println(s"Took ${System.currentTimeMillis()-start} ms")
  }

  def generateInvalidFile(filename: String, rows: Int, data: String, data2: String): Unit = {
    val start = System.currentTimeMillis()
    Files.deleteIfExists(new File(filepath + s"${filename}.csv").toPath)
    val outputFile = new BufferedWriter(new FileWriter(filepath + s"${filename}.csv"))
    val csvWriter = new CsvWriter(outputFile ,new CsvWriterSettings())
    for (i <- 0 until rows) {
      if (i % 10000 == 0) {
        csvWriter.writeEmptyRow()
      } else if (i % 4 == 0) {
        csvWriter.writeRow(data2)
      }
      else {
        csvWriter.writeRow(data)
      }
    }
    csvWriter.close()
    println(s"Took ${System.currentTimeMillis()-start} ms")
  }

  val optionsGranted = "2015-09-23,250,123.12,12.1234,12.1234,no,yes,AB12345678,no" // CSOP_OptionsGranted_V3
  val optionsExercised = "2015-07-23,Jerry,Daniel,Springer,AB123456A,123/CD1234,2015-08-29,123.12,yes,no,12.1234,12.1234,12.1234,yes,AB12345678,yes,yes,12.1234,yes,yes" // CSOP_OptionsExercised_V3
  val emiAdjustments = "no,no,yes,3,2015-12-09,John,Barry,Doe,AA123456A,123/XZ55555555,10.1234,100.12,10.1234,10.1234" // EMI40_Adjustments_V3
  val emiRCL = "2015-12-09,yes,1,John,James,Smith,AA123456A,123/XZ55555555,123.12,yes,10.1234,yes" // EMI40_RCL_V3
  val otherOptions = "2015-01-02,yes,12345678,John,Adam,Williams,AA123456A,123/AB12345678,2015-01-03,Company Name,12 Example Street,Example,Exampley,Example-upon-River,United Kingdom,SE1 2AB,AB123456,1234567890,123/XZ12345678,Another Company Name,12 Test Street,Test,Testing,Testing-upon-River,United Kingdom,TE12 3ST,AB123456,1234567890,123/DC12345678,yes,123.12,12.1234,12.1234,no,yes,AB12345678,12.1234,yes,12.1234,yes,yes,yes" // Other_Options_V3
  val otherAcquisitions = "2015-01-03,yes,12345678,Julie,Erin,Smith,AB123456A,123/AB12345678,Company Name,12 Example Road,Example,Exampley,Example-upon-River,United Kingdom,TE123ST,AB123456,1234567890,123/AB12345678,1,yes,no,yes,12345678,123.12,1,2,0.6,12.1234,12.1234,yes,all,12.1234,12.1234,yes,yes,1,yes,yes,yes,no" // Other_Acquisitions_V3
  val invalidColumns = "2015-10-10,James"

  //generateValidFile("Other_Acquisition_V3", 1000, otherAcquisitions)
  generateInvalidFile("EMI40_RCL_V3", 1000, emiRCL, optionsExercised)

}
