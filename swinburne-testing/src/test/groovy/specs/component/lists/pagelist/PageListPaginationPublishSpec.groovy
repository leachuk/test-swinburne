package specs.component.lists.pagelist

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class PageListPaginationPublishSpec extends ComponentSpec {

    String pathPage = "component/lists/page-list/pagination"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/contentblock1/par/pagelist"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Functionality of Component with Default variant and cardHorizontal Badge in #viewport.label")
    def "Functionality of Component with Default variant and cardHorizontal Badge"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List" > "Pagination"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist1"
        def selectorContainer = "#contentblock1 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has one list items"
        assert $("${selector} li").size() == 1

        and: "Has pagination details"
        assert $("${selector} > .pagination > div.label").text().trim() == "[1 - 1] of 5"
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on first page")

        and: "Can select page 2"
        $("${selector} > .pagination > .next > a").click()
        assert $("${selector} > .pagination > div.label").text().trim() == "[2 - 2] of 5"
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on second page")

        and: "Can select page 3"
        $("${selector} > .pagination > .next > a").click()
        assert $("${selector} > .pagination > div.label").text().trim() == "[3 - 3] of 5"
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on third page")

        and: "Can select page 4"
        $("${selector} > .pagination > .next > a").click()
        assert $("${selector} > .pagination > div.label").text().trim() == "[4 - 4] of 5"
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on forth page")

        and: "Can select page 5"
        $("${selector} > .pagination > .next > a").click()
        assert $("${selector} > .pagination > div.label").text().trim() == "[5 - 5] of 5"
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on fifth page")

        and: "Only previous link exists on last page, without next link"
        assert $("${selector} > .pagination > .previous").isDisplayed()
        assert !$("${selector} > .pagination > .next").isDisplayed()

        where:
        viewport << getViewPorts()
    }
}
