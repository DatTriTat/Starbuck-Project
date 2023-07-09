# CMPE 172 - Lab #8 Notes

# **Kong on Local Docker**

**1. Run Starbucks API in Docker**
![img.png](./images/img.png)

**2. Run Kong Docker in DB-Less Mode and Test Ping**
![img_1.png](./images/img_1.png)

**No API key testing**
![img_2.png](./images/img_2.png)

**Postman testing**
![img_3.png](./images/img_3.png)

# **Deploy Kong on Google GKE**

**1. Pull Starbucks API Docker Image**
![img_4.png](./images/img_4.png)

**2. Deploy Starbucks to GKE**

![img_5.png](./images/img_5.png)

**3. Create a Service for Starbucks API**
![img_6.png](./images/img_6.png)

**4. Test Reachability from GKE Jumbox Pod**
![img_7.png](./images/img_7.png)

**5. Install Kong GKE Ingress Controller**
![img_8.png](./images/img_8.png)

**Ping testing**
![img_9.png](./images/img_9.png)

**6. Create an Ingress rule to proxy the Starbucks Service and Test Kong API Ping Endpoint**
![img_10.png](./images/img_10.png)

**7. Configure an API Client Key and Create Kubernetes Secret**
![img_11.png](./images/img_11.png)

**8. Test Your API Against Kong via Public IP of Load Balancer**
![img_12.png](./images/img_12.png)

![img_13.png](./images/img_13.png)

![img_14.png](./images/img_14.png)

![img_15.png](./images/img_15.png)

![img_16.png](./images/img_16.png)

![img_17.png](./images/img_17.png)

# Discussion 

I encountered some issues when deploying SpringStarbuckAPI on Google GKE while changing the version and name of the image. I resolved this issue by checking the version and name of each file.

The necessary changes to deploy your Starbucks API with MySQL/Cloud SQL are: I will change the application.properties file by accessing MySQL with a username and password. Secondly, I will update the deployment.yaml file (spec->containers).
