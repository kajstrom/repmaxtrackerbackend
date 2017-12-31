[![Tracis CI](https://travis-ci.org/kajstrom/repmaxtrackerbackend.svg?branch=master)](https://travis-ci.org/kajstrom/repmaxtrackerbackend)
[![Coverage Status](https://coveralls.io/repos/github/kajstrom/repmaxtrackerbackend/badge.svg?branch=master)](https://coveralls.io/github/kajstrom/repmaxtrackerbackend?branch=master)

# REST ENDPOINTS

## Exercises
- GET /exercises returns list of exercise types

## Sets
- GET /sets returns list of sets.
- GET /sets/{id} Returns a single set.
- POST /sets adds a new set

Sets endpoints will be removed or made available only with adequate rights in the future.

## User's sets
- GET /users/{id}/sets returns list of a user's sets.
- DELETE /users/{user_id}/sets/{set_id} deletes a user's set.

# TODO

- Endpoint GET /user/{user_id}/sets/{set_id}
- Authentication.
- Support for pagination of sets.