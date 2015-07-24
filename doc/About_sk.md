### Popis

Nahrá RDF dáta na Virtuoso použitím L-RdfToVirtuoso a vytvorí CKAN zdroje použitím L-RdfToCkan

### Konfiguračné parametre dialógu

|Parameter|Description|
|:----|:----|
|**Vyčistiť cieľový graf pred nahratím (checkbox):** |ak je checkbox aktívny, pred nahratím nového RDF vyčistí všetky predtým nahrané dáta (do príslušného grafu). Inak budú pôvodné a nové RDF dáta zjednotené ako prienik RDF trojíc (RDF trojice nemôžu byť duplicitné)|

### Konfiguračné parametre

| Meno | Popis |
|:----|:----|
|**dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern**|URL  vzor pre zostavenie názvu RDF grafu na úložisku Virtuoso|
|**dpu.uv-l-rdfToVirtuosoAndCkan.resource.name**| Názov použitého CKAN zdroja, má prednosť pred vstupom z distributionMetadata|
|**org.opendatanode.CKAN.secret.token**|Reťazec použitý na CKAN autentifikáciu, nastavuje sa v backend.properties|
|**org.opendatanode.CKAN.api.url**|URL kde sa nachádza CKAN API, nastavuje sa v backend.properties|
|**org.opendatanode.CKAN.http.header.[key]**|Aktuálna HTTP hlavička pridaná k požiadavkam na CKAN|

#### Zastarané parametre

Nasledujúce parametre sú zastarané a uchované iba z dôvodu spätnej kompatibility s verziou 1.0.X.
Budú odstránené od verzie DPU 1.1.0.

| Meno | Popis |
|-----|-----|
|**dpu.uv-l-filesToCkan.secret.token**| alias k _org.opendatanode.CKAN.secret.token_  |
|**dpu.uv-l-filesToCkan.catalog.api.url** | alias k _org.opendatanode.CKAN.api.url_ |

#### Príklady
```INI
dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern = https://host/internalcatalog/dataset/${id}
```
príklad názvu grafu Virtuoso https://host/internalcatalog/dataset/452fddaa-c469-4b78-823d-320fb1bd8646.
Pozn.: 452fddaa-c469-4b78-823d-320fb1bd8646 - id CKAN datasetu.

```INI
dpu.uv-l-rdfToVirtuosoAndCkan.dataset.uri.pattern = https://host/internalcatalog/dataset/${name}
```
príklad názvu grafu Virtuoso https://host/internalcatalog/dataset/my-dataset-name
Pozn.: my-dataset-name - názov vlastnosti CKAN datasetu (z URL datasetu).

### Vstupy a výstupy ###

|Meno |Typ | Dátová hrana | Popis | Povinné |
|:--------|:------:|:------:|:-------------|:---------------------:|
|rdfInput|i|RDFDataUnit|RDF data nahrané na Virtuoso a zadané inštancie CKAN|x|
|distributionInput|i|RDFDataUnit|Distribučné metadáta vytvorené z e-distributionMetadata||