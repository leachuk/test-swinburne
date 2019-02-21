/* eslint-disable */
const exec                                                 = require('child_process').exec
const { getIfUtils, removeEmpty }                          = require('webpack-config-utils')
const { ProvidePlugin, LoaderOptionsPlugin, DefinePlugin } = require('webpack')
const { relative, resolve }                                = require('path')
const CleanWebpackPlugin                                   = require('clean-webpack-plugin')
const CopyWebpackPlugin                                    = require('copy-webpack-plugin')
const EventHooksPlugin                                     = require('event-hooks-webpack-plugin')
const ImageminPlugin                                       = require('imagemin-webpack-plugin').default
const LodashPlugin                                         = require('lodash-webpack-plugin')
const MiniCssExtractPlugin                                 = require('mini-css-extract-plugin')
const OptimizeCSSAssetsPlugin                              = require('optimize-css-assets-webpack-plugin')
const TsconfigPathsPlugin                                  = require('tsconfig-paths-webpack-plugin')
const UglifyJsPlugin                                       = require('uglifyjs-webpack-plugin')

const { core, paths } = require('./config.json')

module.exports = env => {
  const { ifDev, ifProd } = getIfUtils(env)

  if (!env.version) {
    console.log('Specify a version when running webpack eg --env.version="microsites"')
    return
  }

  return {
    devtool : ifDev('source-map'),
    context : resolve(__dirname, 'source'),
    mode    : env.dev === true ? 'development' : 'production',

    node: {
      fs: 'empty',
    },

    entry: {
      'js/app': `./${env.version}/js/${core[env.version].entryFile}`,

      'js/vendor': [
        'es6-promise/auto',
        'object-fit-images',
        'picturefill',
        'bootstrap/js/dist/util',
        'bootstrap/js/dist/collapse',
        'bootstrap/js/dist/dropdown',
        'bootstrap/js/dist/modal',
        'bootstrap/js/dist/tab',
      ],

      'js/vendorlib/jquery'       : 'jquery',
      'js/vendorlib/owl.carousel' : 'owl.carousel',
    },

    output: {
      chunkFilename : '[name].js',
      publicPath    : env.aem === true ? `/etc/clientlibs/${env.clientLibsFolder}/js/` : '/',
    },

    module: {
      rules: [
        {
          test: /\.(css|sass|scss)$/,

          use: [
            env.dev === true ? 'style-loader' : MiniCssExtractPlugin.loader,
            {
              loader: 'css-loader',

              options: {
                importLoaders : 1,
                sourceMap     : env.dev === true,
              },
            },
            {
              loader: 'postcss-loader',

              options: {
                ident     : 'postcss',
                sourceMap : env.dev === true,

                config: {
                  path: resolve('postcss.config.js'),

                  ctx: {
                    prod: env.prod === true,
                  },
                },
              },
            },
            {
              loader: 'sass-loader',

              options: {
                // implementation : require('sass'),
                outputStyle    : env.dev === true ? 'expanded' : 'compressed',
                precision      : 8,
                sourceMap      : env.dev === true,
              },
            },
          ],
        },
        {
          exclude : [resolve('node_modules')],
          test    : /\.tsx?$/,

          use: [
            {
              loader: 'ts-loader',
            },
            {
              loader: 'babel-loader',
            },
            {
              loader: 'string-replace-loader',

              options: {
                replace : '/vendorlib',
                search  : '/js/vendorlib',
              },
            },
          ],
        },
        {
          enforce : 'pre',
          test    : /\.js$/,
          use     : ['source-map-loader'],
        },
        {
          test: require.resolve('jquery'),

          use: [
            {
              loader  : 'expose-loader',
              options : 'jQuery',
            },
            {
              loader  : 'expose-loader',
              options : '$',
            }
          ],
        },
        {
          test: /\.(png|jpg|gif|eot|ttf|svg|woff|woff2)$/,

          use: [
            {
              loader: 'file-loader',
              options: {
                context    : `source/${env.version}`,
                emitFile   : env.dev === true,
                name       : '[path][name].[ext]',

                publicPath: (_, resourcePath, context) => {
                  return env.aem === true ? `../${relative(context, resourcePath)}` : './public' // eslint-disable-line
                },
              },
            },
          ],
        },
      ],
    },

    optimization: {
      minimizer: [
        new UglifyJsPlugin({
          cache     : true,
          parallel  : true,
          sourceMap : env.dev === true,

          // ecma     : 6,
          // mangle   : false,
          // warnings : false,

          // compress: {
          //   drop_console : true,
          //   warnings     : false,
          // },

          // output: {
          //   beautify: false,
          //   comments: false,
          // },
        }),
        new OptimizeCSSAssetsPlugin({
          canPrint     : true,
          cssProcessor : require('cssnano'),

          cssProcessorPluginOptions: {
            preset: ['default', {
              discardComments: {
                removeAll: true,
              },
            }],
          },
        }),
      ],

      splitChunks: {
        cacheGroups: {
          default: false,
          vendors: false,

          common: {
            chunks             : 'async',
            enforce            : true,
            minChunks          : 2,
            name               : 'common',
            priority           : 10,
            reuseExistingChunk : true,
          },

          vendor: {
            chunks   : 'all',
            name     : 'vendor',
            priority : 20,
            test     : /node_modules/,
          },
        },
      },
    },

    plugins: removeEmpty([
      env.clean === true ? new CleanWebpackPlugin(['./public/']) : undefined,
      new MiniCssExtractPlugin({
        filename      : 'css/[name].css',
        chunkFilename : 'css/[id].css',
      }),
      new CopyWebpackPlugin([
        {
          // Copy all images from source to public
          context: resolve(paths.source[env.version].images),
          from: './**/*.*',
          to: resolve(paths.public[env.version].images),
        },
        {
          // Copy favicon from source to public
          context: resolve(paths.source[env.version].root),
          from: './*.ico',
          to: resolve(paths.public[env.version].root),
        },
        {
          // Copy all web fonts from source to public
          context: resolve(paths.source[env.version].fonts),
          from: './**/*.*',
          to: resolve(paths.public[env.version].fonts),
        },
        {
          // Copy all css from source to public
          context: resolve(paths.source[env.version].css),
          from: './*.css',
          to: resolve(paths.public[env.version].css),
        },
        {
          // Styleguide Copy everything but css
          context: resolve(paths.source[env.version].styleguide),
          from: './**/*',
          to: resolve(paths.public[env.version].root),
          ignore: ['*.css'],
        },
        {
          // Styleguide Copy and flatten css
          context: resolve(paths.source[env.version].styleguide),
          from: './**/*.css',
          to: resolve(paths.public[env.version].styleguide, 'css'),
          flatten: true,
        },
      ]),
      new ImageminPlugin({
        test: /\.(jpe?g|png|gif|svg)$/i,
      }),
      new LodashPlugin({
        collections : true,
        shorthands  : true,
      }),
      new ProvidePlugin({
        FastClick       : 'fastclick',
        ObjectFitImages : 'object-fit-images',
        PubSub          : 'pubsub-js',

        // Expose the Bootstrap modules to the global namespace
        // https://github.com/shakacode/bootstrap-loader#bootstrap-4-internal-dependency-solution
        Popper          : ['popper.js', 'default'],
        Alert           : 'exports-loader?Alert!bootstrap/js/dist/alert',
        Button          : 'exports-loader?Button!bootstrap/js/dist/button',
        Carousel        : 'exports-loader?Carousel!bootstrap/js/dist/carousel',
        Collapse        : 'exports-loader?Collapse!bootstrap/js/dist/collapse',
        Dropdown        : 'exports-loader?Dropdown!bootstrap/js/dist/dropdown',
        Modal           : 'exports-loader?Modal!bootstrap/js/dist/modal',
        Popover         : 'exports-loader?Popover!bootstrap/js/dist/popover',
        Scrollspy       : 'exports-loader?Scrollspy!bootstrap/js/dist/scrollspy',
        Tab             : 'exports-loader?Tab!bootstrap/js/dist/tab',
        Tooltip         : 'exports-loader?Tooltip!bootstrap/js/dist/tooltip',
        Util            : 'exports-loader?Util!bootstrap/js/dist/util',
      }),
      ifProd(new LoaderOptionsPlugin({
        minimize: true,
      })),
      ifProd(new DefinePlugin({
        'process.env': {
          NODE_ENV: JSON.stringify('production'),
        },
      })),
      ifDev(new DefinePlugin({
        'process.env': {
          NODE_ENV: JSON.stringify('development'),
        },
      })),
      new EventHooksPlugin({
        done() {
          // if (env.deploy) {
          //   const child = exec(`./deploy-front-end ${env.version}`)

          //   child.stdout.on('data', data => process.stdout.write(data))
          //   child.stderr.on('data', data => process.stderr.write(data))
          // }
        },
      }),
    ]),

    resolve: {
      extensions: ['.webpack.js', '.web.js', '.ts', '.tsx', '.js'],

      plugins: [
        new TsconfigPathsPlugin(),
      ],
    },
  }
}
/* eslint-enable */
