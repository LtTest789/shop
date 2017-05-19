# shop
Docker image parsiuntimas ir paleidimas:

      docker pull romas/shop
      docker run -d -p 80:9000 romas/shop:latest
  

Parsiusti ir paleisti programa sekite šiuos zingsnius :

      git clone https://github.com/LtTest789/shop.git

      docker build -t shop:latest .

      docker run -d -p 80:9000 shop:latest

Gauti JWT token'a:
    POST
        /shop/login

        {
        	"username" : "admin",
        	"password" : "password"
        }
    Bus gautas atsakymas "Bearer <TOKEN>".

    Tokeną pridėti prie kiekvieno Http request'o header'io "Authorization : Bearer <TOKEN>"

Rest Endpoints:
 
  GET

    Pridėti header'i "Authorization : Bearer <TOKEN>"

    /shop/items/{id} -> gauti viena prekę 'item' įrašą id prekės numeris
    /shop/items -> gauti visas prekes 'items'
    
  POST

     Pridėti header'i "Authorization : Bearer <TOKEN>"

    shop/items -> pridėti prekę 'item'
      {
        "itemName": "Nauja preke",
        "description": "Aprasymas,
        "countryOfManufactor": "Salis",
        "dateOfManufactor" : "2016-12-12",
        "warehouses" : [
         {
            "city" : "Miestas",
            "street" : "gatve",
            "number" : "12"
         }]
     }
     
  PUT

     Pridėti header'i "Authorization : Bearer <TOKEN>"
  
    /shop/items/{id} -> atnaujinti prekės įrašą, id prekės numeris
      {
        "itemName": "NameUpdated",
        "description": "description Update",
        "countryOfManufactor": "China",
        "dateOfManufactor" : "2016-12-12",
        "warehouses" : [
        	{
        		"city" : "Vilnius",
        		"street" : "obelu",
        		"number" : "12"
        	},
        		{
        		"city" : "Kaunas",
        		"street" : "laisves",
        		"number" : "5"
        	}
        ]
      }
    
  DELETE

    Pridėti header'i "Authorization : Bearer <TOKEN>"

    shop/items/{id} -> ištrinam prekę iš duomenų bazės naudojant id.

