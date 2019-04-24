package specs.styleguide.components.carousel

import spock.lang.Stepwise
import spock.lang.Unroll
import support.ComponentSpec

@Stepwise
class DLSCarouselPublishSpec extends ComponentSpec {
    String pathPage = "styleguide/components/carousel"
    String pathSite = "content/swinburne-showcase"
    String language = "en"
    String componentPath = "jcr:content/article/par"

    def setupSpec() {
        loginAsAdmin()
    }

    @Unroll("Basic Carousel - Default Background (Page List) in #viewport.label")
    def "Basic Carousel - Default Background (Page List)"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_pagelist_default_list"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: 'Has six items'
        assert $("${selector} .owl-item").size() == 6

        and: 'First item is active'
        assert $("${selector} .owl-stage").children().firstElement().getAttribute("class").contains("active")

        and: 'Clicking the next button progresses the carousel'
        $("${selector} .owl-next").click()

        and: 'The first item is no longer active'
        assert !$("${selector} .owl-stage").children().firstElement().getAttribute("class").contains("active")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Basic Carousel - Grey Background (Page List) in #viewport.label")
    def "Basic Carousel - Grey Background (Page List)"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_pagelist_grey_list"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: 'Has six items'
        assert $("${selector} .owl-item").size() == 6

        and: 'First item is active'
        assert $("${selector} .owl-stage").children().firstElement().getAttribute("class").contains("active")

        and: 'Clicking the next button progresses the carousel'
        $("${selector} .owl-next").click()

        and: 'The first item is no longer active'
        assert !$("${selector} .owl-stage").children().firstElement().getAttribute("class").contains("active")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Basic Carousel - Yellow Background (Page List) in #viewport.label")
    def "Basic Carousel - Yellow Background (Page List)"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_pagelist_yellow_list"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: 'Has six items'
        assert $("${selector} .owl-item").size() == 6

        and: 'First item is active'
        assert $("${selector} .owl-stage").children().firstElement().getAttribute("class").contains("active")

        and: 'Clicking the next button progresses the carousel'
        $("${selector} .owl-next").click()

        and: 'The first item is no longer active'
        assert !$("${selector} .owl-stage").children().firstElement().getAttribute("class").contains("active")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Basic Carousel - Default Background (News List) in #viewport.label")
    def "Basic Carousel - Default Background (News List)"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_newslist_default_list"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: 'Has six items'
        assert $("${selector} .owl-item").size() == 6

        and: 'First item is active'
        assert $("${selector} .owl-stage").children().firstElement().getAttribute("class").contains("active")

        and: 'Clicking the next button progresses the carousel'
        $("${selector} .owl-next").click()

        and: 'The first item is no longer active'
        assert !$("${selector} .owl-stage").children().firstElement().getAttribute("class").contains("active")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Basic Carousel - Grey Background (News List) in #viewport.label")
    def "Basic Carousel - Grey Background (News List)"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_newslist_grey_list"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: 'Has six items'
        assert $("${selector} .owl-item").size() == 6

        and: 'First item is active'
        assert $("${selector} .owl-stage").children().firstElement().getAttribute("class").contains("active")

        and: 'Clicking the next button progresses the carousel'
        $("${selector} .owl-next").click()

        and: 'The first item is no longer active'
        assert !$("${selector} .owl-stage").children().firstElement().getAttribute("class").contains("active")

        where:
        viewport << getViewPorts()
    }

    @Unroll("Basic Carousel - Yellow Background (News List) in #viewport.label")
    def "Basic Carousel - Yellow Background (News List)"() {
        given: '>I am in the component showcase page'
        and: '>the component is on the showcase page'
        def selector = "#contentblock_carousel_newslist_yellow_list"

        when: 'I am in the component showcase page'
        setWindowSize(viewport)
        waitForAuthorPreviewPage()

        then: 'The component should appear on the page'
        waitForComponent(selector)
        takeScreenshot($(selector).firstElement(), "The component should be on the page")

        and: 'Has six items'
        assert $("${selector} .owl-item").size() == 6

        and: 'First item is active'
        assert $("${selector} .owl-stage").children().firstElement().getAttribute("class").contains("active")

        and: 'Clicking the next button progresses the carousel'
        $("${selector} .owl-next").click()

        and: 'The first item is no longer active'
        assert !$("${selector} .owl-stage").children().firstElement().getAttribute("class").contains("active")

        where:
        viewport << getViewPorts()
    }
}
