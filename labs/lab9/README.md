# CMPE 172 - Lab #9 Notes

# **Messaging with RabbitMQ**

**1 .Build and Send a Test Message**

![img.png](./images/img.png)

It retrieves the RabbitTemplate from the application context and sends a Hello from RabbitMQ! message on the spring-boot queue

![img_2.png](./images/img_2.png)

**Check connection**
![img_1.png](./images/img_1.png)

# **RabbitMQ Tutorial - Hello World**

**1 .Build and Send Messages**
![img_3.png](./images/img_3.png)

**Check connection**

![img_4.png](./images/img_4.png)

**2 .Get a Message**
![img_5.png](./images/img_5.png)

**3 .Receive all Messages**
![img_6.png](./images/img_6.png)

**Check messages total**

![img_7.png](./images/img_7.png)

# **RabbitMQ Tutorial - Work Queues**

**1 .Deploying RabbitMQ on Docker**
![img_8.png](./images/img_8.png)

**2 .Build and Send Messages**
![img_9.png](./images/img_9.png)

**Check messages total**
![img_10.png](./images/img_10.png)

**3 .Receive Messages**
![img_11.png](./images/img_11.png)

**Check messages total**
![img_12.png](./images/img_12.png)

# **Discussion**
- Spring Profiles define and configure beans for specific environments or application contexts, customize the behavior of application without changing its code.  This configuration class defines a queue and beans for receivers and sender based on the "workers" or "work-queues" profile. The beans for receiver are defined only if the "receiver" profile is active. Similarly, the sender bean is defined only if the "sender" profile is active.
- Overall, this code demonstrates how RabbitMQ can be used to send and receive messages between different components in a Spring Boot application.
