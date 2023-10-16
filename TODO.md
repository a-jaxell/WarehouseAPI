### Delmål

1. - [X] Se till att warehouse fungerar med multi-threads
2. - [ ] implementera Resource-klasser med lämpliga endpoints för att hantera produkter och kategorier
3. - [ ] VG - skapa testmetoder för att validera response-koder och data från våra resource metoder.

####   0 -[X] Skriv om Warehouse enl Martins feedback

#### 1 - Concurrent Warehouse

- [X] Skapa interface emellan warehouseAPI och warehouse
    - [X] modifiera warehouse så den använder sig av concurrent metoder
      - [X] CopyOnWriteArrayList för ArrayList
      - [ ] (Extra) Eller skapa en WarehouseService som erbjuder metoder anpassade för concurrency
      - [ ] (Extra) Alternativt skapa en Thread-pool för hantering av multipla requests

#### 2 - Resource endpoints

- [ ] Skapa endpoints för enskilda metoder i warehouse
    - [ ] Lägga till ny produkt med validering av värden
      - [ ] "/products/add?name=name&price=price&quantity=quantity
      - [ ] finns inte parametrarna, kasta 404
        - [X] ~~evt validering (clientside?) innan tillägg på backend~~
      - [X] Hämta alla produkter
        - [X] "/products"
        - [ ] query för filtrering (alfabetiskt, efter datum...)
      - [X] Hämta en produkt genom produktens id
        - [X] "/products/${id}
      - [X] Hämta alla produkter som tillhör en kategori
        - [X] "products/${category}"

    - [ ] REST
    - [ ] Rita upp diagram för REST struktur (datastruktur?)
    - [ ] Skapa JSON konverterare/parser
    - [ ] (Extra) Paginering med JSON

#### 3 - Tests
        - Skriv tester för varje endpoint
            - Mocka objekt som resource klassen använder
        - Enums för responsekoder? Kanske kan funka 
        och göra det enklare att testa
        - 

        RestEasy, jax-rs
