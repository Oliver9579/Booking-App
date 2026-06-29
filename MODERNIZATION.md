# Booking-App — Medior Developer Modernization Guide

> This document combines the full medior skill checklist with a concrete, step-by-step implementation plan for upgrading the Booking-App to professional, production-ready quality.
> Every skill topic is explained in context and mapped to actual changes in this codebase.

---

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

---
---

# Implementation Plan — Full Modernization

> Everything below maps directly to the skills above and to specific files in this repository.
> Work through each phase in order. Each step references the exact files to change and the exact code pattern to apply.

---

## Phase 1 — Spring Boot 3.x Upgrade

**Skill covered:** Spring Boot alapok, Konfiguráció kezelése, Bean-ek és Spring Context

### Why
Spring Boot 2.7.x is end-of-life. Spring Boot 3.x requires Java 17+ (already satisfied), migrates from the `javax.*` namespace to `jakarta.*`, and is what every company runs today.

### Step 1.1 — Update `build.gradle`

**File:** `build.gradle`

Change these values:

```groovy
// BEFORE
id 'org.springframework.boot' version '2.7.5'
id 'io.spring.dependency-management' version '1.0.15.RELEASE'

// AFTER
id 'org.springframework.boot' version '3.4.1'
id 'io.spring.dependency-management' version '1.1.7'
```

Add new dependencies:

```groovy
dependencies {
    // Keep all existing, then add:

    // MapStruct — DTO mapping code generation
    implementation 'org.mapstruct:mapstruct:1.6.3'
    annotationProcessor 'org.mapstruct:mapstruct-processor:1.6.3'
    annotationProcessor 'org.projectlombok:lombok-mapstruct-binding:0.2.0'

    // Swagger / OpenAPI 3 for Spring Boot 3
    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.3'

    // Redis for caching
    implementation 'org.springframework.boot:spring-boot-starter-data-redis'
    implementation 'org.springframework.boot:spring-boot-starter-cache'

    // Replace JWT 0.11.5 with 0.12.6 (required for Spring Boot 3)
    // Remove old:
    // implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
    // runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
    // runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'
    // Add new:
    implementation 'io.jsonwebtoken:jjwt-api:0.12.6'
    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.12.6'
    runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.12.6'

    // Remove JUnit 4 entirely — Spring Boot 3 uses JUnit 5 by default
    // Remove: testImplementation 'junit:junit:4.13.1'
    // Remove: testRuntimeOnly 'org.junit.vintage:junit-vintage-engine'
}
```

Make sure Lombok annotation processor is ordered before MapStruct:

```groovy
configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}
```

### Step 1.2 — Migrate `javax.*` → `jakarta.*`

**Skill covered:** Java alapok (OOP, Spring annotációk)

Spring Boot 3 uses Jakarta EE 9+ which renames `javax.*` to `jakarta.*`. This affects every file that imports from:
- `javax.persistence.*` → `jakarta.persistence.*`
- `javax.validation.*` → `jakarta.validation.*`
- `javax.mail.*` → `jakarta.mail.*`
- `javax.servlet.*` → `jakarta.servlet.*`
- `javax.transaction.*` → `jakarta.transaction.*`

**Files requiring this change (every `@Entity`, every DTO with validation, every security filter):**

```
src/main/java/com/example/booking/booking/models/Booking.java
src/main/java/com/example/booking/car/models/Car.java
src/main/java/com/example/booking/flight/models/Flight.java
src/main/java/com/example/booking/hotel/models/Hotel.java
src/main/java/com/example/booking/room/models/Room.java
src/main/java/com/example/booking/seat/models/Seat.java
src/main/java/com/example/booking/user/models/User.java
src/main/java/com/example/booking/date/models/Days.java
src/main/java/com/example/booking/email/models/EmailVerificationToken.java
src/main/java/com/example/booking/review/models/Review.java
src/main/java/com/example/booking/login/dtos/LoginDTO.java
src/main/java/com/example/booking/registration/dtos/RegistrationDTO.java
src/main/java/com/example/booking/user/dtos/NewUserDetailsRequestDTO.java
src/main/java/com/example/booking/review/dtos/ReviewRequestDTO.java
src/main/java/com/example/booking/security/config/JwtRequestFilter.java
src/main/java/com/example/booking/email/config/EmailConfig.java (if exists)
```

**In every file above**, do a find-and-replace:
- `import javax.persistence.` → `import jakarta.persistence.`
- `import javax.validation.` → `import jakarta.validation.`
- `import javax.servlet.` → `import jakarta.servlet.`
- `import javax.mail.` → `import jakarta.mail.`

### Step 1.3 — Rewrite `WebSecurityConfig.java`

**Skill covered:** Spring Boot alapok, Autentikáció (JWT)

**File:** `src/main/java/com/example/booking/security/config/WebSecurityConfig.java`

Spring Boot 3 removes `WebSecurityConfigurerAdapter`. Replace the entire class:

```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/register", "/login", "/verify",
                    "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
            )
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

### Step 1.4 — Fix the `User.java` critical bugs

**File:** `src/main/java/com/example/booking/user/models/User.java`

Two bugs that currently break authentication for all users:

```java
// BUG 1: isAccountNonExpired() returns false — this locks every account
// BEFORE:
@Override
public boolean isAccountNonExpired() {
    return false;
}

// AFTER:
@Override
public boolean isAccountNonExpired() {
    return true;
}

// BUG 2: getAuthorities() returns null — Spring Security throws NullPointerException
// BEFORE:
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
}

// AFTER:
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
}
```

### Step 1.5 — Update `JwtTokenUtil.java` for jjwt 0.12.x

**File:** `src/main/java/com/example/booking/security/config/JwtTokenUtil.java`

The `jjwt` API changed significantly between 0.11.x and 0.12.x. Key changes:

```java
// BEFORE (0.11.x):
private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(token)
        .getBody();
}

public String createJwtsToken(LinkedHashMap<String, Object> claims) {
    return Jwts.builder()
        .setClaims(claims)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
}

// AFTER (0.12.x):
private SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
}

private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser()
        .verifyWith(getSigningKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
}

