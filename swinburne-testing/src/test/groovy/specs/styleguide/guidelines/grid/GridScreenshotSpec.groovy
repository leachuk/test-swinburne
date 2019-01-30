package specs.styleguide.guidelines.grid

import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class GridScreenshotSpec extends ComponentSpec {

    String pathPage = "styleguide/guidelines/grid"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/embed-source-grid"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Appearance of 4 column grid"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#grid-col-4"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

    def "Appearance of 3 column grid"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#grid-col-3"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

    def "Appearance of 2 column grid"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#grid-col-2"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

    def "Appearance of 6 column grid"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#grid-col-6"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

    def "Appearance of 12 column grid"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#grid-col-12"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }
}
