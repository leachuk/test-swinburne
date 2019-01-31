package specs.styleguide.guidelines.typography

import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class TypographyScreenshotSpec extends ComponentSpec {

    String pathPage = "styleguide/guidelines/typography"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/heading-bg-light"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Appearance of headings on light background"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#heading-bg-light"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

    def "Appearance of body on light background"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#body-bg-light"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

    def "Appearance of headings on dark background"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#heading-bg-dark"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

    def "Appearance of body on dark background"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#body-bg-dark"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

    def "Appearance of headings not underlined"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#heading-not-undelined"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

}
