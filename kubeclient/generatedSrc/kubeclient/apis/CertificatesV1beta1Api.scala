package kubeclient

import cats.Applicative
import cats.data.Kleisli
import cats.effect.Sync

import _root_.io.circe._

import openapigen.ClientConfig

import org.http4s._, circe._, client.Client

import kubeclient.io.k8s.api.certificates.v1beta1.CertificateSigningRequest
import kubeclient.io.k8s.api.certificates.v1beta1.CertificateSigningRequestList
import kubeclient.io.k8s.apimachinery.pkg.apis.meta.v1.APIResourceList
import kubeclient.io.k8s.apimachinery.pkg.apis.meta.v1.DeleteOptions
import kubeclient.io.k8s.apimachinery.pkg.apis.meta.v1.Patch
import kubeclient.io.k8s.apimachinery.pkg.apis.meta.v1.Status
import kubeclient.io.k8s.apimachinery.pkg.apis.meta.v1.WatchEvent

object CertificatesV1beta1Api {
  private implicit def circeEncoderToHttp4sEntityEncoder[F[_]: Applicative, A: Encoder]
      : EntityEncoder[F, A] = jsonEncoderOf
  private implicit def circeDecoderToHttp4sEntityDecoder[F[_]: Sync, A: Decoder]
      : EntityDecoder[F, A] = jsonOf

  type Action[F[_], A] = Kleisli[F, ClientConfig[F], A]

  /** get available resources
    *

    */
  def getCertificatesV1beta1APIResources[F[_]: Applicative: Sync]
      : Action[F, APIResourceList] = Kleisli { _config =>
    val _path = s"""/apis/certificates.k8s.io/v1beta1/"""
    val _uri  = _config.baseApiUri.withPath(_path)

    val _uriWithQueryParams = _uri

    val _method = Method.GET

    val _request = Request[F](
      method = _method,
      uri = _uriWithQueryParams
    )

    _config.httpClient.expect[APIResourceList](_request)
  }

  /** list or watch objects of kind CertificateSigningRequest
    *
    *  @param pretty If 'true', then the output is pretty printed.
    *  @param allowWatchBookmarks allowWatchBookmarks requests watch events with type "BOOKMARK". Servers that do not implement bookmarks may ignore this flag and bookmarks are sent at the server's discretion. Clients should not assume bookmarks are returned at any specific interval, nor may they assume the server will send any BOOKMARK event during a session. If this is not a watch, this field is ignored. If the feature gate WatchBookmarks is not enabled in apiserver, this field is ignored.

This field is beta.
    *  @param continue The continue option should be set when retrieving more results from the server. Since this value is server defined, clients may only use the continue value from a previous query result with identical query parameters (except for the value of continue) and the server may reject a continue value it does not recognize. If the specified continue value is no longer valid whether due to expiration (generally five to fifteen minutes) or a configuration change on the server, the server will respond with a 410 ResourceExpired error together with a continue token. If the client needs a consistent list, it must restart their list without the continue field. Otherwise, the client may send another list request with the token received with the 410 error, the server will respond with a list starting from the next key, but from the latest snapshot, which is inconsistent from the previous list results - objects that are created, modified, or deleted after the first list request will be included in the response, as long as their keys are after the "next key".

This field is not supported when watch is true. Clients may start a watch from the last resourceVersion value returned by the server and not miss any modifications.
    *  @param fieldSelector A selector to restrict the list of returned objects by their fields. Defaults to everything.
    *  @param labelSelector A selector to restrict the list of returned objects by their labels. Defaults to everything.
    *  @param limit limit is a maximum number of responses to return for a list call. If more items exist, the server will set the `continue` field on the list metadata to a value that can be used with the same initial query to retrieve the next set of results. Setting a limit may return fewer than the requested amount of items (up to zero items) in the event all requested objects are filtered out and clients should only use the presence of the continue field to determine whether more results are available. Servers may choose not to support the limit argument and will return all of the available results. If limit is specified and the continue field is empty, clients may assume that no more results are available. This field is not supported if watch is true.

The server guarantees that the objects returned when using continue will be identical to issuing a single list call without a limit - that is, no objects created, modified, or deleted after the first request is issued will be included in any subsequent continued requests. This is sometimes referred to as a consistent snapshot, and ensures that a client that is using limit to receive smaller chunks of a very large result can ensure they see all possible objects. If objects are updated during a chunked list the version of the object that was present at the time the first list result was calculated is returned.
    *  @param resourceVersion When specified with a watch call, shows changes that occur after that particular version of a resource. Defaults to changes from the beginning of history. When specified for list: - if unset, then the result is returned from remote storage based on quorum-read flag; - if it's 0, then we simply return what we currently have in cache, no guarantee; - if set to non zero, then the result is at least as fresh as given rv.
    *  @param timeoutSeconds Timeout for the list/watch call. This limits the duration of the call, regardless of any activity or inactivity.
    *  @param watch Watch for changes to the described resources and return them as a stream of add, update, and remove notifications. Specify resourceVersion.
    */
  def listCertificatesV1beta1CertificateSigningRequest[F[_]: Applicative: Sync](
      pretty: Option[String] = None,
      allowWatchBookmarks: Option[Boolean] = None,
      continue: Option[String] = None,
      fieldSelector: Option[String] = None,
      labelSelector: Option[String] = None,
      limit: Option[Int] = None,
      resourceVersion: Option[String] = None,
      timeoutSeconds: Option[Int] = None,
      watch: Option[Boolean] = None
  ): Action[F, CertificateSigningRequestList] = Kleisli { _config =>
    val _path =
      s"""/apis/certificates.k8s.io/v1beta1/certificatesigningrequests"""
    val _uri = _config.baseApiUri.withPath(_path)

    val _uriWithQueryParams = _uri
      .withOptionQueryParam("pretty", pretty)
      .withOptionQueryParam("allowWatchBookmarks", allowWatchBookmarks)
      .withOptionQueryParam("continue", continue)
      .withOptionQueryParam("fieldSelector", fieldSelector)
      .withOptionQueryParam("labelSelector", labelSelector)
      .withOptionQueryParam("limit", limit)
      .withOptionQueryParam("resourceVersion", resourceVersion)
      .withOptionQueryParam("timeoutSeconds", timeoutSeconds)
      .withOptionQueryParam("watch", watch)

    val _method = Method.GET

    val _request = Request[F](
      method = _method,
      uri = _uriWithQueryParams
    )

    _config.httpClient.expect[CertificateSigningRequestList](_request)
  }

