package kubeclient.io.k8s.api.apps.v1

import io.circe.{Encoder, Decoder}
import io.circe.generic.semiauto._

case class DeploymentStrategy(
    rollingUpdate: Option[RollingUpdateDeployment] = None,
    `type`: Option[String] = None
)

object DeploymentStrategy {
  implicit val `io.k8s.api.apps.v1.DeploymentStrategy-Decoder`
      : Decoder[DeploymentStrategy] = deriveDecoder
  implicit val `io.k8s.api.apps.v1.DeploymentStrategy-Encoder`
      : Encoder[DeploymentStrategy] = deriveEncoder
}