public String createJwtsToken(LinkedHashMap<String, Object> claims) {
    if (claims == null || claims.isEmpty()) return "";
    return Jwts.builder()
        .claims(claims)
        .signWith(getSigningKey())
        .compact();
}
```

---

## Phase 2 — Configuration: `application.yml` with Profiles

**Skill covered:** Konfiguráció kezelése (`application.yml`, profilok), Docker (környezeti változók)

### Why
`application.properties` is fine but `application.yml` is more readable for nested config and is the modern standard. Profiles (`dev`, `prod`) allow running locally with hardcoded values while production uses environment variables — critical for Docker/CI security.

### Step 2.1 — Delete `application.properties`, create `application.yml`

**Delete:** `src/main/resources/application.properties`

**Create:** `src/main/resources/application.yml`

```yaml
spring:
  profiles:
    active: dev
  jpa:
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect
    show-sql: false
  flyway:
    baseline-on-migrate: true
    enabled: true
    out-of-order: true
    locations: classpath:db/migration/mysql
  jackson:
    date-format: yyyy-MM-dd HH:mm
    time-zone: Europe/Zagreb

jwt:
  token:
    secret: ${JWT_SECRET:dev-secret-key-minimum-32-characters-long}
  expiration:
    time: 3600000

logging:
  level:
    com.example.booking: INFO
    org.hibernate.SQL: WARN
```

**Create:** `src/main/resources/application-dev.yml`

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/booking
    username: root
    password: root
  data:
    redis:
      host: localhost
      port: 6379
  mail:
    host: smtp.gmail.com
    port: 587
    username: booking.2024.test@gmail.com
    password: YOUR_APP_PASSWORD_HERE
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
          ssl:
            trust: "*"
  jpa:
    show-sql: true

logging:
  level:
    com.example.booking: DEBUG
```

**Create:** `src/main/resources/application-prod.yml`

```yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USER}
    password: ${DB_PASSWORD}
  data:
    redis:
      host: ${REDIS_HOST:redis}
      port: ${REDIS_PORT:6379}
  mail:
    host: smtp.gmail.com
    port: 587
    username: ${MAIL_USER}
    password: ${MAIL_PASSWORD}
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true

logging:
  level:
    com.example.booking: INFO
```

**Create:** `.env.example` (commit this, never commit actual `.env`)

```
DB_URL=jdbc:mysql://db:3306/booking
DB_USER=root
DB_PASSWORD=your_secure_password_here
JWT_SECRET=your-minimum-32-character-secret-key-here
MAIL_USER=your.email@gmail.com
MAIL_PASSWORD=your_gmail_app_password
REDIS_HOST=redis
REDIS_PORT=6379
```

Add `.env` to `.gitignore` (never commit real secrets).

---

## Phase 3 — MapStruct for DTO Mapping

**Skill covered:** DTO és Mapping, MapStruct használata

### Why
Currently every `ServiceImpl` manually constructs DTOs:
```java
// Current pattern in FlightServiceImpl — messy and error-prone:
return new FlightDTO(
    flight.getId(),
    flight.getAirline(),
    flight.getOrigin(),
    flight.getOriginAirportCode(),
    // ... 8 more fields
);
```
MapStruct generates this boilerplate at compile-time, keeps it type-safe, and is required knowledge for medior Java roles.

### Step 3.1 — Create `FlightMapper.java`

**New file:** `src/main/java/com/example/booking/flight/mappers/FlightMapper.java`

```java
@Mapper(componentModel = "spring")
public interface FlightMapper {

    FlightDTO toFlightDTO(Flight flight);

    FlightListDTO toFlightListDTO(List<FlightDTO> flights);
}
```

If DTO field names differ from entity field names, use `@Mapping`:
```java
@Mapping(source = "originAirportCode", target = "originCode")
FlightDTO toFlightDTO(Flight flight);
```

### Step 3.2 — Create `HotelMapper.java`

**New file:** `src/main/java/com/example/booking/hotel/mappers/HotelMapper.java`

```java
@Mapper(componentModel = "spring")
public interface HotelMapper {
    AllHotelDTO toAllHotelDTO(Hotel hotel);
    HotelDTO toHotelDTO(Hotel hotel);
}
```

### Step 3.3 — Create `CarMapper.java`

**New file:** `src/main/java/com/example/booking/car/mappers/CarMapper.java`

```java
@Mapper(componentModel = "spring")
public interface CarMapper {
    AllCarsDTO toAllCarsDTO(Car car);
    // CarDTO has computed fields (fullPrice = pricePerDay * travelLength)
    // These cannot be auto-mapped — keep manual for computed fields,
    // use @AfterMapping or @Mapping(ignore=true) for non-entity fields
}
```

For computed fields (like `fullPrice`), use a default method:
```java
@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(target = "fullPrice", ignore = true)
    @Mapping(target = "pickUpDate", ignore = true)
    @Mapping(target = "dropOffDate", ignore = true)
    CarDTO toCarDTO(Car car);

    default CarDTO toCarDTOWithPrice(Car car, LocalDate pickUpDate, LocalDate dropOffDate, int travelLength) {
        CarDTO dto = toCarDTO(car);
        dto.setPickUpDate(pickUpDate);
        dto.setDropOffDate(dropOffDate);
        dto.setFullPrice(car.getPricePerDay() * travelLength);
        return dto;
    }
}
```

### Step 3.4 — Create `UserMapper.java`

**New file:** `src/main/java/com/example/booking/user/mappers/UserMapper.java`

```java
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
}
```

### Step 3.5 — Create `ReviewMapper.java`

**New file:** `src/main/java/com/example/booking/review/mappers/ReviewMapper.java`

```java
@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(source = "user.userName", target = "username")
    ReviewResponseDTO toReviewResponseDTO(Review review);
}
```

### Step 3.6 — Inject mappers into ServiceImpl files

In each ServiceImpl, remove manual DTO construction and use the injected mapper:

**`FlightServiceImpl.java`:**
```java
@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightMapper flightMapper;  // Add this

    // Remove the convertToFlightDTO() method entirely
    // Replace usages with: flightMapper.toFlightDTO(flight)
}
```

