# Initialize database

on a local MySQL database, create a skeleton database and execute sql/initDatabase.sql 

# Start Java server (api module)

## Java environnement variables

### For spring social (create dev account for each provider)

- google.clientId=
- google.clientSecret=
- facebook.clientId=
- facebook.clientSecret=
- linkedin.clientId=
- linkedin.clientSecret=
- twitter.clientId=
- twitter.clientSecret=

### For prerender (create account on [prerender.io](https://prerender.io/))

- prerender.token=

## Start server

run as Java application class net.wismas.spring.skeleton.api.Application

# Start front server (front module)

- Make sure you have [node.js](https://nodejs.org/) installed version 5+
- Make sure you have NPM installed version 3+
- `WINDOWS ONLY` run `npm install -g webpack webpack-dev-server typescript` to install global dependencies
- run `npm install` to install dependencies
- run `npm start` to fire up dev server
- open browser to [`http://localhost:3000`](http://localhost:3000)
- if you want to use other port, open `package.json` file, then change port in `--port 3000` script
