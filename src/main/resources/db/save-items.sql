INSERT INTO items
VALUES (:$1, :$2, :$3)
ON CONFLICT ON CONSTRAIT items_pkey DO UPDATE SET
    count = :$3
;