package specs.component.details.newsdetails

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class NewsDetailsScreenshotSpec extends ComponentSpec {

    String pathPage = "component/details/news-details"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/news-details"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Appearance of News Details Component default in #viewport.label")
    def "Appearance of News Details Component default"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#news_details_O6GDPCTVP"

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