## Spring Boot App / Cloud Pub Sub

This is a basic demo of a `Spring Boot App Subscriber` with `GCP Cloud Pub Sub` which will consume a subscription topic with `pull` method.

Also, there is a `publisher` in the `PublisherController` which allow to publish a message in the `GCP topic` and the `consumer` will consume this message by subscribing to the subscription.

The application is deployed on `Google App Engine` and this is why, in my case, I don't need a Service Account with a credentials key file because on GAE, the credentials key file is already defined in the GAE env variable. 

In a real app, the `consumer and publisher` are in different application but for the purpose of the demo it was easier to have both consumer and producer in the same app.