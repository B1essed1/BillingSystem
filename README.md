# BillingSystem
Supermarket billing system

This project created to use in small/middle shops that has not branches or shopping network
For database part I used Postgres
Project is fully dokcerized:

  There are some images :
  
    * Grafana ---- implemenyed ✔
    
    * Prometheus --implemented ✔
    
    * Kafka -------Topics created, but Consumers did not implemented ✔
    
    * Postgres-----implemented✔
    

All you need to do ```docker-compose up``` and VUALYA if you have Docker you do not have to install anything and cofigure nothing
  


salesmens can be registered with OTP ✔
project securet with  Role based JWT token so only we can track salesmens ,
and can add some features that only shop owners can see✔

with this project shop owner can monitor:
- debitors: all transactions related to this specific debitor ✔
- how much debt he has , does he have deposite or not ✔
- how many product in shop ✔
- how much profit shop owner does in specific period 
- what product is more profitibe
- what product is on demand 
- daily stats about products that are left less than 10 with cron Job , So shop owner can bring new ones 
- monthly profit stats or in specific preiod 
- debitors can pay all debts ✔

 and some more features 
 
 Below you can see databes structure briefly 
 ![billingsystem](https://user-images.githubusercontent.com/77454227/202519349-d88591a7-e5ac-4651-92da-f4fc29525237.png)
 
 and there is Swwagger docs for client side developers 
 
 ![image](https://user-images.githubusercontent.com/77454227/202520848-a1bcc6b7-06f3-449c-a47b-a5b6524349a2.png)


 
 
