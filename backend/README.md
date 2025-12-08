# eXXellent Nights! - Backend

Java Spring Boot Backend für die Hotelmanager-Anwendung des Hotels 'eXXellent Nights!'.

_Funktionen:_ 
- Hotelzimmer verwalten (CRUD)
- Verfügbarkeiten anhand vorhandener Buchungen prüfen (mit Datumsbereichen)

_Dieses Java Spring Boot Backend besteht aus folgendem technischen Setup:_
- OpenAPI basierte API-Definition und Code-Generierung (Contract-First Ansatz)
- Anbindung an PostgreSQL + Flyway Migrationen
- Exception Handling mit konsistentem Fehlerobjekt
- Unit-, Repository- und Controller-Tests

### Tech Stack

- Java 25
- Spring Boot 4.0
- PostgreSQL 17
- Flyway (DB Migrationen)
- Spring Data JPA
- OpenAPI Generator 
- MapStruct
- JUnit 5, Mockito, Spring Boot Test, H2 (in-memory DB für Tests)


## Anwendung starten

### Build
```bash
mvn clean install
```
OpenAPI + MapStruct Implementierungen werden automatisch generiert.

### Lokal starten
1. Docker starten
2. PostgreSQL via Docker starten
```bash
cd .. && cd docker && docker compose up db
```
3. Backend über Entwicklungsumgebung starten (z.B. via IntelliJ -> Run oder Debug) oder 
```bash
mvn spring-boot:run
```

Flyway wird beim Start automatisch ausgeführt und legt Datenbank Schema + initiale Daten an. Siehe "Flyway" für mehr Infos.

## Flyway

Beim Start der Anwendung führt Flyway automatisch die in [src/main/resources/db/migration](src/main/resources/db/migration) 
hinterlegten SQL-Skripte aus.

Hierbei werden
1. Das Datenbank Schema initialisiert
2. Initial 3 Hotelzimmer angelegt
3. Initial mehrere Buchungen im Dezember 2025 zu den Hotelzimmern angelegt

Des Weiteren legt Flyway eine Tabelle "flyway_schema_history" in der Datenbank an.
Hier wird gespeichert, welche der SQL Skripte bereits ausgeführt wurden, inkl. einer Checksum.
So wird sichergestellt, dass die Skripte bei einem erneuten Start der Anwendung nicht nochmal ausgeführt werden.

So sollte auch bei Schema Änderungen etc. immer ein neues SQL Skript zur Ausführung angelegt werden.
So können auch später auf Develop/Integrations/Produktions-Umgebungen Migrationen der Datenbank durchgeführt werden.
Bei Änderungen in bestehenden Skripten ist ein sauberes Starten nur durch Löschen der vorhandenen Datenbank möglich.


## OpenAPI

Die API-Definition gilt als gemeinsamer Vertrag zwischen Backend und Frontend:
[hotel-mgmt.yaml](../openapi/hotel-mgmt.yaml)

Beim Maven Build werden daraus die DTOs und API-Interfaces automatisch generiert.

Alternativ kann der Generator wie folgt ausgeführt werden:
```bash
mvn clean generate-sources
```
Nach dem Starten der Anwendung können die einzelnen Endpunkte über [hotel-mgmt.yaml](../openapi/hotel-mgmt.yaml) aufgerufen werden.
Alternativ ist auch eine Swagger UI verfügbar unter:
```bash
http://localhost:8080/swagger-ui.html
```

## Projektstruktur
```pqsql
src/main/java/de/exxellentnights
 ├─ controller        ← REST Controller
 ├─ entity            ← Entities
 ├─ exception         ← Globales Exception Handling & Custom Exceptions
 ├─ mapper            ← MapStruct Mapper
 ├─ model             ← Domain Enums
 ├─ repository        ← Spring Data JPA Repos
 ├─ service           ← Services
 └─ BackendApplication
 
 target/generated-sources/annotations/de/exxellentnights
 └─ mapper            ← MapStruct Mapper
 
 target/generated-sources/openapi/java/de/exxellentnights
 ├─ api               ← OpenAPI generierte Interfaces
 └─ api/model         ← OpenAPI generierte Models
```
