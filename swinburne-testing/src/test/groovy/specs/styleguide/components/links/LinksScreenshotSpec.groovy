package specs.styleguide.components.links

import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class LinksScreenshotSpec extends ComponentSpec {

    String pathPage = "Styleguide-SWU/components/link"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock_148734255/"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Appearance of Link with default variant"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#link-1"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

    def "Appearance of Link with default variant charcoal colour"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#link-2"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

}
