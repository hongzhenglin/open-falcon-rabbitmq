# open-falcon-rabbitmq

provide the customized consumer to replace Open-Falcon Transfer module with RabbitMQ and consumers. 

```

  -------       ----------- 
 | agent | ---->| transfer |----> Database
  -------       -----------   

 
  -------       -----------         -----------
 | agent | ---->| rabbitmq | <====> | consumer |---> Database
  -------       -----------         -----------  

```
# Why?

Open-Falcon Transfer module is a stateful process. RabbitMQ is also stateful, but the consumer is stateless. After this replacement, all the updating can be done on consumer. For example, if we want to add a new database into this monitoring system, we can only change consumer without any touching of the stateful components in this monitoring system. 

# Get Started

## Start testing environment

- Install [Leiningen](https://leiningen.org/).
- Install docker
- `invoke_testing_env.sh` will provide the testing environment 

