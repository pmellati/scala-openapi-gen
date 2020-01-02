package kubeclient.io.k8s.api.apps.v1beta2

import kubeclient.io.k8s.apimachinery.pkg.apis.meta.v1.Time

import io.circe.{Encoder, Decoder}
import io.circe.generic.semiauto._

case class DeploymentCondition(
    lastUpdateTime: Option[Time] = None,
    reason: Option[String] = None,
    message: Option[String] = None,
    `type`: String,
    status: String,
    lastTransitionTime: Option[Time] = None
)

object DeploymentCondition {
  implicit val `io.k8s.api.apps.v1beta2.DeploymentCondition-Decoder`
      : Decoder[DeploymentCondition] = deriveDecoder
  implicit val `io.k8s.api.apps.v1beta2.DeploymentCondition-Encoder`
      : Encoder[DeploymentCondition] = deriveEncoder
}
