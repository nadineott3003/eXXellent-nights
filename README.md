# eXXellent Nights!
Dieses Repository beinhaltet HotelManager Anwendung für das Hotel 'eXXellent Nights!'.

Mit dieser Anwendung können die Hotelzimmer, dessen Verfügbarkeiten und die Buchungen des Hotels verwaltet werden.

## Über die Anwendung

Die Anwendung besteht aus 3 Komponenten:
1. Backend (Java, Spring Boot), beinhaltet die API
2. Frontend (Angular Nx)
3. Datenbank (PostgreSQL)

Für die Anwendung wurde ein "Contract-First"-Ansatz gewählt. 
Hierfür liegt eine gemeinsame OpenAPI-Spezifikation zur Beschreibung der Schnittstelle vor, die sowohl vom Backend, 
als auch vom Frontend eingesetzt wird.

Das Backend nutzt Flyway für die Erstellung und Migration der Datenbank. (Siehe Backend README für Details)

## Anwendung starten
### Lokal
1. PostgreSQL mit Docker starten
```bash
cd docker && docker compose up db
```

2. Backend lokal starten (siehe [README.md](backend/README.md))

3. Frontend lokal starten (siehe [README.md](frontend/README.md))

