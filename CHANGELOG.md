L-RdfToVirtuosoAndCkan
----------

v1.2.0-SNAPSHOT
---
* dataset uri pattern parameters names changed to be clearer and more unique to prevent conflicts with Maven build properties
* new pattern: https://host/internalcatalog/dataset/${ckan_package_id} OR https://host/internalcatalog/dataset/${ckan_package_name}

v1.1.5-SNAPSHOT
---
* Update to API 2.1.4

v1.1.4
---
* Update to API 2.1.3

v1.1.3
---
* Improved documentation (About)

v1.1.0
---
* possibility to clear destination graph before loading
* changed configuration value dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern = https://host/internalcatalog/dataset/{} to new format:dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern = https://host/internalcatalog/dataset/${id} or dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern = https://host/internalcatalog/dataset/${name}
* DPU code / artifact moved to org.opendatanode.plugins

v1.0.0
---
* initial release