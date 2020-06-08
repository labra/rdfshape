package es.weso.server

import cats.effect._
import org.http4s.Uri.uri
import org.http4s._
import org.http4s.dsl.Http4sDsl
import org.http4s.headers.Location

class LinksService[F[_]](blocker: Blocker)(implicit F: Effect[F], cs: ContextShift[F])
  extends Http4sDsl[F] {

  case class RequestData(domain: String, url: String)

  val routes: HttpRoutes[F] = HttpRoutes.of[F] {

    // data Info
    case GET -> Root / "links" / "i1" =>
      MovedPermanently(Location(uri("""/dataInfo?data=%40prefix%20%3A%20%20%20%20%20%20%3Chttp%3A%2F%2Fexample.org%2F%3E%20.%0A%40prefix%20schema%3A%20%3Chttp%3A%2F%2Fschema.org%2F%3E%20.%0A%40prefix%20item%3A%20%20%3Chttp%3A%2F%2Fdata.europeana.eu%2Fitem%2F04802%2F%3E%20.%0A%40prefix%20dbr%3A%20%20%20%3Chttp%3A%2F%2Fdbpedia.org%2Fresource%2F%3E%20.%0A%40prefix%20xsd%3A%20%20%20%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%20.%0A%40prefix%20dcterms%3A%20%3Chttp%3A%2F%2Fpurl.org%2Fdc%2Fterms%2F%3E%20.%0A%40prefix%20it%3A%20%20%20%20%3Chttp%3A%2F%2Fdata.example.org%2Fitem%2F%3E%20.%0A%40prefix%20wd%3A%20%20%20%20%3Chttp%3A%2F%2Fwww.wikidata.org%2Fentity%2F%3E%20.%0A%40prefix%20foaf%3A%20%20%3Chttp%3A%2F%2Fxmlns.com%2Ffoaf%2F0.1%2F%3E%20.%0A%0A%3Aalice%20%20a%20%20%20%20%20%20%20foaf%3APerson%20.%0A%0A%3Abob%20%20%20%20a%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20foaf%3APerson%20%3B%0A%20%20%20%20%20%20%20%20schema%3AbirthDate%20%20%20%20%20%221990-07-04%22%5E%5Exsd%3Adate%20%3B%0A%20%20%20%20%20%20%20%20foaf%3Aknows%20%20%20%20%20%20%20%20%20%20%20%3Chttp%3A%2F%2Fexample.org%2Falice%23me%3E%20%3B%0A%20%20%20%20%20%20%20%20foaf%3Atopic_interest%20%20wd%3AQ12418%20.%0A%0A%3Acarol%20%20a%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20foaf%3APerson%20%3B%0A%20%20%20%20%20%20%20%20schema%3AbirthDate%20%20%22unknown%22%20.%0A%0Awd%3AQ12418%20%20dcterms%3Acreator%20%20dbr%3ALeonardo_da_Vinci%20%3B%0A%20%20%20%20%20%20%20%20dcterms%3Atitle%20%20%20%20%22Mona%20Lisa%22%20.%0A%0Ait%3A243FA%20%20dcterms%3Asubject%20%20wd%3AQ12418%20%3B%0A%20%20%20%20%20%20%20%20dcterms%3Atitle%20%20%20%20%22La%20Joconde%20%C3%A0%20Washington%22%40fr%20.%0A&dataFormat=TURTLE&inference=NONE""")))

    // data conversions
    case GET -> Root / "links" / "i2" =>
      MovedPermanently(Location(uri("""/dataConversions?data=%40prefix%20%3A%20%20%20%20%20%20%3Chttp%3A%2F%2Fexample.org%2F%3E%20.%0A%40prefix%20schema%3A%20%3Chttp%3A%2F%2Fschema.org%2F%3E%20.%0A%40prefix%20item%3A%20%20%3Chttp%3A%2F%2Fdata.europeana.eu%2Fitem%2F04802%2F%3E%20.%0A%40prefix%20dbr%3A%20%20%20%3Chttp%3A%2F%2Fdbpedia.org%2Fresource%2F%3E%20.%0A%40prefix%20xsd%3A%20%20%20%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%20.%0A%40prefix%20dcterms%3A%20%3Chttp%3A%2F%2Fpurl.org%2Fdc%2Fterms%2F%3E%20.%0A%40prefix%20it%3A%20%20%20%20%3Chttp%3A%2F%2Fdata.example.org%2Fitem%2F%3E%20.%0A%40prefix%20wd%3A%20%20%20%20%3Chttp%3A%2F%2Fwww.wikidata.org%2Fentity%2F%3E%20.%0A%40prefix%20foaf%3A%20%20%3Chttp%3A%2F%2Fxmlns.com%2Ffoaf%2F0.1%2F%3E%20.%0A%0A%3Aalice%20%20a%20%20%20%20%20%20%20foaf%3APerson%20.%0A%0A%3Abob%20%20%20%20a%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20foaf%3APerson%20%3B%0A%20%20%20%20%20%20%20%20schema%3AbirthDate%20%20%20%20%20%221990-07-04%22%5E%5Exsd%3Adate%20%3B%0A%20%20%20%20%20%20%20%20foaf%3Aknows%20%20%20%20%20%20%20%20%20%20%20%3Chttp%3A%2F%2Fexample.org%2Falice%23me%3E%20%3B%0A%20%20%20%20%20%20%20%20foaf%3Atopic_interest%20%20wd%3AQ12418%20.%0A%0A%3Acarol%20%20a%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20foaf%3APerson%20%3B%0A%20%20%20%20%20%20%20%20schema%3AbirthDate%20%20%22unknown%22%20.%0A%0Awd%3AQ12418%20%20dcterms%3Acreator%20%20dbr%3ALeonardo_da_Vinci%20%3B%0A%20%20%20%20%20%20%20%20dcterms%3Atitle%20%20%20%20%22Mona%20Lisa%22%20.%0A%0Ait%3A243FA%20%20dcterms%3Asubject%20%20wd%3AQ12418%20%3B%0A%20%20%20%20%20%20%20%20dcterms%3Atitle%20%20%20%20%22La%20Joconde%20%C3%A0%20Washington%22%40fr%20.&amp;dataFormat=TURTLE&amp;targetDataFormat=JSON-LD&amp;inference=NONE""")
      ))

    // schema conversions
    case GET -> Root / "links" / "i3" =>
      MovedPermanently(Location(uri("/schemaConversions?schema=prefix%20schema%3A%20%3Chttp%3A%2F%2Fschema.org%2F%3E%20%0Aprefix%20xsd%3A%20%20%20%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%20%0Aprefix%20dcterms%3A%20%3Chttp%3A%2F%2Fpurl.org%2Fdc%2Fterms%2F%3E%20%0Aprefix%20it%3A%20%20%20%20%3Chttp%3A%2F%2Fdata.europeana.eu%2Fitem%2F%3E%20%0Aprefix%20foaf%3A%20%20%3Chttp%3A%2F%2Fxmlns.com%2Ffoaf%2F0.1%2F%3E%20%0A%0A%3CUser%3E%20IRI%20%7B%20%0A%20a%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%5B%20foaf%3APerson%20%5D%3B%20%0A%20schema%3AbirthDate%20%20%20%20%20xsd%3Adate%3F%20%20%3B%0A%20foaf%3Aknows%20%20%20%20%20%20%20%20%20%20%20%40%3CUser%3E*%20%3B%0A%20foaf%3Atopic_interest%20%20%40%3CTopic%3E%7B0%2C10%7D%0A%7D%0A%0A%3CTopic%3E%20%7B%0A%20%20dcterms%3Atitle%20%20%20xsd%3Astring%20%3B%0A%20%20dcterms%3Acreator%20IRI%20%3B%0A%20%20%5Edcterms%3Asubject%20%40%3CItem%3E%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%0A%7D%0A%0A%3CItem%3E%20%7B%0A%20%20dcterms%3Atitle%20%5B%40en%20%40fr%20%40es%5D%20%3B%0A%7D&amp;schemaFormat=ShExC&amp;&amp;schemaEngine=ShEx&amp;&amp;targetSchemaFormatTURTLE&amp;&amp;targetSchemaEngineShEx")))

    // Shema info
    case GET -> Root / "links" / "i4" =>
      MovedPermanently(Location(uri("""/schemaInfo?schema=prefix%20schema%3A%20%3Chttp%3A%2F%2Fschema.org%2F%3E%20%0Aprefix%20xsd%3A%20%20%20%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%20%0Aprefix%20dcterms%3A%20%3Chttp%3A%2F%2Fpurl.org%2Fdc%2Fterms%2F%3E%20%0Aprefix%20it%3A%20%20%20%20%3Chttp%3A%2F%2Fdata.europeana.eu%2Fitem%2F%3E%20%0Aprefix%20foaf%3A%20%20%3Chttp%3A%2F%2Fxmlns.com%2Ffoaf%2F0.1%2F%3E%20%0A%0A%3CUser%3E%20IRI%20%7B%20%0A%20a%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%5B%20foaf%3APerson%20%5D%3B%20%0A%20schema%3AbirthDate%20%20%20%20%20xsd%3Adate%3F%20%20%3B%0A%20foaf%3Aknows%20%20%20%20%20%20%20%20%20%20%20%40%3CUser%3E*%20%3B%0A%20foaf%3Atopic_interest%20%20%40%3CTopic%3E%7B0%2C10%7D%0A%7D%0A%0A%3CTopic%3E%20%7B%0A%20%20dcterms%3Atitle%20%20%20xsd%3Astring%20%3B%0A%20%20dcterms%3Acreator%20IRI%20%3B%0A%20%20%5Edcterms%3Asubject%20%40%3CItem%3E%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%0A%7D%0A%0A%3CItem%3E%20%7B%0A%20%20dcterms%3Atitle%20%5B%40en%20%40fr%20%40es%5D%20%3B%0A%7D&schemaFormat=ShExC&schemaEngine=ShEx""")))

    // ShEx validation
    case GET -> Root / "links" / "i5" =>
      MovedPermanently(Location(uri("/validate?data=%40prefix%20%3A%20%20%20%20%20%20%3Chttp%3A%2F%2Fexample.org%2F%3E%20.%0A%40prefix%20schema%3A%20%3Chttp%3A%2F%2Fschema.org%2F%3E%20.%0A%40prefix%20item%3A%20%20%3Chttp%3A%2F%2Fdata.europeana.eu%2Fitem%2F04802%2F%3E%20.%0A%40prefix%20dbr%3A%20%20%20%3Chttp%3A%2F%2Fdbpedia.org%2Fresource%2F%3E%20.%0A%40prefix%20xsd%3A%20%20%20%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%20.%0A%40prefix%20dcterms%3A%20%3Chttp%3A%2F%2Fpurl.org%2Fdc%2Fterms%2F%3E%20.%0A%40prefix%20it%3A%20%20%20%20%3Chttp%3A%2F%2Fdata.example.org%2Fitem%2F%3E%20.%0A%40prefix%20wd%3A%20%20%20%20%3Chttp%3A%2F%2Fwww.wikidata.org%2Fentity%2F%3E%20.%0A%40prefix%20foaf%3A%20%20%3Chttp%3A%2F%2Fxmlns.com%2Ffoaf%2F0.1%2F%3E%20.%0A%0A%3Aalice%20%20a%20%20%20%20%20%20%20foaf%3APerson%20.%0A%0A%3Abob%20%20%20%20a%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20foaf%3APerson%20%3B%0A%20%20%20%20%20%20%20%20schema%3AbirthDate%20%20%20%20%20%221990-07-04%22%5E%5Exsd%3Adate%20%3B%0A%20%20%20%20%20%20%20%20foaf%3Aknows%20%20%20%20%20%20%20%20%20%20%20%3Aalice%20%3B%0A%20%20%20%20%20%20%20%20foaf%3Atopic_interest%20%20wd%3AQ12418%20.%0A%0A%3Acarol%20%20a%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20foaf%3APerson%20%3B%0A%20%20%20%20%20%20%20%20schema%3AbirthDate%20%20%22unknown%22%20.%0A%0Awd%3AQ12418%20%20dcterms%3Acreator%20%20dbr%3ALeonardo_da_Vinci%20%3B%0A%20%20%20%20%20%20%20%20dcterms%3Atitle%20%20%20%20%22Mona%20Lisa%22%20.%0A%0Ait%3A243FA%20%20dcterms%3Asubject%20%20wd%3AQ12418%20%3B%0A%20%20%20%20%20%20%20%20dcterms%3Atitle%20%20%20%20%22La%20Joconde%20%C3%A0%20Washington%22%40fr%20.&amp;dataFormat=TURTLE&amp;schema=prefix%20schema%3A%20%3Chttp%3A%2F%2Fschema.org%2F%3E%20%0Aprefix%20xsd%3A%20%20%20%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%20%0Aprefix%20dcterms%3A%20%3Chttp%3A%2F%2Fpurl.org%2Fdc%2Fterms%2F%3E%20%0Aprefix%20it%3A%20%20%20%20%3Chttp%3A%2F%2Fdata.europeana.eu%2Fitem%2F%3E%20%0Aprefix%20foaf%3A%20%20%3Chttp%3A%2F%2Fxmlns.com%2Ffoaf%2F0.1%2F%3E%20%0A%0A%3CUser%3E%20IRI%20%7B%20%0A%20a%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%5B%20foaf%3APerson%20%5D%3B%20%0A%20schema%3AbirthDate%20%20%20%20%20xsd%3Adate%3F%20%20%3B%0A%20foaf%3Aknows%20%20%20%20%20%20%20%20%20%20%20%40%3CUser%3E*%20%3B%0A%20foaf%3Atopic_interest%20%20%40%3CTopic%3E%7B0%2C10%7D%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%0A%7D%0A%0A%3CTopic%3E%20%7B%0A%20%20dcterms%3Atitle%20%20%20xsd%3Astring%20%3B%0A%20%20dcterms%3Acreator%20IRI%20%3B%0A%20%20%5Edcterms%3Asubject%20%40%3CItem%3E%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%0A%7D%0A%0A%3CItem%3E%20%7B%0A%20%20dcterms%3Atitle%20%5B%40en%20%40fr%20%40es%5D%20%3B%0A%7D&amp;schemaFormat=ShExC&amp;schemaEngine=ShEx&amp;triggerMode=ShapeMap&amp;schemaEmbedded=false&amp;inference=NONE&amp;activeDataTab=%23dataTextArea&amp;activeSchemaTab=%23schemaTextArea&amp;activeShapeMapTab=%23shapeMapTextArea&amp;&amp;shapeMap=%3Abob%40%3CUser%3E%2C%3Acarol%40%3CUser%3E")))

    // SHACL validation
    case GET -> Root / "links" / "i6" =>
      MovedPermanently(Location(uri("""/validate?data=%40prefix%20%3A%20%20%20%20%20%20%3Chttp%3A%2F%2Fexample.org%2F%3E%20.%0A%40prefix%20schema%3A%20%3Chttp%3A%2F%2Fschema.org%2F%3E%20.%0A%40prefix%20item%3A%20%20%3Chttp%3A%2F%2Fdata.europeana.eu%2Fitem%2F04802%2F%3E%20.%0A%40prefix%20dbr%3A%20%20%20%3Chttp%3A%2F%2Fdbpedia.org%2Fresource%2F%3E%20.%0A%40prefix%20xsd%3A%20%20%20%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%20.%0A%40prefix%20dcterms%3A%20%3Chttp%3A%2F%2Fpurl.org%2Fdc%2Fterms%2F%3E%20.%0A%40prefix%20it%3A%20%20%20%20%3Chttp%3A%2F%2Fdata.example.org%2Fitem%2F%3E%20.%0A%40prefix%20wd%3A%20%20%20%20%3Chttp%3A%2F%2Fwww.wikidata.org%2Fentity%2F%3E%20.%0A%40prefix%20foaf%3A%20%20%3Chttp%3A%2F%2Fxmlns.com%2Ffoaf%2F0.1%2F%3E%20.%0A%0A%3Aalice%20%20a%20%20%20%20%20%20%20foaf%3APerson%20.%0A%0A%3Abob%20%20%20%20a%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20foaf%3APerson%20%3B%0A%20%20%20%20%20%20%20%20schema%3AbirthDate%20%20%20%20%20%221990-07-04%22%5E%5Exsd%3Adate%20%3B%0A%20%20%20%20%20%20%20%20foaf%3Aknows%20%20%20%20%20%20%20%20%20%20%20%3Aalice%20%3B%0A%20%20%20%20%20%20%20%20foaf%3Atopic_interest%20%20wd%3AQ12418%20.%0A%0A%3Acarol%20%20a%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20foaf%3APerson%20%3B%0A%20%20%20%20%20%20%20%20schema%3AbirthDate%20%20%22unknown%22%20.%0A%0Awd%3AQ12418%20%20dcterms%3Acreator%20%20dbr%3ALeonardo_da_Vinci%20%3B%0A%20%20%20%20%20%20%20%20dcterms%3Atitle%20%20%20%20%22Mona%20Lisa%22%20.%0A%0Ait%3A243FA%20%20dcterms%3Asubject%20%20wd%3AQ12418%20%3B%0A%20%20%20%20%20%20%20%20dcterms%3Atitle%20%20%20%20%22La%20Joconde%20%C3%A0%20Washington%22%40fr%20.&dataFormat=TURTLE&schema=prefix%20%3A%20%20%20%20%20%20%20%3Chttp%3A%2F%2Fexample.org%2F%3E%20%0Aprefix%20sh%3A%20%20%20%20%20%3Chttp%3A%2F%2Fwww.w3.org%2Fns%2Fshacl%23%3E%20%0Aprefix%20xsd%3A%20%20%20%20%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0Aprefix%20schema%3A%20%3Chttp%3A%2F%2Fschema.org%2F%3E%0Aprefix%20foaf%3A%20%20%20%3Chttp%3A%2F%2Fxmlns.com%2Ffoaf%2F0.1%2F%3E%0Aprefix%20rdfs%3A%20%20%20%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0Aprefix%20dcterms%3A%20%3Chttp%3A%2F%2Fpurl.org%2Fdc%2Fterms%2F%3E%20%0A%20%20%20%20%20%20%20%20%0A%3CUser%3E%20a%20sh%3ANodeShape%20%3B%0A%20%20%20sh%3AtargetClass%20foaf%3APerson%20%3B%0A%20%20%20sh%3Aproperty%20%5B%0A%20%20%20sh%3Apath%20schema%3AbirthDate%20%3B%20%0A%20%20%20sh%3AmaxCount%201%3B%0A%20%20%20sh%3Adatatype%20xsd%3Adate%20%3B%0A%20%5D%20%3B%0A%20sh%3Aproperty%20%5B%0A%20%20%20%20sh%3Apath%20foaf%3Aknows%20%3B%20%0A%20%20%20%20sh%3Anode%20%3CUser%3E%20%3B%0A%20%20%5D%20%3B%20%20%0A%20sh%3Aproperty%20%5B%0A%20%20%20%20sh%3Apath%20foaf%3Atopic_interest%20%3B%20%0A%20%20%20%20sh%3Anode%20%3CTopic%3E%20%3B%0A%20%20%5D%20%3B%20%20%0A.%0A%0A%3CTopic%3E%20a%20sh%3ANodeShape%20%3B%0A%20%20%20sh%3Aproperty%20%5B%0A%20%20%20sh%3Apath%20dcterms%3Atitle%20%3B%20%0A%20%20%20sh%3AminCount%201%3B%20%20%20%20%20%20%20%20%0A%20%20%20sh%3AmaxCount%201%3B%0A%20%20%20sh%3Adatatype%20xsd%3Astring%20%3B%0A%20%5D%20%3B%0A%20sh%3Aproperty%20%5B%0A%20%20%20%20sh%3Apath%20dcterms%3Acreator%20%3B%20%0A%20%20%20%20sh%3AnodeKind%20sh%3AIRI%20%3B%0A%20%20%5D%20%3B%20%20%0A%20sh%3Aproperty%20%5B%0A%20%20%20%20sh%3Apath%20%5B%20sh%3AinversePath%20dcterms%3Asubject%20%5D%20%20%3B%20%0A%20%20%20%20sh%3Anode%20%3CItem%3E%20%20%20%20%20%20%20%20%0A%20%5D.%0A%0A%0A%3CItem%3E%20a%20sh%3ANodeShape%20%3B%0A%20sh%3Aproperty%20%5B%0A%20%20%20sh%3Apath%20dcterms%3Atitle%20%3B%20%0A%20%20%20sh%3AminCount%201%3B%20%20%20%20%20%20%20%20%0A%20%20%20sh%3AmaxCount%201%3B%0A%20%20%20sh%3AlanguageIn%20(%22en%22%20%22fr%22%20%22es%22)%0A%20%5D%20.&schemaFormat=Turtle&schemaEngine=SHACLex&triggerMode=TargetDecls&schemaEmbedded=false&inference=NONE&activeDataTab=%23dataTextArea&activeSchemaTab=%23schemaTextArea&activeShapeMapTab=%23shapeMapTextArea&&shapeMap=%3Abob%40%3CUser%3E%2C%3Acarol%40%3CUser%3E""")))

    // SPARQL endpoint
    case GET -> Root / "links" / "i7" =>
      MovedPermanently(Location(uri("/validate?data=&amp;dataFormat=TURTLE&amp;schema=PREFIX%20%3A%20%20%20%20%20%20%20%3Chttp%3A%2F%2Fexample.org%2F%3E%0APREFIX%20schema%3A%20%3Chttp%3A%2F%2Fschema.org%2F%3E%0APREFIX%20xsd%3A%20%20%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0APREFIX%20wdt%3A%20%3Chttp%3A%2F%2Fwww.wikidata.org%2Fprop%2Fdirect%2F%3E%0APREFIX%20geo%3A%20%3Chttp%3A%2F%2Fwww.opengis.net%2Font%2Fgeosparql%23%3E%0A%3CPlace%3E%20%7B%0A%20%20wdt%3AP625%20geo%3AwktLiteral%0A%7D%20&amp;schemaFormat=ShExC&amp;schemaEngine=ShEx&amp;triggerMode=ShapeMap&amp;schemaEmbedded=false&amp;inference=NONE&amp;activeDataTab=%23dataEndpoint&amp;activeSchemaTab=%23schemaTextArea&amp;activeShapeMapTab=%23shapeMapTextArea&amp;&amp;shapeMap=%3Chttp%3A%2F%2Fwww.wikidata.org%2Fentity%2FQ99%3E%40%3CPlace%3E&amp;endpoint=https://query.wikidata.org/sparql")))

    // Inference
    case GET -> Root / "links" / "i8" =>
      MovedPermanently(Location(uri("/validate?data=%40prefix%20schema%3A%20%3Chttp%3A%2F%2Fschema.org%2F%3E%20.%0A%40prefix%20%3A%20%20%20%20%20%20%3Chttp%3A%2F%2Fexample.org%2F%3E%20.%0A%40prefix%20item%3A%20%20%3Chttp%3A%2F%2Fdata.europeana.eu%2Fitem%2F04802%2F%3E%20.%0A%40prefix%20dbr%3A%20%20%20%3Chttp%3A%2F%2Fdbpedia.org%2Fresource%2F%3E%20.%0A%40prefix%20xsd%3A%20%20%20%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%20.%0A%40prefix%20dcterms%3A%20%3Chttp%3A%2F%2Fpurl.org%2Fdc%2Fterms%2F%3E%20.%0A%40prefix%20rdfs%3A%20%20%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%20.%0A%40prefix%20it%3A%20%20%20%20%3Chttp%3A%2F%2Fdata.example.org%2Fitem%2F%3E%20.%0A%40prefix%20wd%3A%20%20%20%20%3Chttp%3A%2F%2Fwww.wikidata.org%2Fentity%2F%3E%20.%0A%40prefix%20foaf%3A%20%20%3Chttp%3A%2F%2Fxmlns.com%2Ffoaf%2F0.1%2F%3E%20.%0A%0Ait%3A243FA%20%20dcterms%3Asubject%20%20wd%3AQ12418%20%3B%0A%20%20%20%20%20%20%20%20dcterms%3Atitle%20%20%20%20%22La%20Joconde%20%C3%A0%20Washington%22%40fr%20.%0A%0A%3Abob%20%20%20%20a%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20foaf%3APerson%20%3B%0A%20%20%20%20%20%20%20%20schema%3AbirthDate%20%20%20%20%20%221990-07-04%22%5E%5Exsd%3Adate%20%3B%0A%20%20%20%20%20%20%20%20foaf%3Aknows%20%20%20%20%20%20%20%20%20%20%20%3Aalice%20%3B%0A%20%20%20%20%20%20%20%20foaf%3Atopic_interest%20%20wd%3AQ12418%20.%0A%0A%3Acarol%20%20a%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20foaf%3APerson%20%3B%0A%20%20%20%20%20%20%20%20schema%3AbirthDate%20%20%22unknown%22%20.%0A%0Awd%3AQ12418%20%20dcterms%3Acreator%20%20dbr%3ALeonardo_da_Vinci%20%3B%0A%20%20%20%20%20%20%20%20dcterms%3Atitle%20%20%20%20%22Mona%20Lisa%22%20.%0A%0A%3Aalice%20a%20%3AUser%20.%0A%0A%3AUser%20rdfs%3AsubClassOf%20foaf%3APerson%20.%0A%0A&amp;dataFormat=TURTLE&amp;schema=prefix%20schema%3A%20%3Chttp%3A%2F%2Fschema.org%2F%3E%20%0Aprefix%20xsd%3A%20%20%20%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%20%0Aprefix%20dcterms%3A%20%3Chttp%3A%2F%2Fpurl.org%2Fdc%2Fterms%2F%3E%20%0Aprefix%20it%3A%20%20%20%20%3Chttp%3A%2F%2Fdata.europeana.eu%2Fitem%2F%3E%20%0Aprefix%20foaf%3A%20%20%3Chttp%3A%2F%2Fxmlns.com%2Ffoaf%2F0.1%2F%3E%20%0A%0A%3CUser%3E%20IRI%20EXTRA%20a%20%7B%20%0A%20a%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%5B%20foaf%3APerson%20%5D%3B%20%0A%20schema%3AbirthDate%20%20%20%20%20xsd%3Adate%3F%20%20%3B%0A%20foaf%3Aknows%20%20%20%20%20%20%20%20%20%20%20%40%3CUser%3E*%20%3B%0A%20foaf%3Atopic_interest%20%20%40%3CTopic%3E%7B0%2C10%7D%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%0A%7D%0A%0A%3CTopic%3E%20%7B%0A%20%20dcterms%3Atitle%20%20%20xsd%3Astring%20%3B%0A%20%20dcterms%3Acreator%20IRI%20%3B%0A%20%20%5Edcterms%3Asubject%20%40%3CItem%3E%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%0A%7D%0A%0A%3CItem%3E%20%7B%0A%20%20dcterms%3Atitle%20%5B%40en%20%40fr%20%40es%5D%20%3B%0A%7D&amp;schemaFormat=ShExC&amp;schemaEngine=ShEx&amp;triggerMode=ShapeMap&amp;schemaEmbedded=false&amp;inference=RDFS&amp;activeDataTab=%23dataTextArea&amp;activeSchemaTab=%23schemaTextArea&amp;activeShapeMapTab=%23shapeMapTextArea&amp;&amp;shapeMap=%3Abob%40%3CUser%3E%2C%3Acarol%40%3CUser%3E")))

    // SPARQL
    case GET -> Root / "links" / "i9" =>
      MovedPermanently(Location(uri("""/query?data=%40prefix%20schema%3A%20%3Chttp%3A%2F%2Fschema.org%2F%3E%20.%0A%40prefix%20item%3A%20%20%3Chttp%3A%2F%2Fdata.europeana.eu%2Fitem%2F04802%2F%3E%20.%0A%40prefix%20dbr%3A%20%20%20%3Chttp%3A%2F%2Fdbpedia.org%2Fresource%2F%3E%20.%0A%40prefix%20xsd%3A%20%20%20%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%20.%0A%40prefix%20dcterms%3A%20%3Chttp%3A%2F%2Fpurl.org%2Fdc%2Fterms%2F%3E%20.%0A%40prefix%20it%3A%20%20%20%20%3Chttp%3A%2F%2Fdata.example.org%2Fitem%2F%3E%20.%0A%40prefix%20wd%3A%20%20%20%20%3Chttp%3A%2F%2Fwww.wikidata.org%2Fentity%2F%3E%20.%0A%40prefix%20foaf%3A%20%20%3Chttp%3A%2F%2Fxmlns.com%2Ffoaf%2F0.1%2F%3E%20.%0A%0Ait%3A243FA%20%20dcterms%3Asubject%20%20wd%3AQ12418%20%3B%0A%20%20%20%20%20%20%20%20dcterms%3Atitle%20%20%20%20%22La%20Joconde%20%C3%A0%20Washington%22%40fr%20.%0A%0A%3Chttp%3A%2F%2Fexample.org%2Fbob%23me%3E%0A%20%20%20%20%20%20%20%20a%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20foaf%3APerson%20%3B%0A%20%20%20%20%20%20%20%20schema%3AbirthDate%20%20%20%20%20%221990-07-04%22%5E%5Exsd%3Adate%20%3B%0A%20%20%20%20%20%20%20%20foaf%3Aknows%20%20%20%20%20%20%20%20%20%20%20%3Chttp%3A%2F%2Fexample.org%2Falice%23me%3E%20%3B%0A%20%20%20%20%20%20%20%20foaf%3Atopic_interest%20%20wd%3AQ12418%20.%0A%0A%3Chttp%3A%2F%2Fexample.org%2Falice%23me%3E%0A%20%20%20%20%20%20%20%20a%20%20%20%20%20%20%20foaf%3APerson%20.%0A%0A%3Chttp%3A%2F%2Fexample.org%2Fcarol%23me%3E%0A%20%20%20%20%20%20%20%20a%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20foaf%3APerson%20%3B%0A%20%20%20%20%20%20%20%20schema%3AbirthDate%20%20%22unknown%22%20.%0A%0Awd%3AQ12418%20%20dcterms%3Acreator%20%20dbr%3ALeonardo_da_Vinci%20%3B%0A%20%20%20%20%20%20%20%20dcterms%3Atitle%20%20%20%20%22Mona%20Lisa%22%20.%0A&dataFormat=TURTLE&query=prefix%20schema%3A%20%3Chttp%3A%2F%2Fschema.org%2F%3E%20%0Aprefix%20item%3A%20%20%3Chttp%3A%2F%2Fdata.europeana.eu%2Fitem%2F04802%2F%3E%20%0Aprefix%20dbr%3A%20%20%20%3Chttp%3A%2F%2Fdbpedia.org%2Fresource%2F%3E%20%0Aprefix%20xsd%3A%20%20%20%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%20%0Aprefix%20dcterms%3A%20%3Chttp%3A%2F%2Fpurl.org%2Fdc%2Fterms%2F%3E%20%0Aprefix%20it%3A%20%20%20%20%3Chttp%3A%2F%2Fdata.example.org%2Fitem%2F%3E%20%0Aprefix%20wd%3A%20%20%20%20%3Chttp%3A%2F%2Fwww.wikidata.org%2Fentity%2F%3E%20%0Aprefix%20foaf%3A%20%20%3Chttp%3A%2F%2Fxmlns.com%2Ffoaf%2F0.1%2F%3E%20%0A%0Aselect%20%3Fitem%20%3Ftopic%20%3Fperson%20where%20%7B%0A%20%20%3Fperson%20foaf%3Atopic_interest%20%3Ftopic%20.%0A%20%20%3Fitem%20%20%20dcterms%3Asubject%20%3Ftopic%20%0A%7D&&inference=NONE&activeDataTab=%23dataTextArea&activeQueryTab=%23queryTextArea""")))

    // Wikidata
    case GET -> Root / "links" / "i10" =>
      MovedPermanently(Location(uri("""/load?examples=http://www.validatingrdf.com/examples/wikidata/manifest.json""")))

    // FHIR
    case GET -> Root / "links" / "i11" =>
      MovedPermanently(Location(uri("""/load?examples=https://raw.githubusercontent.com/BD2KOnFHIR/FHIRDevDays2017/master/shex_examples.json""")))
  }
}

object LinksService {
  def apply[F[_]: Effect: ContextShift](blocker: Blocker): LinksService[F] =
    new LinksService[F](blocker)
}
