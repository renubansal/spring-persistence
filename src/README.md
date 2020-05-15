1. Create table in database 'persistence'
2. Make sure to add key and auto_increment. In case you forgot use this,
    alter table person modify column id int auto_increment;   
3. Use curl command to test post and get mapping
    curl localhost:8080/demo/add -d firstName=anyName -d lastName=anyName
    curl 'localhost:8080/demo/all' 