# JPA/SQL vs MongoDB In-Depth Comparison

## Architecture & Layer Comparison

| Aspect | JPA / SQL Server | MongoDB NoSQL | Design Implication |
|---|---|---|---|
| Persistence Model | Normalized relational tables (@Entity, @Table) | Hierarchical BSON documents (@Document) | Denormalize/embed data frequently read together. |
| Primary Identifier | Long numeric ID (@GeneratedValue IDENTITY) | String or ObjectId mapped to _id | Flexible distributed ID generation. |
| Relationships | Foreign keys + SQL JOINs (@ManyToOne, @OneToMany) | Embedded documents or manual ID reference | Avoid cross-collection joins; handle integrity in service. |
| Schema Enforcement | DDL schema constraints at database engine level | Dynamic/flexible schema with application validation | Application DTOs and validation boundaries become crucial. |
| Query Mechanism | JPQL, SQL, and Derived Queries | MongoDB Query methods, criteria, and aggregation | Derived queries work similarly via Spring Data. |
| Indexing | B-Tree indexes on relational columns | B-Tree compound and unique indexes (@Indexed) | Indexes align with access patterns and queries. |
| Transaction & Atomicity | ACID multi-row/table transactions | Single-document atomic operations | Structure aggregate roots within single documents. |
| Persistence Testing | @DataJpaTest with SQL/H2 slice | Unit tests with Mockito + Mongo slice tests | Controller/Service tests remain independent of database engine. |