  /** create a CertificateSigningRequest
    *
    *  @param pretty If 'true', then the output is pretty printed.
    *  @param body null
    *  @param dryRun When present, indicates that modifications should not be persisted. An invalid or unrecognized dryRun directive will result in an error response and no further processing of the request. Valid values are: - All: all dry run stages will be processed
    *  @param fieldManager fieldManager is a name associated with the actor or entity that is making these changes. The value must be less than or 128 characters long, and only contain printable characters, as defined by https://golang.org/pkg/unicode/#IsPrint.
    */
  def createCertificatesV1beta1CertificateSigningRequest[F[_]: Applicative: Sync](
      pretty: Option[String] = None,
      body: CertificateSigningRequest,
      dryRun: Option[String] = None,
      fieldManager: Option[String] = None
  ): Action[F, CertificateSigningRequest] = Kleisli { _config =>
    val _path =
      s"""/apis/certificates.k8s.io/v1beta1/certificatesigningrequests"""
    val _uri = _config.baseApiUri.withPath(_path)

    val _uriWithQueryParams = _uri
      .withOptionQueryParam("pretty", pretty)
      .withOptionQueryParam("dryRun", dryRun)
      .withOptionQueryParam("fieldManager", fieldManager)

    val _method = Method.POST

    val _request = Request[F](
      method = _method,
      uri = _uriWithQueryParams
    ).withEntity(body)

    _config.httpClient.expect[CertificateSigningRequest](_request)
  }

