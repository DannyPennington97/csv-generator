
import java.io.{BufferedReader, BufferedWriter, File, FileReader, FileWriter}
import java.nio.file.Files

import com.univocity.parsers.csv.{CsvParser, CsvParserSettings, CsvWriter, CsvWriterSettings}

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer
import akka.Done
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.adapter._
import akka.http.scaladsl._
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.model.headers.Accept
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, MediaRanges}
import akka.stream.scaladsl.{Sink, Source}
import akka.util.ByteString

import scala.concurrent.Future

trait Helper {


  def fileFromName(name: String): File = {
    new File(s"./src/main/scala/data/$name")
  }

  def parseFile(filename: String): Unit = {
    var ohNoCounter = 0
    println("Starting new parser...")

    val start = System.currentTimeMillis()
    val settings: CsvParserSettings = new CsvParserSettings()
    settings.getFormat.setLineSeparator("\n")
    val parser: CsvParser = new CsvParser(settings)

    val data = fileFromName(s"${filename}.csv")

    // We want to actually do something with each entry to simulate the service
    val output = parser.iterate(data).asScala
    output.foreach(row => {
      row.foreach(cell => if (cell.contains("oh no")) {ohNoCounter += 1})
    })
    println(s"Oh no count: $ohNoCounter")
    println(s"Took ${System.currentTimeMillis()-start} ms")
  }


  def currentFileParser(filename: String): Unit = {
    var ohNoCounter = 0
    println("Starting old parser...")
    val start = System.currentTimeMillis()

    val reader = new BufferedReader(new FileReader(s"./src/main/scala/data/$filename.csv"))
    val iterator = reader.lines().iterator().asScala

    val rows: ListBuffer[List[String]] = new ListBuffer()
    while (iterator.hasNext) {
      val row = iterator.next().split(",").toList
      rows += row
    }

    rows.toList.foreach( row => {
      row.foreach(cell => if (cell.contains("oh no")) {ohNoCounter += 1})
    })
    println(s"Oh no count: $ohNoCounter")
    println(s"Took ${System.currentTimeMillis()-start} ms")
  }




}
