package es.weso.rdfshape.server.utils.secure

import es.weso.rdfshape.server.utils.error.exceptions.SSLContextCreationException

import java.io.FileInputStream
import java.nio.file.Paths
import java.security.{KeyStore, SecureRandom}
import javax.net.ssl.{KeyManagerFactory, SSLContext, TrustManagerFactory}

object SSLHelper {
  lazy val keyStorePassword: Option[String] = sys.env.get("KEYSTORE_PASSWORD")
  lazy val keyManagerPassword: Option[String] =
    sys.env.get("KEYMANAGER_PASSWORD")
  lazy val keyStorePath: Option[String] = sys.env.get("KEYSTORE_PATH")

  def getContext: SSLContext = {

    if(
      keyStorePassword.isEmpty ||
      keyManagerPassword.isEmpty ||
      keyStorePath.isEmpty
    ) {
      throw SSLContextCreationException("Some environment variables are missing.")
    }

    val keyStore            = loadKeystore(keyStorePassword.get)
    val keyManagerFactory   = getKeyManager(keyStore, keyStorePassword.get)
    val trustManagerFactory = getTrustManager(keyStore)

    val sslContext = SSLContext.getInstance("TLS")
    sslContext.init(
      keyManagerFactory.getKeyManagers,
      trustManagerFactory.getTrustManagers,
      new SecureRandom()
    )
    sslContext
  }

  private def loadKeystore(keyStorePassword: String): KeyStore = {
    val in = new FileInputStream(
      Paths.get(keyStorePath.get).toAbsolutePath.toString
    )
    val keyStore = KeyStore.getInstance(KeyStore.getDefaultType)
    keyStore.load(in, keyStorePassword.toCharArray)
    keyStore
  }

  private def getKeyManager(keyStore: KeyStore, keyStorePassword: String) = {
    val keyManagerFactory =
      KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm)
    keyManagerFactory.init(keyStore, keyStorePassword.toCharArray)
    keyManagerFactory
  }

  private def getTrustManager(keyStore: KeyStore) = {
    val trustManagerFactory = TrustManagerFactory.getInstance(
      TrustManagerFactory.getDefaultAlgorithm
    )
    trustManagerFactory.init(keyStore)
    trustManagerFactory
  }
}
