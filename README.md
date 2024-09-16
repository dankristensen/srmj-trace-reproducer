This repo is used for reproducing tracepropagation in smallrye-reactive-messaging-jms.

In order to execute this example, you MUST run a IBM MQ container.

I have used this one ibmcom/mq:9.2.0.0-r1, but a newer version should also work.

Start it using docker run -e LICENSE=accept -p 1414:1414 -p 9443:9443 ibmcom/mq:9.2.0.0-r1

You must also start a jaeger / opentelemetry collector. In the folder jaeger, there is a docker-compose file to run
using
docker-compose up. Since i am only allowed to run behind a proxy, i cannot configure this exactly for you. I have done
to my best effort.

Now run the quarkus service, and do this:
curl -v -X POST -k http://localhost:8097/trigger/test -H 'Content-Type: text/plain'

Then goto http://localhost:16686/search (jaeger), and find the trace for the above request. (Can be a little delayed)

I would now suspect, that the entire span is found below the POST for /trigger/test, so that means the following:

POST /trigger/test

* queue:///DEV.QUEUE.1 publish
    * queue:///DEV.QUEUE.1 receive
        * queue:///DEV.QUEUE.2 publish
            * queue:///DEV.QUEUE.2 receive

But i only see for DEV.QUEUE.1. The DEV.QUEUE.2 publish is present, but as a standalone tag.

So i must be doing something wrong or what

The libs folder contains a SNAPSHOT version of smallrye-reactive-messaging-jms