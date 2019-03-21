module.exports = {
  comments    : true,
  compact     : 'auto',
  retainLines : true,

  presets: [
    ['@babel/preset-env', {
      corejs      : 3,
      loose       : true,
      modules     : false,
      useBuiltIns : 'usage',
    }],
    '@babel/preset-typescript',
  ],

  plugins: [
    '@babel/plugin-syntax-dynamic-import',
    '@babel/proposal-class-properties',
    '@babel/proposal-object-rest-spread',
  ],
}
