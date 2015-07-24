L-RdfToVirtuosoAndCkan
----------

### Documentation

* see [Plugin Documentation](./doc/About.md)
* see [Plugin Documentation](./doc/About_sk.md) (in Slovak)

### Technical notes

These properties have to be set in backend.properties of UnifiedViews for correct functionality of DPU

| Property Name | Description |
|:----|:----|
|`dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern` | URL  pattern to build RDF graph name in Virtuoso storage |
|`dpu.uv-l-rdfToVirtuosoAndCkan.resource.name` | Name of the CKAN resource to be used, has precedence over distributionMetadata input |
|`org.opendatanode.CKAN.secret.token`| Token used to authenticate to CKAN, has to be set in backend.properties |
|`org.opendatanode.CKAN.api.url`|URL where CKAN api is located, has to be set in backend.properties |
|`org.opendatanode.CKAN.http.header.[key]`| Custom HTTP header added to requests on CKAN |


#### Examples

```INI
org.opendatanode.CKAN.secret.token = 12345678901234567890123456789012
org.opendatanode.CKAN.api.url = ﻿http://localhost:9080/internalcatalog/api/action/internal_api
org.opendatanode.CKAN.http.header.X-Forwarded-Host = www.myopendatanode.org
org.opendatanode.CKAN.http.header.X-Forwarded-Proto = https
```

```INI
dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern = https://host/internalcatalog/dataset/${id}
```
example Virtuoso graph name https://host/internalcatalog/dataset/452fddaa-c469-4b78-823d-320fb1bd8646.
Notice the 452fddaa-c469-4b78-823d-320fb1bd8646 - id of CKAN dataset.

```INI
dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern = https://host/internalcatalog/dataset/${name}
```
example Virtuoso graph name https://host/internalcatalog/dataset/my-dataset-name
Notice the my-dataset-name - name property of CKAN dataset (the one from URL of dataset).

### Version history

* see [Changelog](./CHANGELOG.md)