# shop
Docker image parsiuntimas ir paleidimas:

      docker pull romas/shop
      docker run -d -p 80:9000 romas/shop:latest
  

Parsiusti ir paleisti programa sekite šiuos zingsnius :

      git clone https://github.com/LtTest789/shop.git

      docker build -t shop:latest .

      docker run -d -p 80:9000 shop:latest
  
Rest Endpoints:
 
  GET
  
    /shop/items/{id} -> gauti viena prekę 'item' įrašą id prekės numeris
    /shop/items -> gauti visas prekes 'items'
    
  POST
  
    shop/items -> pridėti prekę 'item'
    {
    "itemName": "Mouse",
    "description": "Logitect mouse 3500 Dpi",
    "countryOfManufactor" : "Vietnam",
    "dateOfManufactor" : "2013-12-5",
    "price": 444
     }
     
  PUT
  
    /shop/items/{id} -> atnaujinti prekės įrašą, id prekės numeris
    {
    "itemName": "NameUpdated",
    "description": "description Update",
    "countryOfManufactor" : "China",
    "dateOfManufacturing" : "2017-03-30",
    "price": 123
    }
    
  DELETE
  
    shop/items/{id} -> ištrinam prekę iš duomenų bazės naudojant id.
