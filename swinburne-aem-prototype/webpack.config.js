/* eslint-disable */
const { getIfUtils, removeEmpty }                          = require('webpack-config-utils')
const { DefinePlugin, LoaderOptionsPlugin, ProvidePlugin } = require('webpack')

const { relative, resolve }   = require('path')
const CleanWebpackPlugin      = require('clean-webpack-plugin')
const CopyWebpackPlugin       = require('copy-webpack-plugin')
const EventHooksPlugin        = require('event-hooks-webpack-plugin')
const exec                    = require('child_process').exec
const ImageminPlugin          = require('imagemin-webpack-plugin').default
const LodashPlugin            = require('lodash-webpack-plugin')
const MiniCssExtractPlugin    = require('mini-css-extract-plugin')
const OptimizeCSSAssetsPlugin = require('optimize-css-assets-webpack-plugin')
const StyleLintPlugin         = require('stylelint-webpack-plugin')
const TerserPlugin            = require('terser-webpack-plugin')
const TsconfigPathsPlugin     = require('tsconfig-paths-webpack-plugin')

const config = require('./config.json')

const PUBLIC_PATH = resolve(__dirname, 'public')
const SOURCE_PATH = resolve(__dirname, 'source')

module.exports = env => {
  const { ifDev, ifProd } = getIfUtils(env)

  if (!env.project) {
    console.log('Specify a project when running webpack eg --env.project="sut"')
    return
  }

  const PUBLIC_PATH_AEM = `/etc/clientlibs/${env.clientLibsFolder || 'swinburne'}/${env.project}/`

  const project = config[env.project]

  const PROJECT_PATH = resolve(SOURCE_PATH, env.project)

  return {
    context : SOURCE_PATH,
    devtool : ifDev('inline-source-map'),
    mode    : env.dev === true ? 'development' : 'production',

    entry: {
      [project.outputName]: [
        `./${env.project}/js/${project.entryFile.js}`,
        `./${env.project}/scss/${project.entryFile.sass}`,
      ],

      ...project.additionalEntries,
    },

    output: {
      filename      : 'js/[name].js',
      chunkFilename : `js/[name]${env.prod === true ? '.[chunkhash]' : ''}.js`,
      path          : resolve(PUBLIC_PATH, env.project),
      publicPath    : PUBLIC_PATH_AEM,
    },

    module: {
      rules: [
        {
          test: /\.scss$/,

          use: [
            {
              loader: MiniCssExtractPlugin.loader,
            },
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
                  path: resolve(__dirname, 'postcss.config.js'),

                  ctx: {
                    prod: env.prod === true,
                  },
                },
              },
            },
            {
              loader: 'sass-loader',

              options: {
                implementation : require(project.sassModule || 'sass'),
                outputStyle    : env.dev === true ? 'expanded' : 'compressed',
                precision      : 5,
                sourceMap      : env.dev === true,
              },
            },
          ],
        },
        {
          exclude : [resolve('node_modules')],
          test    : /\.[jt]sx?$/,

          use: removeEmpty([
            {
              loader: 'babel-loader',
            },
            {
              loader: 'ts-loader',
            },
          ]),
        },
        {
          enforce : 'pre',
          exclude : [resolve('node_modules'), resolve('source/microsites')],
          test    : /\.js$/,
          use     : ['eslint-loader'],
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
                context    : `source/${env.project}`,
                emitFile   : env.dev === true,
                name       : '[path][name].[ext]',
                publicPath : (_, resourcePath, context) => `../${relative(context, resourcePath)}`,
              },
            },
          ],
        },
      ],
    },

    optimization: {
      minimizer: [
        new TerserPlugin({
          cache     : true,
          sourceMap : false,

          extractComments: {
            condition: true,

            banner() {
              return `Copyright 2018-${(new Date).getFullYear()} Swinburne Univerisity of Technology.`
            },
          },

          terserOptions: {
            ecma     : 6,
            safari10 : true,
            warnings : false,

            compress: {
              drop_console: true,
            },

            output: {
              beautify: false,
              comments: false,
            },
          },
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
        chunks: 'all',

        cacheGroups: {
          default: false,
          vendors: false,
        },
      },
    },

    plugins: removeEmpty([
      env.clean === true ? new CleanWebpackPlugin({
        cleanOnceBeforeBuildPatterns: [resolve(PUBLIC_PATH, env.project, '**/*')],
      }) : undefined,
      new CopyWebpackPlugin([
        {
          context : PROJECT_PATH,
          from    : './*.ico',
          to      : resolve(PUBLIC_PATH, env.project),
        },
        {
          context : resolve(PROJECT_PATH, 'images'),
          from    : './**/*.*',
          to      : resolve(PUBLIC_PATH, env.project, 'images'),
        },
        {
          context : resolve(PROJECT_PATH, 'fonts'),
          from    : './**/*.*',
          to      : resolve(PUBLIC_PATH, env.project, 'fonts'),
        },
        {
          context : resolve(PROJECT_PATH, 'css'),
          from    : './*.css',
          to      : resolve(PUBLIC_PATH, env.project, 'css'),
        },
      ]),
      new MiniCssExtractPlugin({
        filename      : 'css/[name].css',
        chunkFilename : 'css/[id].css',
      }),
      env.maven !== true ? new StyleLintPlugin({
        context     : resolve(PROJECT_PATH, 'scss'),
        emitErrors  : false,
        failOnError : false,
        files       : '**/*.scss',
        quiet       : false,
      }) : undefined,
      ifProd(new ImageminPlugin({
        test: /\.(jpe?g|png|gif|svg)$/i,
      })),
      new LodashPlugin({
        collections : true,
        shorthands  : true,
      }),
      new ProvidePlugin({
        FastClick : 'fastclick',
        PubSub    : 'pubsub-js',

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
          if (env.deploy) {
            const child = exec(`./deploy-front-end ${env.project}`)

            child.stdout.on('data', data => process.stdout.write(data))
            child.stderr.on('data', data => process.stderr.write(data))
          }
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
