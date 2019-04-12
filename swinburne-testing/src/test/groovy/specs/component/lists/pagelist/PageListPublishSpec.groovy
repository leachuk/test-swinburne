package specs.component.lists.pagelist


import spock.lang.Ignore
import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class PageListPublishSpec extends ComponentSpec {

    String pathPage = "component/lists/page-list"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par/pagelist"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Page List: Default variant using Default badge in #viewport.label")
    def "Page List: Default variant using Default badge"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
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

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        and: "Has first item"
        assert $("${selector} li.first").text().trim() == "Page1"

        and: "Has last item"
        assert $("${selector} li.last").text().trim() == "Page5"

        and: "Has three plain items"
        assert $("${selector} li.item").size() == 3

        where:
        viewport << getViewPorts()
    }

    @Unroll("Page List: Default variant using Icon badge in #viewport.label")
    def "Page List: Default variant using Icon badge"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist2"
        def selectorContainer = "#contentblock2 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 3

        and: "Has icon as contents"
        assert $("${selector} li i").size() == 3

        where:
        viewport << getViewPorts()
    }

    @Unroll("Page List: Default variant using Image badge in #viewport.label")
    def "Page List: Default variant using Image badge"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist3"
        def selectorContainer = "#contentblock3 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        and: "Has image as contents"
        assert $("${selector} li img").size() == 5

        where:
        viewport << getViewPorts()
    }



    @Unroll("Page List: Default variant using Horizontal badge in #viewport.label")
    def "Page List: Default variant using Horizontal badge"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist5"
        def selectorContainer = "#contentblock5 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has five list items"
        assert $("${selector} li").size() == 3

        and: "Has card as contents"
        assert $("${selector} li .card").size() == 3

        where:
        viewport << getViewPorts()
    }


    @Unroll("Page List: Default Empty in #viewport.label")
    def "Page List: Default Empty"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist6"
        def selectorContainer = "#contentblock6 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has no content"
        assert $("${selector} .content > child").isEmpty() == true

        where:
        viewport << getViewPorts()
    }


    @Unroll("Badge: Card with Image, Title, Description and Action in #viewport.label")
    def "Badge: Card with Image, Title, Description and Action"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist29"
        def selectorContainer = "#contentblock29 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has two list items"
        assert $("${selector} ul.list > li").size() == 2

        and: "Has image in first item"
        assert $("${selector} > div > ul > li.first > div > div.card-img-top > img").attr("alt") == "Page1"

        and: "Has title in first item"
        assert $("${selector} > div > ul > li.first > div > div.card-body > h3").text() == "Page1"

        and: "Has description in first item"
        assert $("${selector} > div > ul > li.first > div > div.card-body > div.card-text").text() == "Page with Licensed Page Image, with non-Licensed Secondary Image and with Background non-Licensed Image"

        and: "Has call to action in first item"
        assert $("${selector} > div > ul > li.first > div > div.card-body > div > a").text().toUpperCase() == "BUTTON TEXT"

        where:
        viewport << getViewPorts()
    }

    @Unroll("Page List: Default variant using Card badge with List Split every 2 in #viewport.label")
    def "Page List: Default variant using Card badge with List Split every 2"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist30"
        def selectorContainer = "#contentblock30 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "Has three lists"
        assert $("${selector} ul").size() == 3

        and: "Has five list items"
        assert $("${selector} li").size() == 5

        and: "First list has two items"
        assert $("${selector} ul:nth-child(1)").find("li").size() == 2

        and: "Second list has two items"
        assert $("${selector} ul:nth-child(2)").find("li").size() == 2

        and: "Third list has one items"
        assert $("${selector} ul:nth-child(3)").find("li").size() == 1

        where:
        viewport << getViewPorts()
    }

    @Unroll("Page List: Card Action with Analytics #viewport.label")
    def "Page List: Card Action with Analytics"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist33"
        def selectorContainer = "#contentblock33 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

        and: "First page link should have attribute: data-layer-track"
        assert $("${selector} ul li a").getAt(0).attr("data-layer-track").equals("true")

        and: "First page link should have attribute: data-layer-label"
        assert $("${selector} ul li a").getAt(0).attr("data-layer-label").equals("link")

        and: "First page link should have attribute: data-layer-location"
        assert $("${selector} ul li a").getAt(0).attr("data-layer-location").equals("pagelist")




        where:
        viewport << getViewPorts()
    }

    @Unroll("Page List: Default with Thumbnail Override in #viewport.label")
    def "Page List: Default with Thumbnail Override"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist31"
        def selectorContainer = "#contentblock31 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: "Has two list items"
        assert $("${selector} li").size() == 3

        and: "Last item should have thumbnail override from list"
        assert $("${selector} li img").getAt(0).attr("src").contains("/cablecar.jpg/")
        takeScreenshot($(selector).firstElement(), "Last item should have thumbnail override from list")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Page List: Default without Thumbnail Override in #viewport.label")
    def "Page List: Default without Thumbnail Override"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist32"
        def selectorContainer = "#contentblock32 .contents"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: "Has two list items"
        assert $("${selector} li").size() == 3

        and: "Last item should have thumbnail override from page details"
        assert $("${selector} li img").getAt(0).attr("src").contains("/city2.jpg/")
        takeScreenshot($(selector).firstElement(), "Last item should have thumbnail override from page details")


        where:
        viewport << getViewPorts()
    }



    @Unroll("Page List: Pages with no Details in #viewport.label")
    def "Page List: Pages with no Details"() {

        given: '>the page hierarchy is created as "Components" > "Lists" > "List"'
        and: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#pagelist38"

        when: "I am on the component showcase page"
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: "The component should be on the page"
        def component = waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: "Has five items of page missing details"
        assert $("${selector} .page-missing-details").size() == 5

        where:
        viewport << getViewPorts()
    }

	@Unroll("Page List: Card Action with default Analytics #viewport.label")
	def "Page List: Card Action with default Analytics"() {

		given: '>the page hierarchy is created as "Components" > "Lists" > "Page List"'
		and: '>I am in the component showcase page'
		and: '>the component is on the showcase page'
		def selector = "#pagelist_39"
		def selectorContainer = "#contentblock39 .contents"

		when: "I am on the component showcase page"
		setWindowSize(viewport)
		waitForAuthorPreviewPage()

		then: "The component should be on the page"
		def component = waitForComponent(selector)
		takeScreenshot($(selectorContainer).firstElement(), "The component should be on the page")

		and: "First page link should have attribute: data-layer-track false - unauthored"
		assert $("${selector} ul li a").getAt(0).attr("data-layer-track").equals("false")

		and: "First page link should have attribute: data-layer-label"
		assert $("${selector} ul li a").getAt(0).attr("data-layer-label").equals("pagelist_39")

		and: "First page link should have attribute: data-layer-location as blank - unauthored"
		assert $("${selector} ul li a").getAt(0).attr("data-layer-location").equals("blank")




		where:
		viewport << getViewPorts()
	}

}
