[![Tracis CI](https://travis-ci.org/kajstrom/repmaxtrackerbackend.svg?branch=master)](https://travis-ci.org/kajstrom/repmaxtrackerbackend)
[![Coverage Status](https://coveralls.io/repos/github/kajstrom/repmaxtrackerbackend/badge.svg?branch=master)](https://coveralls.io/github/kajstrom/repmaxtrackerbackend?branch=master)

# REST ENDPOINTS

- GET /exercises returns list of exercise types
- GET /sets returns list of sets (For all user ids currently)
- GET /sets/{id} Returns a single set.
- POST /sets adds a new set
- GET /users/{id}/sets returns list of a user's sets.

## TODO

- Separate Set mapping to a RowMapper class.
- Implement a static factory method in SetResource for creating an instance from a Set.
- Add error handling for attempting to retrieve a non-existent user's sets.