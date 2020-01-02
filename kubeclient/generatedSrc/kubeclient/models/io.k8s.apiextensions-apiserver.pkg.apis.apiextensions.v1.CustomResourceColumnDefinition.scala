package kubeclient.io.k8s.`apiextensions-apiserver`.pkg.apis.apiextensions.v1

import io.circe.{Encoder, Decoder}
import io.circe.generic.semiauto._

case class CustomResourceColumnDefinition(
    format: Option[String] = None,
    description: Option[String] = None,
    jsonPath: String,
    `type`: String,
    name: String,
    priority: Option[Int] = None
)

object CustomResourceColumnDefinition {
  implicit val `io.k8s.apiextensions-apiserver.pkg.apis.apiextensions.v1.CustomResourceColumnDefinition-Decoder`
      : Decoder[CustomResourceColumnDefinition] = deriveDecoder
  implicit val `io.k8s.apiextensions-apiserver.pkg.apis.apiextensions.v1.CustomResourceColumnDefinition-Encoder`
      : Encoder[CustomResourceColumnDefinition] = deriveEncoder
}
