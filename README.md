# SBA301 Lab 04.1 - REST + MongoDB

## Prerequisites
- JDK 21
- MongoDB Community Server
- MongoDB Compass

## Database
mongodb://localhost:27017/sba301_lab41

## Run
1. Start MongoDB service
2. Run Spring Boot application
3. Open Compass
4. Import/run Postman collection

## API
- GET /api/products
- GET /api/products/{id}
- POST /api/products
- PUT /api/products/{id}
- DELETE /api/products/{id}
- POST /api/products/{id}/reviews

## Modeling decision
Review is embedded because reviews are always read together with the product and remain bounded. Category is referenced by ID because categories are shared across many products and have an independent lifecycle.

## Evidence
List E01-E15.