  /** delete collection of CertificateSigningRequest
    *
    *  @param pretty If 'true', then the output is pretty printed.
    *  @param allowWatchBookmarks allowWatchBookmarks requests watch events with type "BOOKMARK". Servers that do not implement bookmarks may ignore this flag and bookmarks are sent at the server's discretion. Clients should not assume bookmarks are returned at any specific interval, nor may they assume the server will send any BOOKMARK event during a session. If this is not a watch, this field is ignored. If the feature gate WatchBookmarks is not enabled in apiserver, this field is ignored.

This field is beta.
    *  @param body null
    *  @param continue The continue option should be set when retrieving more results from the server. Since this value is server defined, clients may only use the continue value from a previous query result with identical query parameters (except for the value of continue) and the server may reject a continue value it does not recognize. If the specified continue value is no longer valid whether due to expiration (generally five to fifteen minutes) or a configuration change on the server, the server will respond with a 410 ResourceExpired error together with a continue token. If the client needs a consistent list, it must restart their list without the continue field. Otherwise, the client may send another list request with the token received with the 410 error, the server will respond with a list starting from the next key, but from the latest snapshot, which is inconsistent from the previous list results - objects that are created, modified, or deleted after the first list request will be included in the response, as long as their keys are after the "next key".

This field is not supported when watch is true. Clients may start a watch from the last resourceVersion value returned by the server and not miss any modifications.
    *  @param dryRun When present, indicates that modifications should not be persisted. An invalid or unrecognized dryRun directive will result in an error response and no further processing of the request. Valid values are: - All: all dry run stages will be processed
    *  @param fieldSelector A selector to restrict the list of returned objects by their fields. Defaults to everything.
    *  @param gracePeriodSeconds The duration in seconds before the object should be deleted. Value must be non-negative integer. The value zero indicates delete immediately. If this value is nil, the default grace period for the specified type will be used. Defaults to a per object value if not specified. zero means delete immediately.
    *  @param labelSelector A selector to restrict the list of returned objects by their labels. Defaults to everything.
    *  @param limit limit is a maximum number of responses to return for a list call. If more items exist, the server will set the `continue` field on the list metadata to a value that can be used with the same initial query to retrieve the next set of results. Setting a limit may return fewer than the requested amount of items (up to zero items) in the event all requested objects are filtered out and clients should only use the presence of the continue field to determine whether more results are available. Servers may choose not to support the limit argument and will return all of the available results. If limit is specified and the continue field is empty, clients may assume that no more results are available. This field is not supported if watch is true.

The server guarantees that the objects returned when using continue will be identical to issuing a single list call without a limit - that is, no objects created, modified, or deleted after the first request is issued will be included in any subsequent continued requests. This is sometimes referred to as a consistent snapshot, and ensures that a client that is using limit to receive smaller chunks of a very large result can ensure they see all possible objects. If objects are updated during a chunked list the version of the object that was present at the time the first list result was calculated is returned.
    *  @param orphanDependents Deprecated: please use the PropagationPolicy, this field will be deprecated in 1.7. Should the dependent objects be orphaned. If true/false, the "orphan" finalizer will be added to/removed from the object's finalizers list. Either this field or PropagationPolicy may be set, but not both.
    *  @param propagationPolicy Whether and how garbage collection will be performed. Either this field or OrphanDependents may be set, but not both. The default policy is decided by the existing finalizer set in the metadata.finalizers and the resource-specific default policy. Acceptable values are: 'Orphan' - orphan the dependents; 'Background' - allow the garbage collector to delete the dependents in the background; 'Foreground' - a cascading policy that deletes all dependents in the foreground.
    *  @param resourceVersion When specified with a watch call, shows changes that occur after that particular version of a resource. Defaults to changes from the beginning of history. When specified for list: - if unset, then the result is returned from remote storage based on quorum-read flag; - if it's 0, then we simply return what we currently have in cache, no guarantee; - if set to non zero, then the result is at least as fresh as given rv.
    *  @param timeoutSeconds Timeout for the list/watch call. This limits the duration of the call, regardless of any activity or inactivity.
    *  @param watch Watch for changes to the described resources and return them as a stream of add, update, and remove notifications. Specify resourceVersion.
    */
  def deleteCertificatesV1beta1CollectionCertificateSigningRequest[F[_]: Applicative: Sync](
      pretty: Option[String] = None,
      allowWatchBookmarks: Option[Boolean] = None,
      body: Option[DeleteOptions] = None,
      continue: Option[String] = None,
      dryRun: Option[String] = None,
      fieldSelector: Option[String] = None,
      gracePeriodSeconds: Option[Int] = None,
      labelSelector: Option[String] = None,
      limit: Option[Int] = None,
      orphanDependents: Option[Boolean] = None,
      propagationPolicy: Option[String] = None,
      resourceVersion: Option[String] = None,
      timeoutSeconds: Option[Int] = None,
      watch: Option[Boolean] = None
  ): Action[F, Status] = Kleisli { _config =>
    val _path =
      s"""/apis/certificates.k8s.io/v1beta1/certificatesigningrequests"""
    val _uri = _config.baseApiUri.withPath(_path)

    val _uriWithQueryParams = _uri
      .withOptionQueryParam("pretty", pretty)
      .withOptionQueryParam("allowWatchBookmarks", allowWatchBookmarks)
      .withOptionQueryParam("continue", continue)
      .withOptionQueryParam("dryRun", dryRun)
      .withOptionQueryParam("fieldSelector", fieldSelector)
      .withOptionQueryParam("gracePeriodSeconds", gracePeriodSeconds)
      .withOptionQueryParam("labelSelector", labelSelector)
      .withOptionQueryParam("limit", limit)
      .withOptionQueryParam("orphanDependents", orphanDependents)
      .withOptionQueryParam("propagationPolicy", propagationPolicy)
      .withOptionQueryParam("resourceVersion", resourceVersion)
      .withOptionQueryParam("timeoutSeconds", timeoutSeconds)
      .withOptionQueryParam("watch", watch)

    val _method = Method.DELETE

    val _request = Request[F](
      method = _method,
      uri = _uriWithQueryParams
    ).withEntity(body)

    _config.httpClient.expect[Status](_request)
  }