Do the same pattern in: `HotelServiceImpl.java`, `CarServiceImpl.java`, `UserServiceImpl.java`, `ReviewServiceImpl.java`.

---

## Phase 4 — Validation, `@Transactional`, and N+1 Fixes

**Skill covered:** Validation (`@Valid`, Bean Validation), Tranzakciókezelés, Fetching (N+1 probléma)

### Step 4.1 — Add `@Valid` to all controller endpoints

**Skill:** Validation (`@Valid`, Bean Validation)

Every `@RequestBody` that has a DTO with validation annotations must have `@Valid` added:

**`LoginController.java`:**
```java
// BEFORE:
public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO)

// AFTER:
public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginDTO loginDTO)
```

Apply the same fix to:
- `BookingController.java` — all four POST methods
- `UserController.java` — the PUT method
- `ReviewController.java` — all three POST methods
- `RegistrationController.java` — verify `@Valid` is present

### Step 4.2 — Harden DTO validation annotations

**`ReviewRequestDTO.java`:**
```java
public class ReviewRequestDTO {
    @NotBlank(message = "Review comment cannot be empty")
    @Size(min = 1, max = 2000, message = "Review must be between 1 and 2000 characters")
    private String comment;
}
```

**`NewUserDetailsRequestDTO.java`:**
```java
public class NewUserDetailsRequestDTO {
    @Size(max = 100)
    private String firstName;

    @Email(message = "Must be a valid email address")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Invalid phone number")
    private String phoneNumber;
}
```

**`BookingOneWayFlightRequestDTO.java`** — add `@NotNull` on required fields, `@NotEmpty` on seat ID arrays.

### Step 4.3 — Add `@Transactional` to write operations

**Skill:** Tranzakciókezelés, `@Transactional`, Rollback

Add `@Transactional` to every method that writes to multiple tables. If an exception is thrown mid-way, the entire operation rolls back — preventing orphaned records.

**`BookingServiceImpl.java`** — all four `create*Booking()` methods touch 3–5 tables:
```java
@Transactional
public BookingOneWayFlightResponseDTO createOneWayFlightBooking(User user, BookingOneWayFlightRequestDTO dto) {
    // If anything here throws, the booking, seat updates, all roll back
}
```

**`UserServiceImpl.java`** — `deleteUser()` releases seats, rooms, and cars:
```java
@Transactional
public UserDTO deleteUser(User user) {
    // Releasing all linked records is one atomic operation
}
```

**`RegistrationServiceImpl.java`** — `register()` saves user + verification token + sends email:
```java
@Transactional
public User register(RegistrationDTO dto) throws MessagingException {
    // If email sending fails, user creation rolls back
}
```

**`CarServiceImpl.java`** — availability update methods:
```java
@Transactional
public void updateCarAvailability(List<BookedCar> bookedCars) { ... }
```

### Step 4.4 — Fix N+1 Queries with JPQL Fetch Joins

**Skill:** Fetching, Lazy Loading, N+1 probléma

**Problem:** When `BookingServiceImpl.getBookings()` loads a user's bookings, Hibernate fires one query for the bookings list, then for each booking it fires separate queries to load `car`, `outboundFlight`, `hotel`, `bookedSeats`, `bookedRooms`. For 10 bookings this is 50+ queries.

**File:** `src/main/java/com/example/booking/booking/repositories/BookingRepository.java`

```java
// BEFORE (causes N+1):
List<Booking> findAllByUserId(Integer userId);

// AFTER (single query with all joins):
@Query("SELECT b FROM Booking b " +
       "LEFT JOIN FETCH b.car c " +
       "LEFT JOIN FETCH b.outboundFlight of " +
       "LEFT JOIN FETCH b.returnFlight rf " +
       "LEFT JOIN FETCH b.hotel h " +
       "LEFT JOIN FETCH b.bookedSeats " +
       "LEFT JOIN FETCH b.bookedRooms " +
       "WHERE b.user.id = :userId")
List<Booking> findAllByUserIdWithDetails(@Param("userId") Integer userId);
```

**File:** `src/main/java/com/example/booking/flight/repositories/FlightRepository.java`

Add a fetch join for seats when loading a flight for booking:
```java
@Query("SELECT f FROM Flight f LEFT JOIN FETCH f.seats WHERE f.id = :id")
Optional<Flight> findByIdWithSeats(@Param("id") Integer id);
```

**File:** `src/main/java/com/example/booking/hotel/repositories/HotelRepository.java`

Add a fetch join for rooms when loading a hotel:
```java
@Query("SELECT h FROM Hotel h LEFT JOIN FETCH h.rooms WHERE h.id = :id")
Optional<Hotel> findByIdWithRooms(@Param("id") Integer id);
```

### Step 4.5 — Add Pagination to List Endpoints

**Skill:** REST API fejlesztés, Spring Data JPA

Unbounded list queries are a production problem. Change `CrudRepository` to `JpaRepository` for entities that have list endpoints, then add `Pageable`.

**`FlightRepository.java`** — change interface:
```java
// BEFORE:
public interface FlightRepository extends CrudRepository<Flight, Integer>

// AFTER:
public interface FlightRepository extends JpaRepository<Flight, Integer>
```

**`FlightController.java`** — add pageable params:
```java
@GetMapping
public ResponseEntity<Page<FlightDTO>> getAllFlights(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("departureDate").ascending());
    return ResponseEntity.ok(flightService.getAllFlights(pageable));
}
```

Apply the same pattern to `HotelController` and `CarController`.

---

## Phase 5 — Swagger / OpenAPI Documentation

**Skill covered:** API tervezés, OpenAPI, Swagger, Contract-first megközelítés

### Step 5.1 — Create `OpenApiConfig.java`

**New file:** `src/main/java/com/example/booking/config/OpenApiConfig.java`

```java
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bookingOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Booking App API")
                .description("Travel booking platform — flights, hotels, car rentals")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Developer")
                    .email("dev@booking.com")))
            .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
            .components(new Components()
                .addSecuritySchemes("Bearer Authentication",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .description("Enter JWT token from /login response")));
    }
}
```

