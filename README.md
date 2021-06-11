## Spring Boot App / Cloud Pub Sub

This is a basic demo of a `Spring Boot App Subscriber` with `GCP Cloud Pub Sub` which will consume a subscription topic with `pull` method.

Also, there is a `publisher` in the `PublisherController` which allow to publish a message in the `GCP topic` and the `consumer` will consume this message by subscribing to the subscription.