package specs.component.lists.searchlist

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class SearchListScreenshotSpec extends ComponentSpec {

    String pathPage = "component/lists/search-list"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock2/par/searchlist"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Appearance of Component Default without Query in #viewport.label")
    def "Appearance of Component Default without Query"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#searchlist1"

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


    @Unroll("Appearance of Component Default with Query in #viewport.label")
    def "Appearance of Component Default with Query"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#searchlist1"

        when: 'I am in the component showcase page with Query Specified'
        setWindowSize(viewport)
        waitForAuthorPreviewPageWithQuery(language, "q=city")

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match the small viewport reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()

    }


    @Unroll("Appearance of Component as Cards with Query in #viewport.label")
    def "Appearance of Component as Cards with Query"() {

        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#searchlist2"

        when: 'I am in the component showcase page with Query Specified'
        setWindowSize(viewport)
        waitForAuthorPreviewPageWithQuery(language, "q=city")

        then: 'The component should appear on the page'
        def component = waitForComponent(selector)

        then: 'It should match the small viewport reference image.'
        designRef(selector)

        where:
        viewport << getViewPorts()

    }


}