  /** read the specified CertificateSigningRequest
    *
    *  @param name name of the CertificateSigningRequest
    *  @param pretty If 'true', then the output is pretty printed.
    *  @param exact Should the export be exact.  Exact export maintains cluster-specific fields like 'Namespace'. Deprecated. Planned for removal in 1.18.
    *  @param export Should this value be exported.  Export strips fields that a user can not specify. Deprecated. Planned for removal in 1.18.
    */
  def readCertificatesV1beta1CertificateSigningRequest[F[_]: Applicative: Sync](
      name: String,
      pretty: Option[String] = None,
      exact: Option[Boolean] = None,
      export: Option[Boolean] = None
  ): Action[F, CertificateSigningRequest] = Kleisli { _config =>
    val _path =
      s"""/apis/certificates.k8s.io/v1beta1/certificatesigningrequests/${name}"""
    val _uri = _config.baseApiUri.withPath(_path)

    val _uriWithQueryParams = _uri
      .withOptionQueryParam("pretty", pretty)
      .withOptionQueryParam("exact", exact)
      .withOptionQueryParam("export", export)

    val _method = Method.GET

    val _request = Request[F](
      method = _method,
      uri = _uriWithQueryParams
    )

    _config.httpClient.expect[CertificateSigningRequest](_request)
  }

  /** replace the specified CertificateSigningRequest
    *
    *  @param name name of the CertificateSigningRequest
    *  @param pretty If 'true', then the output is pretty printed.
    *  @param body null
    *  @param dryRun When present, indicates that modifications should not be persisted. An invalid or unrecognized dryRun directive will result in an error response and no further processing of the request. Valid values are: - All: all dry run stages will be processed
    *  @param fieldManager fieldManager is a name associated with the actor or entity that is making these changes. The value must be less than or 128 characters long, and only contain printable characters, as defined by https://golang.org/pkg/unicode/#IsPrint.
    */
  def replaceCertificatesV1beta1CertificateSigningRequest[F[_]: Applicative: Sync](
      name: String,
      pretty: Option[String] = None,
      body: CertificateSigningRequest,
      dryRun: Option[String] = None,
      fieldManager: Option[String] = None
  ): Action[F, CertificateSigningRequest] = Kleisli { _config =>
    val _path =
      s"""/apis/certificates.k8s.io/v1beta1/certificatesigningrequests/${name}"""
    val _uri = _config.baseApiUri.withPath(_path)

    val _uriWithQueryParams = _uri
      .withOptionQueryParam("pretty", pretty)
      .withOptionQueryParam("dryRun", dryRun)
      .withOptionQueryParam("fieldManager", fieldManager)

    val _method = Method.PUT

    val _request = Request[F](
      method = _method,
      uri = _uriWithQueryParams
    ).withEntity(body)

    _config.httpClient.expect[CertificateSigningRequest](_request)
  }

  /** delete a CertificateSigningRequest
    *
    *  @param name name of the CertificateSigningRequest
    *  @param pretty If 'true', then the output is pretty printed.
    *  @param body null
    *  @param dryRun When present, indicates that modifications should not be persisted. An invalid or unrecognized dryRun directive will result in an error response and no further processing of the request. Valid values are: - All: all dry run stages will be processed
    *  @param gracePeriodSeconds The duration in seconds before the object should be deleted. Value must be non-negative integer. The value zero indicates delete immediately. If this value is nil, the default grace period for the specified type will be used. Defaults to a per object value if not specified. zero means delete immediately.
    *  @param orphanDependents Deprecated: please use the PropagationPolicy, this field will be deprecated in 1.7. Should the dependent objects be orphaned. If true/false, the "orphan" finalizer will be added to/removed from the object's finalizers list. Either this field or PropagationPolicy may be set, but not both.
    *  @param propagationPolicy Whether and how garbage collection will be performed. Either this field or OrphanDependents may be set, but not both. The default policy is decided by the existing finalizer set in the metadata.finalizers and the resource-specific default policy. Acceptable values are: 'Orphan' - orphan the dependents; 'Background' - allow the garbage collector to delete the dependents in the background; 'Foreground' - a cascading policy that deletes all dependents in the foreground.
    */
  def deleteCertificatesV1beta1CertificateSigningRequest[F[_]: Applicative: Sync](
      name: String,
      pretty: Option[String] = None,
      body: Option[DeleteOptions] = None,
      dryRun: Option[String] = None,
      gracePeriodSeconds: Option[Int] = None,
      orphanDependents: Option[Boolean] = None,
      propagationPolicy: Option[String] = None
  ): Action[F, Status] = Kleisli { _config =>
    val _path =
      s"""/apis/certificates.k8s.io/v1beta1/certificatesigningrequests/${name}"""
    val _uri = _config.baseApiUri.withPath(_path)

    val _uriWithQueryParams = _uri
      .withOptionQueryParam("pretty", pretty)
      .withOptionQueryParam("dryRun", dryRun)
      .withOptionQueryParam("gracePeriodSeconds", gracePeriodSeconds)
      .withOptionQueryParam("orphanDependents", orphanDependents)
      .withOptionQueryParam("propagationPolicy", propagationPolicy)

    val _method = Method.DELETE

    val _request = Request[F](
      method = _method,
      uri = _uriWithQueryParams
    ).withEntity(body)

    _config.httpClient.expect[Status](_request)
  }

