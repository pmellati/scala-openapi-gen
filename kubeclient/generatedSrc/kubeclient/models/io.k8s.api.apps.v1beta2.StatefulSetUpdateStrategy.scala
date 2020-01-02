package kubeclient.io.k8s.api.apps.v1beta2

import io.circe.{Encoder, Decoder}
import io.circe.generic.semiauto._

case class StatefulSetUpdateStrategy(
    rollingUpdate: Option[RollingUpdateStatefulSetStrategy] = None,
    `type`: Option[String] = None
)

object StatefulSetUpdateStrategy {
  implicit val `io.k8s.api.apps.v1beta2.StatefulSetUpdateStrategy-Decoder`
      : Decoder[StatefulSetUpdateStrategy] = deriveDecoder
  implicit val `io.k8s.api.apps.v1beta2.StatefulSetUpdateStrategy-Encoder`
      : Encoder[StatefulSetUpdateStrategy] = deriveEncoder
}
