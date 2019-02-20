import ExtractTextPlugin from 'extract-text-webpack-plugin';
import ImageminPlugin from 'imagemin-webpack-plugin';
import LodashPlugin from 'lodash-webpack-plugin';
import { resolve, relative } from 'path';
import { getIfUtils, removeEmpty } from 'webpack-config-utils';
import { optimize, ProvidePlugin, LoaderOptionsPlugin, DefinePlugin } from 'webpack';

export default env => {
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
        outputStyle       : env.dev === true ? 'expanded' : 'compressed',
        sourceMap         : env.dev === true,
        sourceMapContents : env.dev === true,
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
      'js/app'        : `./${env.version}/js/app.js`,
      'js/styleguide' : `./${env.version}/js/styleguide.js`,

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

      'js/vendorlib/jquery'       : 'jquery',
      'js/vendorlib/fancybox'     : '@fancyapps/fancybox',
      'js/vendorlib/owl.carousel' : 'owl.carousel',
    },

    output: {
      chunkFilename : '[name].[chunkhash].js',
      publicPath    : env.aem === true ? `/etc/clientlibs/${env.clientLibsFolder}/js/` : '/',
    },

    module: {
      rules: [
        {
          test: /\.(css|sass|scss)$/,

          use: env.dev === true ? ['style-loader', ...cssLoaders] : extractSass.extract({
            fallback : 'style-loader',
            use      : cssLoaders,
          }),
        },
        {
          exclude : [ resolve('node_modules') ],
          test    : /\.(j|t)sx?$/,

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
                  return env.aem === true ? `../${relative(context, resourcePath)}` : __webpack_public_path__ // eslint-disable-line
                },
              },
            },
          ],
        },
      ],
    },

    plugins: removeEmpty([
      extractSass,
      new ImageminPlugin({
        test: /\.(jpe?g|png|gif|svg)$/i,
      }),
      new optimize.CommonsChunkPlugin({
        filename : 'js/[name].js',
        name     : 'common',
      }),
      new LodashPlugin({
        collections : true,
        shorthands  : true,
      }),
      new ProvidePlugin({
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
      ifProd(new optimize.UglifyJsPlugin({
        'ecma'     : 6,
        'mangle'   : false,
        'warnings' : false,

        'compress': {
          'drop_console' : true,
          'warnings'     : false,
        },

        'output': {
          'beautify': false,
          'comments': false,
        },
      })),
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
      // ifDev(new webpack.NamedModulesPlugin()),
    ]),
  }
}
