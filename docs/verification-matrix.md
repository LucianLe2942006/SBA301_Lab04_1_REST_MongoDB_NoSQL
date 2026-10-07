# Postman Verification Matrix

| # | Request | Expected Status & Result | Evidence Type | Status |
|---|---|---|---|---|
| 1 | POST /api/products valid | 201 Created + Location header + response body | Response Body & Headers | PASS |
| 2 | POST /api/products invalid name | 400 Bad Request | Error JSON | PASS |
| 3 | POST /api/products invalid categoryId | 404 Not Found | Error JSON | PASS |
| 4 | GET /api/products/{id} existing | 200 OK | Response Body | PASS |
| 5 | GET /api/products/{id} missing | 404 Not Found | Error JSON | PASS |
| 6 | PUT /api/products/{id} existing | 200 OK with updated attributes | Response Body | PASS |
| 7 | DELETE /api/products/{id} existing | 204 No Content | No body | PASS |
| 8 | GET /api/products?page=0&size=5 | 200 OK + Paginated JSON with max 5 items | Page JSON | PASS |
| 9 | GET /api/products?sortBy=price&direction=desc | 200 OK sorted by price descending | Sorted JSON | PASS |
| 10 | GET /api/products?categoryId={id} | 200 OK filtered by categoryId | Filtered JSON | PASS |
| 11 | POST /api/products/{id}/reviews | 200 OK with embedded review array updated | Document Body | PASS |
| 12 | Duplicate unique sku | Controlled error (unique index constraint) | HTTP 500 / 409 + Log | PASS |
| 13 | Verify collections in Compass | products and categories collections exist | Compass Schema | PASS |
| 14 | API contract unchanged for consumers | HTTP contract compatible with existing frontend | Integration Verification | PASS |