  /** partially update the specified CertificateSigningRequest
    *
    *  @param name name of the CertificateSigningRequest
    *  @param pretty If 'true', then the output is pretty printed.
    *  @param body null
    *  @param dryRun When present, indicates that modifications should not be persisted. An invalid or unrecognized dryRun directive will result in an error response and no further processing of the request. Valid values are: - All: all dry run stages will be processed
    *  @param fieldManager fieldManager is a name associated with the actor or entity that is making these changes. The value must be less than or 128 characters long, and only contain printable characters, as defined by https://golang.org/pkg/unicode/#IsPrint. This field is required for apply requests (application/apply-patch) but optional for non-apply patch types (JsonPatch, MergePatch, StrategicMergePatch).
    *  @param force Force is going to "force" Apply requests. It means user will re-acquire conflicting fields owned by other people. Force flag must be unset for non-apply patch requests.
    */
  def patchCertificatesV1beta1CertificateSigningRequest[F[_]: Applicative: Sync](
      name: String,
      pretty: Option[String] = None,
      body: Patch,
      dryRun: Option[String] = None,
      fieldManager: Option[String] = None,
      force: Option[Boolean] = None
  ): Action[F, CertificateSigningRequest] = Kleisli { _config =>
    val _path =
      s"""/apis/certificates.k8s.io/v1beta1/certificatesigningrequests/${name}"""
    val _uri = _config.baseApiUri.withPath(_path)

    val _uriWithQueryParams = _uri
      .withOptionQueryParam("pretty", pretty)
      .withOptionQueryParam("dryRun", dryRun)
      .withOptionQueryParam("fieldManager", fieldManager)
      .withOptionQueryParam("force", force)

    val _method = Method.PATCH

    val _request = Request[F](
      method = _method,
      uri = _uriWithQueryParams
    ).withEntity(body)

    _config.httpClient.expect[CertificateSigningRequest](_request)
  }

  /** replace approval of the specified CertificateSigningRequest
    *
    *  @param dryRun When present, indicates that modifications should not be persisted. An invalid or unrecognized dryRun directive will result in an error response and no further processing of the request. Valid values are: - All: all dry run stages will be processed
    *  @param fieldManager fieldManager is a name associated with the actor or entity that is making these changes. The value must be less than or 128 characters long, and only contain printable characters, as defined by https://golang.org/pkg/unicode/#IsPrint.
    *  @param name name of the CertificateSigningRequest
    *  @param pretty If 'true', then the output is pretty printed.
    *  @param body null
    */
  def replaceCertificatesV1beta1CertificateSigningRequestApproval[F[_]: Applicative: Sync](
      dryRun: Option[String] = None,
      fieldManager: Option[String] = None,
      name: String,
      pretty: Option[String] = None,
      body: CertificateSigningRequest
  ): Action[F, CertificateSigningRequest] = Kleisli { _config =>
    val _path =
      s"""/apis/certificates.k8s.io/v1beta1/certificatesigningrequests/${name}/approval"""
    val _uri = _config.baseApiUri.withPath(_path)

    val _uriWithQueryParams = _uri
      .withOptionQueryParam("dryRun", dryRun)
      .withOptionQueryParam("fieldManager", fieldManager)
      .withOptionQueryParam("pretty", pretty)

    val _method = Method.PUT

    val _request = Request[F](
      method = _method,
      uri = _uriWithQueryParams
    ).withEntity(body)

    _config.httpClient.expect[CertificateSigningRequest](_request)
  }

