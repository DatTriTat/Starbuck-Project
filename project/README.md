# CMPE 172 Project Journal

## Architecture Diagram
![img.png](./images/img.png)

## Cashier's App
#### Port Node.js App to Spring MVC 
![img_7.png](./images/img_7.png)
![img_8.png](./images/img_8.png)
![img_9.png](./images/img_9.png)

**Controller must process JSON responses from API and pass to View via Models**
![img_4.png](./images/img_4.png)
![img_5.png](./images/img_5.png)
![img_6.png](./images/img_6.png)

#### Support Admin Logins for Starbucks Employees
![img_1.png](./images/img_1.png)
![img_10.png](./images/img_10.png)
![img_2.png](./images/img_2.png)
![img_11.png](./images/img_11.png)

## Scalable Cloud Deployment on GCP
![img_12.png](./images/img_12.png)
![img_13.png](./images/img_13.png)

#### External Load Balancer as Ingress for Cashier's App
![img_14.png](./images/img_14.png)

#### Internal Load Balancer for Starbucks API behind Kong API Gateway
![img_15.png](./images/img_15.png)

## Implementation Uses Required Cloud Databases

#### MySQL Database 8.0
**Must use Cloud SQL (MySQL Option)**
![img_16.png](./images/img_16.png)
![img_17.png](./images/img_17.png)
![img_18.png](./images/img_18.png)
![img_19.png](./images/img_19.png)
![img_20.png](./images/img_20.png)
![img_21.png](./images/img_21.png)
## Starbucks API for Mobile App and Store Front
![img_22.png](./images/img_22.png)
![img_23.png](./images/img_23.png)
#### Deployed with Kong API Gateway with API Key Authentication
![img_24.png](./images/img_24.png)
![img_25.png](./images/img_25.png)
![img_26.png](./images/img_26.png)

## Rabbit
![img_27.png](./images/img_27.png)

## Github Commit
May 4, 2023:I added user api in starbuck project

May 6, 2023: I added login and signup for starbuck cashier. I faced difficulties in retrieving input from the template in the login and register sections.

May 13, 2023: I added authentication starbuck cashier

May 14, 2023 I added rabbitmq

May 17, 2023: I update yaml files to deploy and kong. I encountered an issue with Kong when trying to add it, and this version causes the Kong Ingress to not be able to connect to the Kong controller. I have tried to fix it.

May 22, 2023 i added scheduling.

**How does your Solution Scale?  Can it handle > 1 Million Mobile Devices? Explain.**
My solution is capable of scaling to accommodate more than 1 million mobile devices. It achieves this scalability by employing a horizontal scaling approach, where additional servers can be added under a load balancer. However, it is worth noting that while this approach can handle the increased load, it may not be the most efficient or optimal solution for such a scenario.

## Link to Final demo recording running on Google Cloud
https://drive.google.com/file/d/1_yRPufkZcxcSCXdAbMcIcwnxnET9z9T7/view?usp=share_link
