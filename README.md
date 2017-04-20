# shop
Parsiusti git repozitorija:
  git clone 
  
Paleisti Programa:
  docker build -t shop:latest .
  docker run -d -p 9000:9000 shop:latest
  
Rest Endpoints:
 
  GET
  
    /shop/retrieveItem/{id} -> gauti viena prekę 'item' įrašą id prekės numeris
    /shop/items -> gauti visas prekes 'items'
    
  POST
  
    shop/add -> pridėti prekę 'item'
    {
    "itemName": "Mouse",
    "description": "Logitect mouse 3500 Dpi",
    "countryOfManufactor" : "Vietnam",
    "dateOfManufactor" : "2013-12-5",
    "price": 444
     }
     
  PUT
  
    /shop/updateItem/{id} -> atnaujinti prekės įrašą, id prekės numeris
    {
    "itemName": "NameUpdated",
    "description": "description Update",
    "countryOfManufactor" : "China",
    "dateOfManufacturing" : "2017-03-30",
    "price": 123
    }
    
  DELETE
    shop/deleteItem/{id} -> ištrinam prekę iš duomenų bazės naudojant id.
