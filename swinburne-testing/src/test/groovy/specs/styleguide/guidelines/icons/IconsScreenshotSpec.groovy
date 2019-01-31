package specs.styleguide.guidelines.icons

import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class IconsScreenshotSpec extends ComponentSpec {

    String pathPage = "styleguide/guidelines/iconography"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock_1460944026/par/embedsource"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Appearance of Iconography Section 1"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#icon-section-0"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

    def "Appearance of Iconography Section 2"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#icon-section-12"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

    def "Appearance of Iconography Section 4"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#icon-section-36"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

    def "Appearance of Iconography Section 5"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#icon-section-48"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

    def "Appearance of Iconography Section 6"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#icon-section-60"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }
}
