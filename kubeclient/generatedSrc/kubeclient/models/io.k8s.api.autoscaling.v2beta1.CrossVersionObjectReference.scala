package kubeclient.io.k8s.api.autoscaling.v2beta1

import io.circe.{Encoder, Decoder}
import io.circe.generic.semiauto._

case class CrossVersionObjectReference(
    apiVersion: Option[String] = None,
    kind: String,
    name: String
)

object CrossVersionObjectReference {
  implicit val `io.k8s.api.autoscaling.v2beta1.CrossVersionObjectReference-Decoder`
      : Decoder[CrossVersionObjectReference] = deriveDecoder
  implicit val `io.k8s.api.autoscaling.v2beta1.CrossVersionObjectReference-Encoder`
      : Encoder[CrossVersionObjectReference] = deriveEncoder
}
