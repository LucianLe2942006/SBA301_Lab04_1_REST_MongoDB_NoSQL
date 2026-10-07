# API Contract - SBA301 Lab 04.1 MongoDB

Base URL: http://localhost:8080/api/products

## Endpoints

### 1. GET /api/products
- Method: GET
- Path: /api/products
- Query Parameters:
  - keyword: String (optional)
  - categoryId: String (optional)
  - page: int (default 0)
  - size: int (default 10, max 50)
  - sortBy: String (default name, whitelist: name, price, stock)
  - direction: String (default asc)
- Response: 200 OK + Page of ProductResponse

### 2. GET /api/products/{id}
- Method: GET
- Path Parameter: id (String)
- Responses:
  - 200 OK: ProductResponse
  - 404 Not Found: ApiError

### 3. POST /api/products
- Method: POST
- Headers: Content-Type: application/json
- Request Body:
```json
{
  "name": "Mechanical Keyboard",
  "price": 120.00,
  "stock": 20,
  "categoryId": "c1"
}
```
- Responses:
  - 201 Created: ProductResponse + Location header /api/products/{id}
  - 400 Bad Request: Validation error
  - 404 Not Found: Category not found

### 4. PUT /api/products/{id}
- Method: PUT
- Path Parameter: id (String)
- Headers: Content-Type: application/json
- Request Body:
```json
{
  "name": "Mechanical Keyboard Pro",
  "price": 140.00,
  "stock": 15,
  "categoryId": "c1"
}
```
- Responses:
  - 200 OK: ProductResponse
  - 400 Bad Request: Validation error
  - 404 Not Found: Product or category not found

### 5. DELETE /api/products/{id}
- Method: DELETE
- Path Parameter: id (String)
- Responses:
  - 204 No Content
  - 404 Not Found

### 6. POST /api/products/{id}/reviews
- Method: POST
- Path Parameter: id (String)
- Headers: Content-Type: application/json
- Request Body:
```json
{
  "userId": "u101",
  "rating": 5,
  "comment": "Great mechanical keyboard!"
}
```
- Responses:
  - 200 OK: ProductResponse with updated embedded reviews
  - 400 Bad Request: Validation error
  - 404 Not Found: Product not found