  /** read status of the specified CertificateSigningRequest
    *
    *  @param name name of the CertificateSigningRequest
    *  @param pretty If 'true', then the output is pretty printed.
    */
  def readCertificatesV1beta1CertificateSigningRequestStatus[F[_]: Applicative: Sync](
      name: String,
      pretty: Option[String] = None
  ): Action[F, CertificateSigningRequest] = Kleisli { _config =>
    val _path =
      s"""/apis/certificates.k8s.io/v1beta1/certificatesigningrequests/${name}/status"""
    val _uri = _config.baseApiUri.withPath(_path)

    val _uriWithQueryParams = _uri
      .withOptionQueryParam("pretty", pretty)

    val _method = Method.GET

    val _request = Request[F](
      method = _method,
      uri = _uriWithQueryParams
    )

    _config.httpClient.expect[CertificateSigningRequest](_request)
  }

  /** replace status of the specified CertificateSigningRequest
    *
    *  @param name name of the CertificateSigningRequest
    *  @param pretty If 'true', then the output is pretty printed.
    *  @param body null
    *  @param dryRun When present, indicates that modifications should not be persisted. An invalid or unrecognized dryRun directive will result in an error response and no further processing of the request. Valid values are: - All: all dry run stages will be processed
    *  @param fieldManager fieldManager is a name associated with the actor or entity that is making these changes. The value must be less than or 128 characters long, and only contain printable characters, as defined by https://golang.org/pkg/unicode/#IsPrint.
    */
  def replaceCertificatesV1beta1CertificateSigningRequestStatus[F[_]: Applicative: Sync](
      name: String,
      pretty: Option[String] = None,
      body: CertificateSigningRequest,
      dryRun: Option[String] = None,
      fieldManager: Option[String] = None
  ): Action[F, CertificateSigningRequest] = Kleisli { _config =>
    val _path =
      s"""/apis/certificates.k8s.io/v1beta1/certificatesigningrequests/${name}/status"""
    val _uri = _config.baseApiUri.withPath(_path)

    val _uriWithQueryParams = _uri
      .withOptionQueryParam("pretty", pretty)
      .withOptionQueryParam("dryRun", dryRun)
      .withOptionQueryParam("fieldManager", fieldManager)

    val _method = Method.PUT

    val _request = Request[F](
      method = _method,
      uri = _uriWithQueryParams
    ).withEntity(body)

    _config.httpClient.expect[CertificateSigningRequest](_request)
  }

  /** partially update status of the specified CertificateSigningRequest
    *
    *  @param name name of the CertificateSigningRequest
    *  @param pretty If 'true', then the output is pretty printed.
    *  @param body null
    *  @param dryRun When present, indicates that modifications should not be persisted. An invalid or unrecognized dryRun directive will result in an error response and no further processing of the request. Valid values are: - All: all dry run stages will be processed
    *  @param fieldManager fieldManager is a name associated with the actor or entity that is making these changes. The value must be less than or 128 characters long, and only contain printable characters, as defined by https://golang.org/pkg/unicode/#IsPrint. This field is required for apply requests (application/apply-patch) but optional for non-apply patch types (JsonPatch, MergePatch, StrategicMergePatch).
    *  @param force Force is going to "force" Apply requests. It means user will re-acquire conflicting fields owned by other people. Force flag must be unset for non-apply patch requests.
    */
  def patchCertificatesV1beta1CertificateSigningRequestStatus[F[_]: Applicative: Sync](
      name: String,
      pretty: Option[String] = None,
      body: Patch,
      dryRun: Option[String] = None,
      fieldManager: Option[String] = None,
      force: Option[Boolean] = None
  ): Action[F, CertificateSigningRequest] = Kleisli { _config =>
    val _path =
      s"""/apis/certificates.k8s.io/v1beta1/certificatesigningrequests/${name}/status"""
    val _uri = _config.baseApiUri.withPath(_path)

    val _uriWithQueryParams = _uri
      .withOptionQueryParam("pretty", pretty)
      .withOptionQueryParam("dryRun", dryRun)
      .withOptionQueryParam("fieldManager", fieldManager)
      .withOptionQueryParam("force", force)

    val _method = Method.PATCH

    val _request = Request[F](
      method = _method,
      uri = _uriWithQueryParams
    ).withEntity(body)

    _config.httpClient.expect[CertificateSigningRequest](_request)
  }

