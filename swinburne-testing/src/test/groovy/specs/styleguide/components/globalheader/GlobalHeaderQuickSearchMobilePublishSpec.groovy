package specs.styleguide.components.globalheader

import org.openqa.selenium.Keys
import support.ComponentSpec

class GlobalHeaderQuickSearchMobilePublishSpec extends ComponentSpec {
    String pathPage = "styleguide/components/global-header"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock/par/reference"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Quick Search on mobile"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#swinburne_global_header"
        def selectorSearch = "${selector} .brand-header__container > .brand-header__actions .brand-header__quick-search"
        def selectorSearchInput = "${selectorSearch} input[type=search]"

        when: 'I am in the component showcase page'
        setWindowSizeSM()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)

        and: 'We can click on the search submit button to show the search input'
        $("${selectorSearch} button[type=submit]").click()

        and: 'The search input is visible and focused'
        waitFor(2, 0.1) {
            $(selectorSearchInput).isDisplayed()
            $("${selectorSearch} > form").css("right") == "-8px"
        }
        assert $(selectorSearchInput).isFocused()
        takeScreenshot($(selector).firstElement(), "The search input is focused")
    }
}
