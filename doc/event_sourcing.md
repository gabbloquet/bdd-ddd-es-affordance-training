# Event sourcing

## Continued from DDD

At the moment, I'm near something like `Domain-driven design` architecture.  
My aggregates have `apply` function which return `events` of what happen.  
After that, we publish these events and functions in the system will react to it.  
I made the choice to use `EventBus` & `CommandBus` thanks to `Spring ApplicationEventPublisher`.  

## Target

Now I want to be able to recreate my Aggregates thanks to events.  

To help me, my friend @ecattez told me : 

_In fact, when you start event sourcing, the repository becomes an event store.
So you save events, and then, from the events, you can recreate any **projection**. From my experience and what I've seen in training, when you do event sourcing, the aggregate disappears in favor of decision projections. In short, the aggregate becomes a collection of functions, which can be seen as a module._

**Instead of the Aggregate itself, in a valid state, being saved directly to the database using some ORM framework - we collect events that are applied to the Aggregate and save them to the Event Store, replaying them to arrive at the current state of the Aggregate later down the road.** [source](https://buildplease.com/pages/constructing-aggregates/)

### Useful resources

 - [Aggregates VS Projections](https://domaincentric.net/blog/event-sourcing-aggregates-vs-projections)