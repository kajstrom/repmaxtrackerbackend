[![Tracis CI](https://travis-ci.org/kajstrom/repmaxtrackerbackend.svg?branch=master)](https://travis-ci.org/kajstrom/repmaxtrackerbackend)
[![Coverage Status](https://coveralls.io/repos/github/kajstrom/repmaxtrackerbackend/badge.svg?branch=master)](https://coveralls.io/github/kajstrom/repmaxtrackerbackend?branch=master)

# REST ENDPOINTS

- GET /exercises returns list of exercise types
- GET /sets returns list of sets (For all user ids currently)
- POST /sets adds a new set

## TODO

- Add endpoint GET /sets/{id} for retrieving a specific set.
- Add endpoint GET /user/{id}/sets for retrieving sets by user id.
