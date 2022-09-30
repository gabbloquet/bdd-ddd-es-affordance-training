# Getting started

This aims to help you to understand how to deal with this codebase.  

## Add a route

Routes are referenced [routes.tsx](../src/configuration/routes.tsx) file. To add one, you just have to duplicate an existing route and change properties.

```jsx
{
  url: '/my-component-url',
  name: 'Name which is visible on Menu',
  component: <ReactComponent />
}
```
## Add an API

To add an API you want to consume, you can take a look to what have been done in [Contest service](../src/services/contest).  
In `services` directory you can create a directory by api or by feature for example.

```typescript
export const getContests = async (): Promise<Array<Contest>> => {
  const { data } = await axios.get(`https://kontests.net/api/v1/all`);

  return data.map((contestDto: ContestDto) => ({
    title: contestDto.name,
    url: contestDto.url
  }));
};
```

Here we get `contests` from `Kontests` API and map returned data. To gain readability, it could be interesting to put mappers in a dedicated file (Example : `contest.mappers.ts`).  

## Create a reusable component

This kind of component could be placed in `./src/components` directory.

## Create a smart component (Container)

On the other hand, a smart component ([Smart VS Dumb components](https://medium.com/@thejasonfile/dumb-components-and-smart-components-e7b33a698d43)) could be placed in [containers directory](../src/containers).  

## Utilities

Utilities functions could be placed in [utils directory](../src/utils).  
It could be a function to get Date, manipulate session or local storage, sort arrays, rework object...

## Style

Style could be placed next to the component or container if it's specific. You can create a `mycomponent.scss` file next to your component.  
Often, `scss` files are in lowercase. You can follow [BEM conventions](https://getbem.com/naming/) to name you classes in a interesting way.  

For global style, you can create a `style` directory. Don't forget to add created files in [index.scss](../src/index.scss).

## Views

Finally, views or pages could be placed in [views directory](../src/views).  
You can find some example here, `contests` with API consumption, `testing` with state usage or `configuration` with configuration consumption.

## Environment variables

You can add an environment variable in [conf.json](../public/conf.json) file.  
To consume it you can use `useConfiguration` hook, like in [`App.tsx`](../src/App.tsx) component.
