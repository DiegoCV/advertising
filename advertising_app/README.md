## Base Project Implementing Clean Architecture

## Before we start

We will begin by explaining the different components of the project and we will start with the external components, continuing with the core business components (domain) and finally the startup and configuration of the application.

Read the article [Clean Architecture � Isolating the details](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Architecture

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

It is the most internal module of the architecture, it belongs to the domain layer and encapsulates the business logic and rules through domain models and entities.

## Usecases

This module belongs to the domain layer, implements the system use cases, defines the application logic and reacts to invocations from the entry points module, orchestrating the flows to the entities module.

## Infrastructure

### Helpers

In the helpers section we will have general utilities for the Driven Adapters and Entry Points.

These utilities are not rooted to concrete objects, the use of generics is used to model behaviors.
generics are used to model generic behaviors of the different persistence objects that may exist.
based on the design pattern [Unit of Work and Repository](https://medium.com/@krzychychukosobudzki/repository-design-pattern-bc490b256006).

These classes cannot exist on their own and must inherit their sharing in the **Driven Adapters**.

### Driven Adapters

The driven adapters represent external implementations to our system, such as connections to rest,
soap, databases, flat file reading, and in particular any data source and origin with which we must interact.
interact with.

### Entry Points

The entry points represent the entry points of the application or the beginning of the business flows.

## Application

This module is the most external of the architecture, it is in charge of assembling the different modules, resolving the dependencies and creating the beans of the use cases (UseCases) in an automatic way, injecting in these concrete instances of the declared dependencies. In addition, it starts the application (it is the only module of the project where we will find the public static void main(String[] args)�.

**The beans of the use cases are automatically made available thanks to a '@ComponentScan' located in this layer.
