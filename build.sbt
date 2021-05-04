lazy val scala212               = "2.12.13"
lazy val scala213               = "2.13.5"
lazy val supportedScalaVersions = List(scala213, scala212)
val JavaCIVersion               = "adopt@1.11"

lazy val utilsVersion      = "0.1.82" // for utilsTest
lazy val umlShaclexVersion = "0.0.81"
lazy val shexsVersion      = "0.1.86"

lazy val any23Version = "2.2"
lazy val rdf4jVersion = "2.2.4"

// Dependency versions
lazy val catsVersion         = "2.5.0"
lazy val commonsTextVersion  = "1.7"
lazy val circeVersion        = "0.14.0-M6"
lazy val graphvizJavaVersion = "0.5.2"
lazy val http4sVersion       = "1.0.0-M21"
// lazy val jgraphtVersion        = "1.3.1"
// lazy val kindProjectorVersion  = "0.11.3"
lazy val logbackVersion     = "1.2.3"
lazy val loggingVersion     = "3.9.2"
lazy val munitVersion       = "0.7.23"
lazy val munitEffectVersion = "1.0.1"
lazy val plantumlVersion    = "1.2017.12"
// lazy val scalacheckVersion     = "1.13.5"
// lazy val scalaGraphVersion     = "1.11.5"
// lazy val scalajVersion         = "2.4.2"
lazy val scalatagsVersion = "0.7.0"
// lazy val scallopVersion        = "3.3.1"
// lazy val seleniumVersion       = "2.45.0"
// lazy val silencerVersion       = "1.7.3"
// lazy val typesafeConfigVersion = "1.3.4"
lazy val mongodbVersion = "4.1.1"

// WebJars
lazy val jqueryVersion    = "3.4.1"
lazy val bootstrapVersion = "4.3.1"

// Scalaj
lazy val scalajVersion = "2.4.2"

// Compiler plugin dependency versions
lazy val scalaMacrosVersion = "2.1.1"

// Dependency modules
lazy val catsCore   = "org.typelevel" %% "cats-core"   % catsVersion
lazy val catsKernel = "org.typelevel" %% "cats-kernel" % catsVersion
/* lazy val catsMacros = "org.typelevel" %% "cats-macros" % catsVersion */
lazy val circeCore    = "io.circe"   %% "circe-core"    % circeVersion
lazy val circeGeneric = "io.circe"   %% "circe-generic" % circeVersion
lazy val circeParser  = "io.circe"   %% "circe-parser"  % circeVersion
lazy val circeLiteral = "io.circe"   %% "circe-literal" % circeVersion
lazy val graphvizJava = "guru.nidi"   % "graphviz-java" % graphvizJavaVersion
lazy val http4sDsl    = "org.http4s" %% "http4s-dsl"    % http4sVersion
lazy val http4sBlazeServer =
  "org.http4s" %% "http4s-blaze-server" % http4sVersion
lazy val http4sBlazeClient =
  "org.http4s" %% "http4s-blaze-client" % http4sVersion
lazy val http4sEmberClient =
  "org.http4s" %% "http4s-ember-client" % http4sVersion
lazy val http4sCirce    = "org.http4s"    %% "http4s-circe"    % http4sVersion
lazy val logbackClassic = "ch.qos.logback" % "logback-classic" % logbackVersion
lazy val munit          = "org.scalameta" %% "munit"           % munitVersion
lazy val munitEffect =
  "org.typelevel" %% "munit-cats-effect-3" % munitEffectVersion
lazy val plantuml = "net.sourceforge.plantuml" % "plantuml" % plantumlVersion
lazy val scalaLogging =
  "com.typesafe.scala-logging" %% "scala-logging" % loggingVersion
/* lazy val scallop = "org.rogach" %% "scallop" % scallopVersion */
/* lazy val scalaj = "org.scalaj" %% "scalaj-http" % scalajVersion */
lazy val scalatags = "com.lihaoyi" %% "scalatags" % scalatagsVersion

lazy val umlShaclex  = "es.weso" %% "umlshaclex"  % umlShaclexVersion
lazy val utilsTest   = "es.weso" %% "utilstest"   % utilsVersion
lazy val wikibaserdf = "es.weso" %% "wikibaserdf" % shexsVersion

