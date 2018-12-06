[![Apache V2 License](http://img.shields.io/badge/license-Apache%20V2-blue.svg)](https://github.com/Comcast/patternlab-edition-node-webpack/blob/master/LICENSE)

First, install the dependencies:

`npm install`

## Develop 

To Develop code and deploy directly into AEM


1. Check that Java is Installed and $JAVA_HOME is set correctly by executing `java` into the terminal. If $JAVA_HOME is not set and you're using MAC OS, execute the following:
`echo export "JAVA_HOME=\$(/usr/libexec/java_home)" >> ~/.bash_profile`
2. Make sure AEM is running locally
3. Execute `npm run aem:checkout` to checkout files from the local AEM server. This only needs to be run once.
4. Execute `npm run dev` to watch for local changes and automatically push Front End Assets back into AEM

## Pattern Lab Node - Webpack Edition
The webpack wrapper around [Pattern Lab Node Core](https://github.com/pattern-lab/patternlab-node) providing tasks to interact with the core library and move supporting frontend assets.

`npm run patternlab:serve`



The webpack edition comes with the following components:

* `patternlab-node`: [GitHub](https://github.com/pattern-lab/patternlab-node), [npm](https://www.npmjs.com/package/patternlab-node)
* `patternengine-node-mustache`: [GitHub](https://github.com/pattern-lab/patternengine-node-mustache), [npm](https://www.npmjs.com/package/patternengine-node-mustache)
* `pattern-lab/styleguidekit-assets-default`: [GitHub](https://github.com/pattern-lab/styleguidekit-assets-default)
* `pattern-lab/styleguidekit-mustache-default`: [GitHub](https://github.com/pattern-lab/styleguidekit-mustache-default)


The Pattern Lab Node - webpack edition uses [Node](https://nodejs.org/en/) for core processing, [npm](https://www.npmjs.com/) to manage project dependencies, and [webpack.io](https://webpack.github.io/) to run tasks and interface with the core library. Node version 4 or higher suffices. You can follow the directions for [installing Node](https://nodejs.org/en/download/) on the Node website if you haven't done so already. Installation of Node will include npm.


`npm install`


 The pre-built project comes with the [Base Starterkit for Mustache](https://github.com/pattern-lab/starterkit-mustache-base) installed by default.

**Please note:** Pattern Lab Node uses [npm](https://www.npmjs.com/) to manage project dependencies. To upgrade the webpack edition or to install plug-ins you'll need to be familiar with npm.


`npm` is a dependency management and package system which can pull in all of the webpack editions's dependencies for you. To accomplish this:

* download or `git clone` this repository to an install location.

* run the following

    ```
    cd install/location
    npm install
    ```

Running `npm install` from a directory containing a `package.json` file will download all dependencies defined within. The `package-lock.json` file is automatically managaged everytime you add/remove/upgrade a dependency. 


Most people want to run Pattern Lab Node standalone and not as a dependency. If you wish to install as a dependency you can do the following:

Use npm's `install` command with an argument to install the Webpack Edition into a location of your choosing. In Terminal type:

    cd install/location/
    npm install edition-node-webpack

This will install the Webpack Edition into a directory called `node_modules` in `install/location/`.


The Pattern Lab Node - Webpack Edition ships with a [base experience](https://github.com/pattern-lab/starterkit-mustache-base) which serves as clean place to start from scratch with Pattern Lab. But if you want to get rolling with a starterkit of your own, or use the [demo starterkit](https://github.com/pattern-lab/starterkit-mustache-demo) like the one on [demo.patternlab.io](http://demo.patternlab.io), you can do so automatically at time of `npm install` by adding your starterkit to the `package.json` file.

You can also [work with starterkits using the command line](https://github.com/pattern-lab/patternlab-node/wiki/Importing-Starterkits).


To update Pattern Lab please refer to each component's GitHub repository, and the [master instructions for core](https://github.com/pattern-lab/patternlab-node/wiki/Upgrading). The components are listed at the top of the README.


To list all available commands type:

    npm run patternlab:help


To generate the front-end for Pattern Lab type:

    npm run patternlab:build


To watch for changes, re-generate the front-end, and server it via a BrowserSync server,  type:

    npm run patternlab:serve

Webpack dev server should open [http://localhost:3000](http://localhost:3000) in your browser, both host and port are configurable in the `patternlab-config.json` file.


To install a specific StarterKit from GitHub type:

    npm run add [starterkit-vendor/starterkit-name]

    npm run patternlab:loadstarterkit --kit=[starterkit-name]


Unlike the other editions, there were a few options added just for this edition that allow for easier upgrading, and better flexibility.

You can set the url and port number in the configuration for 

    "server": {
        "url": "http://localhost",
        "port": 3000
    },

* [babel-core](https://github.com/babel/babel/blob/master/LICENSE) - MIT
* [babel-loader](https://github.com/babel/babel-loader/blob/master/LICENSE) -MIT,
* [babel-preset-es2015](https://github.com/babel/babel/blob/master/LICENSE) - MIT
* [copy-webpack-plugin](https://github.com/webpack-contrib/copy-webpack-plugin/blob/master/LICENSE) - MIT
* [event-hooks-webpack-plugin](https://github.com/cascornelissen/event-hooks-webpack-plugin/blob/master/LICENSE.md) - MIT
* [globby](https://github.com/sindresorhus/globby/blob/master/license) - MIT
* [patternlab-node](https://github.com/pattern-lab/patternlab-node/blob/master/LICENSE) - MIT
* [styleguidekit-assets-default](https://github.com/pattern-lab/styleguidekit-assets-default/blob/master/LICENSE) - MIT
* [styleguidekit-mustache-default](https://github.com/pattern-lab/styleguidekit-mustache-default/blob/master/LICENSE) - MIT
* [webpack](https://github.com/webpack/webpack/blob/master/LICENSE) - MIT
* [webpack-config-utils](https://github.com/kentcdodds/webpack-config-utils/blob/master/LICENSE) - MIT
* [webpack-dev-server](https://github.com/webpack/webpack-dev-server/blob/master/LICENSE) - MIT
* [webpack-merge](https://github.com/survivejs/webpack-merge/blob/master/LICENSE) - MIT


Contributor | Message / How to Reach  
----------- | --------------
 ![@paintedbicycle](https://avatars3.githubusercontent.com/u/371114?s=75&v=4)[@paintedbicycle](https://github.com/paintedbicycle) | **Paul Wright** - [https://paintedbicycle.com](https://paintedbicycle.com)
 ![@rgualberto](https://avatars3.githubusercontent.com/u/5126167?v=4&s=75)[@rgualberto](https://github.com/rgualberto) | "A huge thank you to a incredible developer [Rodrigo Gualberto](https://github.com/rgualberto) for all of his hard work, dedication, and support from the start of project."
