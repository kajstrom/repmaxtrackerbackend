[![Tracis CI](https://travis-ci.org/kajstrom/repmaxtrackerbackend.svg?branch=master)](https://travis-ci.org/kajstrom/repmaxtrackerbackend)

# REST ENDPOINTS

- GET /exercises returns list of exercise types
- GET /sets returns list of sets (For all user ids currently)
- POST /sets adds a new set

## TODO

- Move database handling and domain logic elsewhere from controllers.
- Add endpoint GET /sets/{id} for retrieving a specific set.
- Add endpoint GET /user/{id}/sets for retrieving sets by user id.
