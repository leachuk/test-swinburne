package specs.component.content.pageauthor

import spock.lang.Ignore
import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
@Ignore //Designs aren't ready yet so can't provide valid screenshots
class PageAuthorScreenshotSpec extends ComponentSpec {

    String pathPage = "component/content/pageauthor"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/pageauthor"

    def setupSpec() {
        loginAsAdmin()
    }


    @Unroll("Appearance of Component Variant: Default in #viewport.label")
    def "Appearance of Component Variant: Default"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pageauthor1"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match the small viewport reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()

    }


}
