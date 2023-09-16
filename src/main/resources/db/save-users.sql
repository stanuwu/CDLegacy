INSERT INTO users
VALUE ($1=>?, $2=>?, $3=>?, $4=>?, $5=>?, $6=>?, $7=>?, $8=>?, $9=>?, $10=>?, $11=>?, $12=>?, $13=>?, $14=>?, $15=>?, $16=>?, $17=>?, $18=>?, $19=>?, $20=>?, $21=>?, $22=>?, $23=>?, $24=>?)
ON CONFLICT(userid) DO UPDATE SET
    name = $2=>?,
    title = $3=>?,
    description = $4=>?,
    class = $5=>?,
    exp = $6=>?,
    coins = $7=>?,
    weapon = $8=>?,
    armor = $9=>?,
    extra = $10=>?,
    weapon_ex = $11=>?,
    armor_xp = $12=>?,
    extra_xp = $13=>?,
    last_vote = $14=>?,
    quest = $15=>?,
    quest_level = $16=>?,
    quest_progress = $17=>?,
    farming = $18=>?,
    monster_slain = $19=>?,
    doors_opened = $20=>?,
    bosses_slain = $21=>?,
    items_found = $22=>?,
    chests_opened = $23=>?,
    deleted = $24=>?
;