lazy val any23_core = "org.apache.any23" % "apache-any23-core" % any23Version
lazy val any23_api  = "org.apache.any23" % "apache-any23-api"  % any23Version
lazy val any23_scraper =
  "org.apache.any23.plugins" % "apache-any23-html-scraper" % "2.2"
lazy val rdf4j_runtime = "org.eclipse.rdf4j" % "rdf4j-runtime" % rdf4jVersion

lazy val scalaj = "org.scalaj" %% "scalaj-http" % scalajVersion

lazy val jquery    = "org.webjars" % "jquery"    % jqueryVersion
lazy val bootstrap = "org.webjars" % "bootstrap" % bootstrapVersion

lazy val mongodb = "org.mongodb.scala" %% "mongo-scala-driver" % mongodbVersion

// Compiler plugin modules
/* lazy val scalaMacrosParadise = "org.scalamacros" % "paradise" %
 * scalaMacrosVersion cross CrossVersion.full */
/* lazy val simulacrum = "com.github.mpilquist" %% "simulacrum" %
 * simulacrumVersion */
/* lazy val kindProjector = "org.spire-math" %% "kind-projector" %
 * kindProjectorVersion */

lazy val MUnitFramework = new TestFramework("munit.Framework")

ThisBuild / githubWorkflowJavaVersions := Seq(JavaCIVersion)
ThisBuild / githubOwner := "weso"
ThisBuild / githubRepository := "shaclex"

lazy val rdfshape = project
  .in(file("."))
  .enablePlugins(
    ScalaUnidocPlugin,
    SiteScaladocPlugin,
    AsciidoctorPlugin,
    SbtNativePackager,
    WindowsPlugin,
    JavaAppPackaging,
    DockerPlugin
  )
  .disablePlugins(RevolverPlugin)
  .aggregate(server)
  .dependsOn(server)
  .settings(
    dockerExposedPorts ++= Seq(80),
    ScalaUnidoc / siteSubdirName := "scaladoc/latest",
    addMappingsToSiteDir(
      ScalaUnidoc / packageDoc / mappings,
      ScalaUnidoc / siteSubdirName
    ),
    ScalaUnidoc / unidoc / unidocProjectFilter := inAnyProject -- inProjects(
      noDocProjects: _*
    ),
    makeSite / mappings ++= Seq(
      file("src/assets/favicon.ico") -> "favicon.ico"
    ),
    libraryDependencies ++= Seq(
      logbackClassic,
      scalaLogging
      // scallop,
      /* compilerPlugin("com.github.ghik" %% "silencer-plugin" %
       * silencerVersion), */
      // "com.github.ghik" %% "silencer-lib" % silencerVersion % Provided
    ),
    cancelable in Global := true,
    fork := true,
    reStartArgs := Seq("--server"),
    crossScalaVersions := supportedScalaVersions
    // parallelExecution in Test := false
  )
  .settings(commonSettings, packagingSettings, publishSettings)

lazy val server = project
  .in(file("modules/server"))
  .enablePlugins(BuildInfoPlugin)
  .settings(commonSettings, publishSettings)
  .settings(
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "es.weso.rdfshape.buildinfo"
  )
  .settings(
    libraryDependencies ++= Seq(
      http4sDsl,
      http4sBlazeServer,
      http4sBlazeClient,
      http4sEmberClient,
      http4sCirce,
      scalatags,
      umlShaclex,
      wikibaserdf,
      any23_core,
      any23_api,
      any23_scraper,
      rdf4j_runtime,
      plantuml,
      graphvizJava,
      scalaj,
      utilsTest   % Test,
      munitEffect % Test,
      mongodb,
      // webJars
      jquery,
      bootstrap
    ),
    testFrameworks += MUnitFramework,
    crossScalaVersions := supportedScalaVersions
  )

/* ******************************************************** Grouped Settings
 * ******************** */

lazy val noDocProjects = Seq[ProjectReference]()

lazy val noPublishSettings = Seq(
  //  publish := (),
  //  publishLocal := (),
  publishArtifact := false
)

lazy val sharedDependencies = Seq(
  libraryDependencies ++= Seq(
//    scalactic,
//    scalaTest % Test
  )
)