  /** watch individual changes to a list of CertificateSigningRequest. deprecated: use the 'watch' parameter with a list operation instead.
    *
    *  @param allowWatchBookmarks allowWatchBookmarks requests watch events with type "BOOKMARK". Servers that do not implement bookmarks may ignore this flag and bookmarks are sent at the server's discretion. Clients should not assume bookmarks are returned at any specific interval, nor may they assume the server will send any BOOKMARK event during a session. If this is not a watch, this field is ignored. If the feature gate WatchBookmarks is not enabled in apiserver, this field is ignored.

This field is beta.
    *  @param continue The continue option should be set when retrieving more results from the server. Since this value is server defined, clients may only use the continue value from a previous query result with identical query parameters (except for the value of continue) and the server may reject a continue value it does not recognize. If the specified continue value is no longer valid whether due to expiration (generally five to fifteen minutes) or a configuration change on the server, the server will respond with a 410 ResourceExpired error together with a continue token. If the client needs a consistent list, it must restart their list without the continue field. Otherwise, the client may send another list request with the token received with the 410 error, the server will respond with a list starting from the next key, but from the latest snapshot, which is inconsistent from the previous list results - objects that are created, modified, or deleted after the first list request will be included in the response, as long as their keys are after the "next key".

This field is not supported when watch is true. Clients may start a watch from the last resourceVersion value returned by the server and not miss any modifications.
    *  @param fieldSelector A selector to restrict the list of returned objects by their fields. Defaults to everything.
    *  @param labelSelector A selector to restrict the list of returned objects by their labels. Defaults to everything.
    *  @param limit limit is a maximum number of responses to return for a list call. If more items exist, the server will set the `continue` field on the list metadata to a value that can be used with the same initial query to retrieve the next set of results. Setting a limit may return fewer than the requested amount of items (up to zero items) in the event all requested objects are filtered out and clients should only use the presence of the continue field to determine whether more results are available. Servers may choose not to support the limit argument and will return all of the available results. If limit is specified and the continue field is empty, clients may assume that no more results are available. This field is not supported if watch is true.

The server guarantees that the objects returned when using continue will be identical to issuing a single list call without a limit - that is, no objects created, modified, or deleted after the first request is issued will be included in any subsequent continued requests. This is sometimes referred to as a consistent snapshot, and ensures that a client that is using limit to receive smaller chunks of a very large result can ensure they see all possible objects. If objects are updated during a chunked list the version of the object that was present at the time the first list result was calculated is returned.
    *  @param pretty If 'true', then the output is pretty printed.
    *  @param resourceVersion When specified with a watch call, shows changes that occur after that particular version of a resource. Defaults to changes from the beginning of history. When specified for list: - if unset, then the result is returned from remote storage based on quorum-read flag; - if it's 0, then we simply return what we currently have in cache, no guarantee; - if set to non zero, then the result is at least as fresh as given rv.
    *  @param timeoutSeconds Timeout for the list/watch call. This limits the duration of the call, regardless of any activity or inactivity.
    *  @param watch Watch for changes to the described resources and return them as a stream of add, update, and remove notifications. Specify resourceVersion.
    */
  def watchCertificatesV1beta1CertificateSigningRequestList[F[_]: Applicative: Sync](
      allowWatchBookmarks: Option[Boolean] = None,
      continue: Option[String] = None,
      fieldSelector: Option[String] = None,
      labelSelector: Option[String] = None,
      limit: Option[Int] = None,
      pretty: Option[String] = None,
      resourceVersion: Option[String] = None,
      timeoutSeconds: Option[Int] = None,
      watch: Option[Boolean] = None
  ): Action[F, WatchEvent] = Kleisli { _config =>
    val _path =
      s"""/apis/certificates.k8s.io/v1beta1/watch/certificatesigningrequests"""
    val _uri = _config.baseApiUri.withPath(_path)

    val _uriWithQueryParams = _uri
      .withOptionQueryParam("allowWatchBookmarks", allowWatchBookmarks)
      .withOptionQueryParam("continue", continue)
      .withOptionQueryParam("fieldSelector", fieldSelector)
      .withOptionQueryParam("labelSelector", labelSelector)
      .withOptionQueryParam("limit", limit)
      .withOptionQueryParam("pretty", pretty)
      .withOptionQueryParam("resourceVersion", resourceVersion)
      .withOptionQueryParam("timeoutSeconds", timeoutSeconds)
      .withOptionQueryParam("watch", watch)

    val _method = Method.GET

    val _request = Request[F](
      method = _method,
      uri = _uriWithQueryParams
    )

    _config.httpClient.expect[WatchEvent](_request)
  }

