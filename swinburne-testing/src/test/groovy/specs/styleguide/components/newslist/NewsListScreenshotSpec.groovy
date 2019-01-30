package specs.styleguide.components.newslist

import spock.lang.Stepwise
import support.ComponentSpec

@Stepwise
class NewsListScreenshotSpec extends ComponentSpec {

    String pathPage = "styleguide/components/news-list"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/"

    def setupSpec() {
        loginAsAdmin()
    }

    def "Appearance of News List with variant 'Card with image, title, category, action, date'"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#newslist-1"

        when: 'I am in the component showcase page'
        setWindowSize()
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

    }

}