Swagger UI is automatically available at: `http://localhost:8080/swagger-ui.html`
OpenAPI JSON spec at: `http://localhost:8080/v3/api-docs`

### Step 5.2 — Annotate controllers with OpenAPI annotations

Add `@Tag`, `@Operation`, `@ApiResponse`, `@Parameter` to every controller.

**`FlightController.java`** example:
```java
@Tag(name = "Flights", description = "Flight search and retrieval")
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Operation(
        summary = "Search one-way flights",
        description = "Returns available flights matching origin, destination, and departure date"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Flights found"),
        @ApiResponse(responseCode = "404", description = "No flights found for these criteria")
    })
    @GetMapping("/oneWay")
    public ResponseEntity<FlightListDTO> getOneWayFlights(
            @Parameter(description = "Departure city or airport code") @RequestParam String origin,
            @Parameter(description = "Arrival city or airport code") @RequestParam String destination,
            @Parameter(description = "Departure date (yyyy-MM-dd)") @RequestParam String departureDate) {
        // ...
    }
}
```

Apply the same pattern to all 8 controllers.

---

## Phase 6 — Redis Caching

**Skill covered:** Cache alapok, Cache stratégiák, TTL, Redis, Konzisztencia

### Why
Flight/hotel/car data doesn't change between requests. Without caching, every search hits MySQL. With Redis, search results are returned from memory in <1ms.

### Step 6.1 — Create `CacheConfig.java`

**New file:** `src/main/java/com/example/booking/config/CacheConfig.java`

```java
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration
            .defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(10))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair
                    .fromSerializer(new GenericJackson2JsonRedisSerializer()));

        Map<String, RedisCacheConfiguration> cacheConfigs = new HashMap<>();
        cacheConfigs.put("flights", defaultConfig.entryTtl(Duration.ofMinutes(5)));
        cacheConfigs.put("hotels", defaultConfig.entryTtl(Duration.ofMinutes(10)));
        cacheConfigs.put("cars", defaultConfig.entryTtl(Duration.ofMinutes(10)));

        return RedisCacheManager.builder(connectionFactory)
            .cacheDefaults(defaultConfig)
            .withInitialCacheConfigurations(cacheConfigs)
            .build();
    }
}
```

### Step 6.2 — Add `@Cacheable` and `@CacheEvict` to services

**`FlightServiceImpl.java`:**
```java
@Cacheable(value = "flights", key = "#root.method.name")
public FlightListDTO getAllFlights() { ... }

@Cacheable(value = "flights", key = "#dto.origin + '-' + #dto.destination + '-' + #dto.departureDate")
public FlightListDTO getFlightsJustOneWay(FlightOneWayRequestDTO dto) { ... }
```

**`HotelServiceImpl.java`:**
```java
@Cacheable(value = "hotels", key = "#root.method.name")
public List<AllHotelDTO> getAllHotel() { ... }

@Cacheable(value = "hotels", key = "#dto.location + '-' + #dto.checkInDate + '-' + #dto.checkOutDate")
public HotelListDTO getAllByLocation(HotelRequestDTO dto) { ... }
```

**Cache invalidation — `BookingServiceImpl.java`:**
```java
// When a booking is made, the cached availability data is stale
@CacheEvict(value = {"flights", "hotels", "cars"}, allEntries = true)
@Transactional
public BookingOneWayFlightResponseDTO createOneWayFlightBooking(User user, BookingOneWayFlightRequestDTO dto) { ... }
```

### Note on Cache Consistency
This is a **read-through** cache pattern: if the key isn't in Redis, Spring fetches from MySQL and populates the cache. TTL handles eventual consistency (stale data is at most 5–10 minutes old). `@CacheEvict` on booking creation ensures freshness after a write.

---

## Phase 7 — Structured Logging

**Skill covered:** Megfigyelhetőség, Logolás, Monitoring

### Why
`System.out.println()` is not logging. Structured logging with SLF4J/Logback enables log levels (DEBUG/INFO/WARN/ERROR), log aggregation in production, and debugging without code changes.

### Pattern to apply in every ServiceImpl:

```java
@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);
    // Or with Lombok: just add @Slf4j annotation to the class

    @Transactional
    public BookingOneWayFlightResponseDTO createOneWayFlightBooking(User user, BookingOneWayFlightRequestDTO dto) {
        log.info("Creating one-way flight booking for user={}, flightId={}", user.getUsername(), dto.getOutboundFlightId());

        Flight flight = flightService.getFlightById(dto.getOutboundFlightId());
        log.debug("Found flight: airline={}, route={}->{}", flight.getAirline(), flight.getOrigin(), flight.getDestination());

        // ... business logic ...

        log.info("Booking created successfully: bookingId={}, userId={}", saved.getId(), user.getId());
        return bookingMapper.toOneWayFlightResponseDTO(saved);
    }
}
```

**Log levels guide:**
- `DEBUG` — detailed flow info (only shown in dev profile)
- `INFO` — key business events (booking created, user registered, user logged in)
- `WARN` — non-critical issues (login failed, token about to expire, cache miss after expected hit)
- `ERROR` — unexpected failures (database down, email send failed, unexpected exception)

**`RestControllerExceptionHandler.java`** — add error logging:
```java
@ExceptionHandler(Exception.class)
public ResponseEntity<ErrorMessage> handleUnexpected(Exception e) {
    log.error("Unexpected error: ", e);  // full stack trace in logs
    return ResponseEntity.status(500).body(new ErrorMessage("An unexpected error occurred"));
}
```

---

## Phase 8 — Docker + Docker Compose

**Skill covered:** Docker alapok, Dockerfile, Docker Compose, Konténerizáció, Környezeti változók

### Step 8.1 — Create `Dockerfile`

**New file:** `Dockerfile` (at project root)

```dockerfile
# Stage 1: Build
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copy Gradle wrapper and dependency files first (for layer caching)
COPY gradlew build.gradle settings.gradle ./
COPY gradle ./gradle
RUN ./gradlew dependencies --no-daemon

# Copy source and build
COPY src ./src
RUN ./gradlew bootJar --no-daemon -x test

# Stage 2: Runtime (smaller image — no JDK, just JRE)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
```

