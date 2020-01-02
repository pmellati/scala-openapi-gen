package kubeclient.io.k8s.api.core.v1

import kubeclient.io.k8s.apimachinery.pkg.apis.meta.v1.ObjectMeta

import io.circe.{Encoder, Decoder}
import io.circe.generic.semiauto._

case class PersistentVolumeClaim(
    status: Option[PersistentVolumeClaimStatus] = None,
    spec: Option[PersistentVolumeClaimSpec] = None,
    metadata: Option[ObjectMeta] = None,
    kind: Option[String] = None,
    apiVersion: Option[String] = None
)

object PersistentVolumeClaim {
  implicit val `io.k8s.api.core.v1.PersistentVolumeClaim-Decoder`
      : Decoder[PersistentVolumeClaim] = deriveDecoder
  implicit val `io.k8s.api.core.v1.PersistentVolumeClaim-Encoder`
      : Encoder[PersistentVolumeClaim] = deriveEncoder
}
