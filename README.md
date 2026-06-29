# Booking-App

# Java Backend (Spring) – Felkészülési útmutató

## Spring Boot alapok

### Alkalmazás felépítése

* Réteges architektúra

  * Controller
  * Service
  * Repository
* Dependency Injection
* Bean-ek és Spring Context
* Konfiguráció kezelése (`application.yml`, profilok)

### Spring annotációk

* `@Component`
* `@Service`
* `@Repository`
* `@Controller`
* `@RestController`
* `@Configuration`
* `@Bean`

### REST API fejlesztés

* HTTP alapok
* REST alapelvek
* Request és response modellek
* JSON szerializáció
* Státuszkódok helyes használata
* Hibakezelés API-kban

### Gyakran használt könyvtárak

* Lombok
* MapStruct
* Spring Data JPA
* Validation (`@Valid`, Bean Validation)

---

# Adatbázisok és JPA

## SQL alapok

* SELECT
* INSERT
* UPDATE
* DELETE
* JOIN-ok
* Aggregációk
* Szűrés és rendezés

## Relációs adatmodellezés

* One-to-One
* One-to-Many
* Many-to-One
* Many-to-Many
* Kapcsolótáblák

## JPA és Hibernate

* ORM alapfogalmak
* Entitások
* Repository-k
* Entity lifecycle
* Persistence Context

## DTO és Mapping

* DTO szerepe
* Entity szerepe
* Objektummappelés
* MapStruct használata

## Fetching

* Lazy Loading
* Eager Loading
* N+1 probléma

## Tranzakciókezelés

* `@Transactional`
* Rollback
* Adatkonzisztencia

## Migrációkezelés

* Flyway vagy Liquibase
* Verziózott adatbázis-módosítások

## Lekérdezések

* Derived query-k
* JPQL
* Native SQL

---

# Docker

## Konténerizáció

* A konténerek célja
* Környezetfüggetlen futtatás
* Izoláció

## Docker alapok

* Image
* Container
* Registry

## Dockerfile

* Image építése
* Layer-ek
* Build folyamat

## Docker Compose

* Több szolgáltatás kezelése
* Hálózatok
* Volume-ok

## Alkalmazás futtatása konténerben

* Spring Boot + PostgreSQL
* Környezeti változók
* Indítási sorrend
* Health check-ek

---

# API tervezés

## REST API tervezési alapelvek

* Erőforrás-orientált szemlélet
* URL-struktúra
* Verziókezelés
* Hibaválaszok

## API szerződések

* OpenAPI
* Swagger
* Contract-first megközelítés

---

# Java alapok

## Objektumorientált programozás

* Osztályok
* Objektumok
* Konstruktorok
* Öröklődés
* Polimorfizmus
* Absztrakció

## Referenciák és objektumok

* Objektumreferenciák
* Paraméterátadás Java-ban
* `equals()` és `==`

## String kezelés

* Immutable objektumok
* String Pool

## Memóriakezelés

* Stack
* Heap
* Referenciák

## Garbage Collector

* Young Generation
* Old Generation
* Objektuméletciklus

---

# Exception kezelés

## Kivételek

* Checked Exception
* Unchecked (Runtime) Exception

## Hibakezelési minták

* Try-catch
* Exception propagálás
* Egyedi kivételek

## Spring hibakezelés

* `@ExceptionHandler`
* `@ControllerAdvice`
* Egységes hibaválaszok

---

# Modern Java

## Lambda kifejezések

* Funkcionális programozási alapok
* Lambda szintaxis

## Functional Interface

* Egyetlen absztrakt metódus
* Gyakori interfészek

## Method Reference

* Referencia meglévő metódusokra

## Stream API

### Műveletek

* filter
* map
* flatMap
* distinct
* sorted
* limit
* skip

### Terminális műveletek

* collect
* count
* anyMatch
* allMatch
* findFirst

## Optional

* Null kezelés
* Optional API használata

---

# CI/CD

## Continuous Integration

* Build automatizálás
* Automatikus tesztelés
* Branch alapú fejlesztés

## Continuous Delivery / Deployment

* Release folyamat
* Deploy automatizálás

## Pipeline-ok

* Build
* Teszt
* Elemzés
* Deploy

## Eszközök

* GitHub Actions
* GitLab CI
* Jenkins

---

# Microservice alapok

## Architektúra

* Szolgáltatások szétválasztása
* Független deploy
* Service ownership

## Kommunikáció

* REST
* Aszinkron kommunikáció alapjai

## Autentikáció

* JWT
* Token alapú hitelesítés

## Adatkezelés

* Saját adatbázis szolgáltatásonként
* Adatkonzisztencia

## Eventual Consistency

* Eseményalapú kommunikáció
* Saga szemlélet

## Megfigyelhetőség

* Logolás
* Monitoring
* Distributed tracing

---

# Cache

## Cache alapok

* Cache célja
* Teljesítményjavítás

## Cache stratégiák

* Read-through
* Write-through
* Write-behind

## Élettartam kezelés

* TTL
* Cache invalidation

## Gyakori cache megoldások

* Redis
* In-memory cache

## Konzisztencia

* Cache és adatbázis szinkronizáció
* Elavult adatok kezelése

---

# Tesztelés

## Unit tesztek

* Komponensek izolált tesztelése
* Mockolás alapjai

## Integration tesztek

* Több komponens együttműködése

## End-to-End tesztek

* Teljes rendszer tesztelése

## Regression tesztek

* Korábbi funkcionalitás védelme

## Tesztelési szemlélet

* Tesztpiramis
* TDD alapfogalmak

---

# Resiliency és hibatűrés

## Hibatűrő rendszerek

* Retry
* Timeout
* Circuit Breaker
* Fallback

## Rendelkezésre állás

* Redundancia
* Failover alapok

## Monitoring

* Logok
* Metrikák
* Riasztások

## Hibakeresés

* Root cause analysis
* Production hibák kezelése