**Why multi-stage?** The build stage has the full JDK + Gradle (~500MB). The runtime stage only needs the JRE (~100MB). The final image is much smaller and has no build tooling exposed.

### Step 8.2 — Create `docker-compose.yml`

**New file:** `docker-compose.yml` (at project root)

```yaml
version: '3.9'

services:

  db:
    image: mysql:8.0
    container_name: booking-db
    environment:
      MYSQL_DATABASE: booking
      MYSQL_ROOT_PASSWORD: ${DB_PASSWORD}
    volumes:
      - mysql-data:/var/lib/mysql
    ports:
      - "3307:3306"   # host 3307 to avoid conflict with local MySQL
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost", "-u", "root", "-p${DB_PASSWORD}"]
      interval: 10s
      timeout: 5s
      retries: 5

  redis:
    image: redis:7-alpine
    container_name: booking-redis
    ports:
      - "6379:6379"
    healthcheck:
      test: ["CMD", "redis-cli", "ping"]
      interval: 10s
      timeout: 5s
      retries: 3

  app:
    build:
      context: .
      dockerfile: Dockerfile
    container_name: booking-app
    ports:
      - "8080:8080"
    environment:
      SPRING_PROFILES_ACTIVE: prod
      DB_URL: jdbc:mysql://db:3306/booking
      DB_USER: root
      DB_PASSWORD: ${DB_PASSWORD}
      JWT_SECRET: ${JWT_SECRET}
      MAIL_USER: ${MAIL_USER}
      MAIL_PASSWORD: ${MAIL_PASSWORD}
      REDIS_HOST: redis
      REDIS_PORT: 6379
    depends_on:
      db:
        condition: service_healthy
      redis:
        condition: service_healthy

volumes:
  mysql-data:
```

**How to run:**
```bash
# Create .env file from template
cp .env.example .env
# Edit .env with real values

# Start everything
docker compose up --build

# Stop
docker compose down

# Stop and remove volumes (wipes database)
docker compose down -v
```

**Key concepts demonstrated:**
- **Services**: app, db, redis are separate containers
- **Networks**: Docker Compose creates a default network — containers communicate by service name (app → `db:3306`)
- **Volumes**: `mysql-data` persists the database between restarts
- **Health checks**: `app` waits for `db` to be healthy before starting
- **Environment variables**: loaded from `.env`, never hardcoded

### Step 8.3 — Add `.dockerignore`

**New file:** `.dockerignore`

```
.git
.github
*.md
.env
src/main/frontend/node_modules
src/main/frontend/build
build/
.gradle/
```

---

## Phase 9 — GitHub Actions CI Pipeline

**Skill covered:** CI/CD, GitHub Actions, Build automatizálás, Automatikus tesztelés

### Step 9.1 — Create the workflow file

**New file:** `.github/workflows/ci.yml`

```yaml
name: CI

on:
  push:
    branches: [main, develop]
  pull_request:
    branches: [main]

jobs:
  test:
    name: Build and Test
    runs-on: ubuntu-latest

    services:
      mysql:
        image: mysql:8.0
        env:
          MYSQL_DATABASE: booking_test
          MYSQL_ROOT_PASSWORD: testpassword
        ports:
          - 3306:3306
        options: >-
          --health-cmd "mysqladmin ping -h localhost -u root -ptestpassword"
          --health-interval 10s
          --health-timeout 5s
          --health-retries 5

    steps:
      - name: Checkout code
        uses: actions/checkout@v4

      - name: Set up Java 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'
          cache: gradle

      - name: Grant execute permission for gradlew
        run: chmod +x gradlew

      - name: Run tests
        env:
          SPRING_PROFILES_ACTIVE: test
          DB_URL: jdbc:mysql://localhost:3306/booking_test
          DB_USER: root
          DB_PASSWORD: testpassword
          JWT_SECRET: test-secret-key-minimum-32-characters
          MAIL_USER: test@test.com
          MAIL_PASSWORD: test
        run: ./gradlew test --no-daemon

      - name: Build JAR
        run: ./gradlew bootJar --no-daemon -x test

      - name: Upload test results
        uses: actions/upload-artifact@v4
        if: always()
        with:
          name: test-results
          path: build/reports/tests/

      - name: Upload JAR artifact
        uses: actions/upload-artifact@v4
        with:
          name: booking-app
          path: build/libs/*.jar
```

**What this does:**
- Runs on every push to `main`/`develop` and every PR to `main`
- Spins up a real MySQL container (not H2 mock) for integration tests
- Builds the app with Java 17
- Runs all tests
- Builds the JAR
- Uploads test reports and the JAR as artifacts
- Fails the build if any test fails → blocks merging broken code

---

## Phase 10 — Test Migration (JUnit 4 → JUnit 5) and New Unit Tests

**Skill covered:** Tesztelés, Unit tesztek, Integration tesztek, Tesztpiramis, TDD alapfogalmak

### Step 10.1 — Migrate existing tests from JUnit 4 to JUnit 5

Spring Boot 3 uses JUnit 5 by default. JUnit 4 tests must be updated.

**Pattern for unit tests:**
```java
// BEFORE (JUnit 4):
@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceImplTest {
    @Before
    public void setUp() { ... }

    @Test(expected = AlreadyTakenException.class)
    public void testRegisterThrowsWhenUsernameTaken() { ... }
}

// AFTER (JUnit 5):
@ExtendWith(MockitoExtension.class)
public class RegistrationServiceImplTest {
    @BeforeEach
    void setUp() { ... }

    @Test
    void testRegisterThrowsWhenUsernameTaken() {
        assertThrows(AlreadyTakenException.class, () -> {
            registrationService.register(dto);
        });
    }
}
```

**Pattern for integration tests:**
```java
// BEFORE:
@RunWith(SpringRunner.class)
@SpringBootTest

// AFTER:
@ExtendWith(SpringExtension.class)
@SpringBootTest
// (or simply @SpringBootTest — JUnit 5 extension is auto-registered in Spring Boot 3)
```

