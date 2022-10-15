# Initialization

This starter has been initialized with [Vite](https://vitejs.dev/) which is a build tool that aims to provide a faster and leaner development experience for modern web projects. (And more precisely [react-ts](https://stackblitz.com/edit/vitejs-vite-n1nea3?file=index.html&terminal=dev) template).

## Vite, React, Typescript

```shell
npm create vite@latest APPLICATION_NAME -- --template react-ts
```

## Linters

Then, we installed [ESLint](https://eslint.org/) & [Prettier](https://prettier.io/) with react and typescript configuration.  
[You can find here how to do that.](https://blog.devgenius.io/eslint-prettier-typescript-and-react-in-2022-e5021ebca2b1)

## Jest

To make [Jest](https://jestjs.io/docs/getting-started) works we need babel to compile files. Furthermore, we use [jest-sonar-reporter](https://www.npmjs.com/package/jest-sonar-reporter) to create sonar reports.

```shell
npm install -D jest babel-jest jest-environment-jsdom jest-sonar-reporter identity-obj-proxy
```

We use `identity-obj-proxy` to intercept style files, [fileMock](../fileMock.js) to intercept other files we doesn't matter in tests, `jest-environment-jsdom` to emulate browser dom.

Our Jest configuration (in [package.json](../package.json)):

```json
{
  "verbose": true,
  "testEnvironment": "jsdom",
  "testResultsProcessor": "jest-sonar-reporter",
  "transformIgnorePatterns": ["/node_modules/(?!nanoid)(.*)"],
  "moduleNameMapper": {
    "\\.(jpg|jpeg|png|gif|eot|otf|webp|svg|ttf|woff|woff2|mp4|webm|wav|mp3|m4a|aac|oga)$": "<rootDir>/../fileMock.js",
    "\\.(css|less|scss)$": "identity-obj-proxy"
  }
}
```

Our Babel configuration (in [babel.config.json](../babel.config.json)):

```js
module.exports = {
  presets: [
    '@babel/preset-env',
    '@babel/preset-typescript',
    ['@babel/preset-react', { runtime: 'automatic' }]
  ]
};
```

## Testing library

We use [testing library](https://testing-library.com/docs/react-testing-library/intro/) to test component by simulate user behavior.

```shell
npm install -D jest @testing-library/react @testing-library/jest-dom
```

## React Query

We use [React Query](https://tanstack.com/query/v4/docs/installation) to make calls. It could be local files put in [public directory](../public) or `APIs`.

```shell
npm i @tanstack/react-query
npm i -D @tanstack/react-query-devtools
```

## React router dom

We use [React router dom](https://reactrouter.com/en/v6.3.0/getting-started/overview), for routing.

```shell
npm install react-router-dom@6
```
