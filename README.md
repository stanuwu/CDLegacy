# CDLegacy

Remaster of [CustomDungeons](https://github.com/stanuwu/CustomDungeons), with features and content
from [CustomDungeons 2](https://github.com/stanuwu/CD2).

Written with performance and maintainability in mind.

> Building:
> `mvn clean package`

> Database Setup:
>
> Create a new database using `createdb cd_legacy`.
>
> Import database from `./db/cd_legacy.sql` using `psql cd_legacy < cd_legacy.sql`.
>
> Put `db.txt` into `./data` containing:
> ````
> postgres ip
> 
> postgres port
> 
> postgres user
> 
> postgres password
> 
> database name
> ````
> Requires PostgreSQL.

> Running:
>
> Put `token.txt` with your bot token into `./data`.
>
> Requires Java 17 and PostgreSQL.