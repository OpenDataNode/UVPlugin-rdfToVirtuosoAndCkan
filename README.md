# L-RdfToVirtuosoAndCkan #
----------

###General###

|                              |                                                               |
|------------------------------|---------------------------------------------------------------|
|**Name:**                     |L-RdfToVirtuosoAndCkan                                              |
|**Description:**              |Loads RDF data to Virtuoso using L-RdfToVirtuoso and creates CKAN resources using L-RdfToCkan. |
|**Status:**                   |Supported in Plugins v2.X. Updated to use Plugin-DevEnv v2.X.       |
|                              |                                                               |
|**DPU class name:**           |RdfToVirtuosoAndCkan     | 
|**Configuration class name:** |RdfToVirtuosoAndCkanConfig_V1                           |
|**Dialogue class name:**      |RdfToVirtuosoAndCkanVaadinDialog | 

***

###Dialog configuration parameters###


|Parameter                        |Description                             |
|---------------------------------|----------------------------------------|

***

###Configuration parameters###

|Parameter                        |Description                             |                                                        
|---------------------------------|----------------------------------------|
|**dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern** |URL  pattern to build RDF graph name in Virtuoso storage. |
|**org.opendatanode.CKAN.secret.token**    |Token used to authenticate to CKAN, has to be set in backend.properties  |
|**org.opendatanode.CKAN.api.url** | URL where CKAN api is located, has to be set in backend.properties |
|**org.opendatanode.CKAN.http.header.[key]** | Custom HTTP header added to requests on CKAN |

####Deprecated parameters###

These parameters are deprecated and kept only for backward compatibility with version 1.0.X.
They will be removed in 1.1.0 of DPU.

|Parameter                             |Description                             |
|--------------------------------------|----------------------------------------|
|**dpu.uv-l-rdfToCkan.secret.token**    | alias to _org.opendatanode.CKAN.secret.token_  |
|**dpu.uv-l-rdfToCkan.catalog.api.url** | alias to _org.opendatanode.CKAN.api.url_ |

***

####Examples####
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

***

### Inputs and outputs ###

|Name                |Type       |DataUnit                         |Description                        |
|--------------------|-----------|---------------------------------|-----------------------------------|
|rdfInput |i |RDFDataUnit |RDF data loaded to Virtuoso and specified CKAN instance  |
|distributionInput |i (optional) |RDFDataUnit | Distribution metadata produced by e-distributionMetadata  |

***

### Version history ###

|Version            |Release notes                                   |
|-------------------|------------------------------------------------|
|1.1.0              |Changed configuration value dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern = https://host/internalcatalog/dataset/{} to new format:dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern = https://host/internalcatalog/dataset/${id} or dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern = https://host/internalcatalog/dataset/${name}                        |
|1.0.0              |Initial release.                         |


***

### Developer's notes ###

|Author            |Notes                 |
|------------------|----------------------|
|N/A               |N/A                   | 

