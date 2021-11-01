# Affordance

## Technology

To implement affordance, and more precisely HATEOAS REST constraint I chose **Spring Hateoas** which is interesting when we are used to Java & spring environment.  
Sadly for now that's a little difficult to find interesting and up-to-date example project to implement it.

I chose **Hal+form** content type because HAL is the most popular content type and also the one most likely to be known by consumers.  
And the form extension to be able to **explicit form elements and types**. (Like that in front end you only have to react to this information).  

### HAL FORM exemple

![Hal form exemple github](https://raw.githubusercontent.com/osvaldopina/hal-chrome-extension/master/form-examle.PNG)

## Documentation

- [Spring documentation](https://docs.spring.io/spring-hateoas/docs/current/reference/html)
- [Spring HAL-FORMS documentation](https://docs.spring.io/spring-hateoas/docs/current/reference/html/#mediatypes.hal-forms)
- [Up to date exemple](https://github.com/spring-projects/spring-hateoas-examples/tree/main/affordances)
