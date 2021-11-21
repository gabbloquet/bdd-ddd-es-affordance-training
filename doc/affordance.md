# Affordance

## Technology

### First try : Spring HATEOAS

To implement affordance, and more precisely HATEOAS REST constraint I chose **Spring Hateoas** which is interesting when we are used to Java & spring environment.  
Sadly for now that's a little difficult to find interesting and up-to-date example project to implement it.
I tried to do it with spring but for now the API is still tricky according to me, my tests [on](../deprecated-todolist-spring).

#### Linked Documentation

- [Spring documentation](https://docs.spring.io/spring-hateoas/docs/current/reference/html)
- [Spring HAL-FORMS documentation](https://docs.spring.io/spring-hateoas/docs/current/reference/html/#mediatypes.hal-forms)
- [Up to date exemple](https://github.com/spring-projects/spring-hateoas-examples/tree/main/affordances)

### Second try : Custom with Nest JS

`Nest (NestJS)` is a framework for building efficient, scalable Node.js server-side applications. It uses progressive JavaScript, is built with and fully supports TypeScript (yet still enables developers to code in pure JavaScript) and combines elements of OOP (Object Oriented Programming), FP (Functional Programming), and FRP (Functional Reactive Programming).    
I will try to implement it on my own. GO!

#### Linked Documentation

- [Get started](https://docs.nestjs.com/)

## HAL FORM exemple

I chose **Hal+form** content type because HAL is the most popular content type and also the one most likely to be known by consumers.  
And the form extension to be able to **explicit form elements and types**. (Like that in front end you only have to react to this information).

![Hal form exemple github](https://raw.githubusercontent.com/osvaldopina/hal-chrome-extension/master/form-examle.PNG)
