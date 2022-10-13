# Todolist front-end

➡️Initialized with [React starter](https://github.com/gabbloquet/starter-react)

## Getting started

**Requirements**:
- node 14+
- npm 6.14+

Then, you can **install dependencies** and **start** the application locally:
```bash
npm install
npm start
```

Congratulations ! Your application is running on [http://localhost:4200/](http://localhost:4200/). 🎉

[Need help to get started ?](./doc/getting_started.md)

### Optional

- [Install Docker](https://docs.docker.com/engine/install/ubuntu/#installation-methods)

## Your stack

This starter has been initialized with [Vite](https://vitejs.dev/) which is a build tool that aims to provide a faster and leaner development experience for modern web projects. (And more precisely [react-ts](https://stackblitz.com/edit/vitejs-vite-n1nea3?file=index.html&terminal=dev) template).  

Next to that, you have interesting **tools which are already configure** : 

| Tools                                                                                  | Purpose                                                                                             | Informations                                               |
|:---------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------------|:-----------------------------------------------------------|
| [Eslint](https://eslint.org/) & [Prettier](https://prettier.io/)                       | **Linting** and **formatting**                                                                      | You can configure your IDE by adding these tools libraries |
| [Jest](https://jestjs.io/)                                                             | Delightful JavaScript **Testing Framework** with a focus on simplicity.                             | Examples on `**.spec.ts` or `**.spec.tsx` files            |
| [React Testing library](https://testing-library.com/docs/react-testing-library/intro/) | DOM testing library to reproduce user behavior with tests                                           | Examples on `**.spec.tsx` files                            |
| [React Query](https://tanstack.com/query/v4/docs/adapters/react-query)                 | **Fetching, caching, synchronizing and updating server state** in your React applications a breeze. | Examples in `<Contests />` component                       |
| [React router dom](https://reactrouter.com/en/main/start/overview)                     | Provides **Routing**                                                                                | Examples in `App.tsx` component                            |

You can find [here](./doc/initialization.md) how this application has been initialized.

### Build your application

Local build (After that, you would be able to find it in [Dist folder](./dist)):
```bash
npm run build
```

Docker build:
```bash
docker build -t starter-react:local .
```

### Secrets configuration

Locally, you can edit the configuration directly on [public/conf.json](public/conf.json).

## Going further

 - [Github actions documentation](https://docs.github.com/en/actions)
 - [Vite documentation](https://vitejs.dev/guide/)
 - [React documentation](https://reactjs.org/tutorial/tutorial.html)
 - [Dependabot documentation](https://docs.github.com/en/code-security/dependabot/dependabot-version-updates/configuration-options-for-the-dependabot.yml-file)