  /** watch changes to an object of kind CertificateSigningRequest. deprecated: use the 'watch' parameter with a list operation instead, filtered to a single item with the 'fieldSelector' parameter.
    *
    *  @param allowWatchBookmarks allowWatchBookmarks requests watch events with type "BOOKMARK". Servers that do not implement bookmarks may ignore this flag and bookmarks are sent at the server's discretion. Clients should not assume bookmarks are returned at any specific interval, nor may they assume the server will send any BOOKMARK event during a session. If this is not a watch, this field is ignored. If the feature gate WatchBookmarks is not enabled in apiserver, this field is ignored.

This field is beta.
    *  @param continue The continue option should be set when retrieving more results from the server. Since this value is server defined, clients may only use the continue value from a previous query result with identical query parameters (except for the value of continue) and the server may reject a continue value it does not recognize. If the specified continue value is no longer valid whether due to expiration (generally five to fifteen minutes) or a configuration change on the server, the server will respond with a 410 ResourceExpired error together with a continue token. If the client needs a consistent list, it must restart their list without the continue field. Otherwise, the client may send another list request with the token received with the 410 error, the server will respond with a list starting from the next key, but from the latest snapshot, which is inconsistent from the previous list results - objects that are created, modified, or deleted after the first list request will be included in the response, as long as their keys are after the "next key".

This field is not supported when watch is true. Clients may start a watch from the last resourceVersion value returned by the server and not miss any modifications.
    *  @param fieldSelector A selector to restrict the list of returned objects by their fields. Defaults to everything.
    *  @param labelSelector A selector to restrict the list of returned objects by their labels. Defaults to everything.
    *  @param limit limit is a maximum number of responses to return for a list call. If more items exist, the server will set the `continue` field on the list metadata to a value that can be used with the same initial query to retrieve the next set of results. Setting a limit may return fewer than the requested amount of items (up to zero items) in the event all requested objects are filtered out and clients should only use the presence of the continue field to determine whether more results are available. Servers may choose not to support the limit argument and will return all of the available results. If limit is specified and the continue field is empty, clients may assume that no more results are available. This field is not supported if watch is true.

The server guarantees that the objects returned when using continue will be identical to issuing a single list call without a limit - that is, no objects created, modified, or deleted after the first request is issued will be included in any subsequent continued requests. This is sometimes referred to as a consistent snapshot, and ensures that a client that is using limit to receive smaller chunks of a very large result can ensure they see all possible objects. If objects are updated during a chunked list the version of the object that was present at the time the first list result was calculated is returned.
    *  @param name name of the CertificateSigningRequest
    *  @param pretty If 'true', then the output is pretty printed.
    *  @param resourceVersion When specified with a watch call, shows changes that occur after that particular version of a resource. Defaults to changes from the beginning of history. When specified for list: - if unset, then the result is returned from remote storage based on quorum-read flag; - if it's 0, then we simply return what we currently have in cache, no guarantee; - if set to non zero, then the result is at least as fresh as given rv.
    *  @param timeoutSeconds Timeout for the list/watch call. This limits the duration of the call, regardless of any activity or inactivity.
    *  @param watch Watch for changes to the described resources and return them as a stream of add, update, and remove notifications. Specify resourceVersion.
    */
  def watchCertificatesV1beta1CertificateSigningRequest[F[_]: Applicative: Sync](
      allowWatchBookmarks: Option[Boolean] = None,
      continue: Option[String] = None,
      fieldSelector: Option[String] = None,
      labelSelector: Option[String] = None,
      limit: Option[Int] = None,
      name: String,
      pretty: Option[String] = None,
      resourceVersion: Option[String] = None,
      timeoutSeconds: Option[Int] = None,
      watch: Option[Boolean] = None
  ): Action[F, WatchEvent] = Kleisli { _config =>
    val _path =
      s"""/apis/certificates.k8s.io/v1beta1/watch/certificatesigningrequests/${name}"""
    val _uri = _config.baseApiUri.withPath(_path)

    val _uriWithQueryParams = _uri
      .withOptionQueryParam("allowWatchBookmarks", allowWatchBookmarks)
      .withOptionQueryParam("continue", continue)
      .withOptionQueryParam("fieldSelector", fieldSelector)
      .withOptionQueryParam("labelSelector", labelSelector)
      .withOptionQueryParam("limit", limit)
      .withOptionQueryParam("pretty", pretty)
      .withOptionQueryParam("resourceVersion", resourceVersion)
      .withOptionQueryParam("timeoutSeconds", timeoutSeconds)
      .withOptionQueryParam("watch", watch)

    val _method = Method.GET

    val _request = Request[F](
      method = _method,
      uri = _uriWithQueryParams
    )

    _config.httpClient.expect[WatchEvent](_request)
  }

}