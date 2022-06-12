# Domain model

There are three domain objects in the model: the Member and the Authority and the user group.

Member represents a user. It has an id, a username, national code and a set consist of authorities.

Authority is used to organize the user access to the end points. It has an id and a title and description. 

User Group is used to organize the users in sets. It has an id and a name.

# Security

Each request can contain a valid JWT token as an Authorization HTTP header. If it is present, 
the application verifies this token and authorizes the user.

In case of an incorrect token the request is blocked.

# Use cases

### User registration

A new user can be created via REST. This endpoint is not available for everyone.

If you Run the Project for the first time, admin user with username 'admin' is created by system and
the password is shown on the command line. If you want the password for admin look for 
'Admin account's password is set to :' 
in the command line.

you can get JWT token with provided password as admin and can perform as super admin.

All the end point is secure wit JWT Token and only refresh-token and login is not protected.
If a user has MEMBER_CREATE authority it can create users.
Admin has all the authority.

It is possible to perform CRUD on User Groups as an authenticated user.
It is possible to perform CRUD on Authorities as an authenticated User.
It is possible to Perform change password, get user authority, create, update, 
get and get list as an authenticated user that has every one of authorities.

login, refresh-token end point is not protected by JWT token.

swagger-ui is provided for more Information and documentation.

### User login

This endpoint is also not protected. After submitting a user's username and password, 
the app verifies the credentials and
in case of success, returns a JWT token in the following format:

Response body
{
"accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsIlRPS0VOX1RZUEUiOiJBQ0NFU1NfVE9LRU4iLCJleHAiOjE2NTQ5NzQ2NTUsImlhdCI6MTY1NDk2ODY1NX0.SDKImOMCFHyTlaEIcGiwqEuxrEfVYuu0G50qDteowaiivPf-1KVeGbupNs8ec5F_Rg5BjApKdEeOsQ_EA-L5SA",
"refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsIlRPS0VOX1RZUEUiOiJSRUZSRVNIX1RPS0VOIiwiZXhwIjoxNjU0OTgwNjU1LCJpYXQiOjE2NTQ5Njg2NTV9.Z9CifnzYgqHMCOgYWHLAbDvgWNx4DFaqK_EVsrO0Vh3XP0FmmcseziULwZfXgDprmMoC_QEel-qEwR-qaxzI9w",
"accessTokenExpirationTimeInMilliSeconds": 6000000,
"refreshTokenExpirationTimeInMilliSeconds": 12000000
}

### Group management

It is possible to perform CRUD on groups as an authenticated user.

### AUTHORITIES

It is possible to perform CRUD on authorities as an authenticated user.

Running the application:

```docker-compose up```

```./gradlew bRu```