Files to migrate:
```
src/test/java/com/example/booking/registration/services/RegistrationServiceImplTest.java
src/test/java/com/example/booking/security/JwtTokenUtilTest.java
src/test/java/com/example/booking/booking/services/BookingServiceImplTest.java
src/test/java/com/example/booking/login/services/LoginServiceImplTest.java
src/test/java/com/example/booking/security/PasswordServiceImplTest.java
src/test/java/com/example/booking/email/services/EmailServiceImplTest.java
src/test/java/com/example/booking/errorhandling/RestControllerExceptionHandlerTest.java
+ all *IT.java files
```

### Step 10.2 — Add missing unit tests

**New file:** `src/test/java/com/example/booking/flight/services/FlightServiceImplTest.java`

```java
@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {

    @Mock private FlightRepository flightRepository;
    @Mock private SeatService seatService;
    @InjectMocks private FlightServiceImpl flightService;

    @Test
    void getAllFlights_returnsMappedDTOs() {
        // given
        Flight flight = new Flight(); // set fields
        when(flightRepository.findAll()).thenReturn(List.of(flight));

        // when
        FlightListDTO result = flightService.getAllFlights();

        // then
        assertThat(result.getFlights()).hasSize(1);
        verify(flightRepository, times(1)).findAll();
    }

    @Test
    void getFlightById_throwsIdNotFoundException_whenNotFound() {
        // given
        when(flightRepository.findById(99)).thenReturn(Optional.empty());

        // when / then
        assertThrows(IdNotFoundException.class, () -> flightService.getFlightById(99));
    }

    @Test
    void getFlightsJustOneWay_throwsNoFlightFoundException_whenEmpty() {
        // given
        FlightOneWayRequestDTO dto = new FlightOneWayRequestDTO("LHR", "JFK", "2025-06-01");
        when(flightRepository.findAllByOriginDestinationAndDepartureTime(any())).thenReturn(List.of());

        // when / then
        assertThrows(NoFlightFoundException.class, () -> flightService.getFlightsJustOneWay(dto));
    }
}
```

Create similar test files for:
- `HotelServiceImplTest.java` — search, date validation, not-found cases
- `CarServiceImplTest.java` — same/different drop-off, availability, same-date exception
- `UserServiceImplTest.java` — update uniqueness check, delete releases resources

---

## Phase 11 — Frontend Full Redesign

**Skill covered:** Modern React patterns, TypeScript/JS best practices, component architecture

### Tech stack for the new frontend:
- **Build tool:** Vite 6 (replaces Create React App)
- **UI:** Tailwind CSS + shadcn/ui
- **State:** Zustand (global auth + search state)
- **Forms:** React Hook Form + Zod
- **HTTP:** Axios with interceptors (centralized API service layer)
- **Icons:** Lucide React
- **Dates:** date-fns

### Step 11.1 — Migrate from CRA to Vite

**File:** `src/main/frontend/package.json`

Remove:
```json
"react-scripts": "5.0.1",
"@testing-library/jest-dom": "...",
"@testing-library/react": "...",
"@testing-library/user-event": "...",
"web-vitals": "..."
```

Add:
```json
"devDependencies": {
    "vite": "^6.0.0",
    "@vitejs/plugin-react": "^4.3.0",
    "tailwindcss": "^3.4.0",
    "postcss": "^8.4.0",
    "autoprefixer": "^10.4.0"
},
"dependencies": {
    "react": "^18.2.0",
    "react-dom": "^18.2.0",
    "react-router-dom": "^6.22.1",
    "axios": "^1.6.7",
    "zustand": "^4.5.0",
    "react-hook-form": "^7.51.0",
    "@hookform/resolvers": "^3.3.4",
    "zod": "^3.22.4",
    "lucide-react": "^0.358.0",
    "date-fns": "^3.6.0",
    "sonner": "^1.4.0"
}
```

Update scripts:
```json
"scripts": {
    "dev": "vite",
    "build": "vite build",
    "preview": "vite preview"
}
```

Remove the `"proxy"` field — handled by Vite config.

**New file:** `src/main/frontend/vite.config.js`

```js
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
    plugins: [react()],
    server: {
        port: 3000,
        proxy: {
            '/api': { target: 'http://localhost:8080', changeOrigin: true },
            '/login': { target: 'http://localhost:8080', changeOrigin: true },
            '/register': { target: 'http://localhost:8080', changeOrigin: true },
            '/verify': { target: 'http://localhost:8080', changeOrigin: true },
        }
    }
})
```

**New file:** `src/main/frontend/index.html` (Vite requires HTML at root, not in public/)

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Booking App</title>
</head>
<body>
    <div id="root"></div>
    <script type="module" src="/src/main.jsx"></script>
</body>
</html>
```

Rename `src/index.js` → `src/main.jsx`.

**New file:** `src/main/frontend/tailwind.config.js`

```js
export default {
    content: ['./index.html', './src/**/*.{js,jsx}'],
    theme: {
        extend: {
            colors: {
                primary: { DEFAULT: '#2563EB', hover: '#1D4ED8' },
                accent:  { DEFAULT: '#F59E0B' },
            }
        }
    },
    plugins: []
}
```

**Update:** `src/main/frontend/src/index.css`

```css
@tailwind base;
@tailwind components;
@tailwind utilities;
```

### Step 11.2 — Zustand Auth Store

**New file:** `src/main/frontend/src/store/authStore.js`

```js
import { create } from 'zustand'
import { persist } from 'zustand/middleware'

export const useAuthStore = create(
    persist(
        (set, get) => ({
            token: null,
            user: null,
            isAuthenticated: () => !!get().token,
            login: (token, user) => set({ token, user }),
            logout: () => set({ token: null, user: null }),
            setUser: (user) => set({ user }),
        }),
        { name: 'booking-auth' }  // stored in localStorage under this key
    )
)
```

### Step 11.3 — Centralized Axios API Client

**New file:** `src/main/frontend/src/api/client.js`

```js
import axios from 'axios'
import { useAuthStore } from '../store/authStore'

const apiClient = axios.create({
    baseURL: '/',
    headers: { 'Content-Type': 'application/json' }
})

