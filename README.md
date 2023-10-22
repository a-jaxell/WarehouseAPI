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
#### `/products`
`POST` Adds new product with supplied query params `(name, price, quantity)`