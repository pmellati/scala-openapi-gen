package kubeclient.io.k8s.api.core.v1

import kubeclient.io.k8s.apimachinery.pkg.apis.meta.v1.ObjectMeta

import io.circe.{Encoder, Decoder}
import io.circe.generic.semiauto._

case class ConfigMap(
    data: Option[Map[String, String]] = None,
    binaryData: Option[Map[String, String]] = None,
    metadata: Option[ObjectMeta] = None,
    kind: Option[String] = None,
    apiVersion: Option[String] = None
)

object ConfigMap {
  implicit val `io.k8s.api.core.v1.ConfigMap-Decoder`: Decoder[ConfigMap] =
    deriveDecoder
  implicit val `io.k8s.api.core.v1.ConfigMap-Encoder`: Encoder[ConfigMap] =
    deriveEncoder
}