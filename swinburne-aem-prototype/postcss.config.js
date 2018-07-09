module.exports = ({ env }) => ({
  plugins: {
    'postcss-pxtorem': {
      rootValue: 16,
      unitPrecision: 5,
      propList: ['*'],
      selectorBlackList: ['html'],
      replace: false
    },
    'postcss-sorting': {},
    autoprefixer: true,
    cssnano: env === 'production' ? true : false
  }
});
