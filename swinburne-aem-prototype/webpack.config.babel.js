const { getIfUtils, removeEmpty } = require('webpack-config-utils')
const { resolve }                 = require('path')
const config                      = require('./config.json')
const CleanWebpackPlugin          = require('clean-webpack-plugin')
const CopyWebpackPlugin           = require('copy-webpack-plugin')
const customization               = require('./source/_app/webpack.app.js')
const EventHooksPlugin            = require('event-hooks-webpack-plugin')
const exec                        = require('child_process').exec;
const globby                      = require('globby')
const merge                       = require('webpack-merge')
const webpack                     = require('webpack')

const patternLabConfig = require('./patternlab-config.json')

module.exports = (env) => {
  const { ifDev } = getIfUtils(env)

  if (!env.version) {
    console.log('Specify a version when running webpack eg --env.version="microsites"');
    return;
  }

  const webpackConfig = merge.smartStrategy({ entry: 'replace' })({
    devtool: ifDev('source-map'),
    context: resolve(__dirname, 'source'),

    node: {
      fs: 'empty',
    },

    entry: {
      // Gathers any Source JS files and creates a bundle
      //NOTE: This name can be changed, if so, make sure to update _meta/01-foot.mustache
      'js/pl-source': globby.sync([resolve(config.paths.source[env.version].js + '**/*.js')]).map(filePath => filePath)
    },

    output: {
      filename : '[name].js',
      path     : resolve(config.paths.public[env.version].root)
    },

    plugins: removeEmpty([
      env.clean === true ? new CleanWebpackPlugin([patternLabConfig.paths.public.root]) : undefined,
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
          to      : resolve(config.paths.public[env.version].images),
        },
        {
          // Copy favicon from source to public
          context : resolve(config.paths.source[env.version].root),
          from    : './*.ico',
          to      : resolve(config.paths.public[env.version].root),
        },
        {
          // Copy all web fonts from source to public
          context : resolve(config.paths.source[env.version].fonts),
          from    : './**/*.*',
          to      : resolve(config.paths.public[env.version].fonts),
        },
        {
          // Copy all css from source to public
          context : resolve(config.paths.source[env.version].css),
          from    : './*.css',
          to      : resolve(config.paths.public[env.version].css),
        },
        {
          // Styleguide Copy everything but css
          context : resolve(config.paths.source[env.version].styleguide),
          from    : './**/*',
          to      : resolve(config.paths.public[env.version].root),
          ignore  : ['*.css'],
        },
        {
          // Styleguide Copy and flatten css
          context : resolve(config.paths.source[env.version].styleguide),
          from    : './**/*.css',
          to      : resolve(config.paths.public[env.version].styleguide, 'css'),
          flatten : true,
        },
      ]),
      new EventHooksPlugin({
        done() {
          if (env.deploy) {
            const child = exec(`./deploy-front-end ${env.version}`)

            child.stdout.on('data', data => process.stdout.write(data))
            child.stderr.on('data', data => process.stderr.write(data))
          }
        },
      }),
    ]),

    devServer: {
      host             : '0.0.0.0',
      useLocalIp       : true,
      contentBase      : resolve(config.paths.source[env.version].root),
      port             : config.server.port,
      open             : true,
      watchContentBase : false,

      headers: {
        'Access-Control-Allow-Origin': 'http://localhost:3000/',
      },
    },
  }, customization(env))

  return webpackConfig
}
