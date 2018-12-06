// webpack.config.js
const webpack                     = require('webpack')
const {resolve}                   = require('path')
const globby                      = require('globby')
const { getIfUtils, removeEmpty } = require('webpack-config-utils')
const CopyWebpackPlugin           = require('copy-webpack-plugin')
const EventHooksPlugin            = require('event-hooks-webpack-plugin')
const config                      = require('./config.json')
// const patternlab                  = require('patternlab-node')(plConfig)
// const patternEngines              = require('patternlab-node/core/lib/pattern_engines')
const merge                       = require('webpack-merge')
const customization               = require('./source/_app/webpack.app.js');
const exec = require('child_process').exec;

module.exports = (env) => {
  const { ifProd, ifDev } = getIfUtils(env)

  if (!env.version) {
    console.log('Specify a version when running webpack eg --env.version="microsites"');
    return;
  }

  const webpackConfig = merge.smartStrategy({ entry: 'replace' })({
    devtool: ifDev('source-map'),
    context: resolve(__dirname, 'source'),

    node: {
      fs: "empty"
    },

    entry: {
      // Gathers any Source JS files and creates a bundle
      //NOTE: This name can be changed, if so, make sure to update _meta/01-foot.mustache
      "js/pl-source": globby.sync([resolve(config.paths.source[env.version].js + '**/*.js')]).map(filePath => filePath)
    },

    output: {
      filename : '[name].js',
      path     : resolve(__dirname, 'public'),
    },

    plugins: removeEmpty([
      new webpack.optimize.CommonsChunkPlugin({
        // Combines any node module libraries used into their own file
        name      : 'js/pl-vendor-libraries',
        chunks    : ['js/pl-source'],
        minChunks : module => module.context && /node_modules/.test(module.context),
      }),
      new CopyWebpackPlugin([
        {
          // Copy all images from source to public
          context : resolve(config.paths.source[env.version].images),
          from    : './**/*.*',
          to      : resolve(config.paths.public.images),
        },
        {
          // Copy favicon from source to public
          context : resolve(config.paths.source[env.version].root),
          from    : './*.ico',
          to      : resolve(config.paths.public.root),
        },
        {
          // Copy all web fonts from source to public
          context : resolve(config.paths.source[env.version].fonts),
          from    : './**/*.*',
          to      : resolve(config.paths.public.fonts),
        },
        {
          // Copy all css from source to public
          context : resolve(config.paths.source[env.version].css),
          from    : './*.css',
          to      : resolve(config.paths.public.css),
        },
        {
          // Styleguide Copy everything but css
          context : resolve(config.paths.source[env.version].styleguide),
          from    : './**/*',
          to      : resolve(config.paths.public.root),
          ignore  : ['*.css'],
        },
        {
          // Styleguide Copy and flatten css
          context : resolve(config.paths.source[env.version].styleguide),
          from    : './**/*.css',
          to      : resolve(config.paths.public.styleguide, 'css'),
          flatten : true,
        },
      ]),
      new EventHooksPlugin({
        ['done'](compilation, callback) {
          const child = exec('npm run aem:frontend');
          child.stdout.on('data', function (data) {
            process.stdout.write(data);
          });
          child.stderr.on('data', function (data) {
            process.stderr.write(data);
          });
        }
      }),
    ]),

    devServer: {
      host             : "0.0.0.0",
      useLocalIp       : true,
      contentBase      : resolve(__dirname, 'public'),
      port             : config.server.port,
      open             : true,
      watchContentBase : false,
      headers: { "Access-Control-Allow-Origin": "http://localhost:3000/" }
    },
  }, customization(env))

  return webpackConfig
}
