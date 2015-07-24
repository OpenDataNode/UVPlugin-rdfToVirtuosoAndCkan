### Description

Loads RDF data to Virtuoso using L-RdfToVirtuoso and creates CKAN resources using L-RdfToCkan

### Configuration parameters

| Name | Description |
|:----|:----|
|**Clear destination graph before loading (checkbox)** | If checked (default): before loading new RDF clear all previously loaded data (into specified graph). When unchecked, old RDF data and new RDF data are merged as union of RDF triples (RDF triples cannot be duplicated) |

### Inputs and outputs

|Name |Type | DataUnit | Description | Mandatory |
|:--------|:------:|:------:|:-------------|:---------------------:|
|rdfInput          |i| RDFDataUnit| RDF data loaded to Virtuoso and specified CKAN instance |x|
|distributionInput |i| RDFDataUnit| Distribution metadata produced by e-distributionMetadata ||