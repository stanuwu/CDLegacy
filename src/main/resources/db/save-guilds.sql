INSERT INTO guilds
VALUES (:$1, :$2, :$3, :$4)
ON CONFLICT ON CONSTRAINT guilds_pkey DO UPDATE SET
    doors_opened = :$2,
    monsters_slain = :$3,
    bosses_slain = :$4
;