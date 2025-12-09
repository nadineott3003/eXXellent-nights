# eXXellent Nights! - Frontend

## Über die Anwendung
Angular Frontend für die Hotelmanager-Anwendung des Hotels 'eXXellent Nights!'.

Das Frontend basiert auf einer Angular Standalone Architektur und kommuniziert über eine mit OpenAPI generierte API mit dem Spring Boot Backend.

_Funktionen:_
- Hotelzimmer verwalten (Übersicht aller Zimmer, Zimmer anlegen/bearbeiten/löschen)
- Verfügbarkeiten überprüfen

_Dieses Angular Frontend besteht aus folgendem technischen Setup:_
- Angular Nx Frontend (Standalone)
- OpenAPI basierte API-Definition und Code-Generierung (Contract-First Ansatz)
- UI mit Tailwind CSS und Flowbite

### Tech Stack

- Angular Nx Version 22 (Standalone Ansatz) mit Angular Version 20
- Tailwind CSS Version 4.1
- [Flowbite](https://flowbite.com/) 4.0 (UI-Komponenten, nutzt Tailwind CSS)
- OpenAPI Generator [ng-openapi-gen](https://www.npmjs.com/package/ng-openapi-gen/v/1.0.5)
- 


### Setup, Installation & Starten der Anwendung

#### Vorbedingungen:
- Node & npm installieren. Die aktuell benötigte node version kann in der [package.json](package.json) unter engines > nodes abgelesen werden

#### Applikation starten

Zum Starten der Applikation können die vorhandenen Skripte aus der [package.json](frontend%2Fpackage.json) genutzt werden.

- Pakete installieren
```bash
npm install
```
- Skript `build` ausführen - _Baut die Applikation und generiert die nötigen Models & Services der Openapi Definition aus [hotel-mgmt.yaml](../openapi/hotel-mgmt.yaml)_
- Skript `start` ausführen - _Startet die Applikation, vorab sollte das Backend gestartet werden, siehe [../backend/README.md](../backend/README.md)_

Anwendung kann im Anschluss unter http://localhost:4200/ aufgerufen werden.


### OpenAPI

Für die Applikation wird ein Contract-First Ansatz verwendet. 
Frontend und Backend nutzen eine OpenAPI Definition als gemeinsamen Vertrag zur Erstellung der API. 

Die OpenAPI Definition ist hier zu finden: [hotel-mgmt.yaml](../openapi/hotel-mgmt.yaml)

Zur Generierung der API (inkl. Models und Services) wird der OpenAPI Generator 
[ng-openapi-gen](https://www.npmjs.com/package/ng-openapi-gen/v/1.0.5) eingesetzt.

Nach Aktualisierung der yaml muss die openapi Generierung neu ausgeführt werden, indem aus der [package.json](frontend/package.json)
das Skript `api` ausgeführt wird.

Die generierten Dateien sind im Nachgang unter [src/app/core/api/generated](src/app/core/api/generated) zu finden.
Dieser Ordner ist Teil der .gitignore und wird nicht commited.

Nach Aktualisierung ist zu prüfen, ob Anpassungen an der Frontend-Umsetzung vorgenommen werden müssen.

### UI & Styling

In diesem Angular Frontend wird für UI & Styling folgendes eingesetzt: 
- Tailwind 4.1 für modernes CSS ohne Build-Plugins
- Flowbite 4.0 für UI Komponenten und Interaktionen (Modal, Toggle, Navbar)

### Tests
Derzeit sind noch keine Frontend-Tests umgesetzt.
Das Angular Nx Setup beinhaltet ein Playwright Setup für künftig zu erstellende Tests.
