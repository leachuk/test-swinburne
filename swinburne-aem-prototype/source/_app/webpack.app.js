const ExtractTextPlugin           = require('extract-text-webpack-plugin')
const ImageminPlugin              = require('imagemin-webpack-plugin').default
const LodashPlugin                = require('lodash-webpack-plugin')
const { resolve }                 = require('path')
const { getIfUtils, removeEmpty } = require('webpack-config-utils')
const webpack                     = require('webpack')

const plConfig = require('../../patternlab-config.json')

module.exports = env => {
  const { ifProd, ifDev } = getIfUtils(env)

  const extractSass = new ExtractTextPlugin({
    allChunks : true,
    disable   : env.dev === true,

    filename(getPath) {
      return getPath('css/[name].css').replace('js/', '')
    },
  })

  const cssLoaders = [
    {
      loader: 'css-loader',

      options: {
        importLoaders : 1,
        minimize      : env.prod === true,
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
        outputStyle : 'expanded',
        sourceMap   : env.dev === true,
      },
    },
  ]

  return {
    devServer: {
      compress           : true,
      historyApiFallback : true,

      headers: {
        'Access-Control-Allow-Origin': '*',
      },
    },

    entry: {
      'js/app'        : './js/app.js',
      'js/styleguide' : './js/styleguide.js',

      'js/vendor': [
        'es6-promise/auto',
        'react',
        'react-dom',
        'object-fit-images',
        'picturefill',
        'bootstrap/js/dist/util',
        'bootstrap/js/dist/collapse',
        'bootstrap/js/dist/dropdown',
        'bootstrap/js/dist/modal',
        'bootstrap/js/dist/tab',
      ],

      'js/vendorlib/jquery': 'jquery',
    },

    output: {
      chunkFilename : '[name].[chunkhash].js',
      publicPath    : env.aem === true ? '/etc/clientlibs/'+env.clientLibsFolder+'/js/' : '/',
    },

    module: {
      rules: [
        {
          test   : /\.(eot|svg|ttf|woff|woff2)$/,
          loader : 'file-loader',

          options: {
            name       : '[path][name].[ext]',
            emitFile   : env.dev === true,
            publicPath : env.prod === true ? '../' : '/',
          },
        },
        {
          test: /\.(css|sass|scss)$/,

          use: env.dev === true ? ['style-loader', ...cssLoaders] : extractSass.extract({
            fallback : 'style-loader',
            use      : cssLoaders,
          }),
        },
        {
          exclude : [ resolve('node_modules') ],
          test    : /\.jsx?$/,

          use: [
            {
              loader: 'babel-loader',
            },
            {
              loader: 'string-replace-loader',

              options: {
                search: '/js/vendorlib',
                replace: '/vendorlib',
              }
            },
          ],
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
      ],
    },

    plugins: removeEmpty([
      extractSass,
      new ImageminPlugin({
        test: /\.(jpe?g|png|gif|svg)$/i,
      }),
      new webpack.optimize.CommonsChunkPlugin({
        filename : 'js/[name].js',
        name     : 'common',
      }),
      new LodashPlugin({
        collections : true,
        shorthands  : true,
      }),
      new webpack.ProvidePlugin({
        FastClick       : 'fastclick',
        Handsontable    : 'handsontable',
        PubSub          : 'pubsub-js',
        React           : 'react',
        objectFitImages : 'object-fit-images',
        urlParser       : 'js-video-url-parser/dist/jsVideoUrlParser',

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
      ifProd(new webpack.optimize.UglifyJsPlugin({
        ecma     : 6,
        mangle   : false,
        warnings : false,

        compress: {
          drop_console : true,
          warnings     : false,
        },

        output: {
          beautify: false,
          comments: false,
        },
      })),
      ifProd(new webpack.LoaderOptionsPlugin({
        minimize: true,
      })),
      ifProd(new webpack.DefinePlugin({
        'process.env': {
          NODE_ENV: JSON.stringify('production'),
        },
      })),
      ifDev(new webpack.DefinePlugin({
        'process.env': {
          NODE_ENV: JSON.stringify('development'),
        },
      })),
      ifDev(new webpack.NamedModulesPlugin()),
    ]),
  }
}