// Attach token to every request automatically
apiClient.interceptors.request.use((config) => {
    const token = useAuthStore.getState().token
    if (token) {
        config.headers.Authorization = `Bearer ${token}`
    }
    return config
})

// Handle 401 globally — log out user if token expired
apiClient.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response?.status === 401) {
            useAuthStore.getState().logout()
            window.location.href = '/login'
        }
        return Promise.reject(error)
    }
)

export default apiClient
```

### Step 11.4 — API Service Modules

**New file:** `src/main/frontend/src/api/auth.js`

```js
import apiClient from './client'

export const authApi = {
    login: (credentials) => apiClient.post('/login', credentials),
    register: (data) => apiClient.post('/register', data),
    verify: (token) => apiClient.get(`/verify?token=${token}`),
}
```

**New file:** `src/main/frontend/src/api/flights.js`

```js
import apiClient from './client'

export const flightsApi = {
    getAll: (page = 0, size = 10) => apiClient.get(`/api/flights?page=${page}&size=${size}`),
    getById: (id) => apiClient.get(`/api/flights/${id}`),
    searchOneWay: (params) => apiClient.get('/api/flights/oneWay', { params }),
    searchRoundTrip: (params) => apiClient.get('/api/flights/return', { params }),
}
```

Create similarly: `src/main/frontend/src/api/hotels.js`, `cars.js`, `bookings.js`, `users.js`, `reviews.js`.

### Step 11.5 — Protected Route

**New file:** `src/main/frontend/src/components/ProtectedRoute.jsx`

```jsx
import { Navigate } from 'react-router-dom'
import { useAuthStore } from '../store/authStore'

export function ProtectedRoute({ children }) {
    const token = useAuthStore((s) => s.token)
    return token ? children : <Navigate to="/login" replace />
}
```

### Step 11.6 — Form Validation with React Hook Form + Zod

**Example — Login form:**

**New file:** `src/main/frontend/src/schemas/authSchemas.js`

```js
import { z } from 'zod'

export const loginSchema = z.object({
    userName: z.string().min(1, 'Username is required'),
    password: z.string().min(8, 'Password must be at least 8 characters'),
})

export const registerSchema = z.object({
    firstName:   z.string().min(1, 'First name is required'),
    lastName:    z.string().min(1, 'Last name is required'),
    userName:    z.string().min(3, 'Username must be at least 3 characters'),
    email:       z.string().email('Invalid email address'),
    password:    z.string().min(8, 'Password must be at least 8 characters'),
    phoneNumber: z.string().regex(/^\+?[0-9]{7,15}$/, 'Invalid phone number'),
})
```

**LoginForm.jsx with RHF + Zod:**

```jsx
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import { loginSchema } from '../schemas/authSchemas'
import { authApi } from '../api/auth'
import { useAuthStore } from '../store/authStore'

export function LoginForm() {
    const login = useAuthStore((s) => s.login)
    const navigate = useNavigate()

    const { register, handleSubmit, formState: { errors, isSubmitting } } = useForm({
        resolver: zodResolver(loginSchema)
    })

    const onSubmit = async (data) => {
        const response = await authApi.login(data)
        login(response.data.token, null)
        navigate('/dashboard')
    }

    return (
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
            <div>
                <input
                    {...register('userName')}
                    placeholder="Username"
                    className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"
                />
                {errors.userName && (
                    <p className="text-red-500 text-sm mt-1">{errors.userName.message}</p>
                )}
            </div>
            <button
                type="submit"
                disabled={isSubmitting}
                className="w-full bg-primary text-white py-2 rounded-lg hover:bg-primary-hover disabled:opacity-50"
            >
                {isSubmitting ? 'Logging in...' : 'Log In'}
            </button>
        </form>
    )
}
```

### Step 11.7 — Page Redesigns

All pages are rebuilt using Tailwind CSS + shadcn/ui component library.

**shadcn/ui components to install and use:**
- `Card`, `CardHeader`, `CardContent` — for flight/hotel/car cards
- `Button` — all buttons
- `Input`, `Select`, `Checkbox` — all form fields
- `Badge` — for seat types, star ratings, booking status
- `Dialog`, `DialogContent` — for reviews modal
- `Sheet` — for filter sidebar on cars page
- `Skeleton` — loading states while API calls are in-flight
- `Sonner` (toast notifications) — for success/error messages

**Layout structure for all list pages (Flights, Hotels, Cars):**
```
┌──────────────────────────────────────────────────┐
│  Navbar (sticky)                                  │
├──────────────────────────────────────────────────┤
│  Search bar (hero section with gradient bg)       │
├──────────────────────────────────────────────────┤
│  Results area                                     │
│  ┌──────────────────┐  ┌──────────────────────┐  │
│  │ Filter sidebar   │  │ Result cards (grid)  │  │
│  │ (desktop only)   │  │                      │  │
│  └──────────────────┘  └──────────────────────┘  │
└──────────────────────────────────────────────────┘
```

**Card design for FlightOneWayCard:**
```jsx
<Card className="hover:shadow-lg transition-shadow">
    <CardContent className="p-4">
        <div className="flex items-center justify-between">
            <div className="flex items-center gap-3">
                <img src={airlineLogo} alt={flight.airline} className="w-12 h-12 object-contain" />
                <div>
                    <p className="font-semibold">{flight.airline}</p>
                    <p className="text-sm text-gray-500">{flight.flightNumber}</p>
                </div>
            </div>
            <div className="text-center">
                <p className="text-lg font-bold">{departureTime}</p>
                <p className="text-sm text-gray-500">{flight.originAirportCode}</p>
            </div>
            <div className="flex flex-col items-center">
                <p className="text-xs text-gray-400">{duration}</p>
                <div className="w-24 h-px bg-gray-300 my-1" />
                <PlaneIcon className="w-4 h-4 text-primary" />
            </div>
            <div className="text-center">
                <p className="text-lg font-bold">{arrivalTime}</p>
                <p className="text-sm text-gray-500">{flight.destinationAirportCode}</p>
            </div>
            <div className="text-right">
                <p className="text-2xl font-bold text-primary">€{lowestPrice}</p>
                <Button onClick={handleSelect} className="mt-2">Select</Button>
            </div>
        </div>
    </CardContent>
