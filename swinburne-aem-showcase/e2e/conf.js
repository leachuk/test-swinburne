exports.config = {
    framework: 'custom',
    frameworkPath: require.resolve('protractor-cucumber-framework'),
    restartBrowserBetweenTests: true,
    baseUrl: 'http://pace-sitecore-qa-1.at.isobaraustralia.com',
    specs: [
      './features/**/*.feature'
    ],
    cucumberOpts: {
      require: ['./steps/**/*.js'],
    //   tags: ["@ignition_one"],
    //   plugin: 'pretty',
    //   profile: false,
    //   'no-source': true,
    //   format: ['pretty', 'json:./src/tests/reports/report.json']
    },
    ignoreUncaughtExceptions: true,
    allScriptsTimeout: 15000,
    restartBrowserBetweenTests: false,

    // allScriptsTimeout: 15000,
    // browserstackUser: 'vjqa',
    // browserstackKey: 'TpPiAJsqppgZ6UeCKstE',
    // commonCapabilities: {
    //   'browserstack.local': false,
    //   'browserstack.debug': true,
    // },
    // multiCapabilities: [{
    //   project: 'Holden Personalisation',
    //   browserName: 'Chrome',
    //   os: 'Windows'
    // }]
    capabilities: {'browserName': 'chrome' },
    seleniumAddress: 'http://127.0.0.1:4444/wd/hub'
}