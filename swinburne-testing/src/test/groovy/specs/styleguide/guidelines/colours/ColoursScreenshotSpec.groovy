package specs.styleguide.guidelines.colours

import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class ColoursScreenshotSpec extends ComponentSpec {

    String pathPage = "Styleguide-SWU/guidelines/colour"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/embedsource_colours"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Appearance of Primary colours"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#primary-colours"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

    def "Appearance of Main colours"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#main-colours"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

    def "Appearance of Grey Tones"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#grey-tones"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }
}
