package specs.styleguide.components.newsdetails

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class NewsDetailScreenshotSpec extends ComponentSpec {
    String pathPage = "styleguide/components/news-details"
	String pathSite = "content/swinburne-showcase"
	String language = "en"
	String componentPath = "jcr:content/article/par/news_details"

    def setupSpec() {
        loginAsAdmin()
    }

	@Unroll("Appearance of News Details with default variant in #viewport.label")
    def "Appearance of News Details with default variant'"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#news_details"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)

        then: 'It should match reference image.'
        designRef(selector)

		where:
		viewport << getViewPorts()
    }
}
