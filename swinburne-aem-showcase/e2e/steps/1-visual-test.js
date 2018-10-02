var chai = require("chai");
var chaiAsPromised = require("chai-as-promised");
chai.use(chaiAsPromised);
var expect = chai.expect;

var Eyes = require("eyes.selenium").Eyes;
var eyes = new Eyes();
eyes.setApiKey("pqS0t80104yMCBNZ6y98nhcsvRA1UBMVkSvk3hN6L9WaXw110");

const cucumberLoader = require('protractor-cucumber-framework/lib/cucumberLoader');
const { Given, When, Then , BeforeAll } = cucumberLoader.load();
const { setDefaultTimeout } = require('cucumber');

setDefaultTimeout(240000);

var currentPageName = '';
var currentBatch;
const appName = 'Swinburne Microsites';

var EC = protractor.ExpectedConditions;

// Window widths for screenshots (in px)
const widths = [480, 640, 1024, 1280, 1480];

BeforeAll(() => {

    return browser.get("/").then(() => {
        element(by.id("username")).sendKeys("admin");
        element(by.id("password")).sendKeys("admin");
        element(by.id("submit-button")).click();

        var navigationElement = element(by.className("globalnav-collection-container"));

        return browser.wait(EC.visibilityOf(navigationElement), 5000);
    });
});

Given('that I am on the {string} page at {string}', function (pageName, pageUrl) {
  eyes.setForceFullPageScreenshot(true);
  currentPageName = pageName;

  // Login




    return browser.getCapabilities().then((capabilities) => {

        // Create a new batch and store it for the duration of this session
        // If available, use the existing batch
        if (currentBatch) {
            eyes.setBatch(currentBatch.name, currentBatch.id, currentBatch.startedA);
        } else {
            var browserName = capabilities.map_.get('browserName');
            var platform = capabilities.map_.get('platform');
            var browserVersion = capabilities.map_.get('version');

            // Name the batch
            eyes.setBatch(`Swinburne - Microsites - ${platform} - ${browserName} ${browserVersion}`);
            currentBatch = eyes.getBatch();
        }

        return eyes.open(browser, appName, currentPageName).then(() => {
            return browser.get(pageUrl);
        });
    });


});


When('I view the page visually', function () {
  // Get the Height of the current browser
  return browser.driver.manage().window().getSize().then(async (size) => {
    var currentHeight = size.height;

    // Now step through all the widths and execute checkWindow() in sequential order
    for (var i = 0; i < widths.length; i++) {
      var width = widths[i];
      await checkWindow(currentPageName, currentHeight, width);
    }

    return true;
  });
});

Then('the visual elements should not have changed', function() {
  // Close the eyes session
  return eyes.close();
});


function checkWindow(currentPageName, currentHeight, width) {
  // Adjust the window size, and then take a screenshot with applitools!
  return browser.driver.manage().window().setSize(width, currentHeight).then(() => {
    return eyes.checkWindow(`${currentPageName} - W: ${width}`);
  });
}