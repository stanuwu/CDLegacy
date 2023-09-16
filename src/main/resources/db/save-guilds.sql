INSERT INTO guilds
VALUE ($1=>?, $2=>?, $3=>?, $4=>?)
ON CONFLICT(guildid) DO UPDATE SET
    doors_opened = $2=>?,
    monsters_slain = $3=>?,
    bosses_slain = $4=>?
;