# Embed vs Reference Decision Sheet

## Decision Matrix

| Relationship | Read Together? | Shared? | Independent Lifecycle? | Bounded? | Decision | Rationale |
|---|---|---|---|---|---|---|
| Product - Review | Yes | No | No | Yes (bounded per product) | Embed | Reviews are always retrieved and displayed alongside the product. Embedding avoids costly joins while maintaining single-document atomicity. |
| Product - Category | Sometimes | Yes (many products share one category) | Yes | N/A | Reference (by ID) | Categories exist independently, are referenced by multiple products, and updating category details should not trigger multi-document writes. |

## Core Principles
1. MongoDB does not enforce relational foreign keys; the application service layer validates reference integrity.
2. Embedding unbounded collections (e.g., hundreds of thousands of reviews) causes document growth beyond the 16MB BSON limit; bounded embedding remains performant.
3. Single-document operations in MongoDB are atomic at the document level.
