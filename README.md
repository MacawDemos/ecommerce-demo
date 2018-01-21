Ecommerce Demo Application powered by Macaw
-------------------------------------------

Java open source e-commerce software - based on Shopizer 2.0.5 (http://www.shopizer.com)


### Get Macaw:
--------------

https://macaw.io/macaw-community-edition-aws/
 or
https://macaw.io/macaw-community-edition-onprem/

Get Started:
------------
https://macaw.io/getting-started/

### Get the Ecommerce Demo:
---------------------------

Set environment variable DEMO_ROOT to the location where the demo application is going to be downloaded to
For example: 

$ export DEMO_ROOT=/opt/demos

cd $DEMO_ROOT 

$ git clone https://github.com/macawdemo/ecommerce-demo.git


### Build and Publish the demo application:
-------------------------------------------
From the command line with Maven installed:

	$ cd ecommerce-demo
	$ mvn clean install

Publish the Megastore containerized microservice to Meta Data Repository (MDR) and deploy it using Macaw DevOps Console

macawpublish service --tag ecommerce-demo-ver1 $DEMO_ROOT/ecommerce-demo

Run the application as a Macaw WebApp
-------------------------------------
Follow Macaw documentation to create and deploy a WebApp of sm-shop/target/sm-shop.war


### Run the application from Spring boot:
-----------------------------------------

- Configure Macaw Client Properties:
In the user home directory create a file named "macaw.conf" with the following properties

        service.registry.host=<IP address of the Macaw Platform Host>
        service.registry.port=8443
        service.registry.protocol=https
        cfx.kafka.servers=<IP address of the Macaw Platform Host>:<Kafka port example: 9092>
        cfx.zookeeper.connect=<IP address of the Macaw Platform Host>:<Zookeeper port example: 2181>
        api.gateway.host=<IP address of the Macaw Platform Host>
        api.gateway.protocol=https
        api.gateway.port=443
        api.gateway.appname=api_gateway
        api.gateway.token.renewal.interval=900000
        cfx.ssl.truststore.location=<Absolute path to the location of trust store file ca_truststore>
        cfx.ssl.truststore.password=<Truststore pass phrase>
        io.macaw.demo.user=<Macaw service api access userid>
        io.macaw.demo.password=<Macaw service api access password>

- Run the demo:

       $ cd sm-shop
       $ mvn spring-boot:run

### Access the application:
--------------------------

Access the deployed web application at: http://localhost:8080/

Acces the admin section at: http://localhost:8080/admin

#####username : admin
#####password : password

### Documentation:
-------------------

Documentation available from https://macaw.io/documentation
