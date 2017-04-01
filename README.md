# example-cloud-app

## Intro
Java Dropwizard apps utilizing AWS cloud services, DDD, hexagonal architecture (aka Ports & Adapters), event-sourcing and CQRS design principles.
The system will be horizontally scalable, fully redundant, deployed with zero downtime, eventually consistent and utilizing asynchronous communication.

Together they make up a simple service to post and retrieve simple blog-style posts.

## Requirements
- Java 8
- Maven 3

## Current state
Under heavy early development, nothing is yet running/tied together.

## How to build
Simply run 'mvn install' from root.

## Todo
*Implement*
- Stubbed data
- Front-end (statically served JS web application)
- Write-side
- Bootstrapping of read-side
- A deprecated API (to illustrate the ACL)
- Hystrix
- Health-checks
- Pact tests
- OpenID auth
- CloudFormation scripts

*Add comments*