lazy val packagingSettings = Seq(
  Compile / mainClass := Some("es.weso.rdfshape.Main"),
  assembly / mainClass := Some("es.weso.rdfshape.Main"),
  assembly / test := {},
  assembly / assemblyJarName := "rdfshape.jar",
  Linux / packageSummary := name.value,
  Windows / packageSummary := name.value,
  packageDescription := name.value
)

lazy val compilationSettings = Seq(
  // scalaVersion := "2.12.11",
  // format: off
  scalacOptions ++= Seq(
    "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
    "-encoding", "utf-8",                // Specify character encoding used by source files.
    "-explaintypes",                     // Explain type errors in more detail.
    "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.  "-encoding", "UTF-8",
    "-language:_",
    "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
    //    "-Xfuture",                          // Turn on future language features.
    //    "-Xlint",
    "-Yrangepos",
    //    "-Ylog-classpath",
    //    "-Yno-adapted-args",                 // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver
    //    "-Ywarn-dead-code",                  // Warn when dead code is identified.
    //    "-Ywarn-extra-implicit",             // Warn when more than one implicit parameter section is defined.
    //    "-Ywarn-inaccessible",               // Warn about inaccessible types in method signatures.
    //    "-Ywarn-infer-any",                  // Warn when a type argument is inferred to be `Any`.
    //    "-Ywarn-nullary-override",           // Warn when non-nullary `def f()' overrides nullary `def f'.
    //    "-Ywarn-nullary-unit",               // Warn when nullary methods return Unit.
    //    "-Ywarn-numeric-widen",              // Warn when numerics are widened.
    //    "-Ywarn-unused:implicits",           // Warn if an implicit parameter is unused.
    //    "-Ywarn-unused:imports",             // Warn if an import selector is not referenced.
    //    "-Ywarn-unused:locals",              // Warn if a local definition is unused.
    //    "-Ywarn-unused:params",              // Warn if a value parameter is unused.
    //    "-Ywarn-unused:patvars",             // Warn if a variable bound in a pattern is unused.
    //    "-Ywarn-unused:privates",            // Warn if a private member is unused.
    //    "-Ywarn-value-discard",              // Warn when non-Unit expression results are unused.
    //    "-Xfatal-warnings",                  // Fail the compilation if there are any warnings.
    //    "-Ypartial-unification",
  )
)

lazy val commonSettings = compilationSettings ++ sharedDependencies ++ Seq(
  organization := "es.weso",
  resolvers ++= Seq(
    Resolver.githubPackages("weso"),
    Resolver.sonatypeRepo("snapshots")
  )
)

lazy val publishSettings = Seq(
  homepage        := Some(url("https://github.com/labra/rdfshape")),
  licenses        := Seq("MIT" -> url("http://opensource.org/licenses/MIT")),
  scmInfo         := Some(ScmInfo(url("https://github.com/labra/rdfshape"), "scm:git:git@github.com:labra/rdfshape.git")),
  autoAPIMappings := true,
  apiURL          := Some(url("http://labra.github.io/rdfshape/latest/api/")),
  pomExtra        := <developers>
    <developer>
      <id>WESO</id>
      <name>WESO Research Group</name>
      <url>https://github.com/weso</url>
    </developer>
  </developers>,
  doc / scalacOptions  ++= Seq(
    "-diagrams-debug",
    "-doc-source-url",
    scmInfo.value.get.browseUrl + "/tree/master€{FILE_PATH}.scala",
    "-sourcepath",
    (LocalRootProject / baseDirectory).value.getAbsolutePath,
    "-diagrams",
  ),
  publishMavenStyle              := true,
  maintainer:= "info@weso.es",

  // publish as zip
  Universal / packageName := "rdfshape"
)

// Github token to be used by sbt-github-actions
githubTokenSource := TokenSource.Or(
  TokenSource.Environment("GITHUB_TOKEN"), // Injected during a github workflow for publishing
  TokenSource.Environment("SHELL")  // safe to assume this will be set in dev environments
)

// Lint-excluded setting/task keys
lazy val excludedLintKeys = Set(
  maintainer,
  rdfshape / reStartArgs,
  Universal / packageName,
  doc / scalacOptions,

)
Global / excludeLintKeys ++= excludedLintKeys
