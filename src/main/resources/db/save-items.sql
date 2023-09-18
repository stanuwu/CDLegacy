INSERT INTO items
VALUES (:$1, :$2, :$3)
ON CONFLICT ON CONSTRAINT items_pkey DO UPDATE SET
    count = :$3
;