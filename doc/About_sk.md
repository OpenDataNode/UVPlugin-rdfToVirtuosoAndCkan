### Popis

Nahrá RDF dáta na Virtuoso použitím L-RdfToVirtuoso a vytvorí CKAN zdroje použitím L-RdfToCkan

### Konfiguračné parametre

| Meno | Popis |
|:----|:----|
|**Vyčistiť cieľový graf pred nahratím (checkbox)** | ak je checkbox aktívny, pred nahratím nového RDF vyčistí všetky predtým nahrané dáta (do príslušného grafu). Inak budú pôvodné a nové RDF dáta zjednotené ako prienik RDF trojíc (RDF trojice nemôžu byť duplicitné) |

### Vstupy a výstupy ###

|Meno |Typ | Dátová hrana | Popis | Povinné |
|:--------|:------:|:------:|:-------------|:---------------------:|
|rdfInput          |vstup| RDFDataUnit | RDF data nahrané na Virtuoso a zadané inštancie CKAN |áno|
|distributionInput |vstup| RDFDataUnit | Distribučné metadáta vytvorené z e-distributionMetadata | |