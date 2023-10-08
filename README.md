# Warehouse API

### Delmål

1. Se till att warehouse fungerar med multi-threads
2. implementera Resourceklasser med lämpliga endpoints för att hantera produkter och kategorier
3. VG - skapa testmetoder för att validera responsekoder och data från våra resource metoder.

#### [X]  0 - Skriv om Warehouse enl Martins feedback

#### 1- Concurrent Warehouse
    - Skapa interface emellan warehouseAPI och warehouse
    - modifiera warehouse så den använder sig av concurrent metoder
        - X -CopyOnWriteArrayList för ArrayList
        - Eller skapa en WarehouseService som erbjuder metoder anpassade för concurrency
        - (Extra) Alternativt skapa en Threadpool för hantering av multipla requests

#### 2 - Resource endpoints
        - Skapa endpoints för enskilda metoder i warehouse
            - Lägga till ny produkt med validering av värden
                - "/products/add?name=name&price=price&quantity=quantity
                - finns inte parametrarna, kasta 404
                - evt validering (clientside?) innnan tillägg på backend
            - Hämta alla produkter
                - "/products"
                    - query för filtrering (alfabetiskt, efter datum...)
            - Hämta en produkt genom produktens id
                - "/products/${id}
            - Hämta alla produkter som tillhör en kategori
                - "products/${category}"
        - REST
        - Rita upp diagram för REST struktur (datastruktur?)
        - Skapa JSON konverterare/parser
        - Paginering med JSON
    
#### 3 - Tests
        - Skriv tester för varje endpoint
            - Mocka objekt som resource klassen använder
        - Enums för responsekoder? Kanske kan funka 
        och göra det enklare att testa
        - 

        RestEasy, jax-rs

# Warehouse API

---
#### `/products`
`GET` Returns all products in database sorted by default

#### `/products/${category}`
`GET` Gets all products in requested `category`

#### `/products/${id}`
`GET` Get product of requested `id`

Query params for filtering (`filter=...`) `A`, `B`, `C`

---
#### `/products/add`
`POST` Adds new product with supplied query params `(name, price, quantity)`
