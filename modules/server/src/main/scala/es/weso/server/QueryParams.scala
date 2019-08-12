package es.weso.server

import org.http4s.dsl.io.{OptionalQueryParamDecoderMatcher, QueryParamDecoderMatcher}

object QueryParams {
  lazy val data = "data"
  lazy val dataURL = "dataURL"
  lazy val endpoint = "endpoint"
  lazy val dataFormat= "dataFormat"
  lazy val targetDataFormat = "targetDataFormat"
  lazy val schema = "schema"
  lazy val entity = "entity"
  lazy val node = "node"
  lazy val withDot = "withDot"
  lazy val schemaURL = "schemaURL"
  lazy val schemaFormat = "schemaFormat"
  lazy val shape = "shape"
  object DataParameter extends QueryParamDecoderMatcher[String](data)
  object OptDataParam extends OptionalQueryParamDecoderMatcher[String](data)
  object OptEndpointParam extends OptionalQueryParamDecoderMatcher[String](endpoint)
  object OptDataURLParam extends OptionalQueryParamDecoderMatcher[String](dataURL)
  object DataFormatParam extends OptionalQueryParamDecoderMatcher[String](dataFormat)
  object TargetDataFormatParam extends OptionalQueryParamDecoderMatcher[String](targetDataFormat)
  object OptSchemaParam extends OptionalQueryParamDecoderMatcher[String](schema)
  object OptEntityParam extends OptionalQueryParamDecoderMatcher[String](entity)
  object OptNodeParam extends OptionalQueryParamDecoderMatcher[String](node)
  object OptWithDotParam extends OptionalQueryParamDecoderMatcher[Boolean](withDot)
  object SchemaURLParam extends OptionalQueryParamDecoderMatcher[String](schemaURL)
  object SchemaFormatParam extends OptionalQueryParamDecoderMatcher[String](schemaFormat)
  object OptNodeSelectorParam extends OptionalQueryParamDecoderMatcher[String]("nodeSelector")
  object SchemaEngineParam extends OptionalQueryParamDecoderMatcher[String]("schemaEngine")
  object TargetSchemaFormatParam extends OptionalQueryParamDecoderMatcher[String]("targetSchemaFormat")
  object TargetSchemaEngineParam extends OptionalQueryParamDecoderMatcher[String]("targetSchemaEngine")
  object OptTriggerModeParam extends OptionalQueryParamDecoderMatcher[String]("triggerMode")
  object NodeParam extends OptionalQueryParamDecoderMatcher[String](node)
  object ShapeParam extends OptionalQueryParamDecoderMatcher[String](shape)
//   object NameParam extends OptionalQueryParamDecoderMatcher[String]("name")
  object ShapeMapParameter extends OptionalQueryParamDecoderMatcher[String]("shapeMap")
  object ShapeMapParameterAlt extends OptionalQueryParamDecoderMatcher[String]("shape-map")
  object ShapeMapURLParameter extends OptionalQueryParamDecoderMatcher[String]("shapeMapURL")
  object ShapeMapFileParameter extends OptionalQueryParamDecoderMatcher[String]("shapeMapFile")
  object ShapeMapFormatParam extends OptionalQueryParamDecoderMatcher[String]("shapeMapFormat")
  object SchemaEmbedded extends OptionalQueryParamDecoderMatcher[Boolean]("schemaEmbedded")
  object InferenceParam extends OptionalQueryParamDecoderMatcher[String]("inference")
  object ExamplesParam extends OptionalQueryParamDecoderMatcher[String]("examples")
  object ManifestURLParam extends OptionalQueryParamDecoderMatcher[String]("manifestURL")
  object OptExamplesParam extends OptionalQueryParamDecoderMatcher[String]("examples")
  object OptQueryParam extends OptionalQueryParamDecoderMatcher[String]("query")
  object OptActiveDataTabParam extends OptionalQueryParamDecoderMatcher[String]("activeDataTab")
  object OptActiveSchemaTabParam extends OptionalQueryParamDecoderMatcher[String]("activeSchemaTab")
  object OptActiveShapeMapTabParam extends OptionalQueryParamDecoderMatcher[String]("activeShapeMapTab")
  object OptActiveQueryTabParam extends OptionalQueryParamDecoderMatcher[String]("activeQueryTab")

}