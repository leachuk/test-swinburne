package specs.styleguide.components.newsdetails

import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class NewsDetailScreenshotSpec extends ComponentSpec {

    String pathPage = "/Styleguide-SWU/components/news-details"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/swinburne-showcase/en/Styleguide-SWU/components/news-details/jcr:content/article/par/news_details"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Appearance of News Details with default variant'"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#news_details"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

}