</Card>
```

**Skeleton loading state:**
```jsx
function FlightCardSkeleton() {
    return (
        <Card>
            <CardContent className="p-4">
                <div className="flex items-center justify-between">
                    <Skeleton className="w-12 h-12 rounded-full" />
                    <Skeleton className="w-24 h-6" />
                    <Skeleton className="w-32 h-4" />
                    <Skeleton className="w-24 h-6" />
                    <Skeleton className="w-20 h-10" />
                </div>
            </CardContent>
        </Card>
    )
}

// Usage in Flights.jsx:
{isLoading ? (
    Array.from({ length: 5 }).map((_, i) => <FlightCardSkeleton key={i} />)
) : (
    flights.map(flight => <FlightOneWayCard key={flight.id} flight={flight} />)
)}
```

**Profile page with tabs:**
```jsx
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs'

export function Profile() {
    return (
        <div className="max-w-4xl mx-auto p-6">
            <Tabs defaultValue="details">
                <TabsList>
                    <TabsTrigger value="details">Personal Details</TabsTrigger>
                    <TabsTrigger value="bookings">My Bookings</TabsTrigger>
                </TabsList>
                <TabsContent value="details">
                    <PersonalDetails />
                </TabsContent>
                <TabsContent value="bookings">
                    <Bookings />
                </TabsContent>
            </Tabs>
        </div>
    )
}
```

---

## Execution Order Checklist

Work through these steps sequentially. Each step should compile and pass tests before moving to the next.

### Backend
- [ ] 1. Update `build.gradle` (Boot 3.4.1, jjwt 0.12.6, MapStruct, Springdoc, Redis, remove JUnit 4)
- [ ] 2. Fix `javax.*` → `jakarta.*` across all entity and DTO files
- [ ] 3. Rewrite `WebSecurityConfig.java` for Boot 3 (no `WebSecurityConfigurerAdapter`)
- [ ] 4. Fix bugs in `User.java` (`isAccountNonExpired`, `getAuthorities`)
- [ ] 5. Update `JwtTokenUtil.java` for jjwt 0.12.x API
- [ ] 6. Run `./gradlew compileJava` — fix any remaining compile errors
- [ ] 7. Delete `application.properties` → create `application.yml` + dev/prod profiles
- [ ] 8. Create all 5 MapStruct mapper interfaces
- [ ] 9. Update ServiceImpl files to inject mappers, remove manual DTO construction
- [ ] 10. Add `@Valid` to all controller endpoints that accept `@RequestBody`
- [ ] 11. Add missing validation annotations to DTOs
- [ ] 12. Add `@Transactional` to write operations in BookingServiceImpl, UserServiceImpl, RegistrationServiceImpl
- [ ] 13. Fix N+1: add fetch join JPQL queries to BookingRepository, FlightRepository, HotelRepository
- [ ] 14. Add pagination (change to JpaRepository, add Pageable) to Flight/Hotel/Car controllers
- [ ] 15. Create `OpenApiConfig.java` + annotate all controllers with OpenAPI annotations
- [ ] 16. Create `CacheConfig.java` + add `@Cacheable`/`@CacheEvict` to service methods
- [ ] 17. Add SLF4J logging to all ServiceImpl classes
- [ ] 18. Run `./gradlew test` — all tests should pass
- [ ] 19. Create `Dockerfile` (multi-stage build)
- [ ] 20. Create `docker-compose.yml` (app + MySQL + Redis)
- [ ] 21. Create `.env.example` + add `.env` to `.gitignore`
- [ ] 22. Create `.github/workflows/ci.yml`
- [ ] 23. Migrate all tests from JUnit 4 → JUnit 5 syntax
- [ ] 24. Add new unit tests for FlightServiceImpl, HotelServiceImpl, CarServiceImpl, UserServiceImpl

### Frontend
- [ ] 25. Update `package.json` (remove CRA, add Vite, Tailwind, Zustand, RHF, Zod, shadcn)
- [ ] 26. Create `vite.config.js` with proxy configuration
- [ ] 27. Create `tailwind.config.js` and `postcss.config.js`
- [ ] 28. Move `public/index.html` → root `index.html`, rename `index.js` → `main.jsx`
- [ ] 29. Update `src/index.css` with Tailwind directives
- [ ] 30. Install shadcn/ui components (`npx shadcn@latest init`)
- [ ] 31. Create `src/store/authStore.js` (Zustand)
- [ ] 32. Create `src/api/client.js` (Axios with interceptors)
- [ ] 33. Create all API service modules (`src/api/*.js`)
- [ ] 34. Create Zod schemas (`src/schemas/`)
- [ ] 35. Create `src/components/ProtectedRoute.jsx`
- [ ] 36. Update `App.jsx` (routes with ProtectedRoute)
- [ ] 37. Redesign LoginForm + RegisterForm with RHF + Zod
- [ ] 38. Redesign Dashboard
- [ ] 39. Redesign Flights page (search bar + skeleton + cards)
- [ ] 40. Redesign Hotels page
- [ ] 41. Redesign Cars page
- [ ] 42. Redesign Booking flows (seat selection, room selection)
- [ ] 43. Redesign Profile page (tabs: personal details + bookings)
- [ ] 44. Redesign BookingSuccess page
- [ ] 45. Add Sonner toast notifications for all success/error states

---

## Verification

After completing all steps, verify the following:

```bash
# Backend
./gradlew test                    # All tests pass
./gradlew bootRun                 # App starts on :8080
# Open: http://localhost:8080/swagger-ui.html
# Try: POST /login, GET /api/flights via Swagger

# Docker
docker compose up --build         # All three containers start
# App accessible at: http://localhost:8080/swagger-ui.html

# GitHub Actions
git push                          # CI pipeline runs and passes

# Frontend
cd src/main/frontend
npm install
npm run dev                       # Vite starts on :3000
# Open: http://localhost:3000
# Test: register, verify email, login, search flights, book a flight, view bookings
```
