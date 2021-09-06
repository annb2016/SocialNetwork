# SocialNetwork
This repository has an application for social networking which allows creation of new posts, comments on posts and likes on posts and comments. 
A feed based on page id and page size will show the posts, commwents and likes relevant to that page.

Following APIs have been implemented in the project-

1. api/v1/createPost
2. api/v1/feeds
3. api/v1/deletePost
4. api/v1/createLike
5. api/v1/createComment

## Here are the cURL for each API-


curl -L -X POST 'http://localhost:8080/api/v1/createPost' \
-H 'Content-Type: application/json' \
--data-raw '{   "id": 11,
    "uid": 11,
    "title": "title_text",
    "body": "body_text"
    }'
    
curl -L -X GET 'http://localhost:8080/api/v1/feeds/?pageSize=5&pageId=2'
    
curl -L -X DELETE 'http://localhost:8080/api/v1/deletePost?postid=13'

curl -L -X POST 'http://localhost:8080/api/v1/createLike' \
-H 'Content-Type: application/json' \
--data-raw '{
        "id": 4,
        "uid": 4,
        "resourceId": 3,
        "name": "Jane Doe",
        "type": "feed" }'
      
curl -L -X POST 'http://localhost:8080/api/v1/createComment' \
-H 'Content-Type: application/json' \
--data-raw '{
        "id": 8,
        "uid": 8,
        "postid": 5,
        "name": "Lucy",
        "body": "Great" }'
      
      
## API documentation - provided by Swagger2
Once the application is started, API documentation can be found at - http://localhost:8080/swagger-ui.html
 
      

