# L-RdfToVirtuosoAndCkan #
----------

### Documentation

Loads RDF data to Virtuoso using L-RdfToVirtuoso and creates CKAN resources using L-RdfToCkan.

### Dialog configuration parameter

|Parameter                        |Description                             |
|---------------------------------|----------------------------------------|
|**Clear destination graph before loading (checkbox):** | When checked (which is the default): before loading new RDF clear all previously loaded data (into specified graph). When unchecked, old RDF data and new RDF data are merged as union of RDF triples (RDF triples cannot be duplicated) |

### Configuration parameters

|Parameter                        |Description                             |                                                        
|---------------------------------|----------------------------------------|
|**dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern** |URL  pattern to build RDF graph name in Virtuoso storage. |
|**dpu.uv-l-rdfToVirtuosoAndCkan.resource.name** | Name of the CKAN resource to be used, has precedence over distributionMetadata input |
|**org.opendatanode.CKAN.secret.token**    |Token used to authenticate to CKAN, has to be set in backend.properties  |
|**org.opendatanode.CKAN.api.url** | URL where CKAN api is located, has to be set in backend.properties |
|**org.opendatanode.CKAN.http.header.[key]** | Custom HTTP header added to requests on CKAN |

#### Deprecated parameters

These parameters are deprecated and kept only for backward compatibility with version 1.0.X.
They will be removed in 1.1.0 of DPU.

|Parameter                             |Description                             |
|--------------------------------------|----------------------------------------|
|**dpu.uv-l-rdfToCkan.secret.token**    | alias to _org.opendatanode.CKAN.secret.token_  |
|**dpu.uv-l-rdfToCkan.catalog.api.url** | alias to _org.opendatanode.CKAN.api.url_ |

#### Examples
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

### Inputs and outputs

|Name                |Type       |DataUnit                         |Description                        |
|--------------------|-----------|---------------------------------|-----------------------------------|
|rdfInput |i |RDFDataUnit |RDF data loaded to Virtuoso and specified CKAN instance  |
|distributionInput |i (optional) |RDFDataUnit | Distribution metadata produced by e-distributionMetadata  |

### Version history

#### v1.1.0
* possibility to clear destination graph before loading
* Changed configuration value dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern = https://host/internalcatalog/dataset/{} to new format:dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern = https://host/internalcatalog/dataset/${id} or dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern = https://host/internalcatalog/dataset/${name}
* DPU code / artifact moved to org.opendatanode.plugins

#### v1.0.0
* Initial release

### Developer's notes
