package specs.component.media.openGraph

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class OpenGraphPublishSpec extends ComponentSpec {

    String pathPage = "component/media/open-graph"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/page-details"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Data Layer in #viewport.label")
    def "Functionality of Open Graph"() {

        given: '>the page hierarchy is created as "Components" > "Media" > "Open Graph"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "head"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "Should have OG Url"
        assert $("$selector [property='og:url']").attr("content").contains("/content/swinburne-showcase/en/component/media/open-graph")

        then: "Should have OG Type"
        assert $("$selector [property='og:type']").attr("content").contains("OG Type")

        then: "Should have OG Title"
        assert $("$selector [property='og:title']").attr("content").contains("OG Title")

        then: "Should have OG Description"
        assert $("$selector [property='og:description']").attr("content").contains("OG Description")

        then: "Should have OG Image"
        assert $("$selector [property='og:image']").attr("content").contains("/content/dam/swinburne-showcase/en/common/knowing.png")


    }

}
