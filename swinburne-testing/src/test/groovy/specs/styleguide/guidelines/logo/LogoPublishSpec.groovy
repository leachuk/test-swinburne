package specs.styleguide.guidelines.logo

import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class LogoPublishSpec extends ComponentSpec {
    String pathPage = "styleguide/guidelines/logo-and-usage"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Appearance of logo on mobile device"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#logo_embedsource"

        when: 'I am in the component showcase page'
        setWindowSizeSM()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        then: 'The first element should be the mobile logo'
        assert $(selector).children()[0].getAttribute("src").contains("logo-small.svg")

        then: 'The first element should be visible'
        assert $(selector).children()[0].css("display") == "block"

        then: 'It should match reference image.'
        designRef(selector)
    }

    def "Appearance of logo on table device"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#logo_embedsource"

        when: 'I am in the component showcase page'
        setWindowSizeMD()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        then: 'The second element should be the tablet logo'
        assert $(selector).children()[1].getAttribute("src").contains("logo-long-full.svg")

        then: 'The second element should be visible'
        assert $(selector).children()[1].css("display") == "block"

        then: 'The first element should be invisible'
        assert $(selector).children()[0].css("display") == "none"

        then: 'It should match reference image.'
        designRef(selector)
    }

    def "Appearance of logo on small desktop device"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#logo_embedsource"

        when: 'I am in the component showcase page'
        setWindowSizeLG()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        then: 'The second element should be the desktop logo'
        assert $(selector).children()[1].getAttribute("src").contains("logo-long-full.svg")

        then: 'The second element should be visible'
        assert $(selector).children()[1].css("display") == "block"

        then: 'The first element should be invisible'
        assert $(selector).children()[0].css("display") == "none"

        then: 'It should match reference image.'
        designRef(selector)
    }

    def "Appearance of logo on large desktop device"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#logo_embedsource"

        when: 'I am in the component showcase page'
        setWindowSizeXLG()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        then: 'The second element should be the desktop logo'
        assert $(selector).children()[1].getAttribute("src").contains("logo-long-full.svg")

        then: 'The second element should be visible'
        assert $(selector).children()[1].css("display") == "block"

        then: 'The first element should be invisible'
        assert $(selector).children()[0].css("display") == "none"

        then: 'It should match reference image.'
        designRef(selector)
    }
}
