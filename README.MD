[![Tracis CI](https://travis-ci.org/kajstrom/repmaxtrackerbackend.svg?branch=master)](https://travis-ci.org/kajstrom/repmaxtrackerbackend)
[![Coverage Status](https://coveralls.io/repos/github/kajstrom/repmaxtrackerbackend/badge.svg?branch=master)](https://coveralls.io/github/kajstrom/repmaxtrackerbackend?branch=master)

# REST ENDPOINTS

- GET /exercises returns list of exercise types
- GET /sets returns list of sets (For all user ids currently)
- GET /sets/{id} Returns a single set.
- POST /sets adds a new set
- GET /users/{id}/sets returns list of a user's sets.
- DELETE /users/{user_id}/sets/{set_id} delete's a user's set.

## TODO

- Authentication.
- Support for pagination of sets.