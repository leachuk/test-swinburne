/* eslint-disable */
module.exports = ({ env }) => ({
  plugins: {
    'postcss-pxtorem': {
      replace       : true,
      rootValue     : 16,
      unitPrecision : 5,

      selectorBlackList: [
        'html'
      ],

      propList: [
        'bottom',
        'font',
        'font-size',
        'height',
        'left',
        'line-height',
        'margin*',
        'padding*',
        'right',
        'top',
        '*width',
      ],
    },

    'postcss-sorting': {},

    autoprefixer: {
      grid: 'autoplace',
    },

    cssnano: env === 'production',

    'postcss-reporter': {
      clearReportedMessages: true,
    },
  },
});
