package specs.styleguide.components.globalheader

import spock.lang.IgnoreRest
import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class GlobalHeaderScreenshotSpec extends ComponentSpec {
    String pathPage = "styleguide/components/global-header"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock/par/reference"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Appearance of Swinburne Global Header on mobile"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#header_FIEH48SBE"

        when: 'I am in the component showcase page'
        setWindowSizeSM()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector, "menu")
    }

    def "Appearance of Swinburne Global Header on mobile with menu open"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#header_FIEH48SBE"

        when: 'I am in the component showcase page'
        setWindowSizeSM()
        waitForAuthorPreviewPage()

        then: 'I click on the Explore button'
        js.exec("\$(\"${selector} #link_BNGXYPV2I\").click(); return true;")


        then: 'Wait for the component to appear on the page'
        waitFor(15, 0.1) { $("${selector} #header-nav-container").isDisplayed() }


        then: 'It should match reference image.'
        designRef(selector, "menu-open")
    }

    def "Appearance of Swinburne Global Header on mobile with menu open and link clicked"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#header_FIEH48SBE"

        when: 'I am in the component showcase page'
        setWindowSizeSM()
        waitForAuthorPreviewPage()

        then: 'I click on the Explore button'
        js.exec("\$(\"${selector} #link_BNGXYPV2I\").click(); return true;")


        then: 'Wait for the component to appear on the page'
        waitFor(15, 0.1) { $("${selector} #header-nav-container").isDisplayed() }

        then: 'I click on the Locations link'
        js.exec("\$(\"${selector} a#life-at-swinburne\").click(); return true;")

        then: 'The component should appear on the page'
        waitFor(15, 0.1) { $("${selector} a#life-at-swinburne + .dropdown-menu").isDisplayed() }

        then: 'It should match reference image.'
        designRef(selector, "menu-open-link-clicked")
    }

    def "Appearance of Swinburne Global Header on tablet"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#header_FIEH48SBE"

        when: 'I am in the component showcase page'
        setWindowSizeMD()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector, "menu")
    }

    def "Appearance of Swinburne Global Header on tablet with menu open"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#header_FIEH48SBE"

        when: 'I am in the component showcase page'
        setWindowSizeMD()
        waitForAuthorPreviewPage()

        then: 'I click on the Explore button'
        js.exec("\$(\"${selector} #link_BNGXYPV2I\").click(); return true;")


        then: 'Wait for the component to appear on the page'
        waitFor(15, 0.1) { $("${selector} #header-nav-container").isDisplayed() }


        then: 'It should match reference image.'
        designRef(selector, "menu-open")
    }

    def "Appearance of Swinburne Global Header on tablet with menu open and link clicked"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#header_FIEH48SBE"

        when: 'I am in the component showcase page'
        setWindowSizeMD()
        waitForAuthorPreviewPage()

        then: 'I click on the Explore button'
        js.exec("\$(\"${selector} #link_BNGXYPV2I\").click(); return true;")


        then: 'Wait for the component to appear on the page'
        waitFor(15, 0.1) { $("${selector} #header-nav-container").isDisplayed() }

        then: 'I click on the Locations link'
        js.exec("\$(\"${selector} a#life-at-swinburne\").click(); return true;")

        then: 'The component should appear on the page'
        waitFor(15, 0.1) { $("${selector} a#life-at-swinburne + .dropdown-menu").isDisplayed() }

        then: 'It should match reference image.'
        designRef(selector, "menu-open-link-clicked")
    }

    def "Appearance of Swinburne Global Header on Desktop Small"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#header_FIEH48SBE"

        when: 'I am in the component showcase page'
        setWindowSizeLG()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector, "menu")
    }

    def "Appearance of Swinburne Global Header on Desktop Small with menu open"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#header_FIEH48SBE"

        when: 'I am in the component showcase page'
        setWindowSizeLG()
        waitForAuthorPreviewPage()

        then: 'I click on the Locations link'
        js.exec("\$(\"${selector} a#life-at-swinburne\").click(); return true;")

        then: 'The component should appear on the page'
        waitFor(15, 0.1) { $("${selector} a#life-at-swinburne + .dropdown-menu").isDisplayed() }

        then: 'It should match reference image.'
        designRef(selector, "menu-link-clicked")
    }

    def "Appearance of Swinburne Global Header on Desktop Large"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#header_FIEH48SBE"

        when: 'I am in the component showcase page'
        setWindowSizeXLG()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector, "menu")
    }

    def "Appearance of Swinburne Global Header on Desktop Large with menu open"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#header_FIEH48SBE"

        when: 'I am in the component showcase page'
        setWindowSizeXLG()
        waitForAuthorPreviewPage()

        then: 'I click on the Locations link'
        js.exec("\$(\"${selector} a#life-at-swinburne\").click(); return true;")

        then: 'The component should appear on the page'
        waitFor(15, 0.1) { $("${selector} a#life-at-swinburne + .dropdown-menu").isDisplayed() }

        then: 'It should match reference image.'
        designRef(selector, "menu-link-clicked")
    }

}
